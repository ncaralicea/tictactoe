package ncaralicea.games.domain.tictactoe.integration;

import org.junit.Assert;
import org.junit.Test;

import ncaralicea.games.domain.IBoardGame;
import ncaralicea.games.domain.tictactoe.TestSupport;
import ncaralicea.games.domain.tictactoe.TicTacToeGame;
import ncaralicea.games.domain.tictactoe.TicTacToeMove;
import ncaralicea.games.domain.tictactoe.TicTacToePlayer;

/**
 * 
 * TestIntTicTacToe contains integration tests for almost all positive test cases.
 * 
 * @author Nicolae Caralicea
 *
 */
public class TestIntTicTacToe extends TestSupport {

	@Test
	public void testWinnerPlayerIWithVerticalLine() {
		this.displayUnitTestDescription("Integration Test for having player 'X' as winner on a vertical line use case");
		final IBoardGame<TicTacToeMove, TicTacToePlayer> ticTacTocGame = new TicTacToeGame();
		final String playerIDisplayName = "X";
		final String playerIIDisplayName = "O";
		final TicTacToePlayer playerI = new TicTacToePlayer(playerIDisplayName);
		final TicTacToePlayer playerII = new TicTacToePlayer(playerIIDisplayName);
		ticTacTocGame.setGamePlayers(new TicTacToePlayer[]{playerI, playerII});
		TicTacToePlayer[][] expectedBoardInteractionSnapshot = new TicTacToePlayer[][]{
			new TicTacToePlayer[]{playerI,		null,		playerII},
			new TicTacToePlayer[]{playerI,		playerII,	null},
			new TicTacToePlayer[]{playerI, 		playerII,	playerI}			
		};
		
		ticTacTocGame.start();
		ticTacTocGame.makeMove(new TicTacToeMove(0, 0));
		ticTacTocGame.makeMove(new TicTacToeMove(1, 1));
		ticTacTocGame.makeMove(new TicTacToeMove(2, 2));
		ticTacTocGame.makeMove(new TicTacToeMove(0, 2));
		ticTacTocGame.makeMove(new TicTacToeMove(2, 0));
		ticTacTocGame.makeMove(new TicTacToeMove(2, 1));
		ticTacTocGame.makeMove(new TicTacToeMove(1, 0));
		
		this.displayBoardPlayerInteractionSnapshot(ticTacTocGame);
		final TicTacToePlayer[][] actualBoardInteractionSnapshot = ticTacTocGame.getPlayerInteractionSnapshotBoard();
		
		Assert.assertFalse(ticTacTocGame.isGameDraw());
		Assert.assertTrue(ticTacTocGame.existWinner());
		Assert.assertEquals(ticTacTocGame.getWinner().getDisplayName(), playerIDisplayName);
		Assert.assertArrayEquals(actualBoardInteractionSnapshot, expectedBoardInteractionSnapshot);		
	}

	@Test
	public void testWinnerPlayerIWithHorizontalLine() {
		this.displayUnitTestDescription("Integration Test for having player 'X' as winner on a horizontal line use case");
		
		final IBoardGame<TicTacToeMove, TicTacToePlayer> ticTacTocGame = new TicTacToeGame();
		String playerIDisplayName = "X";
		String playerIIDisplayName = "O";
		final TicTacToePlayer playerI = new TicTacToePlayer(playerIDisplayName);
		final TicTacToePlayer playerII = new TicTacToePlayer(playerIIDisplayName);
		ticTacTocGame.setGamePlayers(new TicTacToePlayer[]{playerI, playerII});
		TicTacToePlayer[][] expectedBoardInteractionSnapshot = new TicTacToePlayer[][]{
			new TicTacToePlayer[]{playerI,		null,		playerII},
			new TicTacToePlayer[]{playerII,		playerII,	null},
			new TicTacToePlayer[]{playerI, 		playerI,	playerI}			
		};
		
		ticTacTocGame.start();
		ticTacTocGame.makeMove(new TicTacToeMove(0, 0));
		ticTacTocGame.makeMove(new TicTacToeMove(1, 1));
		ticTacTocGame.makeMove(new TicTacToeMove(2, 2));
		ticTacTocGame.makeMove(new TicTacToeMove(0, 2));
		ticTacTocGame.makeMove(new TicTacToeMove(2, 0));
		ticTacTocGame.makeMove(new TicTacToeMove(1, 0));
		ticTacTocGame.makeMove(new TicTacToeMove(2, 1));
		
		this.displayBoardPlayerInteractionSnapshot(ticTacTocGame);
		final TicTacToePlayer[][] actualBoardInteractionSnapshot = ticTacTocGame.getPlayerInteractionSnapshotBoard();

		Assert.assertFalse(ticTacTocGame.isGameDraw());
		Assert.assertTrue(ticTacTocGame.existWinner());
		Assert.assertEquals(ticTacTocGame.getWinner().getDisplayName(), playerIDisplayName);
		Assert.assertArrayEquals(actualBoardInteractionSnapshot, expectedBoardInteractionSnapshot);	
	}

