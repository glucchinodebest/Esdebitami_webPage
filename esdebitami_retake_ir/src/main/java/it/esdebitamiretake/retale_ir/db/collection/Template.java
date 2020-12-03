package it.esdebitamiretake.retale_ir.db.collection;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "templates")
@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonFormat(with = { com.fasterxml.jackson.annotation.JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY })
@XmlRootElement
public class Template {

	@Id
	@Field("_id")
	@JsonProperty("id")
	@XmlElement
	private ObjectId id;

	@Field("title")
	@NotNull
	@JsonProperty("title")
	@XmlElement
	private String title;

	public String getId() {

		return this.id.toString();
	}

	public void setId(ObjectId id) {

		this.id = id;
	}

	public String getTitle() {

		return this.title;
	}

	public void setTitle(String title) {

		this.title = title;
	}

	@Override
	public String toString() {

		return getTitle();
	}
}