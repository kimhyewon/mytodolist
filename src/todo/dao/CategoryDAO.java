package todo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import todo.model.Board;
import todo.model.Category;

public class CategoryDAO {
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

	public void addCategory(Category category) throws SQLException {
		String sql = "insert into Category values(?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);  
			pstmt.setNull(1, java.sql.Types.NULL);
			pstmt.setString(2, category.getCategoryName());
			pstmt.setString(3, category.getColor()); 
			pstmt.setInt(4, category.getBoardId()); 
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

	public List<Category> findCategoryByBoardId(int boardId) throws SQLException {
		String sql = "SELECT * FROM Category WHERE lId = ?";
		
		PreparedStatement pstmt = getConnection().prepareStatement(sql);  
		pstmt.setInt(1, boardId);
		
		ResultSet rs = pstmt.executeQuery();
		
		List<Category> list = new ArrayList<Category>();
		while(rs.next()){
			list.add(new Category(rs.getInt(1), rs.getString(2), rs.getString(3), boardId));
		}
		
		return list;
	}

}
