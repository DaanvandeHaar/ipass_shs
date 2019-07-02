package nl.hu.ipass.project.persistence;

import java.util.List;

import nl.hu.ipass.project.model.User;

public interface UserDao {
	
	String findRoleForUser(String name, String pass);
	boolean save(User User);
	List<User> findAll();
	public User findByNumber(int number);
	public User findByUserName(String name);
    boolean update(User user);
    boolean delete(User user);

}