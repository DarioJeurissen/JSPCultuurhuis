package be.vdab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/bevestigen.htm")
public class BevestigenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/bevestigen.jsp";
	private final String REDIRECT_URL = "%s/done.htm";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("TEST");
		System.out.println(request.getParameter("login"));
		System.out.println(request.getParameter("username"));
		System.out.println(request.getParameter("lastname"));
		//response.sendRedirect(String.format(REDIRECT_URL, request.getContextPath()));
	}	
}