package it.esdebitamiretake.retale_ir.db.collection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "resources")
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(description = "All details about the Resource.")
@CompoundIndex(name = "uri_reference", unique = true, def = "{'uri' : 1, 'reference': 1}")
public class Resource {

	@Id
	@Field("_id")
	@JsonProperty("id")
	@ApiModelProperty(notes = "The database generated resource ID")
	private ObjectId id;

	@Field("uri")
	@JsonProperty("uri")
	@NotBlank
	@ApiModelProperty(notes = "The Uniform Resource Identifier")
	private String uri;
	
	@Field("context")
	@JsonProperty("context")
	@ApiModelProperty(notes = "The context to which the resource refers")
	@NotNull
	private ObjectId contextId;

	@Field("reference")
	@JsonProperty("reference")
	private String reference;
	
	@JsonIgnore
	@Field("embedding")
	private double[] embedding;

	public String getId() {

		return id.toString();
	}

	public String getUri() {

		return this.uri;
	}

	public String getReference() {

		return reference;
	}
	
	public String getContextId() {

		return contextId.toString();
	}
	
	@JsonIgnore
	public double[] getEmbedding() {

		return this.embedding;
	}
	
	public void setEmbeddings(double[] embedding) {
		
		this.embedding=embedding;
	}

	public void setContextId(String contextId) {

		this.contextId = new ObjectId(contextId);
	}

	@Override
	public String toString() {

		return getUri();
	}

}