package ncaralicea.games.domain;

import java.util.Set;

/**
 * IStateSpace interface exposes the basic method that are used for manipulating the state of the game.
 * 
 * By manipulating the state of the game it is meant that any operation done by the players of the 
 * game should be recorded here.
 * 
 * Note:
 *  
 * 	Please not that here "move" and "position" can be used interchangeably, because a move uniquely
 *	defines a position on the board.
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
public interface IStateSpace<M extends IBoardPosition, P extends IPlayer> {

	/**
	 * Gets all the executed moves during the game.
	 * 
	 * 
	 * @return
	 * 	- returns a set of all the board positions/executed moves.
	 * 
	 */
	Set<M> getExecutedMoves();

	/**
	 * Gets the executed moves/positions on the board for the player.
	 * 
	 * @param player
	 * 		- the player which moves or position on the board are queried.
	 * 
	 * @return
	 * 		- returns a set of moves or the positions a certain player has have.
	 */
	Set<M> getExecutedMovesBy(P player);
	
	/**
	 * Adds a move for a player.
	 * 
	 * @param move
	 * 		- the move argument.
	 * 
	 * @param player
	 * 		- the player argument on which behalf the move is done.
	 *  
	 */
	void addMoveFor(M move, P player);
	
	/**
	 * Generates all the available moves (empty cells) on the board.
	 * 
	 * @return
	 * 		- a set of available moves/positions on the board.
	 */
	Set<M> generateAvailableMoves();
	
	/**
	 * Checks if a move has been already executed.
	 * 
	 * @param hPos
	 * 		- the horizontal position.
	 * 
	 * @param vPos
	 * 		- the vertical position.
	 * 
	 * @return
	 */
	boolean isMoveDefinedAt(int hPos, int vPos);
	
	/**
	 * Gets the player that has executed a move at the position provided as arguments.
	 * 
	 * @param hPos
	 * 		- the horizontal position.
	 * 
	 * @param vPos
	 * 		- the vertical position.
	 * 
	 * @return
	 * 		- returns the player who executed a move at the position specified by the arguments.
	 */
	P getPlayerAt(int hPos, int vPos);
	
	/**
	 * Gets the minimum position on the board (usually is is zero).
	 * 
	 * 
	 * Note: All the boards are supposed to be squared, hence no horizontal or vertical min/max values.
	 * 
	 * (For Tic-Tac-Toe its value is 0)
	 * 
	 * @return
	 * 		- returns the minimum position on the board.
	 */
	int getMinPosition();

	/**
	 * Gets the maximum position on the board.
	 * 
	 * (For Tic-Tac-Toe its value is 2)
	 * 
	 * @return
	 * 		- returns the maximum position on the board.
	 */
	int getMaxPosition();
	
}
