/**
 * 
 */
package it.esdebitamiretake.retake_ai.response;

/**
 * @author Utente
 *
 */
public class WordPOSTag {

	private String word;
	private String posTag;
	
	public WordPOSTag() {
	}
	
	public WordPOSTag(String word,String posTag) {
		this.word=word;
		this.posTag=posTag;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getPosTag() {
		return posTag;
	}

	public void setPosTag(String posTag) {
		this.posTag = posTag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((posTag == null) ? 0 : posTag.hashCode());
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof WordPOSTag))
			return false;
		WordPOSTag other = (WordPOSTag) obj;
		if (posTag == null) {
			if (other.posTag != null)
				return false;
		} else if (!posTag.equals(other.posTag))
			return false;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		return true;
	}

	
}
