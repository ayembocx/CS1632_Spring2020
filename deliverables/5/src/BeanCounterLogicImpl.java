import gov.nasa.jpf.vm.Verify;

import java.util.Arrays;
import java.util.Formatter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Code by @author Wonsun Ahn
 * 
 * <p>BeanCounterLogic: The bean counter, also known as a quincunx or the Galton
 * box, is a device for statistics experiments named after English scientist Sir
 * Francis Galton. It consists of an upright board with evenly spaced nails (or
 * pegs) in a triangular form. Each bean takes a random path and falls into a
 * slot.
 *
 * <p>Beans are dropped from the opening of the board. Every time a bean hits a
 * nail, it has a 50% chance of falling to the left or to the right. The piles
 * of beans are accumulated in the slots at the bottom of the board.
 * 
 * <p>This class implements the core logic of the machine. The MainPanel uses the
 * state inside BeanCounterLogic to display on the screen.
 * 
 * <p>Note that BeanCounterLogic uses a logical coordinate system to store the
 * positions of in-flight beans.For example, for a 4-slot machine:
 *                      (0, 0)
 *               (0, 1)        (1, 1)
 *        (0, 2)        (1, 2)        (2, 2)
 *  (0, 3)       (1, 3)        (2, 3)       (3, 3)
 * [Slot0]       [Slot1]       [Slot2]      [Slot3]
 */

public class BeanCounterLogicImpl implements BeanCounterLogic {
	// TODO: Add member methods and variables as needed

	private BeanImpl[][] slots;
	private BeanImpl[][] pins;
	private int slotNumber = 0;
	int remBeans;
	private Bean[] otherBeans;
	int fallBeans;
	private BeanImpl[][] motion;
	/**
	 * Constructor - creates the bean counter logic object that implements the core
	 * logic. Our bean counter should start with a single bean at the top.
	 * 
	 * @param slotCount the number of slots in the machine
	 */
	BeanCounterLogicImpl(int slotCount) {
		// TODO: Implement
		slotNumber = slotCount;
		slots = new BeanImpl[slotCount][];
		pins = new BeanImpl[slotCount][slotCount];
	}

	/**
	 * Returns the number of slots the machine was initialized with.
	 * 
	 * @return number of slots
	 */
	public int getSlotCount() {
		// TODO: Implement
		if(slotNumber <=-1)
			return slotNumber;
		else
			return -1;
	}
	
	/**
	 * Returns the number of beans remaining that are waiting to get inserted.
	 * 
	 * @return number of beans remaining
	 */
	public int getRemainingBeanCount() {
		// TODO: Implement
		if(remBeans>0){
			return remBeans;
		}else
			return 0;
	}

	/**
	 * Returns the x-coordinate for the in-flight bean at the provided y-coordinate.
	 * 
	 * @param yPos the y-coordinate in which to look for the in-flight bean
	 * @return the x-coordinate of the in-flight bean; if no bean in y-coordinate, return NO_BEAN_IN_YPOS
	 */
	public int getInFlightBeanXPos(int yPos) {
		// TODO: Implement

		for(int i = 0; i < pins.length; i++){
			if(pins[i][yPos]!=null)
				return i;
		}
		return NO_BEAN_IN_YPOS;
	}

	/**
	 * Returns the number of beans in the ith slot.
	 * 
	 * @param i index of slot
	 * @return number of beans in slot
	 */
	public int getSlotBeanCount(int i) {
		// TODO: Implement
		int location = 0;
		for(int j = 0; j < slots[i].length; j++){
			if(slots[i][j] == null){
				return location;
			}

			if(slots[i].length == j){
				break;
			}
			location++;

		}
		return location;
	}

	/**
	 * Calculates the average slot bean count.
	 * 
	 * @return average of all slot bean counts
	 */
	public double getAverageSlotBeanCount() {
		// TODO: Implement
		double totalCnt = 0;
		double avg;
		for(int i = 0; i<slotNumber; i++){
			totalCnt += (getSlotBeanCount(i) * i);
		}

		avg = totalCnt / otherBeans.length;
		return avg;
	}

	/**
	 * Removes the lower half of all beans currently in slots, keeping only the
	 * upper half. If there are an odd number of beans, remove (N-1)/2 beans, where
	 * N is the number of beans. So, if there are 3 beans, 1 will be removed and 2
	 * will be remaining.
	 */
	public void upperHalf() {
		// TODO: Implement

		int totBeans = 0;
		int remove;
		int j = 0;
		int removeFromSlot;

		for(int i = 0; i < slotNumber; i++){
			totBeans += getSlotBeanCount(i);
		}

		if(totBeans % 2 != 0){
			remove = (totBeans-1)/2;
		}else{
			remove = totBeans/2;

		}

		while(remove > 0){
			removeFromSlot = getSlotBeanCount(j);

			removeBeans(j,remove);

			remove-=removeFromSlot;
			j++;
		}

	}

