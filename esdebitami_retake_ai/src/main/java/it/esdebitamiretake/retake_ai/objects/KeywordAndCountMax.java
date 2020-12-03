package it.esdebitamiretake.retake_ai.objects;

import org.json.simple.JSONObject;


public class KeywordAndCountMax {

	private JSONObject obj;
	private int countMax;
	
	
	public KeywordAndCountMax(JSONObject obj, int countMax) {
		this.obj = obj;
		this.countMax = countMax;
	}
	
	public KeywordAndCountMax() {
	}
	
	public JSONObject getObj() {
		return obj;
	}
	public void setObj(JSONObject obj) {
		this.obj = obj;
	}
	public int getCountMax() {
		return countMax;
	}
	public void setCountMax(int countMax) {
		this.countMax = countMax;
	}
	
	
	
}
