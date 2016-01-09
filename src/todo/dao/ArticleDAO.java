package todo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import todo.model.Article;

public class ArticleDAO {
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
	
	public int addArticle(Article article) throws SQLException{
		String sql = "insert into Task values(?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		int generatedkey=0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);  
			pstmt.setNull(1, java.sql.Types.NULL);
			pstmt.setString(2, article.getContent());
			pstmt.setString(3, article.getDoneDate()); 
			pstmt.setBoolean(4, article.getIsDone()); 
			pstmt.setInt(5, article.getBoardId()); 
			pstmt.executeUpdate();  
			
			ResultSet rs = pstmt.getGeneratedKeys();;
			
			if(rs.next()) {
				generatedkey=rs.getInt(1);   
	            System.out.println("Auto Generated Primary Key " + generatedkey); 
	            
			}
			return generatedkey;
			
		} finally {
			if(pstmt != null){
				pstmt.close();
			}
			if(conn != null){
				conn.close();
			}
		}
	}
	
	public List<Article> findByBoardId(int boardId) throws SQLException {
		String sql = "select t.*, c.color from Task t inner join Task_Category tc on tc.tId=t.Id inner join Category c on c.Id=tc.cId where t.lId=? order by Id";
		
		PreparedStatement pstmt = getConnection().prepareStatement(sql);  
		pstmt.setInt(1, boardId);
		
		ResultSet rs = pstmt.executeQuery();
		
		List<Article> list = new ArrayList<Article>();
		while(rs.next()){
			list.add(new Article(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), boardId, rs.getString(6)));
		}
		
		return list;
	}
	
	public List<Article> findByBoardIdAndColor(int boardId, String color) throws SQLException {
		String sql = "select t.*, c.color from Task t inner join Task_Category tc on tc.tId=t.Id inner join Category c on c.Id=tc.cId where t.lId=? and c.color=? order by Id";
		
		PreparedStatement pstmt = getConnection().prepareStatement(sql);  
		pstmt.setInt(1, boardId);
		pstmt.setString(2, color);
		
		ResultSet rs = pstmt.executeQuery();
		
		List<Article> list = new ArrayList<Article>();
		while(rs.next()){
			list.add(new Article(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), boardId, rs.getString(6)));
		}
		
		return list;
	}
	
	public void deleteArticleByArticleId(int articleId) throws SQLException {
		String sql = "delete from Task where Id=?";
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
	
	public void completeArticleByArticleId(int articleId) throws SQLException {
		String sql = "update Task set isDone=1 where Id=?";
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
