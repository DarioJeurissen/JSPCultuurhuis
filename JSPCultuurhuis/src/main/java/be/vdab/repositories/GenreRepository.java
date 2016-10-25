package be.vdab.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import be.vdab.entities.Genre;
import be.vdab.entities.Voorstelling;

public class GenreRepository extends AbstractRepository {
	private final static String FIND_NAMES = "select id, naam from genres order by id asc";
	private final static String FIND_VOORSTELLINGEN_BY_GENRE = "select * from voorstellingen WHERE genreid= ? order by id asc";

	/**
	 * Find all genres
	 * @return all genres
	 */
	public List<Genre> findAll() {
		try (Connection connection = dataSource.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(FIND_NAMES)) {
			List<Genre> entries = new ArrayList<>();
			while (resultSet.next()) {
				entries.add(new Genre(resultSet.getLong("id"), resultSet.getString("naam")));
			}
			return entries;
		} catch (SQLException ex) {
			throw new RepositoryException(ex);
		}
	}
	/**
	 * Find all the genres with genreid id
	 * @param id
	 * @return all genres with genreid id
	 */
	public List<Voorstelling> findAllByGenreID(long id) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_VOORSTELLINGEN_BY_GENRE)) {
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			List<Voorstelling> entries = new ArrayList<>();
			while (resultSet.next()) {
				entries.add(voorstellingFromResultSet(resultSet));
			}
			return entries;
			
		} catch (SQLException ex) {
			throw new RepositoryException(ex);
		}
	}	
	/**
	 * Returns a genre from the given resultset
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	public static Voorstelling voorstellingFromResultSet(ResultSet resultSet) throws SQLException{
		return new Voorstelling(resultSet.getLong("id"),
						resultSet.getString("titel"),
						resultSet.getString("uitvoerders"),
						resultSet.getDate("datum"),
						resultSet.getLong("genreid"), 
						resultSet.getBigDecimal("prijs"),
						resultSet.getLong("vrijeplaatsen"));
	}
}
