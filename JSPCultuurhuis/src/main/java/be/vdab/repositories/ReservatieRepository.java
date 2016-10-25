package be.vdab.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import be.vdab.entities.Klant;
import be.vdab.entities.Voorstelling;

public class ReservatieRepository extends AbstractRepository {
	private final static String UPDATE_MOVIE_SQL = "UPDATE FILMS SET gereserveerd=gereserveerd+1 WHERE id=?;";
    private static final String INSERT_RESERVATION_SQL = "INSERT INTO reservaties(klantid, filmid, reservatieDatum) values (?, ?, ?)";
    private static final Date date = new Date();
	
    /**
     * This method will process a reservation and returns true or false whether if was successful or not.
     * @param f 
     * @param klant
     * @return
     */
	public boolean processReservation(Voorstelling voorstelling, Klant klant) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statementUpdate = connection.prepareStatement(UPDATE_MOVIE_SQL);
                PreparedStatement statementInsert = connection.prepareStatement(INSERT_RESERVATION_SQL)) { 
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            // TODO: get from db instead
            if(voorstelling.getVrijeplaatsen() > 0){
	            statementUpdate.setLong(1, voorstelling.getId());
	            statementInsert.setLong(1, klant.getId());
	            statementInsert.setLong(2, voorstelling.getId());
	            statementInsert.setObject(3, new Timestamp(date.getTime()));
	            
	            int updatedMovie = statementUpdate.executeUpdate();
	            int insertedReservation = statementInsert.executeUpdate();
	            if(updatedMovie == 1 && insertedReservation == 1){
	            	connection.commit();
	            	return true;
	            }
            }
		} 
		catch (SQLException ex) {
			return false;
		}
		return false;
	}
}
