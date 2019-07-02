package nl.hu.ipass.project.webservices;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import nl.hu.ipass.project.model.User;
import nl.hu.ipass.project.model.*;


@Path("/user")
public class UserResource {

	@DELETE
	@Path("{nummer}")
	@Produces("application/json")
	@RolesAllowed("beheerder")
	public Response deleteUser(@PathParam("nummer") int GebruikerNummer) {
		if (!ServiceProvider.getUserService().deleteUser(GebruikerNummer)) {
			return Response.status(404).build();
		}
		return Response.ok().build();
	}

	@POST
	@Produces("application/json")
//	@RolesAllowed("beheerder")
	public Response addUser(
			@FormParam("voornaam") String voornaam,
			@FormParam("achternaam") String achternaam,
			@FormParam("email") String email,
			@FormParam("leeftijd") int leeftijd,
			@FormParam("rol") String rol,
			@FormParam("gebruikersnaam") String gebruikersnaam,  
			@FormParam("wachtwoord") String wachtwoord ) {
		User user = ServiceProvider.getUserService().addUser(voornaam,achternaam,email,leeftijd,rol,gebruikersnaam,wachtwoord);
		if (user == null) {
			Map<String, String> messages = new HashMap<>();
			messages.put("error", "Country does not exist!");
			System.out.println("Gebruiker: " + voornaam  + " niet toevoegen");
			return Response.status(409).entity(messages).build();
		}
		System.out.println("Gebruiker : " + voornaam + " toevoegen");
		return Response.ok(user).build();
		
	}

	@PUT
	@Path("{nummer}")
	@Produces("application/json")
	@RolesAllowed("beheerder")
	public Response updateUser(
			@PathParam("nummer") int gebruikerNummer,
			@FormParam("voornaam") String voornaam,
			@FormParam("achternaam") String achternaam,
			@FormParam("email") String email,
			@FormParam("leeftijd") int leeftijd,
			@FormParam("rol") String rol,
			@FormParam("gebruikersnaam") String gebruikersnaam,  
			@FormParam("wachtwoord") String wachtwoord ) {
		
		User user = ServiceProvider.getUserService().updateUser(gebruikerNummer, voornaam, achternaam, email, leeftijd, rol, "geen", gebruikersnaam, wachtwoord);
		if (user == null) {
			Map<String, String> messages = new HashMap<>();
			messages.put("error", "Country does not exist!");
			return Response.status(409).entity(messages).build();
		}
		return Response.ok(user).build();
	}
//	@Path("{code}")
//	@Produces("application/json")
//	@RolesAllowed("gebruiker")
//	public Response UpdateUserAdmin(
//			@PathParam("code") String code,
//			@FormParam("name") String name,
//			@FormParam("capital") String capital,
//			@FormParam("region") String region,
//			@FormParam("surface") double surface,
//			@FormParam("population") int population) {
//		Country country = ServiceProvider.getWorldService().updateCountry(code, name, capital, region, surface, population);
//		if (country == null) {
//			Map<String, String> messages = new HashMap<>();
//			messages.put("error", "Country does not exist!");
//			return Response.status(409).entity(messages).build();
//		}
//		return Response.ok(country).build();
//	}
	
	@GET
	@RolesAllowed("Beheerder")
	@Produces("application/json")
	public String getUsers() {
		UserService service = ServiceProvider.getUserService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (User user : service.getAllUsers()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("gebruikerNummer", user.getGebruikerNummer());
			job.add("voornaam" , user.getVoornaam());
			job.add("achternaam", user.getAchternaam());
			job.add("email", user.getEmail());
			job.add("gebruikersNaam", user.getGebruikersnaam());
			job.add("leeftijd", user.getLeeftijd());
			job.add("rol", user.getRol());
			jab.add(job);
			System.out.println("verzoek");
		}
		return jab.build().toString();
	}
	@GET
	@Path("{nummer}")
	@Produces("application/json")
	public String getUserByNumber(@PathParam("nummer") int nummer) {
		UserService service = ServiceProvider.getUserService();
		User user = service.getUserByNumber(nummer);
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("GebruikerNummer", user.getGebruikerNummer());
			job.add("Voornaam" , user.getVoornaam());
			job.add("AchterNaam", user.getAchternaam());
			job.add("E-mail", user.getEmail());
			job.add("GebruikersNaam", user.getGebruikersnaam());
			job.add("Leeftijd", user.getLeeftijd());
			job.add("Rol", user.getRol());
		return job.build().toString();
	}
	@GET
	@Path("info")
	@Produces("application/json")
	public String getUserByUserName(@Context SecurityContext sc) {
		UserService service = ServiceProvider.getUserService();
		System.out.println("GET Request mijn info in resource");
		String userName = sc.getUserPrincipal().getName();
		System.out.println(userName);
		User user = service.getUserByUserName(userName);
		JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("gebruikerNummer", user.getGebruikerNummer());
			job.add("voornaam" , user.getVoornaam());
			job.add("achternaam", user.getAchternaam());
			job.add("email", user.getEmail());
			job.add("gebruikersnaam", user.getGebruikersnaam());
			job.add("leeftijd", user.getLeeftijd());
			job.add("rol", user.getRol());
			System.out.println("info terugsturen via resource");
			
			return job.build().toString();
		
	}

}