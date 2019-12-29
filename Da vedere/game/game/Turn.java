package game;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import database.DBManager;

public class Turn {

	private List<Move> moves;
	private Manche manche;

	public Turn(Manche manche) {
		moves = new ArrayList<>();
		this.manche = manche;
	}

	/**
	 * Metodo per salvare le mosse eseguite sul Database
	 * 
	 * @param dbManager contiene il gestore di database del sistema
	 * @return success ritorna il database aggiornato
	 */
	public boolean saveMoves(DBManager dbManager) {
		boolean success = true;
		for (Move move : moves) {
			if (success == true) {
				success = dbManager.addMove(move);
			} else {
				dbManager.addMove(move);
			}
		}
		for (int i = moves.size() - 1; i >= 0; i--)
			moves.remove(i);
		return success;
	}

	/**
	 * Metodo per registrare una nuova mossa
	 * 
	 * @param idPlayer contiene l'ID dell'autore della mossa
	 * @param moveType contiene la tipologia di mossa
	 * @param outcome contiene il risultato della mossa
	 */
	public void addMove(String idPlayer, String moveType, int outcome) {
		Move move = new Move();
		move.setIdMatch(manche.getMatch());
		move.setNumManche(manche.getNumManche());
		move.setPlayer(idPlayer);
		move.setMoveType(moveType);
		move.setOutCome(outcome);
		move.setMoveId(UUID.randomUUID().toString());

		moves.add(move);
	}

	/**
	 * Metodo getter per l'ottenimento dell'ultima mossa compiuta
	 * 
	 * @return ritorna l'ultima mossa eseguita
	 */
	public Move getLastMove() {
		return moves.get(moves.size() - 1);
	}
}
