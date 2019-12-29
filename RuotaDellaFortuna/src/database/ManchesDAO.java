package database;

import java.sql.SQLException;

public interface ManchesDAO {
	String ManchesTable = "manches";
	String ManchesNumberAttribute = "number";
	String ManchesIdAttribute = "id";
	String ManchesPhraseAttribute = "phrase";

	/**
	 * Metodo per aggiungere una manche al db
	 * 
	 * @param manche manche che si vuole aggiungere
	 * @return <code>true</code> se l'inserimento è andato a buon fine, altrimente
	 *         <code>false</code>
	 * @throws SQLException gestione mancata connessione al db
	 */
	boolean addManche(ManchesDTO manche) throws SQLException;
}
