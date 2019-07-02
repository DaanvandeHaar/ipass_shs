package nl.hu.ipass.project.model;

public class BoodschappenLijstItem {
	int boodschappenLijstItemNummer;
	String beschrijving;
	double bedrag;
	String datum;
	int gebruikerNummer;
	int boodschappenLijstNummer;
	String gebruikerNaam;
	
	public BoodschappenLijstItem(int boodschappenLijstItemNummer, String beschrijving, double bedrag, String datum,
			int gebruikerNummer, int boodschappenLijstNummer, String gebruikerNaam) {
		super();
		this.boodschappenLijstItemNummer = boodschappenLijstItemNummer;
		this.beschrijving = beschrijving;
		this.bedrag = bedrag;
		this.datum = datum;
		this.gebruikerNummer = gebruikerNummer;
		this.boodschappenLijstNummer = boodschappenLijstNummer;
		this.gebruikerNaam = gebruikerNaam;
	}

	public BoodschappenLijstItem(String beschrijving, double bedrag, String datum, int gebruikerNummer,
			int boodschappenLijstNummer, String gebruikerNaam) {

		this.beschrijving = beschrijving;
		this.bedrag = bedrag;
		this.datum = datum;
		this.gebruikerNummer = gebruikerNummer;
		this.boodschappenLijstNummer = boodschappenLijstNummer;
		this.gebruikerNaam = gebruikerNaam;
	}

	public int getBoodschappenLijstItemNummer() {
		return boodschappenLijstItemNummer;
	}
	public void setBoodschappenLijstItemNummer(int boodschappenLijstItemNummer) {
		this.boodschappenLijstItemNummer = boodschappenLijstItemNummer;
	}
	public String getBeschrijving() {
		return beschrijving;
	}
	public void setBeschrijving(String beschrijving) {
		this.beschrijving = beschrijving;
	}
	public double getBedrag() {
		return bedrag;
	}
	public void setBedrag(double bedrag) {
		this.bedrag = bedrag;
	}
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public int getGebruikerNummer() {
		return gebruikerNummer;
	}
	public void setGebruikerNummer(int gebruikerNummer) {
		this.gebruikerNummer = gebruikerNummer;
	}
	public int getBoodschappenLijstNummer() {
		return boodschappenLijstNummer;
	}
	public void setBoodschappenLijstNummer(int boodschappenLijstNummer) {
		this.boodschappenLijstNummer = boodschappenLijstNummer;
	}
	public String getGebruikerNaam() {
		return gebruikerNaam;
	}
	public void setGebruikerNaam(String gebruikerNaam) {
		this.gebruikerNaam = gebruikerNaam;
	}

	@Override
	public String toString() {
		return "BoodschappenLijstItem{" +
				"boodschappenLijstItemNummer=" + boodschappenLijstItemNummer +
				", beschrijving='" + beschrijving + '\'' +
				", bedrag=" + bedrag +
				", datum='" + datum + '\'' +
				", gebruikerNummer=" + gebruikerNummer +
				", boodschappenLijstNummer=" + boodschappenLijstNummer +
				", gebruikerNaam='" + gebruikerNaam + '\'' +
				'}';
	}
}