package ncaralicea.games.domain.tictactoe;

import java.util.Set;

import ncaralicea.games.domain.IBoardGame;
import ncaralicea.games.domain.IStateSpace;

/**
 * This is a basic implementation of the Tic-Tac-Toe game based on a 3X3 grid board.
 * 
 * Note:
 * 
 *		3X3 grid board constrain is provided by the TicTacToeStateSpace state space that is used internally by
 *		this class.
 * 
 * 
 * @author Nicolae Caralicea
 *
 */
public class TicTacToeGame implements IBoardGame<TicTacToeMove, TicTacToePlayer> {

	/**
	 * players holds the two players of the game.
	 * players is an array of 2 players. the array type was chosen over other
	 * collection to provide fast indexed based access to the current player.
	 * 
	 */
	private TicTacToePlayer[] players;
	
	/**
	 * winner holds the winner of the game (if any).
	 * there is no winner as long as the winner is null.
	 */
	private TicTacToePlayer winner; 
	
	/**
	 * Represents the state space of the whole game.
	 */
	private IStateSpace<TicTacToeMove, TicTacToePlayer> stateSpace;
	
	/**
	 * Indicates if there is a winner in the game or not.
	 */
	private boolean isWinner = false;
	
	/**
	 * movesCounter is used to indicate the current player.
	 * after every moving successfully operation on the board the player's turn
	 * is round-robin-fashion changed by simply incrementing this counter.
	 */
	private int movesCounter;
	
	/**
	 * isStarted is used to indicate if the game started or not.
	 */
	private boolean isStarted = false;

	/**
	 * Sets the players of the game.
	 * 
	 * By convention the first player of the provided array argument will be the one to have given initially the turn,
	 * hence it was placed as first element in the players array.
	 * 
	 * @param players
	 * 		- the players of the game.
	 * 
	 */
	public void setGamePlayers(final TicTacToePlayer[] players) {
		if (validateSetPlayersPreconditions(players)) {
			this.players = players;
		} else {
			throw new TicTacToeGameException("Can not set the players for the game! Player setting preconditions failed.");
		}
	}

	public void reset() {
		this.stateSpace = new TicTacToeStateSpace();
		this.movesCounter = 0;
		this.isWinner = false;
		this.winner = null;
		this.isStarted = false;
	}

	/**
	 * Check if the move is a valid one or not.
	 * 
	 * The position of a valid move has to meet the following criteria:
	 * 
	 *		- should be defined within the boundaries of the board, and 
	 *		- there should not have been another move already executed at the same position
	 * 		  as the one defined inside the move argument.
	 * 
	 * @param move
	 * 		- the move argument contains the position on the board of the move.
	 * @return
	 * 		- returns true if the move is a valid move, or else otherwise.
	 */
	private boolean isMoveValid(TicTacToeMove move) {
		return !this.isOutOfBounds(move) && this.isMoveAvailable(move);
	}
	
	/**
	 * Check if the position of the move is available.
	 * (there is not other move already executed before at the same position)
	 *  
	 * @param move
	 * 		- the move argument containing the position of the board where the move is intended
	 * 			to take place.
	 * @return
	 */
	private boolean isMoveAvailable(final TicTacToeMove move) {
		return !this.stateSpace.getExecutedMoves().contains(move);
	}
	
	/**
	 * Checks if there are still available moves that can be executed on the board.
	 * 
	 * @return
	 * 		- return true if there are still cells on the board for other move operations, or false otherwise.
	 */
	private boolean areAvailableMovesOnBoard() {
		return this.stateSpace.generateAvailableMoves().size() > 0;
	}
	
	/**
	 * Accepts the move, so the state space of the game will be impacted by the
	 * current player's move operation.
	 *
	 * The winner of the game is checked and set here, too.
	 * 
	 * @param move
	 * 		- the move argument indicates where on the board the move is executed.
	 */
	private void acceptMove(final TicTacToeMove move) {
		final TicTacToePlayer player = whoseTurnIs();
		this.stateSpace.addMoveFor(move, player);
		if (this.hasPlayerWon(player)) {
			declareWinner(player);
		}
	}
	
	/**
	 * Declares a certain player of the game as winner by setting the winner related variables.
	 * 
	 * @param player
	 */
	private void declareWinner(final TicTacToePlayer player) {
		this.isWinner = true;
		this.winner = player;
	}
	
