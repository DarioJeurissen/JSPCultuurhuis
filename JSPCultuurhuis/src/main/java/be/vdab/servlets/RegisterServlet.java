package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.entities.Klant;
import be.vdab.repositories.KlantRepository;

@WebServlet("/register.htm")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/register.jsp";
	private final String REDIRECT_URL = "%s/bevestigen.htm";
	private final static KlantRepository klantRepository = new KlantRepository();

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
		String voornaam = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String street = request.getParameter("street");
		String number = request.getParameter("number");
		String postal = request.getParameter("postal");
		String city = request.getParameter("city");
		String username = request.getParameter("username");
		String pw1 = request.getParameter("password1");
		String pw2 = request.getParameter("password2");
		List<String> errors = new ArrayList<String>();
		
		if(voornaam.isEmpty()){
			errors.add("Voornaam niet ingevuld!");
		}
		if(lastname.isEmpty()){
			errors.add("Familienaam niet ingevuld!");
		}
		if(street.isEmpty()){
			errors.add("Straat niet ingevuld!");
		}
		if(number.isEmpty()){
			errors.add("Huisnr. niet ingevuld!");
		}
		if(postal.isEmpty()){
			errors.add("Postcode niet ingevuld!");
		}
		if(city.isEmpty()){
			errors.add("Gemeente niet ingevuld!");
		}
		if(username.isEmpty()){
			errors.add("Gebruikersnaam niet ingevuld!");
		}
		if(pw1.isEmpty()){
			errors.add("Passwoord niet ingevuld!");
		}
		if(!pw1.equals(pw2)){
			errors.add("Passwoorden zijn niet identiek!");
		}
		if(errors.isEmpty()){
			klantRepository.insertKlantInDB(new Klant(3, voornaam, lastname, street, number, postal, city, username, pw1));
			
			response.sendRedirect(response.encodeRedirectURL(
					String.format(REDIRECT_URL, request.getContextPath())));
		}
		else{
			request.setAttribute("errors", errors);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}	
}