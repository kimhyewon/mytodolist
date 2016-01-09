package todo.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.dao.ArticleDAO;
import todo.dao.TaskCategoryDAO;

@WebServlet("/article/complete")
public class CompleteArticleServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int boardId = Integer.parseInt(req.getParameter("boardId"));
		int articleId = Integer.parseInt(req.getParameter("articleId"));
		
		ArticleDAO articleDao = new ArticleDAO();
		try {
			articleDao.completeArticleByArticleId(articleId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		resp.sendRedirect("/article?boardId="+boardId+"&color=all");
	}
}
