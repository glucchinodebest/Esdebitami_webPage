package it.esdebitamiretake.retake_ticket.controller;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import it.esdebitamiretake.retake_ticket.anagrafica.ProfileUser;
import it.esdebitamiretake.retake_ticket.service.UserService;

@SwaggerDefinition(tags = { @Tag(name = "users", description = "Operations pertaining to users in VVAS") })
@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class UserController {
	
	@Autowired
	private UserService userService;

	@SuppressWarnings("unchecked")
	@GetMapping(value = "/usersInfo", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Create the installation documents", tags = "users")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Users Informations retrieved"),
			@ApiResponse(code = 404, message = "No user found") })
	@ResponseBody
	public JSONObject getUsersInfo() throws RemoteException, ResourceNotFoundException {

		JSONObject response = new JSONObject();
		
		List<String> names = new ArrayList<String>();
		List<String> surnames = new ArrayList<String>();
		List<String> usersnames = new ArrayList<String>();
		
		List<ProfileUser> users = userService.findAllUsers();
		
		for(ProfileUser user : users) {
			
			usersnames.add(user.getId());
			names.add(user.getName());
			surnames.add(user.getSurname());
			
		}
		
		response.put("names", names);
		response.put("surnames", surnames);
		response.put("usersnames", usersnames);
		
		return response;
		
	}
	
  	//'TODO type Anagrafica new
  	@SuppressWarnings("unchecked")
	@PostMapping(value = "/anagraficaUsers", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Retrieve the list of available anagraphics", tags = "users")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The list of anagraphics has been successfully retrieved"),
			@ApiResponse(code = 404, message = "User not found")})
	@ResponseBody
	public JSONObject getSurveysAnagraficaNew(
			@ApiParam(value = "The list of template ids", required = true) @RequestBody(required = true) List<String> templateIds,
			@ApiParam(value = "The user id", required = false) @RequestParam(required = false) String userId,
			@ApiParam(value = "The name of the user", required = false) @RequestParam(required = false) String name,
			@ApiParam(value = "The surname of the user", required = false) @RequestParam(required = false) String surname,
			@ApiParam(value = "The email of the user", required = false) @RequestParam(required = false) String email,
			@ApiParam(value = "Zero-based page index", required = false) @RequestParam(required = false) Integer page,
			@ApiParam(value = "The size of the page to be returned", required = true) @RequestParam(required = true) Integer size,
			@ApiParam(value = "Sort the collection in alphabetic order", required = false) @RequestParam(required = false, defaultValue = "true") boolean sortAlph)
					throws ResourceNotFoundException, ParseException {
		
		JSONObject response = new JSONObject();
	
		if (page == null)
			page = 0;
		
		//List<ProfileUser> users =  userService.findUserByInfoNew(name, surname, email, userId, page, size, sortAlph);
		List<ProfileUser> users =  userService.findUserByInfoNew(templateIds, name, surname, email, userId, page, size, sortAlph);
		
		//int userSize =  userService.findUserByInfoNewUnordered(name, surname, email, userId);
		int userSize =  userService.findUserByInfoNewUnordered(templateIds, name, surname, email, userId);
		
		response.put("users", users);
		response.put("userSize", userSize);
		
		return response;
	}
	
}
