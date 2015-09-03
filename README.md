## How to open the project and run its unit tests
### Using Eclipse IDE

The projects is Maven based, so the whole project can be opened and its unit tests can be run from Eclipse like in the
followings:
	
	File ->
		Import -> Existing Maven projects -> Root Direcyory: [parent folder]/NicolaeCaralicea
		
		
	    Note: The Eclipse environment should have the maven plugin already installed.
	
### Running the test using Maven command line

	/[parent folder]/NicolaeCaralicea$ mvn test

### Generate Javadoc
    
	/[parent folder]/NicolaeCaralicea$mvn javadoc:javadoc

## Developed and tested using the following configuration 

	1.  Spring Tool Suite Version: 3.6.1.RELEASE
		Build Id: 201408250818
		Platform: Eclipse Luna (4.4)
	
		(I also tested it from Scala IDE build of Eclipse SDK Build id: 3.0.4-2.11-20140723-2253-Typesafe)
	2.  Java version: 1.7.0_67, vendor: Oracle Corporation
	3.  Apache Maven 3.2.3

