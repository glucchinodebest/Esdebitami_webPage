package it.esdebitamiretake.retake_ir.model;

import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(description = "All details about an Answer in a specific Context.")
public class Answer implements Comparable<Answer> {


	@JsonProperty("content")
	@NotBlank
	@ApiModelProperty(notes = "The content of the answer")
	private final String content;

	@JsonProperty("uri")
	@ApiModelProperty(notes = "The resource where the answer came from")
	@NotBlank
	private final String uri;

	@JsonProperty("confidence")
	@ApiModelProperty(notes = "The confidence value of the answer")
	private final double confidence;

	@JsonProperty("reference")
	@ApiModelProperty(notes = "The reference of the answer")
	private final String reference;

	public Answer(String content, String uri, String reference, double confidence) {

		this.content = content;
		this.uri = uri;
		this.confidence = confidence;
		this.reference=reference;
	}

	public String getContent() {

		return this.content;
	}

	public String getUri() {

		return this.uri;
	}

	public double getConfidence() {

		return this.confidence;
	}

	@Override
	public String toString() {

		return this.getContent();
	}

	@Override
	public int compareTo(Answer o) {

		return Double.compare(o.getConfidence(),this.getConfidence());
	}
}