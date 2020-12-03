/*    */ package it.esdebitamiretake.retake_ai.collection;
/*    */ 
/*    */ import com.fasterxml.jackson.annotation.JsonFormat;
/*    */ import com.fasterxml.jackson.annotation.JsonInclude;
/*    */ import com.fasterxml.jackson.annotation.JsonProperty;
/*    */ import java.util.List;
/*    */ import org.springframework.data.mongodb.core.mapping.Field;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @JsonInclude(JsonInclude.Include.ALWAYS)
/*    */ public class Question
/*    */ {
/*    */   @Field("questionId")
/*    */   @JsonProperty("questionId")
/*    */   private Integer questionId;
/*    */   @Field("questionText")
/*    */   @JsonProperty("questionText")
/*    */   private String questionText;
/*    */   @Field("responses")
/*    */   @JsonProperty("responses")
/*    */   @JsonFormat(with={com.fasterxml.jackson.annotation.JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY})
/*    */   private List<Response> responses;

/*    */   @Field("xPosition")
/*    */   @JsonProperty("xPosition")
/*    */   private int xPosition;
/*    */   @Field("yPosition")
/*    */   @JsonProperty("yPosition")
/*    */   private int yPosition;


		   @Field("lastQuestion")
		   @JsonProperty("lastQuestion")
		   private Boolean lastQuestion;
		   
		   @Field("weight")
		   @JsonProperty("weight")
		   private Integer weight;
		   
		   @Field("dataType")
		   @JsonProperty("dataType")
		   private String dataType;
		   
		   @Field("fieldFlag")
		   @JsonProperty("fieldFlag")
		   private String fieldFlag;

/*    */   
/*    */   public Integer getQuestionId()
/*    */   {
/* 32 */     return this.questionId;
/*    */   }
/*    */   
/*    */   public void setQuestionId(Integer questionId) {
/* 36 */     this.questionId = questionId;
/*    */   }
/*    */   
/*    */   public String getQuestionText() {
/* 40 */     return this.questionText;
/*    */   }
/*    */   
/*    */   public void setQuestionText(String questionText) {
/* 44 */     this.questionText = questionText;
/*    */   }
/*    */   
/*    */   public List<Response> getResponses() {
/* 48 */     return this.responses;
/*    */   }
/*    */   
/*    */   public void setResponses(List<Response> responses) {
/* 52 */     this.responses = responses;
/*    */   }


			public int getxPosition() {
				return xPosition;
			}
			public void setxPosition(int xPosition) {
				this.xPosition = xPosition;
			}
			public int getyPosition() {
				return yPosition;
			}
			public void setyPosition(int yPosition) {
				this.yPosition = yPosition;
			}

			public Boolean getLastQuestion() {
				return lastQuestion;
			}

			public void setLastQuestion(Boolean lastQuestion) {
				this.lastQuestion = lastQuestion;
			}
			public Integer getWeight() {
				return weight;
			}
			public void setWeight(Integer weight) {
				this.weight = weight;
			}
			public String getDataType() {
				return dataType;
			}
			public void setDataType(String dataType) {
				this.dataType = dataType;
			}
			public String getFieldFlag() {
				return fieldFlag;
			}
			public void setFieldFlag(String fieldFlag) {
				this.fieldFlag = fieldFlag;
			}
			
			
			
			
			
			

			
			
			
			




/*    */ }


/* Location:              C:\Users\dercolano\Desktop\VVA\vas-template\vas-template-1.0.0.jar!\BOOT-INF\classes\it\isspa\vas\template\db\collection\Question.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */