package be.vdab.servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import be.vdab.entities.Mandje;
import be.vdab.entities.Voorstelling;
import be.vdab.repositories.VoorstellingRepository;

/**
 *
 * @author Dario.Jeurissen
 *
 */
@WebServlet("/voorstelling.htm")
public class VoorstellingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/voorstelling.jsp";
	private final VoorstellingRepository voorstellingRepository = new VoorstellingRepository();
	private static final String REDIRECT_URL = "%s/mandje.htm";

	@Resource(name = VoorstellingRepository.JNDI_NAME)
	public void setDataSource(DataSource dataSource) {
		voorstellingRepository.setDataSource(dataSource);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("id") != null) {
			long id = Long.parseLong((String) request.getParameter("id"));
			request.setAttribute("voorstelling", voorstellingRepository.findVoorstellingByID(id));
			if(request.getSession().getAttribute("mandje") != null){
				Mandje m = (Mandje) request.getSession().getAttribute("mandje");
				if (m.getMandje().containsKey(id)) {
					request.setAttribute("plaatsen", m.getPlaatsenReserveerd(id));
				}
			}

		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (request.getParameter("id") != null) {
			long id = Long.parseLong(request.getParameter("id"));
			Voorstelling voorstelling = voorstellingRepository.findVoorstellingByID(id);
			if (request.getParameter("plaatsen") != null) {
				int plaatsen = Integer.parseInt(request.getParameter("plaatsen"));
				if (plaatsen < voorstelling.getVrijeplaatsen() && plaatsen > 0) {
					if (session.getAttribute("mandje") == null) {
						session.setAttribute("mandje", new Mandje());
						System.out.println("nieuw mandje aangemaakt");
					}
					Mandje mandje = (Mandje) session.getAttribute("mandje");
					mandje.add(id, plaatsen);
					session.setAttribute("mandje", mandje);
					System.out.println("Mandje geSet");
					System.out.println("responseEncode");
					response.sendRedirect(response.encodeRedirectURL(
							String.format(REDIRECT_URL, request.getContextPath())));
				} 
				else {
					request.setAttribute("error", "Tik een getal tussen 1 en " + voorstelling.getVrijeplaatsen());
					request.setAttribute("voorstelling", voorstelling);
					request.getRequestDispatcher(VIEW).forward(request, response);
				}
			} 
			else {
				request.setAttribute("error", "Tik een getal tussen 1 en " + voorstelling.getVrijeplaatsen());
				request.setAttribute("voorstelling", voorstelling);
				request.getRequestDispatcher(VIEW).forward(request, response);
			}
		} else {
			System.out.println("id/plaatsen is null");
		}

	}
}