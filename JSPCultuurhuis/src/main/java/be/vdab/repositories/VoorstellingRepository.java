package be.vdab.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import be.vdab.entities.Voorstelling;

public class VoorstellingRepository extends AbstractRepository {
	private final static String FIND_VOORSTELLING_BY_ID = "select * from voorstellingen WHERE id= ?";
		
	public Voorstelling findVoorstellingByID(long id) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_VOORSTELLING_BY_ID)) {
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				return GenreRepository.voorstellingFromResultSet(resultSet);
			}
			
		} catch (SQLException ex) {
			throw new RepositoryException(ex);
		}
		return null;
	}
}
