import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.mockito.*;

public class RentACatTest {

	/**
     * The test fixture for this JUnit test.
     * Test fixture: a fixed state of a set of objects used as a baseline for running tests.
     * A test fixture is the JUnit term for a common precondition for all tests in this suite.
     */

	RentACat _r;        // Object to test
	@Mock Cat _c1;      // First mock cat object
	@Mock Cat _c2;      // Second mock cat object
	@Mock Cat _c3;      // Third mock cat object
		
	@Before
	public void setUp() throws Exception {
		_r = new RentACat();
		_c1 = Mockito.mock(Cat.class);
    	Mockito.when(_c1.getId()).thenReturn(1);
    	Mockito.when(_c1.getName()).thenReturn("Jennyanydots");
    	Mockito.when(_c1.toString()).thenReturn("ID 1. Jennyanydots");
    	Mockito.when(_c1.getRented()).thenReturn(false);
    	_c2 = Mockito.mock(Cat.class);
    	Mockito.when(_c2.getId()).thenReturn(2);
    	Mockito.when(_c2.getName()).thenReturn("Old Deuteronomy");
    	Mockito.when(_c2.toString()).thenReturn("ID 2. Old Deuteronomy");
    	Mockito.when(_c2.getRented()).thenReturn(false);
    	_c3 = Mockito.mock(Cat.class);
    	Mockito.when(_c3.getId()).thenReturn(3);
    	Mockito.when(_c3.getName()).thenReturn("Mistoffelees");
    	Mockito.when(_c3.toString()).thenReturn("ID 3. Mistoffelees");
    	Mockito.when(_c3.getRented()).thenReturn(false);
	}
    
	@After
	public void tearDown() throws Exception {
		_r = null;
		_c1 = null;
		_c2 = null;
		_c3 = null;
	}
	
    // Test cases for catExists().
    
    @Test
    public void testCatExistsFailureNumCats0() {
    	assertFalse(_r.catExists(1));
    }
    
    @Test
    public void testCatExistsNumCats1() {
    	_r._cats.add(_c1);
    	assertTrue(_r.catExists(1));
    }
    
    @Test
    public void testCatExistsFailureNumCats1() {
    	_r._cats.add(_c1);
    	assertFalse(_r.catExists(2));
    }
    
    @Test
    public void testCatExistsNumCats2() {
    	_r._cats.add(_c1);
    	_r._cats.add(_c2);
    	assertTrue(_r.catExists(1));
    }
    
    @Test
    public void testCatExistsFailureNumCats2() {
    	_r._cats.add(_c1);
    	_r._cats.add(_c2);
    	assertFalse(_r.catExists(3));
    }
    
    // Test cases for catAvailable().
    
    @Test
    public void testCatAvailableFailureNumCats0() {
    	assertFalse(_r.catAvailable(2));
    }
    
    @Test
    public void testCatAvailableNumCats3() {
    	_r._cats.add(_c1);
    	_r._cats.add(_c2);
    	_r._cats.add(_c3);
    	Mockito.when(_c3.getRented()).thenReturn(true);
    	assertTrue(_r.catAvailable(2));
    }
    
    @Test
    public void testCatAvailableFailure1NumCats3() {
    	_r._cats.add(_c1);
    	_r._cats.add(_c2);
    	_r._cats.add(_c3);
    	Mockito.when(_c3.getRented()).thenReturn(true);
    	assertFalse(_r.catAvailable(3));
    }
    
    @Test
    public void testCatAvailableFailure2NumCats3() {
    	_r._cats.add(_c1);
    	_r._cats.add(_c2);
    	_r._cats.add(_c3);
    	assertFalse(_r.catAvailable(4));
    }
    
    // Test cases for getCat().
    
    @Test
    public void testGetCatFailureNumCats0() {
    	Cat c = _r.getCat(2);
    	assertNull(c);
    }
    
    @Test
    public void testGetCatNumCats3() {
    	_r._cats.add(_c1);
    	_r._cats.add(_c2);
    	_r._cats.add(_c3);
    	Cat c = _r.getCat(2);
    	assertNotNull(c);
    }
    
    // Test cases for listCats().
    
    @Test
    public void testListCatsNumCats0() {
    	assertEquals(_r.listCats(), "");
    }
    
    @Test
    public void testListCatsNumCats3() {
    	_r._cats.add(_c1);
    	_r._cats.add(_c2);
    	_r._cats.add(_c3);
    	assertEquals(_r.listCats(), "ID 1. Jennyanydots\nID 2. Old Deuteronomy\nID 3. Mistoffelees\n");
    }
    
    // Test cases for rentCat().
    
    @Test
    public void testRentCatFailureNumCats0() {
    	assertFalse(_r.rentCat(1));
    }
    
    @Test
    public void testRentCatNumCats3() {
    	_r._cats.add(_c1);
    	_r._cats.add(_c2);
    	_r._cats.add(_c3);
    	assertTrue(_r.rentCat(2));
    	Mockito.verify(_c2, Mockito.times(1)).rentCat();
    	Mockito.verify(_c3, Mockito.never()).rentCat();
    }
    
    @Test
    public void testRentCatFailure1NumCats3() {
    	_r._cats.add(_c1);
    	_r._cats.add(_c2);
    	_r._cats.add(_c3);
    	Mockito.when(_c2.getRented()).thenReturn(true);
    	assertFalse(_r.rentCat(2));
    	Mockito.verify(_c2, Mockito.never()).rentCat();
    	Mockito.verify(_c3, Mockito.never()).rentCat();
    }
    
    @Test
    public void testRentCatFailure2NumCats3() {
    	_r._cats.add(_c1);
    	_r._cats.add(_c2);
    	_r._cats.add(_c3);
    	assertFalse(_r.rentCat(4));
    	Mockito.verify(_c2, Mockito.never()).rentCat();
    	Mockito.verify(_c3, Mockito.never()).rentCat();
    }
    
    // Test cases for returnCat().
    
    @Test
    public void testReturnCatFailureNumCats0() {
    	assertFalse(_r.returnCat(1));
    }
    
    @Test
    public void testReturnCatNumCats3() {
    	_r._cats.add(_c1);
    	_r._cats.add(_c2);
    	_r._cats.add(_c3);
    	Mockito.when(_c2.getRented()).thenReturn(true);
    	assertTrue(_r.returnCat(2));
    	Mockito.verify(_c2, Mockito.times(1)).returnCat();
    	Mockito.verify(_c3, Mockito.never()).returnCat();
    }
    
    @Test
    public void testReturnCatFailure1NumCats3() {
    	_r._cats.add(_c1);
    	_r._cats.add(_c2);
    	_r._cats.add(_c3);
    	assertFalse(_r.returnCat(2));
    	Mockito.verify(_c2, Mockito.never()).returnCat();
    	Mockito.verify(_c3, Mockito.never()).returnCat();
    }
    
    @Test
    public void testReturnCatFailure2NumCats3() {
    	_r._cats.add(_c1);
    	_r._cats.add(_c2);
    	_r._cats.add(_c3);
    	assertFalse(_r.returnCat(4));
    	Mockito.verify(_c2, Mockito.never()).returnCat();
    	Mockito.verify(_c3, Mockito.never()).returnCat();
    }
}
