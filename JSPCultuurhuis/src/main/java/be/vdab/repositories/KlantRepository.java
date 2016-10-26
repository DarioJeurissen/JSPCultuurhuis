package be.vdab.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import be.vdab.entities.Klant;

/**
 *
 * @author Dario.Jeurissen
 *
 */

public class KlantRepository extends AbstractRepository {
	private final static String FIND_KLANTEN_BY_USER = "select * from klanten WHERE gebruikersnaam= ?";
    private static final String INSERT_KLANT_SQL = "INSERT INTO klanten(voornaam, familienaam, straat, huisnr, postcode, gemeente, gebruikersnaam, paswoord) values (?, ?, ?, ?, ?, ?, ?, ?)";
		
	public Klant findKlantByUser(String id) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_KLANTEN_BY_USER)) {
			statement.setString(1, id);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				return klantFromResultSet(resultSet);
			}
			
		} catch (SQLException ex) {
			throw new RepositoryException(ex);
		}
		return null; 
	}
	
	public void insertKlantInDB(Klant k){
		try (Connection connection = dataSource.getConnection();
                PreparedStatement statementInsert = connection.prepareStatement(INSERT_KLANT_SQL)) { 
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
	            statementInsert.setString(1, k.getVoornaam());
	            statementInsert.setString(2, k.getFamilienaam());
	            statementInsert.setString(3, k.getStraat());
	            statementInsert.setString(4, k.getNummer());
	            statementInsert.setString(5, k.getPostcode());
	            statementInsert.setString(6, k.getGemeente());
	            statementInsert.setString(7, k.getGebruikersnaam());
	            statementInsert.setString(8, k.getPassword());
	            
	            statementInsert.executeUpdate();
            	connection.commit();
     
		} 
		catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}

	public static Klant klantFromResultSet(ResultSet resultSet) throws SQLException{
		return new Klant(resultSet.getLong("id"),
						resultSet.getString("voornaam"),
						resultSet.getString("familienaam"),
						resultSet.getString("straat"),
						resultSet.getString("huisnr"),
						resultSet.getString("postcode"),
						resultSet.getString("gemeente"),
						resultSet.getString("gebruikersnaam"),
						resultSet.getString("paswoord"));
	}
}
