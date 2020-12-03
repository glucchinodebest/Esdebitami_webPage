package it.esdebitamiretake.retake_ir.scraping.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import it.esdebitamiretake.retake_ir.scraping.model.Content;
import it.esdebitamiretake.retake_ir.scraping.model.Context;
import it.esdebitamiretake.retake_ir.scraping.model.Resource;

public class IRClient {

	private final WebClient client;

	public IRClient(String url) {

		this.client = WebClient.builder().baseUrl(url)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
	}

	public Context[] getContexts() {

		return client.get().uri("/contexts").retrieve().bodyToMono(Context[].class).block();

	}

	public String postResource(Resource resource) {

		String uri = client.post().uri(String.format("/contexts/%s/resources", resource.getContext()))
				.bodyValue(resource).retrieve().bodyToMono(String.class).block();

		String parts[] = uri.substring(1,uri.length()-1).split("/");

		return parts[parts.length - 1];
	}

	public void postContent(Content content) {

		client.post().uri(String.format("/resources/%s/contents", content.getResource())).bodyValue(content).retrieve().
				bodyToMono(Void.class).block();
	}

	public void deleteResources(String id) {

		client.delete().uri(String.format("/contexts/%s/resources", id)).retrieve().bodyToMono(Void.class).block();
		
	}

	public void postIndex(String id) {

		client.post().uri(String.format("/contexts/%s/indexes", id)).retrieve().bodyToMono(Void.class).block();
	}

	public void putContext(Context context) {

		client.put().uri(String.format("/contexts/%s", context.getID())).bodyValue(context).retrieve()
				.bodyToMono(Void.class).block();
	}
}