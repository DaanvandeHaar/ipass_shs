package nl.hu.ipass.project.persistence;

import java.util.List;

import nl.hu.ipass.project.model.BoodschappenLijstItem;


public interface BoodschappenLijstItemDao {
	
	List<BoodschappenLijstItem> findAll();
	public BoodschappenLijstItem findByNumber(int number);
    boolean update(BoodschappenLijstItem boodschappenLijstItem, int boodschappenLijstItemNummer);
    boolean delete(int number, int gebruikerNummer);
	boolean save(BoodschappenLijstItem boodschappenLijstItem);
}
