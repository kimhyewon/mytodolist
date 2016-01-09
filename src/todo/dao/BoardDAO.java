package todo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import todo.model.Board;

public class BoardDAO {
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

	public void addList(Board board, String userId) throws SQLException {
		String sql = "insert into Todolist values(?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);  
			pstmt.setNull(1, java.sql.Types.NULL);
			pstmt.setString(2, board.getListName());
			pstmt.setString(3, userId); 
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
	
	public List<Board> findByUserId(String userId) throws SQLException {
		String sql = "SELECT * FROM Todolist WHERE userId = ?";
		
		PreparedStatement pstmt = getConnection().prepareStatement(sql);  
		pstmt.setString(1, userId);
		
		ResultSet rs = pstmt.executeQuery();
		
		List<Board> list = new ArrayList<Board>();
		while(rs.next()){
			list.add(new Board(rs.getInt(1), rs.getString(2), userId));
		}
		
		return list;
	}
	
	public String findBoardNameByBoardId(int boardId) throws SQLException {
		String sql = "SELECT Name FROM Todolist WHERE Id=?";
		PreparedStatement pstmt = getConnection().prepareStatement(sql);  
		pstmt.setInt(1, boardId);
		ResultSet rs = pstmt.executeQuery();
		
		if(!rs.next()){
			return null;
		}
		
		return rs.getString(1);
	}
	
	public String findUserIdByBoardId(int boardId) throws SQLException {
		String sql = "SELECT userId FROM Todolist WHERE Id=?";
		PreparedStatement pstmt = getConnection().prepareStatement(sql);  
		pstmt.setInt(1, boardId);
		ResultSet rs = pstmt.executeQuery();
		
		if(!rs.next()){
			return null;
		}
		
		return rs.getString(1);
	}
}
	
	