package Game;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import Database.DBManager;

public class Turn {

	private List<Move> moves;
	private Manche manche;

	public Turn(Manche manche) {
		moves = new ArrayList<>();
		this.manche = manche;
	}

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

	public Move getLastMove() {
		return moves.get(moves.size() - 1);
	}
}
