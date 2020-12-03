package it.esdebitamiretake.retake_ir.search.semantic;

class RestResponse {

	private final int status;
	private final String payload;
	
	RestResponse(int status,String payload){
		
		this.status=status;
		this.payload=payload;
	}
	
	int getStatus() {
		
		return status;
	}
	
	String getPayload() {
		
		return payload;
	}
}
