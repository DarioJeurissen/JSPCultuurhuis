package be.vdab.servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.repositories.GenreRepository;

@WebServlet("/index.htm")
public class GenreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/index.jsp";
	private final GenreRepository genreRepository = new GenreRepository();

	@Resource(name = GenreRepository.JNDI_NAME)
	public void setDataSource(DataSource dataSource) {
		genreRepository.setDataSource(dataSource);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("genres", genreRepository.findAll());
		
		if(request.getParameter("id") != null){
			long id = Long.parseLong(request.getParameter("id"));
			request.setAttribute("voorstellingen", genreRepository.findAllByGenreID(id));
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
}