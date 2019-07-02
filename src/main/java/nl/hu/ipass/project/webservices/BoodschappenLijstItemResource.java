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

import nl.hu.ipass.project.model.ServiceProvider;
import nl.hu.ipass.project.model.User;
import nl.hu.ipass.project.model.UserService;
import nl.hu.ipass.project.model.BoodschappenLijstItem;
import nl.hu.ipass.project.model.BoodschappenLijstItemService;


	@Path("/boodschappen")
	public class BoodschappenLijstItemResource {

		@DELETE
		@Path("{nummer}")
		@Produces("application/json")
//		@RolesAllowed("beheerder")
		public Response deleteBoodschappenLijstItem( @PathParam("nummer") int boodschappenLijstItemNummer, @Context SecurityContext sc)  {
			String userName = sc.getUserPrincipal().getName();
			User user = ServiceProvider.getUserService().getUserByUserName(userName);
			int gebruikerNummer = user.getGebruikerNummer();
			System.out.println(gebruikerNummer + boodschappenLijstItemNummer);
			if (!ServiceProvider.getBoodschappenLijstItemService().deleteBoodschappenLijstItem(boodschappenLijstItemNummer, gebruikerNummer)) {
				return Response.status(404).build();
			}
			return Response.ok().build();
		}

		@POST
		@Produces("application/json")
//		@RolesAllowed("beheerder")
		public Response addBoodschappenLijstItem(
//				@FormParam("boodschappenLijstNummer") int boodschappenLijstNummer,
				@FormParam("beschrijving") String beschrijving,
				@FormParam("bedrag") double bedrag,
				@FormParam("datum") String datum,
				@Context SecurityContext sc) {
			System.out.println(beschrijving + bedrag + datum);
			UserService service = ServiceProvider.getUserService();
			String userName = sc.getUserPrincipal().getName();
			User user = ServiceProvider.getUserService().getUserByUserName(userName);
			String gebruikerNaam = user.getVoornaam();
			int gebruikerNummer = user.getGebruikerNummer();
			int boodschappenLijstNummer = 1;
			BoodschappenLijstItem boodschappenLijstItem = ServiceProvider.getBoodschappenLijstItemService().addBooschappenLijstItem(beschrijving, bedrag, datum, gebruikerNummer, boodschappenLijstNummer, gebruikerNaam);
			if (boodschappenLijstItem == null) {
				return Response.status(404).entity("Item is niet voledig").build();
			}
			return Response.ok(boodschappenLijstItem).build();
			
		}

		@PUT
		@Path("{nummer}")
		@Produces("application/json")
		@RolesAllowed("gebruiker")
		public Response updateBoodschappenLijstItem(
				@PathParam("nummer") int boodschappenLijstItemNummer,
//				@FormParam("boodschappenLijstNummer") int boodschappenLijstNummer,
				@FormParam("beschrijving") String beschrijving,
				@FormParam("bedrag") int bedrag,
				@FormParam("datum") String datum,
				@Context SecurityContext sc) {
			int boodschappenLijstNummer = 1;
			
			UserService service = ServiceProvider.getUserService();
			String userName = sc.getUserPrincipal().getName();
			User user = ServiceProvider.getUserService().getUserByUserName(userName);
			int gebruikerNummer = user.getGebruikerNummer();
			BoodschappenLijstItem boodschappenLijstItem = ServiceProvider.getBoodschappenLijstItemService().updateBoodschappenLijstItem(beschrijving, bedrag, datum, boodschappenLijstNummer, boodschappenLijstItemNummer, gebruikerNummer);
			if (boodschappenLijstItem == null) {
				Map<String, String> messages = new HashMap<>();
				messages.put("error", "Boodschappenlijst does not exist!");
				return Response.status(409).entity(messages).build();
			}
			return Response.ok(boodschappenLijstItem).build();
		}
		
//		@Path("{code}")
//		@Produces("application/json")
//		@RolesAllowed("gebruiker")
//		public Response UpdateBoodschappenLijstItemAdmin(
//				@PathParam("code") String code,
//				@FormParam("name") String name,
//				@FormParam("capital") String capital,
//				@FormParam("region") String region,
//				@FormParam("surface") double surface,
//				@FormParam("population") int population) {
//			Country country = ServiceProvider.getWorldService().updateCountry(code, name, capital, region, surface, population);
//			if (country == null) {
//				Map<String, String> messages = new HashMap<>();
//				messages.put("error", "Country does not exist!");
//				return Response.status(409).entity(messages).build();
//			}
//			return Response.ok(country).build();
//		}
		
		@GET
		@Produces("application/json")
		public String getBoodschappenLijstItems() {
			BoodschappenLijstItemService service = ServiceProvider.getBoodschappenLijstItemService();
			JsonArrayBuilder jab = Json.createArrayBuilder();
			for (BoodschappenLijstItem boodschappenLijstItem : service.getAllBoodschappenLijstItems()) {
				JsonObjectBuilder job = Json.createObjectBuilder();
				job.add("beschrijving", boodschappenLijstItem.getBeschrijving());
				job.add("bedrag" , boodschappenLijstItem.getBedrag());
				job.add("datum", boodschappenLijstItem.getDatum());
				job.add("gebruikerNaam", boodschappenLijstItem.getGebruikerNaam());
				job.add("gebruikerNummer", boodschappenLijstItem.getGebruikerNummer());
				job.add("itemNummer", boodschappenLijstItem.getBoodschappenLijstItemNummer());
				jab.add(job);
			}
			return jab.build().toString();
		}
//		@GET
//		@Path("{nummer}")
//		@Produces("application/json")
//		public String getBoodschappenLijstItemByNumber(@PathParam("nummer") int nummer) {
//			BoodschappenLijstItemService service = ServiceProvider.getBoodschappenLijstItemService();
//			BoodschappenLijstItem boodschappenLijstItem = service.getBoodschappenLijstItemByNumber(nummer);
//			JsonArrayBuilder jab = Json.createArrayBuilder();
//				JsonObjectBuilder job = Json.createObjectBuilder();
//				job.add("GebruikerNummer", boodschappenLijstItem.getGebruikerNummer());
//				job.add("Voornaam" , boodschappenLijstItem.getVoornaam());
//				job.add("AchterNaam", boodschappenLijstItem.getAchternaam());
//				job.add("E-mail", boodschappenLijstItem.getEmail());
//				job.add("GebruikersNaam", boodschappenLijstItem.getGebruikersnaam());
//				job.add("Leeftijd", boodschappenLijstItem.getLeeftijd());
//				job.add("Rol", boodschappenLijstItem.getRol());
//				jab.add(job);
//			return jab.build().toString();
//		}
//		@GET
//		@Path("info")
//		@Produces("application/json")
//		public String getBoodschappenLijstItemByBoodschappenLijstItemName(@Context SecurityContext sc) {
//			UserService service = ServiceProvider.getUserService();
//			String userName = sc.getUserPrincipal().getName();
//			System.out.println(boodschappenLijstItemName);
//			BoodschappenLijstItem boodschappenLijstItem = service.getBoodschappenLijstItemByBoodschappenLijstItemName(boodschappenLijstItemName);
//			JsonObjectBuilder job = Json.createObjectBuilder();
//				job.add("gebruikerNummer", boodschappenLijstItem.getGebruikerNummer());
//				job.add("voornaam" , boodschappenLijstItem.getVoornaam());
//				job.add("achternaam", boodschappenLijstItem.getAchternaam());
//				job.add("email", boodschappenLijstItem.getEmail());
//				job.add("gebruikersnaam", boodschappenLijstItem.getGebruikersnaam());
//				job.add("leeftijd", boodschappenLijstItem.getLeeftijd());
//				job.add("rol", boodschappenLijstItem.getRol());
//				return job.build().toString();
//		}
//
//	}
}
