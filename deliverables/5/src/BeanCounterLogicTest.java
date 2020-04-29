import static org.junit.Assert.*;

import gov.nasa.jpf.vm.Verify;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Random;

/**
 * Code by @author Wonsun Ahn
 * 
 * <p>Uses the Java Path Finder model checking tool to check BeanCounterLogic in
 * various modes of operation. It checks BeanCounterLogic in both "luck" and
 * "skill" modes for various numbers of slots and beans. It also goes down all
 * the possible random path taken by the beans during operation.
 */

public class BeanCounterLogicTest {
	private static BeanCounterLogic logic; // The core logic of the program
	private static Bean[] beans; // The beans in the machine
	private static String failString; // A descriptive fail string for assertions

	private static int slotCount; // The number of slots in the machine we want to test
	private static int beanCount; // The number of beans in the machine we want to test
	private static boolean isLuck; // Whether the machine we want to test is in "luck" or "skill" mode

	/**
	 * Sets up the test fixture.
	 */
	@BeforeClass
	public static void setUp() {
		/*
		 * TODO: Use the Java Path Finder Verify API to generate choices for slotCount,
		 * beanCount, and isLuck: slotCount should take values 1-5, beanCount should
		 * take values 0-3, and isLucky should be either true or false. For reference on
		 * how to use the Verify API, look at:
		 * https://github.com/javapathfinder/jpf-core/wiki/Verify-API-of-JPF
		 */
		
				
		// Create the internal logic
		logic = BeanCounterLogic.createInstance(slotCount);
		// Create the beans
		beans = new Bean[beanCount];
		for (int i = 0; i < beanCount; i++) {
			beans[i] = Bean.createInstance(slotCount, isLuck, new Random());
		}
		
		slotCount = Verify.getInt(1,5);
		beanCount = Verify.getInt(0,3);
		isLuck = Verify.getBoolean();

		// A failstring useful to pass to assertions to get a more descriptive error.
		failString = "Failure in (slotCount=" + slotCount + ", beanCount=" + beanCount
				+ ", isLucky=" + isLuck + "):";
	}

	@AfterClass
	public static void tearDown() {

		logic = null;

	}

	/**
	 * Test case for void void reset(Bean[] beans).
	 * Preconditions: None.
	 * Execution steps: Call logic.reset(beans).
	 * Invariants: If beanCount is greater than 0,
	 *             remaining bean count is beanCount - 1
	 *             in-flight bean count is 1 (the bean initially at the top)
	 *             in-slot bean count is 0.
	 *             If beanCount is 0,
	 *             remaining bean count is 0
	 *             in-flight bean count is 0
	 *             in-slot bean count is 0.
	 */
	@Test
	public void testReset() {
		// TODO: Implement
		/*
		 * Currently, it just prints out the failString to demonstrate to you all the
		 * cases considered by Java Path Finder. If you called the Verify API correctly
		 * in setUp(), you should see all combinations of machines
		 * (slotCount/beanCount/isLucky) printed here:
		 * 
		 * Failure in (slotCount=1, beanCount=0, isLucky=false):
		 * Failure in (slotCount=1, beanCount=0, isLucky=true):
		 * Failure in (slotCount=1, beanCount=1, isLucky=false):
		 * Failure in (slotCount=1, beanCount=1, isLucky=true):
		 * ...
		 * 
		 * PLEASE REMOVE when you are done implementing.
		 */
		logic.reset(beans);
		assertTrue("Reset did not work",(beanCount-1) == logic.getRemainingBeanCount());
	}

	/**
	 * Test case for boolean advanceStep().
	 * Preconditions: None.
	 * Execution steps: Call logic.reset(beans).
	 *                  Call logic.advanceStep() in a loop until it returns false (the machine terminates).
	 * Invariants: After each advanceStep(),
	 *             all positions of in-flight beans are legal positions in the logical coordinate system.
	 */
	@Test
	public void testAdvanceStepCoordinates() {

		// TODO: Implement

		logic.reset(beans);
		boolean coOrd = false;

		while(logic.advanceStep()){
			int location = 0; 
			for(int i = 0; i<slotCount; i++){
				location = logic.getInFlightBeanXPos(i);

				if(location > i){
					coOrd = false;
					break;
				}else if (location < -1){
					coOrd = false;
					break;
				}

				coOrd = true;
			}

			assertTrue("Invalid Coordinates",coOrd);
		}
	}

	/**
	 * Test case for boolean advanceStep().
	 * Preconditions: None.
	 * Execution steps: Call logic.reset(beans).
	 *                  Call logic.advanceStep() in a loop until it returns false (the machine terminates).
	 * Invariants: After each advanceStep(),
	 *             the sum of remaining, in-flight, and in-slot beans is equal to beanCount.
	 */
	@Test
	public void testAdvanceStepBeanCount() {
		// TODO: Implement
		int stationary = 0;
		int inMotion = 0;
		int inSlot = 0;

		logic.reset(beans);

		while(logic.advanceStep()){
			continue;
		}

		stationary = logic.getRemainingBeanCount();
		for(int i = 0; i < slotCount; i++){
			inSlot = inSlot + logic.getSlotBeanCount(i);
		}

		inMotion = getInFlight(logic,slotCount);
		assertTrue("Bean count was reduced",(stationary + inMotion + inSlot) == beanCount);
	}