	@Test
	public void testWinnerPlayerIWithDiagonalLine() {
		this.displayUnitTestDescription("Integration Test for having player 'X' as winner on a diagonal line use case");
		
		final IBoardGame<TicTacToeMove, TicTacToePlayer> ticTacTocGame = new TicTacToeGame();
		String playerIDisplayName = "X";
		String playerIIDisplayName = "O";
		final TicTacToePlayer playerI = new TicTacToePlayer(playerIDisplayName);
		final TicTacToePlayer playerII = new TicTacToePlayer(playerIIDisplayName);
		ticTacTocGame.setGamePlayers(new TicTacToePlayer[]{playerI, playerII});
		TicTacToePlayer[][] expectedBoardInteractionSnapshot = new TicTacToePlayer[][]{
			new TicTacToePlayer[]{playerI,	playerII,		null},
			new TicTacToePlayer[]{null,		playerI,		null},
			new TicTacToePlayer[]{null, 	playerII,		playerI}			
		};
		
		ticTacTocGame.start();
		ticTacTocGame.makeMove(new TicTacToeMove(0, 0));
		ticTacTocGame.makeMove(new TicTacToeMove(2, 1));		
		ticTacTocGame.makeMove(new TicTacToeMove(2, 2));
		ticTacTocGame.makeMove(new TicTacToeMove(0, 1));
		ticTacTocGame.makeMove(new TicTacToeMove(1, 1));
		
		this.displayBoardPlayerInteractionSnapshot(ticTacTocGame);
		final TicTacToePlayer[][] actualBoardInteractionSnapshot = ticTacTocGame.getPlayerInteractionSnapshotBoard();

		Assert.assertFalse(ticTacTocGame.isGameDraw());
		Assert.assertTrue(ticTacTocGame.existWinner());
		Assert.assertEquals(ticTacTocGame.getWinner().getDisplayName(), playerIDisplayName);
		Assert.assertArrayEquals(actualBoardInteractionSnapshot, expectedBoardInteractionSnapshot);
	}

	@Test
	public void testDrawGame() {
		this.displayUnitTestDescription("Integration Test for a game ended in a draw use case");
		
		final IBoardGame<TicTacToeMove, TicTacToePlayer> ticTacTocGame = new TicTacToeGame();
		String playerIDisplayName = "X";
		String playerIIDisplayName = "O";
		final TicTacToePlayer playerI = new TicTacToePlayer(playerIDisplayName);
		final TicTacToePlayer playerII = new TicTacToePlayer(playerIIDisplayName);
		ticTacTocGame.setGamePlayers(new TicTacToePlayer[]{playerI, playerII});
		TicTacToePlayer[][] expectedBoardInteractionSnapshot = new TicTacToePlayer[][]{
			new TicTacToePlayer[]{playerI,		playerII,		playerI},
			new TicTacToePlayer[]{playerII,		playerI,		playerI},
			new TicTacToePlayer[]{playerII, 	playerI,		playerII}			
		};
		
		ticTacTocGame.start();
		ticTacTocGame.makeMove(new TicTacToeMove(0,0));
		ticTacTocGame.makeMove(new TicTacToeMove(2,2));
		ticTacTocGame.makeMove(new TicTacToeMove(1,1));
		ticTacTocGame.makeMove(new TicTacToeMove(2,0));
		ticTacTocGame.makeMove(new TicTacToeMove(2,1));
		ticTacTocGame.makeMove(new TicTacToeMove(0,1));
		ticTacTocGame.makeMove(new TicTacToeMove(1,2));
		ticTacTocGame.makeMove(new TicTacToeMove(1,0));
		ticTacTocGame.makeMove(new TicTacToeMove(0,2));
		
		this.displayBoardPlayerInteractionSnapshot(ticTacTocGame);
		final TicTacToePlayer[][] actualBoardInteractionSnapshot = ticTacTocGame.getPlayerInteractionSnapshotBoard();
		
		Assert.assertTrue(ticTacTocGame.isGameDraw());
		Assert.assertFalse(ticTacTocGame.existWinner());
		Assert.assertArrayEquals(actualBoardInteractionSnapshot, expectedBoardInteractionSnapshot);
	}

