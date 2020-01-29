# Unit Testing Exercise 2

## Description

In this exercise, we will simulate the main Rent-A-Cat rental system software.  This is obviously a "toy" implementation of the vast and powerful Rent-A-Cat apparatus.

I have created a framework for you for this exercise.  It is up to you to fill in the `returnCat()`, `rentCat()`, `listCats()` and `catExists()` methods, and write unit tests for them.  Unit tests must use doubles for the Cat object with appropriate stubbing.  I have intentionally inserted a defect on every Cat method such that an exception is fired if you try to use a real Cat object in any way during your unit testing!  Those defects are turned off when Cat is used within the main RentACat program.

Rent-A-Cat rents cats to customers for various needs (mousing, companionship, homework help, etc.).  From the main menu, users may:

1. See list of cats for rent
2. Rent a cat to a customer
3. Return a cat
4. Quit

A cat which is out for rental cannot be rented and will not be listed until it has been returned.  As part of this exercise, we will not charge money.

## Sample Output

```
Option [1,2,3,4] > 1
Cats for Rent
ID 1. Jennyanydots
ID 2. Old Deuteronomy
ID 3. Mistoffelees
Option [1,2,3,4] > 2
Rent which cat? > 1
Jennyanydots has been rented.
Option [1,2,3,4] > 1
Cats for Rent
ID 2. Old Deuteronomy
ID 3. Mistoffelees
Option [1,2,3,4] > 2
Rent which cat? > 1
Sorry, Jennanydots is not here!
Rent which cat? > 7
Invalid cat ID.
Rent which cat? > 3
Mistoffelees has been rented.
Option [1,2,3,4] > 1
Cats for Rent
ID 2. Old Deuteronomy
Option [1,2,3,4] > 3
Return which cat? > 7
Invalid cat ID.  
Return which cat? Jennyanydots
Invalid cat ID.
Return which cat? 1
Welcome back, Jennyanydots!
Option [1,2,3,4] > 1
Cats for Rent
ID 1. Jennyanydots
ID 2. Old Deuteronomy
Option [1,2,3,4] > 4
Closing up shop for the day!
```


You should also write appropriate unit tests for each public method.  There should be at least one unit test per method, and a total of at least SIX unit tests.  You may group these however you like - (e.g., one unit test for three methods, and three for the last one; two unit tests for two methods, one unit test each for the other two; etc.)

Note that while I have provided a TestRunner, I only provided a skeleton Test file with the name RentACatTest!  You will need to fill in the Test file with test cases as well as modify the TestRunner to include the correct classes.  You can view the LinkedList sample code we discussed in class or code under the CommandLineJunit subdirectory for an example of how to do this.

You should use test doubles/mocks for any references to classes other than the one under test (i.e., double or mock any Cat objects).  You may use an ArrayList of doubled objects (that is, you do not need to double ArrayList itself).

You do not need to test any of the methods in the Cat class since that is an external class that is beyond the unit we are trying to test.

## Running Unit Tests

If you do this in an IDE such as Eclipse, or with a build tool like Gradle, this can be handled automatically.  HOWEVER, please do not do this!  I want you to realize what is happening "behind the scenes".

1. First let's do a sanity check to see if Java is installed properly on your machine.  For Windows try doing:
    ```
    run.bat
    ```
    For Mac or Linux, try doing:
    ```
    make
    ```
    Or if that doesn't work (because make is not installed), try doing:
    ```
    bash run.sh
    ```
    If successful, you will get a message "ALL TESTS PASSSED".  But hold your horses, we aren't done yet!
    
2. We haven't yet added our RentACatTest test class to the list of classes tested by JUnit.  There is a simple boilerplate test runner I created for you (TestRunner.java).  It iterates over a list of test classes and invokesJUnitCore.runClasses on each of them.  Our RentACatTest is not on that list.  You need to modify it so that it is.  Read the test runner for NoogieTest and CoogieTest under CommandLineJunit/ to see how.

    If successful, you should get the following message:
    ```
    initializationError(RentACatTest): No runnable methods

    !!! - At least one failure, see above.
    ```
    This is telling you that there are no test cases inside RentACatTest, which takes us to the next step.

3. Now you are ready to add test cases to RentACatTest.  Add a very simple test case that always succeeds:
    ```
    @Test
    public void testShouldPass() {
        assertNull(null);
    }
    ```
    Now you see the message "ALL TESTS PASSED" again.  Yes!
    
