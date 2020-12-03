package it.esdebitamiretake.retake_ticket.anagrafica;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Field;


@ApiModel(description = "Personal data about an ADR user (type=adr)")
public class ProfileADR extends ProfileCompany{
	
	@Field("doctor")
	@JsonProperty("doctor")
	@NotNull
	private Profile doctor;
	
	@ApiModelProperty(notes = "Vehicles used for work")//allowableValues = "MOTORBIKE, CAR, BUS, TRAIN, CAR_POOLING")
	@Field("transportation")
	@JsonProperty("transportation")
	@NotNull
	private Transportation transportation;
	
	@ApiModelProperty(notes = "Authorization to process personal data")
	@Field("pda")
	@JsonProperty("pda")
	private boolean pda;
	
	@ApiModelProperty(notes = "Authorization to transfer to third parties")
	@Field("tda")
	@JsonProperty("tda")
	private boolean tpa;
	
	@ApiModelProperty(notes = "Covid positivity")
	@Field("positivity")
	@JsonProperty("positivity")
	private boolean positivity;

	@Field("quarantine")
	@JsonProperty("quarantine")
	private boolean quarantine;
	
	@ApiModelProperty(notes = "Fragile worker")
	@Field("fragility")
	@JsonProperty("fragility")
	private boolean fragility;
	
	@ApiModelProperty(notes = "Can return to work")
	@Field("canWork")
	@JsonProperty("canWork")
	private boolean canWork;
}