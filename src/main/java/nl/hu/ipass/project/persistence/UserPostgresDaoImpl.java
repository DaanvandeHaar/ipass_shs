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

import nl.hu.ipass.project.model.User;

public class UserPostgresDaoImpl extends PostgresBaseDao implements UserDao {
	
	public UserPostgresDaoImpl() {
		getConnection();
	}
	
	@Override
	public String findRoleForUser(String name, String pass) {
		String role = "";
		try (Connection conn = super.getConnection()){
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("select * from Gebruiker");
			while (result.next()) {
				if (name.equals(result.getString("gebruikersnaam")) && pass.equals(result.getString("wachtwoord"))) {
					role = result.getString("rol");
					return role;
				}
			}
			result.close();
			statement.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return null;
	}
//	public int findNumberForUser(String name, String pass) {
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
	    public boolean save(User user) {
	    	System.out.println("save");
	    	boolean saved = false;
	        try (Connection conn = super.getConnection()){
	            String query = "INSERT INTO gebruiker(voornaam,achternaam,email,leeftijd,rol,gebruikersnaam,wachtwoord, wachtwoord_hash) VALUES(?, ?, ?, ?, ?, ? , ?, ?)";
	            PreparedStatement statement = conn.prepareStatement(query);
	            statement.setString(1, user.getVoornaam());
	            statement.setString(2, user.getAchternaam());
	            statement.setString(3, user.getEmail());
	            statement.setInt(4, user.getLeeftijd());
	            statement.setString(5, user.getRol());
	            statement.setString(6, user.getGebruikersnaam());
	            statement.setString(7, user.getWachtwoord());
	            statement.setString(8, user.getWachtwoordHash());
	            saved = statement.executeUpdate() == 1;
	            statement.close();
	            return saved;
	        } catch (SQLException se){
	            se.printStackTrace();
	        }
	        return true;
	    }

		@Override
		public List<User> findAll() {
			ArrayList<User> users = new ArrayList<>();
			try (Connection conn = super.getConnection()){
				String query = "SELECT * FROM gebruiker";
				Statement statement = conn.createStatement();
				ResultSet result = statement.executeQuery(query);
				while (result.next()) {
					User user = new User(
					result.getInt("gebruiker_nummer"), 
					result.getString("voornaam"), 
					result.getString("achternaam"),
					result.getString("email"), 
					result.getInt("leeftijd"), 
					result.getString("rol"), 
					result.getString("wachtwoord_hash"),
					result.getString("gebruikersnaam"), 
					result.getString("wachtwoord"));
					users.add(user);
				}
				result.close();
				statement.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			return users;
		}

		@Override
		public User findByNumber(int number) {
			User user = null;
			try (Connection conn = super.getConnection()){
				String query = "SELECT * FROM gebruiker WHERE gebruiker_nummer = ?";
				PreparedStatement statement = conn.prepareStatement(query);
				statement.setInt(1, number);
				ResultSet result = statement.executeQuery();
				while (result.next()) {
					user = new User(
							result.getInt("gebruiker_nummer"), 
							result.getString("voornaam"), 
							result.getString("achternaam"),
							result.getString("email"), 
							result.getInt("leeftijd"), 
							result.getString("rol"), 
							result.getString("wachtwoord_hash"),
							result.getString("gebruikersnaam"), 
							result.getString("wachtwoord"));
				}
				result.close();
				statement.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			return user;
		}
		public User findByUserName(String name) {
			User user = null;
			try (Connection conn = super.getConnection()){
				String query = "SELECT * FROM gebruiker WHERE gebruikersnaam = ?";
				PreparedStatement statement = conn.prepareStatement(query);
				statement.setString(1, name);
				System.out.println("GET Request mijn info in dao ");
				ResultSet result = statement.executeQuery();
				while (result.next()) {
				user = new User(
						result.getInt("gebruiker_nummer"), 
						result.getString("voornaam"), 
						result.getString("achternaam"),
						result.getString("email"), 
						result.getInt("leeftijd"), 
						result.getString("rol"), 
						result.getString("wachtwoord_hash"),
						result.getString("gebruikersnaam"), 
						result.getString("wachtwoord"));
				}
				result.close();
				statement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("GET Request mijn info in dao terugsturen vanaf dao.");
			return user;
		}





		@Override
		public boolean update(User user){
			boolean updated = false;
			try (Connection conn = super.getConnection()){
				String query = "UPDATE gebruiker SET voornaam = ? ,achternaam = ? ,email = ? ,leeftijd = ? ,rol = ? ,gebruikersnaam = ? ,wachtwoord = ? WHERE gebruiker_nummer = ?";
				PreparedStatement statement = conn.prepareStatement(query);
				statement.setString(1, user.getVoornaam());
				statement.setString(2, user.getAchternaam());
				statement.setString(3, user.getEmail());
				statement.setInt(4, user.getLeeftijd());
				statement.setString(5, user.getRol());
				statement.setString(6, user.getGebruikersnaam());
				statement.setString(7, user.getWachtwoord());
				statement.setInt(8, user.getGebruikerNummer());
				updated = statement.executeUpdate() == 1;
				statement.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			return updated;
		}

		@Override
		public boolean delete(User user){
			try (Connection conn = super.getConnection()){
				String query = "DELETE FROM Gebruiker WHERE gebruiker_nummer = ?";
				PreparedStatement statement = conn.prepareStatement(query);
				statement.setInt(1, user.getGebruikerNummer());
				statement.executeUpdate();
				statement.close();
				return true;
			} catch (SQLException se) {
				se.printStackTrace();
			}
			return false;
		}
		
//	    private boolean contains(User User){
//	        for (User c : findAll()){
//	            if (c.equals(User))
//	                return true;
//	        }
//	        return false;
//	    }
//	    
	
	
	}
