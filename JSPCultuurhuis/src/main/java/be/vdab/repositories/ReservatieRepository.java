package be.vdab.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import be.vdab.entities.Klant;
import be.vdab.entities.Voorstelling;

public class ReservatieRepository extends AbstractRepository {
	private final static String FIND_VOORSTELLING_BY_ID = "select * from voorstellingen WHERE id= ?";
	private final static String UPDATE_VOORSTELLING_SQL = "UPDATE voorstellingen SET vrijeplaatsen=vrijeplaatsen- ? WHERE id=?;";
    private static final String INSERT_RESERVATION_SQL = "INSERT INTO reservaties(klantid, voorstellingsid, plaatsen) values (?, ?, ?)";
	
    /**
     * This method will process a reservation and returns true or false whether if was successful or not.
     * @param f 
     * @param klant
     * @return
     * @throws SQLException 
     */
	public void processReservation(long voorstellingID, Klant klant, long plaatsen) throws SQLException {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statementSelect = connection.prepareStatement(FIND_VOORSTELLING_BY_ID);
				PreparedStatement statementUpdate = connection.prepareStatement(UPDATE_VOORSTELLING_SQL);
                PreparedStatement statementInsert = connection.prepareStatement(INSERT_RESERVATION_SQL)) { 
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            
            Voorstelling v = null;
            statementSelect.setLong(1, voorstellingID);
            ResultSet resultSet = statementSelect.executeQuery();
            if(resultSet.next()){
            	v = GenreRepository.voorstellingFromResultSet(resultSet);
            }
            if(v != null && (v.getVrijeplaatsen()-plaatsen > 0)){
	            statementUpdate.setLong(1, plaatsen);
	            statementUpdate.setLong(2, v.getId());
	            statementInsert.setLong(1, klant.getId());
	            statementInsert.setLong(2, v.getId());
	            statementInsert.setLong(3, plaatsen);
	            
	            int updatedMovie = statementUpdate.executeUpdate();
	            int insertedReservation = statementInsert.executeUpdate();
	            if(updatedMovie == 1 && insertedReservation == 1){
	            	connection.commit();
	            }
	            else{
	            	throw new SQLException("Could update the voorstelling or instert the reservation!");
	            }
            }
            else{
            	throw new SQLException("Didn't find the voorstelling");
            }
		} 
		catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}
}