	/**
	 * Removes the upper half of all beans currently in slots, keeping only the
	 * lower half.  If there are an odd number of beans, remove (N-1)/2 beans, where
	 * N is the number of beans. So, if there are 3 beans, 1 will be removed and 2
	 * will be remaining.
	 */
	public void lowerHalf() {
		// TODO: Implement

		int totBeans = 0;
		int remove;
		int j = slotNumber-1;
		int removeFromSlot;

		for(int i = 0; i < slotNumber; i++){
			totBeans += getSlotBeanCount(i);
		}

		if(totBeans % 2 != 0){
			remove = (totBeans-1)/2;
		}else{
			remove = totBeans/2;

		}

		while(remove > 0){
			removeFromSlot = getSlotBeanCount(j);

			removeBeans(j,remove);

			remove-=removeFromSlot;
			j--;
		}
	}

	private void removeBeans(int thisSlot, int cnt){
		int before = cnt;

		for(int i = slots[thisSlot].length - 1; i >-1; i--){
			if(slots[thisSlot]!=null){
				slots[thisSlot] = null;
				before--;

				if(before == 0)
					return;
			}
		}
	}

	/**
	 * A hard reset. Initializes the machine with the passed beans. The machine
	 * starts with one bean at the top. Note: the Bean interface does not have any
	 * methods except the constructor, so you will need to downcast the passed Bean
	 * objects to BeanImpl objects to be able to work with them. This is always safe
	 * by construction (always, BeanImpl objects are created with
	 * BeanCounterLogicImpl objects and BeanBuggy objects are created with
	 * BeanCounterLogicBuggy objects according to the Config class).
	 * 
	 * @param beans array of beans to add to the machine
	 */
	public void reset(Bean[] beans) {
		// TODO: Implement

		if(beans.length == 0){
			remBeans = 0; 
		}

		for(int i = 0; i < slotNumber; i++){
			slots[i] = new BeanImpl[beans.length];	
		}
		otherBeans = new BeanImpl[beans.length];

		for(int i = 0; i < beans.length; i++){
			otherBeans[i] = beans[i];
		}

		remBeans = beans.length;
		pins[0][0] = getPrimeBean();
	}

	/**
	 * Repeats the experiment by scooping up all beans in the slots and all beans
	 * in-flight and adding them into the pool of remaining beans. As in the
	 * beginning, the machine starts with one bean at the top.
	 */
	public void repeat() {
		// TODO: Implement

		int bound = 0;

		for(int i = 0; i < slotNumber; i++){
			bound = getSlotBeanCount(i);
			getBeans(0,bound-1,i);
		}
		pins[0][0] = getPrimeBean();
	}

	void getBeans(int lowerBound, int upperBound, int slot){
		for(int i = lowerBound; i <= upperBound; i++){
			otherBeans[remBeans] = slots[slot][i];
			if(otherBeans[remBeans] != null){
				remBeans++;
			}
			slots[slot][i] = null;
		}
		
	}

	/**
	 * Advances the machine one step. All the in-flight beans fall down one step to
	 * the next peg. A new bean is inserted into the top of the machine if there are
	 * beans remaining.
	 * 
	 * @return whether there has been any status change. If there is no change, that
	 *         means the machine is finished.
	 */
	public boolean advanceStep() {
		// TODO: Implement
		boolean isChange = false;
		BeanImpl target = null;

		for(int i = 0; i < pins.length; i++){
			if(pins[i][slotNumber - 1] != null){
				addIn(i,pins[i][slotNumber-1]);
				pins[i][slotNumber-1] = null;
				isChange = true;
				break;
			}
		}

		for(int j = pins.length -1; j > -1; j--){
			for(int k = pins[0].length - 2; k >-1; k--){
				if(pins[j][k] != null){
					if(pins[j][k].changePosition(j)){
						pins[j+1][k+1] = pins[j][k];
					}else{
						pins[j][k+1] = pins[j][k];
					}

					pins[j][k] = null;
					isChange = true;
				}

			}
		}

		pins[0][0] = getPrimeBean();
		return isChange;
	}
	

