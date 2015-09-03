package ncaralicea.games.domain.tictactoe;

import ncaralicea.games.domain.IBoardPosition;

/**
 * TicTacToeMove class is used to indicate the position where a certain move will be placed on the board. 
 * 
 * @author Nicalae Caralicea
 *
 */
public class TicTacToeMove implements IBoardPosition {

	/**
	 * Represents the horizontal position on the board.
	 */
	private final int atHPos;
	
	/**
	 * Represents the verical position on the board.
	 */
	private final int atVPos;
	
	/**
	 * The constructor class containing the 2 position arguments.
	 * 
	 * @param atHPos
	 * 		- the horizontal position.
	 * @param atVPos
	 * 		- the vertical position.
	 */
	public TicTacToeMove(final int atHPos, final int atVPos) {		
		this.atHPos = atHPos;
		this.atVPos = atVPos;		
	}

	/**
	 * Gets the horizontal positon of the move.
	 */
	public int getHorizontalPosition() {
		return this.atHPos;
	}

	/**
	 * Gets the vertical position of the move.
	 */
	public int getVerticalPosition() {
		return this.atVPos;
	}
	
	/**
	 * hashCode is required, because this class is supposed to be used as key in a map. 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + atHPos;
		result = prime * result + atVPos;
		return result;
	}

	/**
	 * equals is required, because this class is supposed to be used as key in a map. 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TicTacToeMove other = (TicTacToeMove) obj;
		if (atHPos != other.atHPos)
			return false;
		if (atVPos != other.atVPos)
			return false;
		return true;
	}
	
}
