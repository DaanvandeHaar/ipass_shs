package nl.hu.ipass.project.model;



public class User {
	private int gebruikerNummer;
	private String voornaam;
	private String achternaam;
	private String email;
	private int leeftijd;
	private String rol;
	private String wachtwoordHash;
	private String gebruikersnaam;
	private String wachtwoord;
	
	public User(int gebruikerNummer, String voornaam, String achternaam, String email, int leeftijd, String rol,
			String wachtwoordHash, String gebruikersnaam, String wachtwoord) {
		this.gebruikerNummer = gebruikerNummer;
		this.voornaam = voornaam;
		this.achternaam = achternaam;
		this.email = email;
		this.leeftijd = leeftijd;
		this.rol = rol;
		this.wachtwoordHash = wachtwoordHash;
		this.gebruikersnaam = gebruikersnaam;
		this.wachtwoord = wachtwoord;
	}
	

	


	public User(String voornaam, String achternaam, String email, int leeftijd, String rol, String wachtwoordHash,
			String gebruikersnaam, String wachtwoord) {
		this.voornaam = voornaam;
		this.achternaam = achternaam;
		this.email = email;
		this.leeftijd = leeftijd;
		this.rol = rol;
		this.wachtwoordHash = wachtwoordHash;
		this.gebruikersnaam = gebruikersnaam;
		this.wachtwoord = wachtwoord;
	}





	public int getGebruikerNummer() {
		return gebruikerNummer;
	}

	public void setGebruikerNummer(int gebruikerNummer) {
		this.gebruikerNummer = gebruikerNummer;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public String getAchternaam() {
		return achternaam;
	}

	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getLeeftijd() {
		return leeftijd;
	}

	public void setLeeftijd(int leeftijd) {
		this.leeftijd = leeftijd;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getWachtwoordHash() {
		return wachtwoordHash;
	}

	public void setWachtwoordHash(String wachtwoordHash) {
		this.wachtwoordHash = wachtwoordHash;
	}

	public String getGebruikersnaam() {
		return gebruikersnaam;
	}

	public void setGebruikersnaam(String gebruikersnaam) {
		this.gebruikersnaam = gebruikersnaam;
	}

	public String getWachtwoord() {
		return wachtwoord;
	}

	public void setWachtwoord(String wachtwoord) {
		this.wachtwoord = wachtwoord;
	}



	
	
	

}
