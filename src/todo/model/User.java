package todo.model;

import java.sql.SQLException;

import todo.dao.UserDAO;
import todo.exception.PasswordMismatchException;
import todo.exception.UserNotFoundException;

public class User {
	private String userId;
	private String password;
	 	
	public User(String userId, String password) {
		this.userId = userId;
		this.password = password;
	 }

	public String getUserId(){
		return userId;
	}

	public String getPassword() {
		return password;
	}
	
	public static boolean login(String userId, String password) throws UserNotFoundException, PasswordMismatchException {
		UserDAO userDao = new UserDAO();
		User user = null;
		try {
			user = userDao.findByUserId(userId);
		} catch (SQLException e) {
			
		}

		if(user == null){
			throw new UserNotFoundException();
		}
		if(!user.matchPassword(password)) {
			throw new PasswordMismatchException();
		}
		return true;
	}

	public boolean matchPassword(String newPassword) {
		return this.password.equals(newPassword);
	}

}
