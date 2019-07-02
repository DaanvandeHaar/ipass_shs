package nl.hu.ipass.project.model;

import nl.hu.ipass.project.model.*;

public class ServiceProvider {
	private static UserService userService = new UserService();
	private static BoodschappenLijstItemService boodschappenlijstItemService = new BoodschappenLijstItemService();
	
	public static UserService getUserService() {
		return userService;
	}
	public static BoodschappenLijstItemService getBoodschappenLijstItemService() {
		return boodschappenlijstItemService;
	}
}