	@Test
	public void testWinnerPlayerIIWithHorizontalLine() {
		this.displayUnitTestDescription("Integration Test for having player 'O' as winner on a horizontal line use case");
		
		final IBoardGame<TicTacToeMove, TicTacToePlayer> ticTacTocGame = new TicTacToeGame();
		String playerIDisplayName = "X";
		String playerIIDisplayName = "O";
		final TicTacToePlayer playerI = new TicTacToePlayer(playerIDisplayName);
		final TicTacToePlayer playerII = new TicTacToePlayer(playerIIDisplayName);
		ticTacTocGame.setGamePlayers(new TicTacToePlayer[]{playerI, playerII});
		TicTacToePlayer[][] expectedBoardInteractionSnapshot = new TicTacToePlayer[][]{
			new TicTacToePlayer[]{playerI,		playerI,		null},
			new TicTacToePlayer[]{playerII,		playerII,		playerII},
			new TicTacToePlayer[]{null,		 	null,			playerI}			
		};

		ticTacTocGame.start();
		ticTacTocGame.makeMove(new TicTacToeMove(0,0));
		ticTacTocGame.makeMove(new TicTacToeMove(1,0));
		ticTacTocGame.makeMove(new TicTacToeMove(0,1));
		ticTacTocGame.makeMove(new TicTacToeMove(1,2));
		ticTacTocGame.makeMove(new TicTacToeMove(2,2));
		ticTacTocGame.makeMove(new TicTacToeMove(1,1));
		
		this.displayBoardPlayerInteractionSnapshot(ticTacTocGame);
		final TicTacToePlayer[][] actualBoardInteractionSnapshot = ticTacTocGame.getPlayerInteractionSnapshotBoard();
		
		Assert.assertFalse(ticTacTocGame.isGameDraw());
		Assert.assertTrue(ticTacTocGame.existWinner());
		Assert.assertEquals(ticTacTocGame.getWinner().getDisplayName(), playerIIDisplayName);
		Assert.assertArrayEquals(actualBoardInteractionSnapshot, expectedBoardInteractionSnapshot);
	}
	
	@Test
	public void testForIncompleteGame() {
		this.displayUnitTestDescription("Integration Test for an incomplete game use case");
		
		final IBoardGame<TicTacToeMove, TicTacToePlayer> ticTacTocGame = new TicTacToeGame();
		String playerIDisplayName = "X";
		String playerIIDisplayName = "O";
		final TicTacToePlayer playerI = new TicTacToePlayer(playerIDisplayName);
		final TicTacToePlayer playerII = new TicTacToePlayer(playerIIDisplayName);
		ticTacTocGame.setGamePlayers(new TicTacToePlayer[]{playerI, playerII});
		TicTacToePlayer[][] expectedBoardInteractionSnapshot = new TicTacToePlayer[][]{
			new TicTacToePlayer[]{playerI,		playerI,		null},
			new TicTacToePlayer[]{playerII,		null,			playerII},
			new TicTacToePlayer[]{null,		 	null,			playerI}			
		};

		ticTacTocGame.start();
		// player X has the first turn
		ticTacTocGame.makeMove(new TicTacToeMove(0,0)); // player X moved
		ticTacTocGame.makeMove(new TicTacToeMove(1,0)); // player O moved
		ticTacTocGame.makeMove(new TicTacToeMove(0,1)); // player X moved
		ticTacTocGame.makeMove(new TicTacToeMove(1,2)); // player O moved
		ticTacTocGame.makeMove(new TicTacToeMove(2,2)); // player X moved
		// not it should be player O's turn to move
		this.displayBoardPlayerInteractionSnapshot(ticTacTocGame);
		final TicTacToePlayer[][] actualBoardInteractionSnapshot = ticTacTocGame.getPlayerInteractionSnapshotBoard();
		
		Assert.assertEquals(ticTacTocGame.whoseTurnIs().getDisplayName(), playerIIDisplayName);		
		Assert.assertFalse(ticTacTocGame.isGameDraw());
		Assert.assertFalse(ticTacTocGame.existWinner());
		Assert.assertArrayEquals(actualBoardInteractionSnapshot, expectedBoardInteractionSnapshot);
	}
	