4. Now you are ready to start writing the RentACatTest class for real.  Start by adding very simple tests to gain confidence.  Next, try adding more complex cases that require Cat objects.  For that, you will have to modify setUp() to create some Cat test doubles with proper stubs.  We learned how to do that in class.  If you are still unsure, look at the LinkedListTest sample code or the NoogieTest and CoogieTest under the CommandLineJunit/ directory.

## Tips

1. Check to see if junit works on your machine before starting to code.
1. We will try to apply the Test Driven Development (TDD) model here.  Try writing the test case(s) FIRST before writing the code for a feature.  This way, you will always have 100% test coverage for the code you have written.  Hence, if you break any part of it in the course of adding a feature or refactoring your code, you will know immediately.  Also, you will be forced to write code in a testable way.  Otherwise, if you test at the very end, you may have to do some major code refactoring to get to a reasonably testable system.
1. Remember to _not_ double the class under test (i.e. RentACat), only classes that it depends upon (i.e. Cat).  In fact, if you don't double Cat and use the actual Cat objects, your tests will most likely fail.  I have injected artificial defects into the Cat class to emulate an external class that hasn't been completely written yet.
1. The easiest thing to do is assert against a return value, but you can also assert against attributes of an object.  For example:
    ```
    @Test
    public void testChangeCatName() {
       Cat c = new Cat("Bustopher Jones", 150.00);
       String newName = "Growltiger";
       c.setName(newName);
       assertEquals(c.getName(), newName);
    }
    ```
    You can also use the Mockito verify method to perform behavior verification.
1. Try making use of the @Before and @After methods in your JUnit testing.  @Before and @After methods are invoked before and after each @Test method.  They are used to set up some program state required by preconditions and to tear down the setup.  In JUnit terminology, the set of objects with fixed state involved in the preconditions is called a Test Fixture.  The test fixture will work as a baseline for all tests in the test class and allow you to avoid repeating code.  Here you will create and initialize all objects you will be commonly using in your test cases, including all mock objects.

* Try to ensure that you check not only for "happy path" cases but also edge cases.
* Tests are usually grouped into whichever classes they are testing, and have a filename that has `Test` appended to the name.  For example, Foo.java would be tested by FooTest.java.
* Testing println's or other output is difficult - try to have methods return Strings which are easier to test.  It is possible to test for I/O but it requires some extra steps - see Chapter 14, Section 6 of the textbook for instructions.
  
## Submission

Please do a Text Submission to Courseweb with a link to the GitHub repository where you stored it before the beginning of the next class, along with names of all group members.

Example:

John Doe  
Jane Doe  
https://github.com/wonsunahn/CS1632_Fall2019/tree/master/exercises/2

In the repository, beside your code, please add the following two items.

1. Add a screenshot of the command line output from unit testing and name it unit_test.png.  Example:

    https://github.com/wonsunahn/CS1632_Fall2019/blob/master/exercises/2/unit_test.png

    Don't mind the WARNINGs in the screenshot.  It is just telling you the Mockito library is trying to use some features of reflection that is disallowed in more recent versions of the JRE.  The important part is that "ALL TESTS PASSED".  You want to get that for this exercise and also your delivarable.

1. Add a screenshot of code coverage stats given by your IDE of choice and name it code_coverage.png. Example:

    https://github.com/wonsunahn/CS1632_Fall2019/blob/master/exercises/2/code_coverage.png

    I used Eclipse to generate the screenshot.  Here is the user guide: https://www.eclemma.org/userdoc/launching.html.  It is just a click of a button and requires no extra installation.  My screenshot shows 100% code coverage for the public methods we tested.  You don't have to have 100% coverage for this exercise but you will have coverage requirements for your deliverable.  For those of you who are new to eclipse, you need to include the four JAR files under CommandLineJUnit/ as external JARs for it to compile.  You need to go to project properties > Java Build Path > Libraries and Add JARs or Add External JARs.  Also, don't create module-info.java when prompted.
    
    When you run the code coverage tool, you need to run TestRunner.java (modified to run your JUnit test class), not RentACat.java.  You can do that by clicking on and highlighting TestRunner.java before clicking on the code coverage button.  Alternatively, you can right click on TestRunner.java and click on the "Coverage as" item in the menu that pops up.  This is important.  If you run RentACat.java, you will be getting the code coverage while playing the game.

Please submit by Monday (9/30) 11:59 PM to get timely feedback.

IMPORTANT: Please keep the github private and add the following users as collaborators: nikunjgoel95, wonsunahn
