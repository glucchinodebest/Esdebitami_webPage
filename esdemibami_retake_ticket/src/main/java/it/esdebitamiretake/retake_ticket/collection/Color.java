package it.esdebitamiretake.retake_ticket.collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "colors")
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(description = "Structure of the Color for the Invalsi type")
public class Color {

	@Id
	@Field("_id")
	private ObjectId id;

	@Field("colorCode")
	@JsonProperty("colorCode")
	@ApiModelProperty(notes = "The code of the color")
	@NotNull
	private Integer colorCode;

	@Field("colorName")
	@JsonProperty("colorName")
	@NotNull
	@NotEmpty
	@NotBlank
	private String colorName;
	
	@Field("appName")
	@JsonProperty("appName")
	private String appName;

	@Field("hexColor")
	@JsonProperty("hexColor")
	private String hexColor;
	
	public Color() {
	}

	public Color(ObjectId id, @NotNull Integer colorCode, @NotNull @NotEmpty @NotBlank String colorName) {
		this.id = id;
		this.colorCode = colorCode;
		this.colorName = colorName;
	}

	public Color(@NotNull Integer colorCode, @NotNull @NotEmpty @NotBlank String colorName, String appName,
			String hexColor) {
		this.colorCode = colorCode;
		this.colorName = colorName;
		this.appName = appName;
		this.hexColor = hexColor;
	}

	public String getId() {

		return id.toString();
	}

	public void setId(ObjectId id) {
		
		this.id=id;
	}

	public Integer getColorCode() {
		return colorCode;
	}

	public void setColorCode(Integer colorCode) {
		this.colorCode = colorCode;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}
	
	
	
}