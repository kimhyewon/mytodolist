package todo.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.dao.UserDAO;
import todo.model.User;

@WebServlet("/user/create")
public class CreateUserServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		User user = new User(userId, password);
		
		UserDAO userDao = new UserDAO();
		try {
			userDao.addUser(user);
		} catch (SQLException e) {
			
		}
		response.sendRedirect("/");
	}

}
