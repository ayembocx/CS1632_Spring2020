import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.mockito.*;

public class RentACatTest {

	/**
	 * The test fixture for this JUnit test. Test fixture: a fixed state of a set of
	 * objects used as a baseline for running tests. A test fixture is the JUnit
	 * term for a common precondition for all tests in this suite.
	 */

	RentACat _r; // Object to test
	@Mock
	Cat _c1; // First mock cat object
	@Mock
	Cat _c2; // Second mock cat object
	@Mock
	Cat _c3; // Third mock cat object

	@Before
	public void setUp() throws Exception {
		// TODO: INITIALIZE THE TEST FIXTURE HERE.
		// 1. CREATE A NEW RENTACAT OBJECT BEFORE EACH TEST
		// 2. CREATE ALL MOCK CAT OBJECTS YOU WILL BE USING IN YOUR TESTS
		// 3. PERFORM ANY STUBBING REQUIRED FOR CAT METHODS YOU WILL BE CALLING
		// (NOTE: THE DEFAULT BEHAVIOR FOR NON-STUBBED METHODS IS A NO-OP WITH 0 RETURN
		// VALUE)
	}

	@After
	public void tearDown() throws Exception {
		_r = null;
		_c1 = null;
		_c2 = null;
		_c3 = null;
	}

	// TODO: ADD TEST CASES FOR EACH PUBLIC METHOD IN RENTACAT EXCEPT MAIN

}
