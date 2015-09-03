package ncaralicea.games.domain.tictactoe.unit;

import java.util.Set;

import ncaralicea.games.domain.IStateSpace;
import ncaralicea.games.domain.tictactoe.TestSupport;
import ncaralicea.games.domain.tictactoe.TicTacToeGame;
import ncaralicea.games.domain.tictactoe.TicTacToeGame.TicTacToeGameException;
import ncaralicea.games.domain.tictactoe.TicTacToeMove;
import ncaralicea.games.domain.tictactoe.TicTacToePlayer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;

/**
 * 
 * TestUnitTicTacToe contains unit tests.
 * 
 * TODO: For reaching a great unit test code coverage (> 90%) there would be more such unit tests that should be added here.
 * 
 * The idea is to use mocking, spying (not necessary for here, though), or white box testing.
 *
 * However, most of the positive test cases were already covered by the 
 * ncaralicea.games.domain.tictactoe.integration.TestIntTicTacToe integration tests.
 *
 * @author Nicolae Caralicea
 *
 */
public class TestUnitTicTacToe extends TestSupport {

	@InjectMocks
	private TicTacToeGame ticTacToeGame;
	
	@Mock
	private IStateSpace<TicTacToeMove, TicTacToePlayer> stateSpace;	
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testIsGameEnded1() {
		this.displayUnitTestDescription("Unit Test 'isGameEnded' method when there is no winner and there are still available moves");
		
		Whitebox.setInternalState(ticTacToeGame, "isWinner", false);		// no winner
		final Set<TicTacToeMove> movesSetMock = Mockito.mock(Set.class);
		Mockito.when(movesSetMock.size()).thenReturn(1);					// available moves		
		Mockito.when(stateSpace.generateAvailableMoves()).thenReturn(movesSetMock);
		
		final boolean actualRes = ticTacToeGame.isGameEnded();
		
		Mockito.verify(stateSpace).generateAvailableMoves();
		Assert.assertFalse(actualRes);
		Mockito.verifyNoMoreInteractions(this.stateSpace);
	}
	
	@Test
	public void testIsGameEnded2() {
		this.displayUnitTestDescription("Unit Test 'isGameEnded' method when there is a winner");
		
		Whitebox.setInternalState(ticTacToeGame, "isWinner", true);			// there is a winner
		
		final boolean actualRes = ticTacToeGame.isGameEnded();
		
		Assert.assertTrue(actualRes);
	}

	@Test
	public void testIsGameEnded3() {
		this.displayUnitTestDescription("Unit Test 'isGameEnded' method when there is no winner and there are not any available moves");
		
		Whitebox.setInternalState(ticTacToeGame, "isWinner", false);		// no winner
		final Set<TicTacToeMove> movesSetMock = Mockito.mock(Set.class);
		Mockito.when(movesSetMock.size()).thenReturn(0);					// no available moves		
		Mockito.when(stateSpace.generateAvailableMoves()).thenReturn(movesSetMock);
		
		final boolean actualRes = ticTacToeGame.isGameEnded();
		
		Mockito.verify(stateSpace).generateAvailableMoves();
		Assert.assertTrue(actualRes);
		Mockito.verifyNoMoreInteractions(this.stateSpace);
	}
	
	@Test(expected = TicTacToeGameException.class)
	public void testSetGamePlayers1() {
		this.displayUnitTestDescription("Unit Test 'setGamePlayers' method when a player is missing should throw exception");
		final TicTacToePlayer playerIMock = Mockito.mock(TicTacToePlayer.class);
		
		ticTacToeGame.setGamePlayers(new TicTacToePlayer[]{playerIMock});		
	}

	@Test(expected = TicTacToeGameException.class)
	public void testSetGamePlayers2() {
		this.displayUnitTestDescription("Unit Test 'setGamePlayers' method when a player display name is null should throw exception");
		final TicTacToePlayer playerIMock = Mockito.mock(TicTacToePlayer.class);
		final TicTacToePlayer playerIIMock = Mockito.mock(TicTacToePlayer.class);
		Mockito.when(playerIMock.getDisplayName()).thenReturn("X");
		
		ticTacToeGame.setGamePlayers(new TicTacToePlayer[]{playerIMock, playerIIMock});		
	}

