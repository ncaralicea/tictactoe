package ncaralicea.games.domain.tictactoe;

import ncaralicea.games.domain.IPlayer;

/**
 * 
 * 
 * @author Nicolae Caralicea
 *
 */
public class TicTacToePlayer implements IPlayer {
	
	private final String displayName;
	
	public TicTacToePlayer(final String displayName) {
		this.displayName = displayName;
	}
	
	public String getDisplayName() {
		return this.displayName;
	}
	
	/**
	 * hashCode is required, because this class is supposed to be used as key in a map. 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((displayName == null) ? 0 : displayName.hashCode());
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
		TicTacToePlayer other = (TicTacToePlayer) obj;
		if (displayName == null) {
			if (other.displayName != null)
				return false;
		} else if (!displayName.equals(other.displayName))
			return false;
		return true;
	}
}
