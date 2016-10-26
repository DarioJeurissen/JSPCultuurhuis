package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import be.vdab.entities.Klant;
import be.vdab.repositories.KlantRepository;

@WebServlet("/bevestigen.htm")
public class BevestigenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/bevestigen.jsp";
	private static final String REDIRECT_URL = "%s/done.htm";
	private final transient KlantRepository klantRepository = new KlantRepository();
	
	@Resource(name = KlantRepository.JNDI_NAME)
	public void setDataSource(DataSource dataSource) {
		klantRepository.setDataSource(dataSource);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getParameter("login") != null && request.getParameter("login").equals("Login")){
			ArrayList<String> errors = new ArrayList<String>();
			Klant k = klantRepository.findKlantByUser(request.getParameter("username"));
			if(k == null){
				errors.add("Gebruikersnaam niet gevonden!");
			}
			if(!k.getPassword().equals(request.getParameter("password"))){
				errors.add("Wachtwoord onjuist!");
			}
			if(errors.isEmpty()){
				request.setAttribute("klant", k);
				request.getRequestDispatcher(VIEW).forward(request, response);
			}
			else{
				request.setAttribute("errors", errors);
				request.getRequestDispatcher(VIEW).forward(request, response);
			}
		}
		else if(request.getParameter("confirm") != null && request.getParameter("confirm").equals("Bevestigen")){
			HttpSession session = request.getSession();
			String username = request.getParameter("aKlant");
			if(username != null){
				Klant klant = klantRepository.findKlantByUser(username);
				if(klant != null){
					session.setAttribute("klant", klant);
				}
			}
			response.sendRedirect(response.encodeRedirectURL(
					String.format(REDIRECT_URL, request.getContextPath())));
		}
	}	
}