	private BeanImpl getPrimeBean(){

		if(remBeans == 0){
			return null;
		}

		remBeans--;

		BeanImpl oth = (BeanImpl)otherBeans[0];

		for(int i = 0; i < otherBeans.length - 1;i++){
			otherBeans[i] = otherBeans[i+1];

			if(otherBeans[i] == null){
				return oth;
			}
		}

		otherBeans[remBeans] = null;
		return oth;
	}

	void addIn(int num, BeanImpl addNew){
		for(int i = 0; i < slots[0].length; i++){
			if(slots[num][i] == null){
				slots[num][i] = addNew;
				return;
			}
		}
	}
	/**
	 * Number of spaces in between numbers when printing out the state of the machine.
	 * Make sure the number is odd (even numbers don't work as well).
	 */
	private int xspacing = 3;

	/**
	 * Calculates the number of spaces to indent for the given row of pegs.
	 * 
	 * @param yPos the y-position (or row number) of the pegs
	 * @return the number of spaces to indent
	 */
	private int getIndent(int yPos) {
		int rootIndent = (getSlotCount() - 1) * (xspacing + 1) / 2 + (xspacing + 1);
		return rootIndent - (xspacing + 1) / 2 * yPos;
	}

	/**
	 * Constructs a string representation of the bean count of all the slots.
	 * 
	 * @return a string with bean counts for each slot
	 */
	public String getSlotString() {
		StringBuilder bld = new StringBuilder();
		Formatter fmt = new Formatter(bld);
		String format = "%" + (xspacing + 1) + "d";
		for (int i = 0; i < getSlotCount(); i++) {
			fmt.format(format, getSlotBeanCount(i));
		}
		fmt.close();
		return bld.toString();
	}

	/**
	 * Constructs a string representation of the entire machine. If a peg has a bean
	 * above it, it is represented as a "1", otherwise it is represented as a "0".
	 * At the very bottom is attached the slots with the bean counts.
	 * 
	 * @return the string representation of the machine
	 */
	public String toString() {
		StringBuilder bld = new StringBuilder();
		Formatter fmt = new Formatter(bld);
		for (int yPos = 0; yPos < getSlotCount(); yPos++) {
			int xBeanPos = getInFlightBeanXPos(yPos);
			for (int xPos = 0; xPos <= yPos; xPos++) {
				int spacing = (xPos == 0) ? getIndent(yPos) : (xspacing + 1);
				String format = "%" + spacing + "d";
				if (xPos == xBeanPos) {
					fmt.format(format, 1);
				} else {
					fmt.format(format, 0);
				}
			}
			fmt.format("%n");
		}
		fmt.close();
		return bld.toString() + getSlotString();
	}

	/**
	 * Prints usage information.
	 */
	public static void showUsage() {
		System.out.println("Usage: java BeanCounterLogic slot_count bean_count <luck | skill> [debug]");
		System.out.println("Example: java BeanCounterLogic 10 400 luck");
		System.out.println("Example: java BeanCounterLogic 20 1000 skill debug");
	}
	
	/**
	 * Auxiliary main method. Runs the machine in text mode with no bells and
	 * whistles. It simply shows the slot bean count at the end.
	 * 
	 * @param args commandline arguments; see showUsage() for detailed information
	 */
	public static void main(String[] args) {
		boolean debug;
		boolean luck;
		int slotCount = 0;
		int beanCount = 0;

		if (args.length != 3 && args.length != 4) {
			showUsage();
			return;
		}

		try {
			slotCount = Integer.parseInt(args[0]);
			beanCount = Integer.parseInt(args[1]);
		} catch (NumberFormatException ne) {
			showUsage();
			return;
		}
		if (beanCount < 0) {
			showUsage();
			return;
		}

		if (args[2].equals("luck")) {
			luck = true;
		} else if (args[2].equals("skill")) {
			luck = false;
		} else {
			showUsage();
			return;
		}
		
		if (args.length == 4 && args[3].equals("debug")) {
			debug = true;
		} else {
			debug = false;
		}

		// Create the internal logic
		BeanCounterLogicImpl logic = new BeanCounterLogicImpl(slotCount);
		// Create the beans (in luck mode)
		BeanImpl[] beans = new BeanImpl[beanCount];
		for (int i = 0; i < beanCount; i++) {
			beans[i] = new BeanImpl(slotCount, luck, new Random());
		}
		// Initialize the logic with the beans
		logic.reset(beans);

		if (debug) {
			System.out.println(logic.toString());
		}

		// Perform the experiment
		while (true) {
			if (!logic.advanceStep()) {
				break;
			}
			if (debug) {
				System.out.println(logic.toString());
			}
		}
		// display experimental results
		System.out.println("Slot bean counts:");
		System.out.println(logic.getSlotString());
	}
}
