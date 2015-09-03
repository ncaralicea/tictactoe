package ncaralicea.games.domain.tictactoe;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ncaralicea.games.domain.IStateSpace;

/**
 * TicTacToeStateSpace is the class responsible for the state space values for the Tic-Tac-Toe game.
 * 
 * @author Nicolae Caralicea
 *
 */
public class TicTacToeStateSpace implements IStateSpace<TicTacToeMove, TicTacToePlayer> {

	/**
	 * minimum value for the horizontal/vertical position.
	 */
	public static final int MIN = 0;

	/**
	 * maximum value for the horizontal/vertical position.
	 */
	public static final int MAX = 2;

	/**
	 * records all the moves done by players.
	 */
	private final Map<TicTacToeMove, TicTacToePlayer> pastMovesMap = new HashMap<TicTacToeMove, TicTacToePlayer>();

	public Set<TicTacToeMove> generateAvailableMoves() {
		final Set<TicTacToeMove> movesSet = new HashSet<TicTacToeMove>();
		for (int m = MIN; m <= MAX; m++) {
			for (int n = MIN; n <= MAX; n++) {
				if (!isMoveDefinedAt(m, n)) {
					movesSet.add(new TicTacToeMove(m, n));
				}
			}
		}
		return movesSet;
	}
	
	public boolean isMoveDefinedAt(final int hPos, final int vPos) {
		return this.pastMovesMap.containsKey(new TicTacToeMove(hPos, vPos));
	}

	/**
	 * Checks if a certain move already belongs to the "into" provided set argument.
	 * 
	 * @param hPos
	 * 		- the horizontal position.
	 * 
	 * @param vPos
	 * 		- the vertical position.
	 * 
	 * @param into
	 * 		- the set the check is done against.	
	 * 		
	 * @return
	 * 		- returns true if the into set already contains the position/move defined by the hPos, and vPos arguments. 
	 */
	public static boolean isMoveIntoSet(final int hPos, final int vPos, final Set<TicTacToeMove> into) {
		return into.contains(new TicTacToeMove(hPos, vPos));
	}
	
	/**
	 * Checks if a certain move belongs to a player or not.
	 * 
	 * @param move
	 * 		- the move argument.
	 * 
	 * @param player
	 * 		- the player argument.
	 * 
	 * @return
	 * 		- returns true if the move/position already belongs to the player.
	 */
	private boolean doesMoveBelongToPlayer(final TicTacToeMove move, final TicTacToePlayer player) {		
		return this.pastMovesMap.containsKey(move) && this.pastMovesMap.get(move).equals(player);		
	}
	
	public void addMoveFor(final TicTacToeMove move, final TicTacToePlayer player) {
		this.pastMovesMap.put(move, player);
	}

	public Set<TicTacToeMove> getExecutedMoves() {
		return pastMovesMap.keySet();
	}

	public Set<TicTacToeMove> getExecutedMovesBy(final TicTacToePlayer player) {
		final Set<TicTacToeMove> movesSet = new HashSet<TicTacToeMove>();
		for (final TicTacToeMove move : pastMovesMap.keySet()) {
			if (this.doesMoveBelongToPlayer(move, player)) {
				movesSet.add(move);
			}
		}
		return movesSet;
	}

	public TicTacToePlayer getPlayerAt(final int i, final int j) {
		return pastMovesMap.get(new TicTacToeMove(i, j));
	}

	public int getMinPosition() {
		return MIN;
	}

	public int getMaxPosition() {
		return MAX;
	}

}
