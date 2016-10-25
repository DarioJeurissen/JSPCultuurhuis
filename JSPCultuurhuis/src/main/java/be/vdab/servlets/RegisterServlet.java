package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register.htm")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/register.jsp";
	private final String REDIRECT_URL = "%s/bevestigen.htm";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pw1 = request.getParameter("password1");
		String pw2 = request.getParameter("password2");
		List<String> errors = new ArrayList<String>();
		
		if(request.getParameter("firstname").isEmpty()){
			errors.add("Voornaam niet ingevuld!");
		}
		if(request.getParameter("lastname").isEmpty()){
			errors.add("Familienaam niet ingevuld!");
		}
		if(request.getParameter("street").isEmpty()){
			errors.add("Straat niet ingevuld!");
		}
		if(request.getParameter("number").isEmpty()){
			errors.add("Huisnr. niet ingevuld!");
		}
		if(request.getParameter("postal").isEmpty()){
			errors.add("Postcode niet ingevuld!");
		}
		if(request.getParameter("city").isEmpty()){
			errors.add("Gemeente niet ingevuld!");
		}
		if(request.getParameter("username").isEmpty()){
			errors.add("Gebruikersnaam niet ingevuld!");
		}
		if(pw1.isEmpty()){
			errors.add("Passwoord niet ingevuld!");
		}
		if(!pw1.equals(pw2)){
			errors.add("Passwoorden zijn niet identiek!");
		}
		if(errors.isEmpty()){
			// TODO: put in DB
			
			response.sendRedirect(response.encodeRedirectURL(
					String.format(REDIRECT_URL, request.getContextPath())));
		}
		else{
			request.setAttribute("errors", errors);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}	
}