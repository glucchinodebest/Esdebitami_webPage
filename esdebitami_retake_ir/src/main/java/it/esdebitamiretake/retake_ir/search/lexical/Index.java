package it.esdebitamiretake.retake_ir.search.lexical;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.da.DanishAnalyzer;
import org.apache.lucene.analysis.de.GermanAnalyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.it.ItalianAnalyzer;
import org.apache.lucene.analysis.nl.DutchAnalyzer;
import org.apache.lucene.analysis.pt.PortugueseAnalyzer;
import org.apache.lucene.analysis.sv.SwedishAnalyzer;

import it.esdebitamiretake.retake_ir.search.Language;


public abstract class Index implements AutoCloseable {

	enum Field {
		ID, TEXT
	}

	private final Analyzer analyzer;

	Index(Language language) {

		switch (language) {

		case IT:
			analyzer = new ItalianAnalyzer();
			break;

		case DA:
			analyzer = new DanishAnalyzer();
			break;

		case DE:
			analyzer = new GermanAnalyzer();
			break;

		case NL:
			analyzer = new DutchAnalyzer();
			break;

		case PT:
			analyzer = new PortugueseAnalyzer();
			break;

		case SE:
			analyzer = new SwedishAnalyzer();
			break;
			
		default:
			analyzer = new EnglishAnalyzer();
			break;
		}
	}

	Analyzer getAnalyzer() {

		return this.analyzer;
	}
	
}
