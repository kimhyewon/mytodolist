package todo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import todo.model.User;

public class UserDAO {
	public Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/MYTODOLIST";
		String id = "root";
		String pw = "1234";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");

			return DriverManager.getConnection(url,id,pw);
		} catch (Exception e) {
			return null;
		}
	}

	public void addUser(User user) throws SQLException {
		String sql = "insert into USER values(?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);  
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getPassword());
			pstmt.executeUpdate();  
		} finally {
			if(pstmt != null){
				pstmt.close();
			}
			if(conn != null){
				conn.close();
			}
		}
	}

	
	
	public User findByUserId(String userId) throws SQLException {
		String sql = "select * from USER where userId = ?";
		PreparedStatement pstmt = getConnection().prepareStatement(sql);  
		pstmt.setString(1,  userId);
		
		ResultSet rs = pstmt.executeQuery();
		
		if(!rs.next()){
			return null;
		}
		
		return new User(
			rs.getString("userId"),
			rs.getString("password"));
	}

	
	public void removeUser(String userId) throws SQLException {
		String sql = "delete from USER where userId = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);  
			pstmt.setString(1, userId);
			
			pstmt.executeUpdate();  
		} finally {
			if(pstmt != null){
				pstmt.close();
			}
			if(conn != null){
				conn.close();
			}
		} 
	}
	
	public void updateUser(User user) throws SQLException {
		String sql = "update USER set password = ?, where userId = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);  
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getPassword());
			pstmt.executeUpdate();  
			
		} finally {
			if(pstmt != null){
				pstmt.close();
			}
			if(conn != null){
				conn.close();
			}
		} 
	}

}
