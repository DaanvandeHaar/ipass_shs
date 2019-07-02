package nl.hu.ipass.project.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import nl.hu.ipass.project.persistence.BoodschappenLijstItemPostgresDaoImpl;


public class BoodschappenLijstItemService {

	private BoodschappenLijstItemPostgresDaoImpl dao = new BoodschappenLijstItemPostgresDaoImpl();
	
	public BoodschappenLijstItemService() {
	}
	
	public List<BoodschappenLijstItem> getAllBoodschappenLijstItems() {
		return dao.findAll();
	}
	
	public BoodschappenLijstItem getBoodschappenLijstItemByNumber(int number) {
		return dao.findByNumber(number);
	}
	public BoodschappenLijstItem getBoodschappenLijstItemByBoodschappenLijstItemName(String name) {
		return dao.findByBoodschappenLijstItemName(name);
	}

	public boolean deleteBoodschappenLijstItem(int number, int gebruikerNummer) {
		System.out.println(number + gebruikerNummer);
		return dao.delete(number, gebruikerNummer);
	}
	
	public BoodschappenLijstItem addBooschappenLijstItem(String beschrijving, double bedrag, String datum,
			int gebruikerNummer, int boodschappenLijstNummer, String gebruikerNaam) {
		System.out.println(beschrijving + bedrag + datum + gebruikerNummer + gebruikerNaam);
		System.out.println("add boodschappen");
		BoodschappenLijstItem boodschappenLijstItem = new BoodschappenLijstItem(beschrijving, bedrag, datum,
		gebruikerNummer,boodschappenLijstNummer, gebruikerNaam);
		System.out.println(boodschappenLijstItem.toString());
			if (!dao.save(boodschappenLijstItem)) {
				System.out.println("FOUT!");
				return null;
			} else {
				return boodschappenLijstItem;
				
			}
			
		
	}

	public BoodschappenLijstItem updateBoodschappenLijstItem(String beschrijving, double bedrag,String datum, int boodschappenLijstNummer, int boodschappenLijstItemNummer, int gebruikerNummer) {
		
		for (BoodschappenLijstItem i :  getAllBoodschappenLijstItems()) {

			if (i.getGebruikerNummer() == gebruikerNummer && 
				i.getBoodschappenLijstItemNummer() == boodschappenLijstItemNummer &&
				i.getBoodschappenLijstNummer() == boodschappenLijstNummer)   {
				
				i.setBeschrijving(beschrijving);
				i.setBedrag(bedrag);
				i.setDatum(datum);
				dao.update(i, boodschappenLijstItemNummer);
				return i;
			}
		}
		return null;
	}
}
