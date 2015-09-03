package ncaralicea.games.domain.tictactoe;

/**
 * TestSupport is used to provide additional support for the test classes.
 * 
 * @author nicolae caralicea
 *
 */
public class TestSupport {

	/**
	 * Display the description to the standard output.
	 * 
	 * I added this method to display the description of the unit test.
	 * 
	 * JUnit is missing this support, as opposed to TestNG, or other testing frameworks.
	 * (check @Test(description = "some description" of TestNG)
	 * 
	 * So, I still used JUnit because is automatically supported by eclipse, so as per
	 * requirements all the tests could be tested out of the box right from Eclipse.
	 * 
	 * @param description
	 * 		- the description of the unit test.
	 */
	protected void displayUnitTestDescription(final String description) {
		System.out.println(String.format("\n<< %s >>", description));
	}
	
}
