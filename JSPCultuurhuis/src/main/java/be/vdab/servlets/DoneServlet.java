package be.vdab.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import be.vdab.entities.Klant;
import be.vdab.entities.Mandje;
import be.vdab.entities.Voorstelling;
import be.vdab.repositories.ReservatieRepository;
import be.vdab.repositories.VoorstellingRepository;

@WebServlet("/done.htm")
public class DoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/done.jsp";
	private final transient ReservatieRepository reservatieRepository = new ReservatieRepository();
	private final transient VoorstellingRepository voorstellingRepository = new VoorstellingRepository();

	@Resource(name = ReservatieRepository.JNDI_NAME)
	public void setDataSource(DataSource dataSource) {
		reservatieRepository.setDataSource(dataSource);
		voorstellingRepository.setDataSource(dataSource);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Map<Voorstelling, Long> success = new HashMap<Voorstelling, Long>();
		Map<Voorstelling, Long> errors = new HashMap<Voorstelling, Long>();
		if(session.getAttribute("mandje") != null){
			Mandje m = (Mandje) session.getAttribute("mandje");
			if(session.getAttribute("klant") != null){
				Klant klant = (Klant) session.getAttribute("klant");
				m.getMandje().forEach((k, v)->{
					try {
						reservatieRepository.processReservation(k, klant, v);
						success.put(voorstellingRepository.findVoorstellingByID(k), v);
					} catch (SQLException e) {
						errors.put(voorstellingRepository.findVoorstellingByID(k), v);
					}
				});
			}
			session.setAttribute("mandje", null);
			session.setAttribute("klant", null);
		}
		request.setAttribute("success", success);
		request.setAttribute("errors", errors);
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
}