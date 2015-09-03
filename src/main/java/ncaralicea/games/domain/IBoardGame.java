package ncaralicea.games.domain;

/**
 * IBoardGame is a minimal interface representing a generic interface used to interact with board based games.
 * So, it exposes the basic board game operations.
 *  
 * Thus, capabilities like specifying where a move is going to be made on a board, or information like 
 * who is the player on which behalf certain operations are executed, should be exposed by this
 * interface.
 * 
 * Also, this interface should provide basic information regarding the status of the game, too.
 * At any time we should know if the game ended or not.
 * Assuming that the game ended it should also provide details like if it ended in a draw (tie) or if there is a winner.
 * 
 * A game could be reset, to start over, etc.
 * 
 * All these kind of operations are common to many board based games, hence the need to expose them in an interface.
 * 
 * This interface is supposed to be extended, so for instance changing the player's order could be added to this interface, too.
 * 
 * @author Nicolae Caralicea
 *
 * @param <T>
 * 		- generic type argument used to represent the board position type.
 *  
 * @param <P>
 * 		- generic type argument used to represent the player type.
 * 
 */
public interface IBoardGame<T extends IBoardPosition, P extends IPlayer> {
	
	/**
	 * Makes a move on the board at the position defined by the position argument.
	 * 		
	 * Note: The move will be executed on the current player's behalf.
	 * 
	 * So, the move is going to be executed on the behalf of the player given be
	 * the call of the method whoseTurnIs.
	 * 
	 * @param position
	 * 		- the position or move that indicates where on the board something takes place.
	 * 
	 */
	void makeMove(T position);
	
	/**
	 * Checks if the game ended in a draw. 
	 * 
	 * @return
	 * 		- returns true if the game ended in a draw.
	 */
	boolean isGameDraw();
	
	/**
	 * Checks if there is a winner.
	 * 
	 * @return
	 * 		- returns true if the game has a winner.
	 */
	boolean existWinner();
	
	/**
	 * Checks if the game ended.
	 * 
	 * @return
	 * 		- returns true if the game ended.
	 */
	boolean isGameEnded();
	
	/**
	 * Gets the player who won the game.
	 *
	 * Please note that there would be no winner when the game ended in a draw or the game has not ended yet.
	 * 
	 * @return
	 * 		- returns the winner of the game or null if there is no winner
	 */
	P getWinner();
	
	/**
	 * Gets the current player who's turn is to interact with the game.
	 * 
	 * @return
	 * 		- the current player.
	 */
	P whoseTurnIs();

	/**
	 * Gets the player interaction snapshot board array.
	 * 
	 * Every element of the array contains either a player instance if there was
	 * a move executed by that player or null if there was no interaction with
	 * any player. 
	 * 
	 * @return
	 *		- returns a player board interaction snapshot array. 
	 */
	P[][] getPlayerInteractionSnapshotBoard();
	
	/**
	 * Resets the game, so at any time the game can be started over by the
	 * same players after calling this method.
	 * 
	 */
	void reset();
	
	/**
	 * Starts the game.
	 * 
	 */
	void start();
	
	/**
	 * Sets the players of the game.
	 * 
	 * @param players
	 * 		- array of players.
	 */
	void setGamePlayers(P[] players);
}
