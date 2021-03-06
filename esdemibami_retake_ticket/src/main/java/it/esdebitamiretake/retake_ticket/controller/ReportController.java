package it.esdebitamiretake.retake_ticket.controller;

import java.net.URI;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.axis2.client.Options;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.tempuri.XSportService;
import org.tempuri.XSportService.XSpGetApplication;
import org.tempuri.XSportService.XSpGetUser;
import org.tempuri.XSportService.XSpIsCurrentSessionAuthenticate;
import org.tempuri.XSportService.XSpIsCurrentSessionAuthenticateResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import it.esdebitamiretake.retake_ticket.collection.Activity;
import it.esdebitamiretake.retake_ticket.collection.Color;
import it.esdebitamiretake.retake_ticket.collection.Report;
import it.esdebitamiretake.retake_ticket.collection.Template;
import it.esdebitamiretake.retake_ticket.service.ActivityService;
import it.esdebitamiretake.retake_ticket.service.ColorService;
import it.esdebitamiretake.retake_ticket.service.ReportService;
import it.esdebitamiretake.retake_ticket.service.TemplateService;

@SwaggerDefinition(tags = { @Tag(name = "Report", description = "Operations pertaining to reports in VVAS") })
@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class ReportController {

	private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

	private static final String MESSAGE_401 = "You are not authorized to perform this action";

	@Autowired
	private ReportService reportService;

	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private TemplateService templateService;
	
	@Autowired
	private ColorService colorService;

	@PostMapping(value = "/report", produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Create an activity report for a specific user", tags = "reports")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Report created"),
			@ApiResponse(code = 401, message = MESSAGE_401),
			@ApiResponse(code = 404, message = "No user found") })
	@ResponseBody
	public ResponseEntity<URI> postReport(@ApiParam(required = true) @RequestBody @Valid Report report,
			@RequestHeader("Authorization") String accessToken, @ApiParam(value = "The counter score", required = false) @RequestParam(required = false) Integer counterScore,
			@ApiParam(value = "The counter weight", required = false) @RequestParam(required = false) Integer counterWeight) throws RemoteException, ResourceNotFoundException {

		String applicationName = loadApplicationName(accessToken);
		
		Activity activity= activityService.findActivityByCode((int)report.getActivityCode());
		
		System.out.println("code"+ report.getActivityCode());

		if(activity==null) 
			throw new ResourceNotFoundException("activity");

		checkUser(report.getUser(),accessToken);
		
		report.setId(null);
		report.setApplication(applicationName);
		
		if(counterWeight != null && counterScore != null) {
			Integer average = counterScore/counterWeight;
			Color col = valutaQuestionarioADR(average);
			report.setColor(col.getColorCode());
		}

		try {

			return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().pathSegment("{id}")
					.buildAndExpand(reportService.saveReport(report).getId()).toUri()).build();

		} catch (DuplicateKeyException e1) {

			throw new ResourceAlreadyExistsException("Report", report.toString());
		}
	}

	@GetMapping(value = "/report/{id}", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Retrieve a report by id", tags = "reports")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The report has been successfully retrieved"),
			@ApiResponse(code = 404, message = "Report not found") })
	@ResponseBody
	public Report getReport(@ApiParam(value = "Report id", required = true) @PathVariable String id)
			throws ResourceNotFoundException {

		logger.info("GET report [?]", id);

		Report report= reportService.find(id);
		
		if(report==null)
			throw new ResourceNotFoundException("Report",id);
		
		return report;
	}
	
	@DeleteMapping("/report/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete a report", tags = "reports")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Report has been successfully deleted"),
			@ApiResponse(code = 404, message = "Report not found") })
	public void deleteReport(@ApiParam(value = "The id of the report", required = true) @PathVariable String id)
			throws ResourceNotFoundException {

		logger.info("DELETE report [?]", id);

		Report report = reportService.find(id);
		
		if(report==null)
			throw new ResourceNotFoundException("Report",id);
		
		reportService.delete(report.getId());
	}

	@PostMapping(value = "/activity", produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Create a new report activity", tags = "activities")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Activity successfully created"),
			@ApiResponse(code = 409, message = "An activity with this name already exists") })
	@ResponseBody
	public ResponseEntity<URI> postActivity(
			@ApiParam(value = "Activity name", required = true) @RequestBody Activity activity) {

		try {
			return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().pathSegment("{id}")
					.buildAndExpand(activityService.save(activity).getId()).toUri()).build();
		} catch (DuplicateKeyException e) {

			throw new ResourceAlreadyExistsException("Activity", activity.getName());
		}
	}

	@GetMapping(value = "/activities", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get the list of available activities", tags = "activities")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Activities has been successfully retrieved"),
			@ApiResponse(code = 404, message = "No activity found") })
	@ResponseBody
	public List<Activity> getActivities() throws ResourceNotFoundException {

		List<Activity> activities = activityService.findAll();

		if (activities.isEmpty())
			throw new ResourceNotFoundException("Activities");

		return activities;
	}

	@GetMapping(value = "/activity", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get an activity by code", tags = "activities")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The activity has been successfully retrieved"),
			@ApiResponse(code = 404, message = "Activity not found") })
	@ResponseBody
	public Activity getActivity(@ApiParam(value = "Activity name", required = true) @RequestParam String name)
			throws ResourceNotFoundException {

		logger.info("GET activity [?]", name);

		Activity activity= activityService.findActivityByName(name);
		
		if(activity==null)
			throw new ResourceNotFoundException("Activity", name);

		return activity;
	}

	@DeleteMapping("/activity")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete an activity", tags = "activities")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Activity has been successfully deleted"),
			@ApiResponse(code = 404, message = "Activity not found") })
	public void deleteActivity(@ApiParam(value = "Activity name", required = true) @RequestParam String name)
			throws ResourceNotFoundException {

		logger.info("DELETE role [?]", name);

		Activity activity= activityService.findActivityByName(name);
		
		if(activity==null)
			throw new ResourceNotFoundException("Activity", name);
		
		activityService.delete(activity.getName());
	}

	private String loadApplicationName(String accessToken) throws RemoteException {

		return loadXSportService(accessToken).xSpGetApplication(new XSpGetApplication()).getXSpGetApplicationResult();
	}

	private void checkUser(String userName, String accessToken) throws RemoteException, ResourceNotFoundException {

		XSpGetUser xSpGetUser = new XSpGetUser();
		xSpGetUser.setUser(userName);

		if (loadXSportService(accessToken).xSpGetUser(xSpGetUser).getXSpGetUserResult() == null)
			throw new ResourceNotFoundException("user", userName);
	}

	private XSportService loadXSportService(String authToken) throws RemoteException {

		XSportService xSportService = new XSportService();
		Options options = xSportService._getServiceClient().getOptions();
		options.setManageSession(true);
		options.setProperty(org.apache.axis2.transport.http.HTTPConstants.COOKIE_STRING, authToken);

		XSpIsCurrentSessionAuthenticateResponse xSpIsCurrentSessionAuthenticateResponse = xSportService
				.xSpIsCurrentSessionAuthenticate(new XSpIsCurrentSessionAuthenticate());

		if(!xSpIsCurrentSessionAuthenticateResponse.getXSpIsCurrentSessionAuthenticateResult())
			throw new UnauthorizedException();
		
		return xSportService;
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@PostMapping(value = "/reportOne", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get the reports for the Interactions graph", tags = "reports")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The report has been successfully retrieved"),
			@ApiResponse(code = 404, message = "Report not found") })
	@ResponseBody
	public JSONObject getReportOne(@ApiParam(value = "The start date", required = true) @RequestParam(required=true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, 
			@ApiParam(value = "The end date", required = true) @RequestParam(required=true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
			@ApiParam(value = "The list of template ids", required = true) @RequestBody(required=true) List<String> templateIds) throws ResourceNotFoundException {
		
		JSONObject response = new JSONObject();
		List<String> labels = new ArrayList<String> ();
		List<Double> values = new ArrayList<Double>();
		
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date start = Date.from(startDate.atStartOfDay(defaultZoneId).toInstant());
		start.setHours(0);
		start.setMinutes(0);
		start.setSeconds(1);
		long startMillis= start.getTime();
		Date end = Date.from(endDate.atStartOfDay(defaultZoneId).toInstant());
		end.setHours(23);
		end.setMinutes(59);
		end.setSeconds(59);
		long endMillis= end.getTime();
		
		labels.add("Positive");
		labels.add("Negative");
		labels.add("Incomplete");
		
		double positive = 0;
		double negative = 0;
		double incomplete = 0;
		
		String nameInteraction="Interaction";
		Activity activityInteraction= activityService.findActivityByName(nameInteraction);
		Integer codeInteraction = activityInteraction.getActivityCode();
		
		String namePositive="Positive";
		Activity activityPositive= activityService.findActivityByName(namePositive);
		Integer codePositive = activityPositive.getActivityCode();
		
		String nameNegative="Negative";
		Activity activityNegative= activityService.findActivityByName(nameNegative);
		Integer codeNegative = activityNegative.getActivityCode();
		
		List<Report> reportsInteraction = reportService.findReportsByDate(startMillis, endMillis, codeInteraction, templateIds);
		List<Report> reportsPositive = reportService.findReportsByDate(startMillis, endMillis, codePositive, templateIds);
		List<Report> reportsNegative = reportService.findReportsByDate(startMillis, endMillis, codeNegative, templateIds);
		
		if(reportsPositive.size() != 0 && reportsInteraction.size() !=0)
			positive = ((double)reportsPositive.size()*100)/(double)reportsInteraction.size();
		
		values.add(positive);
		
		if(reportsNegative.size() != 0 && reportsInteraction.size() !=0)
			negative = ((double)reportsNegative.size()*100)/(double)reportsInteraction.size();
		
		values.add(negative);
		
		if( reportsInteraction.size() !=0 )
			incomplete = (double)(reportsInteraction.size()-reportsPositive.size()-reportsNegative.size())*100/((double)reportsInteraction.size());
		
		values.add(incomplete);

		response.put("Values", values);
		response.put("Labels", labels);
		
		return response;
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@PostMapping(value = "/reportTwo", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get the reports for the Template graph", tags = "reports")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The report has been successfully retrieved"),
			@ApiResponse(code = 404, message = "Report not found") })
	@ResponseBody
	public JSONObject getReportTwo(@ApiParam(value = "The start date", required = true) @RequestParam(required=true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, 
			@ApiParam(value = "The end date", required = true) @RequestParam(required=true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
			@ApiParam(value = "The list of template ids", required = true) @RequestBody(required=true) List<String> templateIds) throws ResourceNotFoundException {
		
		JSONObject response = new JSONObject();
		
		List<String> labels = new ArrayList<String>();
		List<String> label = new ArrayList<String>();
		List<List<Integer>> values = new ArrayList<List<Integer>>();
		
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date start = Date.from(startDate.atStartOfDay(defaultZoneId).toInstant());
		start.setHours(0);
		start.setMinutes(0);
		start.setSeconds(1);
		long startMillis= start.getTime();
		Date end = Date.from(endDate.atStartOfDay(defaultZoneId).toInstant());
		end.setHours(23);
		end.setMinutes(59);
		end.setSeconds(59);
		long endMillis= end.getTime();
		
		String nameInteraction="Interaction";
		Activity activityInteraction= activityService.findActivityByName(nameInteraction);
		Integer codeInteraction = activityInteraction.getActivityCode();
		
		String namePositive="Positive";
		Activity activityPositive= activityService.findActivityByName(namePositive);
		Integer codePositive = activityPositive.getActivityCode();
		
		String nameNegative="Negative";
		Activity activityNegative= activityService.findActivityByName(nameNegative);
		Integer codeNegative = activityNegative.getActivityCode();
		
		for (String id : templateIds) {
			
			Template template = templateService.findTemplateTitleById(id);
			labels.add(template.getTitle());
			
			List<Report> reportsInteraction = reportService.findReportsByTemplate(startMillis, endMillis, codeInteraction, id);
			List<Report> reportsPositive = reportService.findReportsByTemplate(startMillis, endMillis, codePositive, id);
			List<Report> reportsNegative = reportService.findReportsByTemplate(startMillis, endMillis, codeNegative, id);
			
			List<Integer> temp= new ArrayList<Integer>();
			//List<String> tempLabel = new ArrayList<String>();
			
			temp.add(reportsPositive.size());
			temp.add(reportsNegative.size());
			temp.add(reportsInteraction.size()-reportsPositive.size()-reportsNegative.size());
			
			values.add(temp);
		}
		
		label.add("Positive");
		label.add("Negative");
		label.add("Incomplete");
		
		response.put("Values", values);
		response.put("Labels", labels);
		response.put("Label", label);
		
		return response;
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@PostMapping(value = "/reportThree", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get the reports for Visualization graph", tags = "reports")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The report has been successfully retrieved"),
			@ApiResponse(code = 404, message = "Report not found") })
	@ResponseBody
	public JSONObject getReportThree(@ApiParam(value = "The start date", required = true) @RequestParam(required=true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, 
			@ApiParam(value = "The end date", required = true) @RequestParam(required=true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
			@ApiParam(value = "The list of template ids", required = true) @RequestBody(required=true) List<String> templateIds) throws ResourceNotFoundException {
		
		JSONObject response = new JSONObject();
		
		List<String> labels = new ArrayList<String>();
		List<String> label = new ArrayList<String>();
		List<List<Integer>> values = new ArrayList<List<Integer>>();
		
		ZoneId defaultZoneId = ZoneId.systemDefault();
		
		String nameInteraction="Interaction";
		Activity activityInteraction= activityService.findActivityByName(nameInteraction);
		Integer codeInteraction = activityInteraction.getActivityCode();
		
		String namePositive="Positive";
		Activity activityPositive= activityService.findActivityByName(namePositive);
		Integer codePositive = activityPositive.getActivityCode();
		
		String nameNegative="Negative";
		Activity activityNegative= activityService.findActivityByName(nameNegative);
		Integer codeNegative = activityNegative.getActivityCode();
		
		// end +1
		int noOfDaysBetween = (int)ChronoUnit.DAYS.between(startDate, endDate.plusDays(1));
		
		for (int i = 0; i < noOfDaysBetween; i++) {
			
			List<Integer> temp = new ArrayList<Integer>();
			
			LocalDate startTemp = startDate.plusDays(i);
			Date startTempDate = Date.from(startTemp.atStartOfDay(defaultZoneId).toInstant());
			startTempDate.setHours(0);
			startTempDate.setMinutes(0);
			startTempDate.setSeconds(1);
			long startMillis= startTempDate.getTime();
			
			LocalDate endTemp = startDate.plusDays(i);
			Date endTempDate = Date.from(endTemp.atStartOfDay(defaultZoneId).toInstant());
			endTempDate.setHours(23);
			endTempDate.setMinutes(59);
			endTempDate.setSeconds(59);
			long endMillis= endTempDate.getTime();
			
			List<Report> reportsInteraction = reportService.findReportsByDate(startMillis, endMillis, codeInteraction, templateIds);
			List<Report> reportsPositive = reportService.findReportsByDate(startMillis, endMillis, codePositive, templateIds);
			List<Report> reportsNegative = reportService.findReportsByDate(startMillis, endMillis, codeNegative, templateIds);
			
			temp.add(reportsInteraction.size());
			temp.add(reportsPositive.size());
			temp.add(reportsNegative.size());
			temp.add(reportsInteraction.size()-reportsPositive.size()-reportsNegative.size());
			
			values.add(temp);
			labels.add(startDate.plusDays(i).toString());
			
		}
		
		label.add("TotalInteraction");
		label.add("Positive");
		label.add("Negative");
		label.add("Incomplete");
		
		response.put("Values", values);
		response.put("Labels", labels);
		response.put("Label", label);
		
		return response;
	}
	
	//'TODO new report for test invalsi
	@SuppressWarnings({ "unchecked", "deprecation" })
	@PostMapping(value = "/reportFour", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get the reports for the Valutation", tags = "reports")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The report has been successfully retrieved"),
	@ApiResponse(code = 404, message = "Report not found") })
	@ResponseBody
	public JSONObject getReportFour(@ApiParam(value = "The start date", required = true) @RequestParam(required=true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, 
			@ApiParam(value = "The end date", required = true) @RequestParam(required=true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
			@ApiParam(value = "The list of template ids", required = true) @RequestBody(required=true) List<String> templateIds) throws ResourceNotFoundException {
	
		JSONObject response = new JSONObject();
		
		List<String> labels = new ArrayList<String>();
		List<String> label = new ArrayList<String>();
		List<List<Integer>> values = new ArrayList<List<Integer>>();
		
		ZoneId defaultZoneId = ZoneId.systemDefault();
		
		// end +1
		int noOfDaysBetween = (int)ChronoUnit.DAYS.between(startDate, endDate.plusDays(1));
		
		String name="Valutation";
		Activity activity= activityService.findActivityByName(name);
		Integer code = activity.getActivityCode();
		
		for (int i = 0; i < noOfDaysBetween; i++) {
	  
			  List<Integer> temp = new ArrayList<Integer>();
			  
			  LocalDate startTemp = startDate.plusDays(i);
			  Date startTempDate = Date.from(startTemp.atStartOfDay(defaultZoneId).toInstant());
			  startTempDate.setHours(0);
			  startTempDate.setMinutes(0);
			  startTempDate.setSeconds(1);
			  long startMillis= startTempDate.getTime();
			  
			  LocalDate endTemp = startDate.plusDays(i);
			  Date endTempDate = Date.from(endTemp.atStartOfDay(defaultZoneId).toInstant());
			  endTempDate.setHours(23);
			  endTempDate.setMinutes(59);
			  endTempDate.setSeconds(59);
			  long endMillis= endTempDate.getTime();
			  
			  List<Report> reportsRed = reportService.findReportsByDateAndColor(startMillis,endMillis,code,2);
			  List<Report> reportsYellow= reportService.findReportsByDateAndColor(startMillis,endMillis,code,1);
			  List<Report> reportsGreen = reportService.findReportsByDateAndColor(startMillis,endMillis,code,0);
			  
			  temp.add(reportsRed.size());
			  temp.add(reportsYellow.size());
			  temp.add(reportsGreen.size());
			  
			  values.add(temp);
			  labels.add(startDate.plusDays(i).toString());
		  
		}
	
		label.add("Red");
		label.add("Yellow");
		label.add("Green");
		
		response.put("Values", values);
		response.put("Labels", labels);
		response.put("Label", label);
	
		return response;
	}
	
	 private Color valutaQuestionarioADR(Integer score) throws ResourceNotFoundException {
		  
		  Color color= new Color();
		  
		  if (score >= 2) {
			  color=colorService.findByCode(2);
		  } else if (score.equals(1)) {
			  color=colorService.findByCode(1);
		  } else {
			  color=colorService.findByCode(0);
		  }
		  
		  System.out.println("color"+color.getColorCode());
		  return color;

	 }
	 
	//'TODO new Report for Anagrafica
	@SuppressWarnings({ "unchecked", "deprecation" })
	@PostMapping(value = "/reportFive", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get the reports for the Anagraphic", tags = "reports")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The report has been successfully retrieved"),
	@ApiResponse(code = 404, message = "Report not found") })
	@ResponseBody
	public JSONObject getReportFive(@ApiParam(value = "The start date", required = true) @RequestParam(required=true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, 
			@ApiParam(value = "The end date", required = true) @RequestParam(required=true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
			@ApiParam(value = "The list of template ids (type Anagrafica)", required = true) @RequestBody(required=true) List<String> templateIds) throws ResourceNotFoundException {
	
		JSONObject response = new JSONObject();
		
		List<String> labels = new ArrayList<String>();
		List<String> label = new ArrayList<String>();
		List<List<Integer>> values = new ArrayList<List<Integer>>();
		
		ZoneId defaultZoneId = ZoneId.systemDefault();
		
		// end +1
		int noOfDaysBetween = (int)ChronoUnit.DAYS.between(startDate, endDate.plusDays(1));
		
		String nameEnd = "EndAnagrafica";
		Activity activityEnd = activityService.findActivityByName(nameEnd);
		Integer codeEnd = activityEnd.getActivityCode();
		
		String nameTot = "TotAnagrafica";
		Activity activityTot = activityService.findActivityByName(nameTot);
		Integer codeTot = activityTot.getActivityCode();
		
		for (int i = 0; i < noOfDaysBetween; i++) {
	  
			  List<Integer> temp = new ArrayList<Integer>();
			  
			  LocalDate startTemp = startDate.plusDays(i);
			  Date startTempDate = Date.from(startTemp.atStartOfDay(defaultZoneId).toInstant());
			  startTempDate.setHours(0);
			  startTempDate.setMinutes(0);
			  startTempDate.setSeconds(1);
			  long startMillis= startTempDate.getTime();
			  
			  LocalDate endTemp = startDate.plusDays(i);
			  Date endTempDate = Date.from(endTemp.atStartOfDay(defaultZoneId).toInstant());
			  endTempDate.setHours(23);
			  endTempDate.setMinutes(59);
			  endTempDate.setSeconds(59);
			  long endMillis= endTempDate.getTime();
			  
			  List<Report> reportsEndAnagraphic= reportService.findReportsByDate(startMillis, endMillis, codeEnd, templateIds);
			  List<Report> reportsTotAnagraphic = reportService.findReportsByDate(startMillis, endMillis, codeTot, templateIds);
			  
			  temp.add(reportsEndAnagraphic.size());
			  temp.add(reportsTotAnagraphic.size());
			  temp.add(reportsTotAnagraphic.size() - reportsEndAnagraphic.size());
			  
			  values.add(temp);
			  labels.add(startDate.plusDays(i).toString());
		  
		}
	
		label.add("EndAnagrafica");
		label.add("TotAnagrafica");
		label.add("IncompleteAnagrafica");
		
		response.put("Values", values);
		response.put("Labels", labels);
		response.put("Label", label);
	
		return response;
	}
	
}