	@Test
	public void testForResetGame() {
		this.displayUnitTestDescription("Integration Test for having player 'O' as winner after reset game use case");
		
		final IBoardGame<TicTacToeMove, TicTacToePlayer> ticTacTocGame = new TicTacToeGame();
		String playerIDisplayName = "X";
		String playerIIDisplayName = "O";
		final TicTacToePlayer playerI = new TicTacToePlayer(playerIDisplayName);
		final TicTacToePlayer playerII = new TicTacToePlayer(playerIIDisplayName);
		ticTacTocGame.setGamePlayers(new TicTacToePlayer[]{playerI, playerII});
		TicTacToePlayer[][] expectedBoardInteractionSnapshot = new TicTacToePlayer[][]{
			new TicTacToePlayer[]{playerI,		playerI,		null},
			new TicTacToePlayer[]{playerII,		playerII,		playerII},
			new TicTacToePlayer[]{null,		 	null,			playerI}			
		};

		ticTacTocGame.start();
		// make 2 random moves
		ticTacTocGame.makeMove(new TicTacToeMove(2,2));
		ticTacTocGame.makeMove(new TicTacToeMove(1,1));
		// reset and start the game
		ticTacTocGame.reset();
		ticTacTocGame.start();
		ticTacTocGame.makeMove(new TicTacToeMove(0,0));
		ticTacTocGame.makeMove(new TicTacToeMove(1,0));
		ticTacTocGame.makeMove(new TicTacToeMove(0,1));
		ticTacTocGame.makeMove(new TicTacToeMove(1,2));
		ticTacTocGame.makeMove(new TicTacToeMove(2,2));
		ticTacTocGame.makeMove(new TicTacToeMove(1,1));
		
		this.displayBoardPlayerInteractionSnapshot(ticTacTocGame);
		final TicTacToePlayer[][] actualBoardInteractionSnapshot = ticTacTocGame.getPlayerInteractionSnapshotBoard();
		
		Assert.assertFalse(ticTacTocGame.isGameDraw());
		Assert.assertTrue(ticTacTocGame.existWinner());
		Assert.assertEquals(ticTacTocGame.getWinner().getDisplayName(), playerIIDisplayName);
		Assert.assertArrayEquals(actualBoardInteractionSnapshot, expectedBoardInteractionSnapshot);
	}
	
	/**
	 * Displays to the console the current board interaction snapshot.
	 * 
	 * After any player interaction the board would be affected in some way,
	 * so for testing purposes would be useful to be able to see the board,
	 * hence the existence of this method.
	 *  
	 */
	public void displayBoardPlayerInteractionSnapshot(final IBoardGame<TicTacToeMove, TicTacToePlayer> ticTacTocGame) {
		TicTacToePlayer[][] boardInteractionSnapshot = ticTacTocGame.getPlayerInteractionSnapshotBoard();
		System.out.print("\n");
		for (int i = 0; i <= 2; i++) {
			System.out.print("\n");
			final String emptyStr = "";
			String delim = "\t";
			for (int j = 0; j <= 2; j++) {
				if (j == 2) {
					delim = emptyStr;
				}
				final TicTacToePlayer player = boardInteractionSnapshot[i][j];
				System.out.print(player != null ? player.getDisplayName() + delim: emptyStr + delim); 
			}
		}
		System.out.print("\n");
	}	
}
