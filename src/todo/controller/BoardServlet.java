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

import todo.dao.BoardDAO;
import todo.model.Board;

@WebServlet("/board")
public class BoardServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		BoardDAO boardDao = new BoardDAO();
		
		HttpSession session = req.getSession(); 
		String userId = (String) session.getAttribute("userId");
		
		List<Board> boards;
		try {
			boards = boardDao.findByUserId(userId);
			req.setAttribute("boards", boards);
		} catch (SQLException e) {
			
		}
		req.getRequestDispatcher("/board.jsp").forward(req, resp);		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		
		String listName = request.getParameter("listName");
		
		Board board = new Board(listName, userId);

		BoardDAO boardDao = new BoardDAO();
		try {
			boardDao.addList(board, userId);
		} catch (SQLException e) {
			
		}
		response.sendRedirect("/board");
	}
}
