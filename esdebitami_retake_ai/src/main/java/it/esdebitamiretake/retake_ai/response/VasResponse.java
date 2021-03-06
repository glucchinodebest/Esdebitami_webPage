package it.esdebitamiretake.retake_ai.response;


public class VasResponse
{
  private Integer statusCode;
  
  private String statusMessage;
  private Object content;
  
  public VasResponse() {}
  
  public VasResponse(Integer statusCode, String statusMessage, Object content)
  {
     this.statusCode = statusCode;
    this.statusMessage = statusMessage;
     this.content = content;
  }
  
  public Integer getStatusCode() {
    return this.statusCode;
  }
  
  public void setStatusCode(Integer statusCode) {
     this.statusCode = statusCode;
  }
  
  public String getStatusMessage() {
     return this.statusMessage;
  }
  
  public void setStatusMessage(String statusMessage) {
    this.statusMessage = statusMessage;
  }
  
  public Object getContent() {
     return this.content;
  }
  
  public void setContent(Object content) {
     this.content = content;
  }
}


/* Location:              C:\Users\dercolano\Desktop\VVA\vas-template\vas-template-1.0.0.jar!\BOOT-INF\classes\it\isspa\vas\template\fe\VasResponse.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */