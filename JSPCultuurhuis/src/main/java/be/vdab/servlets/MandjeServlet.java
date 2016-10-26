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
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import be.vdab.entities.Mandje;
import be.vdab.entities.Voorstelling;
import be.vdab.repositories.VoorstellingRepository;

@WebServlet("/mandje.htm")
public class MandjeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/mandje.jsp";
	private final VoorstellingRepository voorstellingRepository = new VoorstellingRepository();

	@Resource(name = VoorstellingRepository.JNDI_NAME)
	public void setDataSource(DataSource dataSource) {
		voorstellingRepository.setDataSource(dataSource);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("mandje") != null){
			Mandje m = (Mandje) session.getAttribute("mandje");
			List<Voorstelling> voorstellingen = new ArrayList<Voorstelling>();
			for(long l : m.getVoorstellingen()){
				voorstellingen.add(voorstellingRepository.findVoorstellingByID(l));
			}
			request.setAttribute("voorstellingen", voorstellingen);
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("submit") != null && request.getParameter("submit").equals("verwijderen") && request.getParameter("voorstellingen") != null) {
			Mandje m = (Mandje) request.getSession().getAttribute("mandje");
			String check[] = request.getParameterValues("voorstellingen");
			if(request.getParameterValues("voorstellingen") != null){
				for(String s : check){
					System.out.println(s);
					m.remove(Long.parseLong(s));
				}
				request.getSession().setAttribute("mandje", m);
			}
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}	
}