	/**
	 * Transfer the turn to the other player in a round-robin-fashion.
	 */
	private void transferPlayerTurn() {
		this.movesCounter++;
	}
	
	/**
	 * Checks if the player provided as argument has won the game.
	 *  
	 * @param player
	 * 		- the player against which the check is done.
	 * @return
	 * 		- returns true if the player has won the game.
	 */
	private boolean hasPlayerWon(final TicTacToePlayer player) {
		final Set<TicTacToeMove> playerMoves = this.stateSpace.getExecutedMovesBy(player);
		if (this.existVerticalLineIn(playerMoves) || this.existHorizontalLineIn(playerMoves) ||
				this.existDiagonalLineIn(playerMoves)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Checks if there is at least one "vertical line" in the set of moves.
	 * It stops searching at the first encounter.
	 * 
	 * @param moves
	 * 		- set of moves.
	 * @return
	 * 		- returns true if there is at least one "vertical line" or false if otherwise.
	 */
	private boolean existVerticalLineIn(final Set<TicTacToeMove> moves) {
		boolean result = false;
		
		for (int column = this.stateSpace.getMinPosition(); column <=  this.stateSpace.getMaxPosition(); column++) {
			if (isVerticalLine(moves, column)) {
				result = true;
				break;
			}
			
		}
		return result;
	}

	/**
	 * Checks in the set of moves if the line given by the fixed column is a vertical line.
	 * 
	 * @param moves
	 * 		- set of moves.
	 * @param forFixedColumn
	 * 		- the column that selects the vertical line for each we do the check. 
	 * @return
	 * 	- returns true for a "vertical line" or false if otherwise.
	 */
	private boolean isVerticalLine(final Set<TicTacToeMove> moves, int forFixedColumn) {
		boolean result = true;

		for (int line = this.stateSpace.getMinPosition(); line <= this.stateSpace.getMaxPosition(); line++) {
			result = result && TicTacToeStateSpace.isMoveIntoSet(line, forFixedColumn, moves);
		}
		return result;
	}
	
	/**
	 * Checks if there is at least one "horizontal line" in the set of moves.
	 * It stops searching at the first encounter.
	 * 
	 * @param moves
	 * 		- set of moves.
	 * @return
	 * 		- returns true if there is at least one "horizontal line" or false if otherwise.
	 */
	private boolean existHorizontalLineIn(final Set<TicTacToeMove> moves) {
		boolean result = false;
		
		for (int line = this.stateSpace.getMinPosition(); line <= this.stateSpace.getMaxPosition(); line++) {
			if (this.isHorizontalLine(moves, line)) {
				result = true;
				break;
			}
			
		}
		return result;
	}

	/**
	 * Checks in the set of moves if the line given by the fixed line is a horizontal line.
	 * 
	 * @param moves
	 * 		- set of moves.
	 * @param forFixedLine
	 * 		- the line that selects the horizontal line for each we do the check. 
	 * @return
	 * 		- returns true for a "horizontal line" or false if otherwise.
	 */
	private boolean isHorizontalLine(final Set<TicTacToeMove> moves, final int forFixedLine) {
		boolean result = true;

		for (int column = this.stateSpace.getMinPosition(); column <= this.stateSpace.getMaxPosition(); column++) {
			result = result && TicTacToeStateSpace.isMoveIntoSet(forFixedLine, column, moves);
		}
		return result;
	}
	
	/**
	 * Check if there is a "diagonal line" in the set of moves.
	 * 
	 * @param moves
	 * 		- set of moves.
	 * @return
	 * 		- returns true if there is a diagonal or false otherwise.
	 */
	private boolean existDiagonalLineIn(final Set<TicTacToeMove> moves) {
		boolean resultMainDiagonal = true;
		boolean resultSecondDiagonal = true;

		for (int index = this.stateSpace.getMinPosition(); index <= this.stateSpace.getMaxPosition(); index++) {
			resultMainDiagonal = resultMainDiagonal && TicTacToeStateSpace.isMoveIntoSet(index, index, moves);
			resultSecondDiagonal = resultSecondDiagonal && TicTacToeStateSpace.isMoveIntoSet(index, index % 2, moves);
		}
		
		return resultMainDiagonal || resultSecondDiagonal;
	}

	/**
	 * Checks if the move argument contains a position that is within the board boundaries.
	 * 
	 * @param move
	 * 		- the move argument against the check is done.
	 * @return
	 * 		- returns true is the position contained inside the move argument is within the board boundaries. 
	 */
	private boolean isOutOfBounds(final TicTacToeMove move) {
		final int min = this.stateSpace.getMinPosition();
		final int max = this.stateSpace.getMaxPosition();
		
		return move.getHorizontalPosition() < min || move.getHorizontalPosition() > max || 
				move.getVerticalPosition() < min || move.getVerticalPosition() > max;
	}

	public void makeMove(final TicTacToeMove move) {
		if (this.validateMovePreconditions(move)) {
			this.acceptMove(move);
			this.transferPlayerTurn();
		} else {
			throw new TicTacToeGameException("Can not make the move! Move preconditions failed.");
		}
	}
	
	public boolean isGameDraw() {
		return !this.existWinner() && !this.areAvailableMovesOnBoard();
	}

	public boolean isGameEnded() {
		return this.isGameDraw() || this.existWinner(); 
	};

	public TicTacToePlayer getWinner() {
		if (existWinner()) {
			return this.winner;
		} else {
			throw new TicTacToeGameException("The game has no winner. It either ended in a draw or is not over yet.");
		}
	}

	public TicTacToePlayer whoseTurnIs() {
		return this.players[this.movesCounter % 2];
	}

	public boolean existWinner() {
		return isWinner;
	}

	public void start() {
		if (this.validateStartPreconditions()) {
			this.reset();
			this.isStarted = true;
		} else {
			throw new TicTacToeGameException("The game can not be started. The game start preconditions failed.");
		}
	}
	
	/**
	 * Validates if a move operation is allowed in the context of the game.
	 * For instance, a move operation is not allowed if the game has not started yet, etc.
	 * 
	 * @param move
	 * 		- the move.
	 * @return
	 * 		- return true if all the preconditions for the move are fulfilled.
	 */
	private boolean validateMovePreconditions(final TicTacToeMove move) {
		return this.isStarted && !this.isGameEnded() && this.isMoveValid(move);		
	}
	
	/**
	 * Validates the players of the game.
	 * 
	 * Basically, there have be two and only two players, their display names should be different, etc.
	 * 
	 * @param players
	 * @return
	 */
	private boolean validateSetPlayersPreconditions(final TicTacToePlayer[] players) {
		return players !=null && players.length == 2 && players[0] != null && players[1] != null
				&& players[0].getDisplayName() != null
				&& players[1].getDisplayName() != null
				&& !players[0].getDisplayName().equals(players[1].getDisplayName());
	}

	public TicTacToePlayer[][] getPlayerInteractionSnapshotBoard() {
		//TODO: validate that this.stateSpace.getMinPosition() == 0 and this.stateSpace.getMaxPosition() == 2, 
		// or otherwise adjust the array index values to avoid array out of bound exceptions.
		final TicTacToePlayer[][] boardPlayerInteractSnapshot = new TicTacToePlayer[3][3];
		for (int i = this.stateSpace.getMinPosition(); i <= this.stateSpace.getMaxPosition(); i++) {
			for (int j = this.stateSpace.getMinPosition(); j <= this.stateSpace.getMaxPosition(); j++) {				
				if (this.stateSpace.isMoveDefinedAt(i, j)) {
					boardPlayerInteractSnapshot[i][j] = this.stateSpace.getPlayerAt(i, j);
				} else {
					boardPlayerInteractSnapshot[i][j] = null;
				}
			}
		}
		return boardPlayerInteractSnapshot;
	}
	
	private boolean validateStartPreconditions() {
		return this.validateSetPlayersPreconditions(this.players) && !this.isStarted;
	}
	
	/**
	 * 
	 * TicTacToeGameException is a custom unchecked exception used as a wrapper exception for any
	 * exception thrown while playing the Tic-Tac_Toe game.
	 * 
	 * @author Nicole Caralicea
	 *
	 */
	public class TicTacToeGameException extends RuntimeException {
		
		private static final long serialVersionUID = 5332275207460791418L;
		
		public TicTacToeGameException() { 
			super(); 
		}
		
		public TicTacToeGameException(String s) {
			super(s);
		}
		
		public TicTacToeGameException(String s, Throwable throwable) {
			super(s, throwable);
		}
		
		public TicTacToeGameException(Throwable throwable) {
			super(throwable);
		}
	}
}
