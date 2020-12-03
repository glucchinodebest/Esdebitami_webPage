package it.esdebitamiretake.retake_ir.scraping;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.quartz.CronExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import it.esdebitamiretake.retake_ir.scraping.Scraper.ScrapingListener;
import it.esdebitamiretake.retake_ir.scraping.model.Content;
import it.esdebitamiretake.retake_ir.scraping.model.Context;
import it.esdebitamiretake.retake_ir.scraping.model.Resource;
import it.esdebitamiretake.retake_ir.scraping.rest.IRClient;

@Component
public class Scheduler implements ScrapingListener {

	private final IRClient client;
	private final int pool;
	private static final Logger log = LoggerFactory.getLogger(Scheduler.class);

	@Autowired
	public Scheduler(@Value("${scraping.env}") String env, @Value("${scraping.pool}") int pool) throws IOException {

		this.client = new IRClient(env);
		this.pool = pool;
	}

	@Scheduled(initialDelay = 1000, fixedDelayString = "${scraping.cron}")
	public void execute() {

		List<Context> contexts = loadContexts();
		int size = contexts.size();
		int count = 0, threads = 0;
		final int queue = pool * 2;

		ExecutorService executor = Executors.newFixedThreadPool(pool);
		CompletionService<ContextThread> service = new ExecutorCompletionService<>(executor);

		do {

			int slots = Math.min(queue - threads, size - count);
			threads += slots;

			for (int j = 0; j < slots; j++)
				service.submit(new ContextThread(contexts.get(j + count)));

			count += slots;
			slots = Math.min(threads, pool);
			threads -= slots;

			while (slots-- > 0) {

				try {
					service.take();
				} catch (InterruptedException e) {
					onError(null, e.getMessage());
				}

			}

		} while (threads > 0);

	}

	@Override
	public String onCreateResource(Resource resource) {

		return client.postResource(resource);
	}

	@Override
	public void onCreateContent(Content content) {

		client.postContent(content);
	}

	@Override
	public void onError(Context context, String message) {

		synchronized (log) {
			log.error(String.format("%s : %s", context, message));
		}
	}

	private List<Context> loadContexts() {

		List<Context> contexts = new ArrayList<>();
		Date now = new Date(System.currentTimeMillis());

		for (Context context : client.getContexts()) {

			try {

				Date date = context.getDate();
				CronExpression cron = new CronExpression(context.getCronExp());

				if (date == null || now.after(cron.getNextValidTimeAfter(date)))
					contexts.add(context);

			} catch (ParseException e) {

			}
		}

		return contexts;
	}

	private class ContextThread implements Callable<ContextThread> {

		private final Context context;

		ContextThread(Context context) {

			this.context = context;
		}

		@Override
		public ContextThread call() {

			try {

				client.deleteResources(context.getID());
				new Scraper(context, Scheduler.this).execute();
				context.setDate(new Date(System.currentTimeMillis()));
				client.putContext(context);
				client.postIndex(context.getID());
			} catch (Exception e) {

				onError(context, e.getMessage());
			}

			return this;
		}
	}
}