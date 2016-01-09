package todo.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.dao.CategoryDAO;
import todo.model.Category;

@WebServlet("/category")
public class CategoryServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String categoryName = req.getParameter("categoryName");
		String color = req.getParameter("color");
		int boardId = Integer.parseInt(req.getParameter("boardId"));
		System.out.println(boardId);
		
		Category category = new Category(categoryName, color, boardId);
		CategoryDAO categoryDao = new CategoryDAO();
		
		try {
			categoryDao.addCategory(category);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resp.sendRedirect("/article?boardId="+boardId+"&color=all");
	}
}
