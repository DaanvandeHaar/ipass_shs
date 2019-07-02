package nl.hu.ipass.project.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import nl.hu.ipass.project.persistence.UserPostgresDaoImpl;


public class UserService {

	private UserPostgresDaoImpl dao = new UserPostgresDaoImpl();
	
	public UserService() {
	}
	
	public List<User> getAllUsers() {
		return dao.findAll();
	}
	
	public User getUserByNumber(int number) {
		return dao.findByNumber(number);
	}
	public User getUserByUserName(String name) {
		System.out.println("GET Request gaat naar dao via service");
		return dao.findByUserName(name);
	}

	public boolean deleteUser(int number) {
		return dao.delete(getUserByNumber(number));
	}
	
	public User addUser(String voornaam, String achternaam, String email, int leeftijd, String rol,
		String gebruikersnaam, String wachtwoord) {
		System.out.println("add user");
		String wachtwoordHash = "geen";
		for (User u :  getAllUsers()) {
			if (!(u.getGebruikersnaam() == (gebruikersnaam) && u.getEmail().equals(email))) {
				System.out.println("save user");
				User user = new User(voornaam, achternaam, email, leeftijd, rol,  wachtwoordHash,
						gebruikersnaam, wachtwoord);
				if (!dao.save(user)) {
					System.out.println("Kon gebruiker niet toevoegen: " + user.getVoornaam() + " " + user.getAchternaam());
					return null;
				} else {
					dao.save(user);
					System.out.println("Gebruiker toevoegen: " + user.getVoornaam() + " " + user.getAchternaam());
					return user;
					
				}
			}
		}
		return null;
	}

	public User updateUser(int gebruikerNummer, String voornaam, String achternaam, String email, int leeftijd, String rol,
			String wachtwoordHash, String gebruikersnaam, String wachtwoord) {
		
		for (User u :  getAllUsers()) {

			if (u.getGebruikerNummer() == gebruikerNummer) {
				u.setAchternaam(achternaam);
				u.setVoornaam(voornaam);
				u.setEmail(email);
				u.setWachtwoord(wachtwoord);
				u.setGebruikersnaam(gebruikersnaam);
				u.setLeeftijd(leeftijd);
				u.setWachtwoord(wachtwoord);
				dao.update(u);
				return u;
			}
		}
		return null;
	}
}
