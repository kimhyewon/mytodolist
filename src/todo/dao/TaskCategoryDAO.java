package todo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import todo.model.Board;
import todo.model.TaskCategory;

public class TaskCategoryDAO {
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

	public void addTaskCategory(TaskCategory taskCategory) throws SQLException {
		String sql = "insert into Task_Category values(?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);  
			pstmt.setInt(1, taskCategory.getArticleId());
			pstmt.setInt(2, taskCategory.getCategoryId()); 
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
	
	public String getColorByCategoryId(int categoryId1, int categoryId2) throws SQLException{
		String sql = "SELECT c.color FROM Category As c INNER JOIN Task_Category AS t WHERE ? = ?";
		PreparedStatement pstmt = getConnection().prepareStatement(sql);  
		pstmt.setInt(1, categoryId1);
		pstmt.setInt(2, categoryId2);
		ResultSet rs = pstmt.executeQuery();
		
		if(!rs.next()){
			return null;
		}
		
		return rs.getString(1);
	}

	public List<TaskCategory> findByArticleId(Integer articleId) throws SQLException {
		String sql = "SELECT * FROM Task_Category as t INNER JOIN Task as t WHERE articleId = ?";
		
		PreparedStatement pstmt = getConnection().prepareStatement(sql);  
		pstmt.setInt(1, articleId);
		
		ResultSet rs = pstmt.executeQuery();
		
		List<TaskCategory> list = new ArrayList<TaskCategory>();
		while(rs.next()){
			list.add(new TaskCategory(articleId, rs.getInt(2)));
		}
		
		return list;
	}

	public void deleteTaskCategoryByArticleId(int articleId) throws SQLException {
		String sql = "delete from Task_Category where tId=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);  
			pstmt.setInt(1, articleId);
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
