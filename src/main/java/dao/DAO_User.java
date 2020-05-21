package dao;

import model.User;

public interface DAO_User {
	
	public User loginUser(String email, String password);

}
