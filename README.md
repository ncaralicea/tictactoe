## Some code

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

## How to open the project and run its unit tests
### Using Eclipse IDE

The projects is Maven based, so the whole project can be opened and its unit tests can be run from Eclipse like in the
followings:
	
	File ->
		Import -> Existing Maven projects -> Root Direcyory: [folder]
		
		
	    Note: The Eclipse environment should have the maven plugin already installed.
	
### Running the test using Maven command line

	$ mvn test

### Generate Javadoc
    
	$mvn javadoc:javadoc

## Developed and tested using the following configuration 

	1.  Spring Tool Suite Version: 3.6.1.RELEASE
		Build Id: 201408250818
		Platform: Eclipse Luna (4.4)
	
		(I also tested it from Scala IDE build of Eclipse SDK Build id: 3.0.4-2.11-20140723-2253-Typesafe)
	2.  Java version: 1.7.0_67, vendor: Oracle Corporation
	3.  Apache Maven 3.2.3