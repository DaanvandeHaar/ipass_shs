package nl.hu.ipass.project.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import nl.hu.ipass.project.model.BoodschappenLijstItem;
import nl.hu.ipass.project.model.User;

public class BoodschappenLijstItemPostgresDaoImpl extends PostgresBaseDao implements BoodschappenLijstItemDao {

	public BoodschappenLijstItemPostgresDaoImpl() {
		getConnection();
	}
	
//	public int findNumberForBoodschappenLijstItem(String name, String pass) {
//		String number = "";
//		try (Connection connection = super.getConnection()){
//			Statement statement = connection.createStatement();
//			ResultSet result = statement.executeQuery("select * from Gebruiker");
//			while (result.next()) {
//				if (name.equals(result.getString("gebruikersnaam")) && pass.equals(result.getString("wachtwoord"))) {
//					number = result.getString("gebruiker_nummer");
//					return number;
//				}
//			}
//		} catch (SQLException se) {
//			se.printStackTrace();
//		}
//		return null;
//	}
		
    @Override
    public boolean save(BoodschappenLijstItem boodschappenLijstItem) {
    	System.out.println("save");
    	boolean saved = false;
        try (Connection conn = super.getConnection()){
            String query = "INSERT INTO boodschappenlijst_item(beschrijving,bedrag,datum,gebruiker_nummer,boodschappenlijst_nummer,gebruiker_naam) VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, boodschappenLijstItem.getBeschrijving());
            statement.setDouble(2, boodschappenLijstItem.getBedrag());
            statement.setString(3, boodschappenLijstItem.getDatum());
            statement.setInt(4, boodschappenLijstItem.getGebruikerNummer());
            statement.setInt(5, boodschappenLijstItem.getBoodschappenLijstNummer());
            statement.setString(6, boodschappenLijstItem.getGebruikerNaam());
            saved = statement.executeUpdate() == 1;
            statement.close();
            return saved;
        } catch (SQLException se){
            se.printStackTrace();
        }  
        return true;
    }

		@Override
		public List<BoodschappenLijstItem> findAll() {
			ArrayList<BoodschappenLijstItem> boodschappenLijstItems = new ArrayList<>();
			try (Connection conn = super.getConnection()){
				String query = "SELECT * FROM boodschappenlijst_item";
				Statement statement = conn.createStatement();
				ResultSet result = statement.executeQuery(query);
				while (result.next()) {
					BoodschappenLijstItem boodschappenLijstItem = new BoodschappenLijstItem(			
							result.getInt("boodschappenlijst_item_nummer"),
							result.getString("beschrijving"), 
							result.getDouble("bedrag"),
							result.getString("datum"),
							result.getInt("gebruiker_nummer"),
							result.getInt("boodschappenlijst_nummer"),
							result.getString("gebruiker_naam"));
					boodschappenLijstItems.add(boodschappenLijstItem);
				}
				result.close();
				statement.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			System.out.println("test");
			return boodschappenLijstItems;
		}

		@Override
		public BoodschappenLijstItem findByNumber(int number) {
			BoodschappenLijstItem BoodschappenLijstItem = null;
			try (Connection conn = super.getConnection()){
				String query = "SELECT * FROM Boodschappenlijst_item WHERE gebruiker_nummer = ?";
				PreparedStatement statement = conn.prepareStatement(query);
				statement.setInt(1, number);
				ResultSet result = statement.executeQuery();
				while (result.next()) {
					BoodschappenLijstItem  = new BoodschappenLijstItem(
							result.getInt("boodschappenlijst_item_nummer"),
							result.getString("beschrijving"), 
							result.getInt("bedrag"),
							result.getString("datum"),
							result.getInt("GebruikerNummer"),
							result.getInt("boodschappenlijst_nummer"),
							result.getString("gebruiker_naam"));
				}
				result.close();
				statement.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			return BoodschappenLijstItem;
		}
		public BoodschappenLijstItem findByBoodschappenLijstItemName(String name) {
			BoodschappenLijstItem boodschappenLijstItem = null;
			try (Connection conn = super.getConnection()){
				String query = "SELECT * FROM boodschappenlijst_item WHERE gebruiker_naam = ?";
				PreparedStatement statement = conn.prepareStatement(query);
				statement.setString(1, name);
				ResultSet result = statement.executeQuery();
				while (result.next()) {
					boodschappenLijstItem = new BoodschappenLijstItem(	
							result.getInt("boodschappenlijst_item_nummer"), 
							result.getString("beschrijving"), 	
							result.getInt("bedrag"),
							result.getString("datum"),
							result.getInt("gebruiker_nummer"),
							result.getInt("booschappenlijst_nummer"),
							result.getString("gebruiker_naam"));
				}
				result.close();
				statement.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			return boodschappenLijstItem;
		}


		@Override
		public boolean update(BoodschappenLijstItem boodschappenLijstItem, int boodschappenLijstItemNummer){
			boolean updated = false;
			try (Connection conn = super.getConnection()){
				String query = "UPDATE BoodschappenLijst_item SET beschrijving = ? ,bedrag = ? ,datum = ? WHERE boodschappenlijst_item_nummer = ?";
				PreparedStatement statement = conn.prepareStatement(query);
	            statement.setString(1, boodschappenLijstItem.getBeschrijving());
	            statement.setDouble(2, boodschappenLijstItem.getBedrag());
	            statement.setString(3, boodschappenLijstItem.getDatum());
	            statement.setInt(4, boodschappenLijstItemNummer);
				updated = statement.executeUpdate() == 1;
				statement.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			return updated;
		}

		@Override
		public boolean delete(int number, int gebruikerNummer){
			try (Connection conn = super.getConnection()){
				String query = "DELETE FROM boodschappenlijst_item WHERE boodschappenlijst_item_nummer = ? AND gebruiker_nummer = ?";
				PreparedStatement statement = conn.prepareStatement(query);
				statement.setInt(1, number);
				statement.setInt(2, gebruikerNummer);
				statement.executeUpdate();
				statement.close();
				return true;
			} catch (SQLException se) {
				se.printStackTrace();
			}
			return false;
		}

		public int getActive(){
			try(Connection conn = super.getConnection()) {
				int number = 0;
				String query = "SELECT boodschappenlijst_nummer FROM boodschappenlijst WHERE boodschappenlijst_nummer=(SELECT max(boodschappenlijst_nummer) FROM boodschappenlijst)";
				PreparedStatement statement = conn.prepareStatement(query);
				ResultSet result = statement.executeQuery();
				while(result.next()){
					number = result.getInt("boodschappenlijst_nummer");
					return number;

				}
			}catch(SQLException se){
					se.printStackTrace();
				}
				return 0;

		}


		
//	    private boolean contains(BoodschappenLijstItem BoodschappenLijstItem){
//	        for (BoodschappenLijstItem c : findAll()){
//	            if (c.equals(BoodschappenLijstItem))
//	                return true;
//	        }
//	        return false;
//	    }
//	    
	
	
	}
