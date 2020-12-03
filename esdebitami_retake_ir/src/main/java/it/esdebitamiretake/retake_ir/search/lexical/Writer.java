package it.esdebitamiretake.retake_ir.search.lexical;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;

import it.esdebitamiretake.retake_ir.search.Language;


public class Writer extends Index {

	private final IndexWriter writer;

	public Writer(Language language,String dir) throws IOException {

		super(language);
		
		this.writer = createWriter(dir);
	}

	private IndexWriter createWriter(String dir) throws IOException {

		IndexWriterConfig config = new IndexWriterConfig(this.getAnalyzer());

		return new IndexWriter(FSDirectory.open(Paths.get(dir)), config);
	}

	void addDocuments(Document... documents) throws IOException {

		writer.addDocuments(Arrays.asList(documents));
		writer.commit();
	}

	void clear() throws IOException {

		writer.deleteAll();
		writer.commit();
	}

	@Override
	public void close() throws IOException {

		writer.close();
	}
}