	/**
	 * Test case for boolean advanceStep().
	 * Preconditions: None.
	 * Execution steps: Call logic.reset(beans).
	 *                  Call logic.advanceStep() in a loop until it returns false (the machine terminates).
	 * Invariants: After the machine terminates,
	 *             remaining bean count is 0
	 *             in-flight bean count is 0
	 *             in-slot bean count is beanCount.
	 */
	@Test
	public void testAdvanceStepPostCondition() {

		// TODO: Implement\

		int stationary = 0;
		int inMotion=0;
		int inSlot = 0;
		logic.reset(beans);
		while(logic.advanceStep()){
			continue;
		}
		
		stationary = logic.getRemainingBeanCount();

		for(int i = 0; i<slotCount;i++){
			inSlot = inSlot + logic.getSlotBeanCount(i);
		}

		inMotion = getInFlight(logic,slotCount);

		assertTrue("Still falling down",inMotion == 0);
		assertTrue("Some stationary waiting to fall", stationary == 0);
		assertTrue("Some beans are not in slots",beanCount == inSlot);

	}
	
	/**
	 * Test case for void lowerHalf()().
	 * Preconditions: None.
	 * Execution steps: Call logic.reset(beans).
	 *                  Call logic.advanceStep() in a loop until it returns false (the machine terminates).
	 *                  Call logic.lowerHalf().
	 * Invariants: After calling logic.lowerHalf(),
	 *             slots in the machine contain only the lower half of the original beans.
	 *             Remember, if there were an odd number of beans, (N+1)/2 beans should remain.
	 *             Check each slot for the expected number of beans after having called logic.lowerHalf().
	 */
	@Test
	public void testLowerHalf() {
		// TODO: Implement

		logic.reset(beans);
		while(logic.advanceStep()){
			continue;
		}

		//BeanCounterLogic oldLogic = BeanCounterLogic.createInstance(slotCount);
		logic.lowerHalf();

		if(beanCount % 2 != 0){
			assertTrue("Lowe half was not in fact tossed",getBeansInSlot() == ((beanCount-1)/2));
		}else{
			assertTrue("Lower half was not in fact tossed",getBeansInSlot() == (beanCount/2));
		}
	}
	
	/**
	 * Test case for void upperHalf().
	 * Preconditions: None.
	 * Execution steps: Call logic.reset(beans).
	 *                  Call logic.advanceStep() in a loop until it returns false (the machine terminates).
	 *                  Call logic.lowerHalf().
	 * Invariants: After calling logic.upperHalf(),
	 *             slots in the machine contain only the upper half of the original beans.
	 *             Remember, if there were an odd number of beans, (N+1)/2 beans should remain.
	 *             Check each slot for the expected number of beans after having called logic.upperHalf().
	 */
	@Test
	public void testUpperHalf() {
		// TODO: Implement

		logic.reset(beans);
		while(logic.advanceStep()){
			continue;
		}

		//BeanCounterLogic oldLogic = BeanCounterLogic.createInstance(slotCount);
		logic.upperHalf();

		if(beanCount % 2 != 0){
			assertTrue("Lowe half was not in fact tossed",getBeansInSlot() == ((beanCount-1)/2));
		}else{
			assertTrue("Lower half was not in fact tossed",getBeansInSlot() == (beanCount/2));
		}

	}
	
	/**
	 * Test case for void repeat().
	 * Preconditions: None.
	 * Execution steps: Call logic.reset(beans).
	 *                  Call logic.advanceStep() in a loop until it returns false (the machine terminates).
	 *                  Call logic.repeat();
	 *                  Call logic.advanceStep() in a loop until it returns false (the machine terminates).
	 * Invariants: If the machine is operating in skill mode,
	 *             bean count in each slot is identical after the first run and second run of the machine. 
	 */
	@Test
	public void testRepeat() {
		// TODO: Implement

		logic.reset(beans);
		while(logic.advanceStep()){
			continue;
		}
		int[] slotTally = new int[slotCount];

		//int x = getBeansInSlot();

		for(int i =0; i <slotCount; i++){
			slotTally[i] = logic.getSlotBeanCount(i);
		}
		
		logic.reset(beans);
		while(logic.advanceStep()){
			continue;
		}

		if(!isLuck){
			for(int i = 0; i < slotCount;i++){
				assertTrue("Slot did not fall right",slotTally[i] == logic.getSlotBeanCount(i));
			}
		}
	}

	int getInFlight(BeanCounterLogic logic, int slotNum){
		int tot = 0;
		for(int i = 0; i < slotNum;i++){
			if(logic.getInFlightBeanXPos(i) >= 0){
				tot++;
			}
		}
		return tot;
	}

	public int getBeansInSlot() {
		// TODO: Implement
	return beanCount;

}

}


