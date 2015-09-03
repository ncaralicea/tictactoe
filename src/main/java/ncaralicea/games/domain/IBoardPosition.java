package ncaralicea.games.domain;

/**
 *	Represents a basic position on the board.  
 * 
 * @author Nicolae Caralicea
 *
 */
public interface IBoardPosition {
	
	/**
	 * Gets the horizontal coordinate of the position.
	 * 
	 * @return
	 * 		- the horizontal position.
	 */
	int getHorizontalPosition();
	
	/**
	 * Gets the vertical coordinate of the position.
	 * 
	 * @return
	 * 		- the vertical position.
	 */
	int getVerticalPosition();
	
}
