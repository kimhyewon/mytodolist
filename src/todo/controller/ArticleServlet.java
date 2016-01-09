package todo.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import todo.dao.ArticleDAO;
import todo.dao.BoardDAO;
import todo.dao.CategoryDAO;
import todo.dao.TaskCategoryDAO;
import todo.model.Article;
import todo.model.Category;
import todo.model.TaskCategory;

@WebServlet("/article")
public class ArticleServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		BoardDAO boardDao = new BoardDAO();
		ArticleDAO articleDao = new ArticleDAO();
		CategoryDAO catagoryDao = new CategoryDAO();
		TaskCategoryDAO taskCategoryDao = new TaskCategoryDAO();
		
		int boardId = Integer.parseInt(req.getParameter("boardId"));
		
		HttpSession session = req.getSession(); 
		String sessionUserId = (String) session.getAttribute("userId");
		
		String userId = null;
		try {
			userId = boardDao.findUserIdByBoardId(boardId);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		if(!sessionUserId.equals(userId)) {
			resp.sendRedirect("/login.jsp");
			return;
		}
		
		List<Category> categorys = null;
		try {
			categorys = catagoryDao.findCategoryByBoardId(boardId);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		List<Article> articles;
		List<Article> articles2;
		try {
//			여기부터 
			String color = req.getParameter("color");
			
			if(color.equals("all")) {
				articles = articleDao.findByBoardId(boardId);
				req.setAttribute("articles", articles);
			} 
			else {
				articles2 = articleDao.findByBoardIdAndColor(boardId, color);
				req.setAttribute("articles", articles2);
			}
//			여기까지 
			
			String boardName = boardDao.findBoardNameByBoardId(boardId);
			
			req.setAttribute("boardId", boardId);
			req.setAttribute("boardName", boardName);
			req.setAttribute("categorys", categorys);
		} catch (SQLException e) {
			System.out.println("error");
		}
				
		req.getRequestDispatcher("/showTasks.jsp").forward(req, resp);	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String content = req.getParameter("content");
		String doneDate = req.getParameter("doneDate");
		boolean isdone = Boolean.valueOf(req.getParameter("isdone"));
		int boardId = Integer.parseInt(req.getParameter("boardId"));
		
		Article article = new Article(content, doneDate, isdone, boardId);
		ArticleDAO articleDao = new ArticleDAO();
		int articleId = 0;
		try {
			articleId = articleDao.addArticle(article);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String [] categoryId = req.getParameterValues("categoryId");
		
		for(int i=0; i<categoryId.length; i++) {
			TaskCategory taskCategory = new TaskCategory(articleId, Integer.parseInt(categoryId[i]));
			TaskCategoryDAO taskCategoryDao = new TaskCategoryDAO();
			try {
				taskCategoryDao.addTaskCategory(taskCategory);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		resp.sendRedirect("/article?boardId="+boardId+"&color=all");
	}
}
