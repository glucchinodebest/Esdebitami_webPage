package it.esdebitamiretake.retake_ai.collection;

import java.util.List;

public class findSentenceForInvalsi {
	

	private List<Integer> questionsId;
	private List<Integer> score ;
	
	public findSentenceForInvalsi() {
	}
	
	public findSentenceForInvalsi(List<Integer> questionsId, List<Integer> score) {
		super();
		this.questionsId = questionsId;
		this.score = score;
	}

	public List<Integer> getQuestionsId() {
		return questionsId;
	}

	public void setQuestionsId(List<Integer> questionsId) {
		this.questionsId = questionsId;
	}

	public List<Integer> getScore() {
		return score;
	}

	public void setScore(List<Integer> score) {
		this.score = score;
	}



	

}