	@Test
	public void testSetGamePlayers3() {
		this.displayUnitTestDescription("Unit Test 'setGamePlayers' method for valid players should not throw any exception");
		final TicTacToePlayer playerIMock = Mockito.mock(TicTacToePlayer.class);
		final TicTacToePlayer playerIIMock = Mockito.mock(TicTacToePlayer.class);
		Mockito.when(playerIMock.getDisplayName()).thenReturn("X");
		Mockito.when(playerIIMock.getDisplayName()).thenReturn("O");
		
		ticTacToeGame.setGamePlayers(new TicTacToePlayer[]{playerIMock, playerIIMock});
	}
	
	@Test(expected = TicTacToeGameException.class)
	public void testStart1() {
		this.displayUnitTestDescription("Unit Test 'start' method should throw exception when attempting to start a game with no players");
		
		ticTacToeGame.start();		
	}
	
	@Test(expected = TicTacToeGameException.class)
	public void testStart2() {
		this.displayUnitTestDescription("Unit Test 'start' method should throw exception when attempting to start an already started game");
		final TicTacToePlayer playerIMock = Mockito.mock(TicTacToePlayer.class);
		final TicTacToePlayer playerIIMock = Mockito.mock(TicTacToePlayer.class);
		Mockito.when(playerIMock.getDisplayName()).thenReturn("X");
		Mockito.when(playerIIMock.getDisplayName()).thenReturn("O");
		Whitebox.setInternalState(ticTacToeGame, "players", new TicTacToePlayer[]{playerIMock, playerIIMock});
		Whitebox.setInternalState(ticTacToeGame, "isStarted", true);
		
		ticTacToeGame.start();		
	}

	@Test
	public void testStart3() {
		this.displayUnitTestDescription("Unit Test 'start' method should throw no exception");
		final TicTacToePlayer playerIMock = Mockito.mock(TicTacToePlayer.class);
		final TicTacToePlayer playerIIMock = Mockito.mock(TicTacToePlayer.class);
		Mockito.when(playerIMock.getDisplayName()).thenReturn("X");
		Mockito.when(playerIIMock.getDisplayName()).thenReturn("O");
		Whitebox.setInternalState(ticTacToeGame, "players", new TicTacToePlayer[]{playerIMock, playerIIMock});
		Whitebox.setInternalState(ticTacToeGame, "isStarted", false);
		
		ticTacToeGame.start();		
	}
	
	@Test(expected = TicTacToeGameException.class)
	public void testGetWinner1() {
		this.displayUnitTestDescription("Unit Test 'getWinner' method should throw an exception if there is no winner yet");
		Whitebox.setInternalState(ticTacToeGame, "isWinner", false);
		
		ticTacToeGame.getWinner();				
	}

	@Test
	public void testGetWinner2() {
		this.displayUnitTestDescription("Unit Test 'getWinner' method should throw no exception if there is already a winner");
		Whitebox.setInternalState(ticTacToeGame, "isWinner", true);
		
		ticTacToeGame.getWinner();				
	}
	
	@Test(expected = TicTacToeGameException.class)
	public void testMakeMove() {
		this.displayUnitTestDescription("Unit Test 'makeMove' method should throw an exception if attempting to make a move if the game is not started yet.");
		Whitebox.setInternalState(ticTacToeGame, "isStarted", false);
		final TicTacToeMove moveMock = Mockito.mock(TicTacToeMove.class);
		
		ticTacToeGame.makeMove(moveMock);				
	}

	@Test(expected = TicTacToeGameException.class)
	public void testMakeMove2() {
		this.displayUnitTestDescription("Unit Test 'makeMove' method should throw an exception if attempting to make a move if the game has ended with a winner.");
		Whitebox.setInternalState(ticTacToeGame, "isStarted", true);
		Whitebox.setInternalState(ticTacToeGame, "isWinner", true);
		final TicTacToeMove moveMock = Mockito.mock(TicTacToeMove.class);
		
		ticTacToeGame.makeMove(moveMock);				
	}

	@Test(expected = TicTacToeGameException.class)
	public void testMakeMove3() {
		this.displayUnitTestDescription("Unit Test 'makeMove' method should throw an exception if attempting to make a move if the game ended in a draw.");
		Whitebox.setInternalState(ticTacToeGame, "isStarted", true);
		Whitebox.setInternalState(ticTacToeGame, "isWinner", false);		// no winner
		final Set<TicTacToeMove> movesSetMock = Mockito.mock(Set.class);
		Mockito.when(movesSetMock.size()).thenReturn(0);					// no available moves		
		Mockito.when(stateSpace.generateAvailableMoves()).thenReturn(movesSetMock);
		final TicTacToeMove moveMock = Mockito.mock(TicTacToeMove.class);
		
		ticTacToeGame.makeMove(moveMock);				
	}
}
