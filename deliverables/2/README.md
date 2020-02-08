# CS 1632 - Software Quality Assurance
Spring Semester 2020

* DUE: February 28, 2020 11:59 PM

## Deliverable 2

For this assignment, your group will write code and unit tests for an
authorized reproduction of Coffee Maker Quest.  

Requirements for this program are in the requirements.txt file in this
directory.  In case of ambiguity, please see the original program
coffeemaker.jar as an example of what to display and how the system should
work.

Some of the work has already been done for you.  Classes such as
CoffeeMakerQuest.java, Config.java, Game.java, Player.java, Room.java, and
TestRunner.java are already complete.  You need only modify
CoffeeMakerQuestImpl.java and CoffeeMakerQuestTest.java.  As in the
exercise, the places where you need to modify code are marked by the // TODO
comments.  DO NOT TOUCH the already complete classes as they will be used AS
IS during grading.  Here is a brief rundown of the classes:

* CoffeeMakerQuest.java - the interface for the CoffeeMakerQuest game engine
* Config.java - allows configuration of bug injection into various classes
* Game.java - contains the main method; generates rooms and runs the game using the CoffeeMakerQuest engine
* Player.java - player object with inventory information
* Room.java - room object with furnishings and items
* TestRunner.java - the runner for the JUnit test class CoffeeMakerQuestTest
* CoffeeMakerQuestImpl.java - an implementation of CoffeeMakerQuest (_modify_)
* CoffeeMakerQuestTest.java - JUnit test class CoffeeMakerQuest (_modify_)


1. To run the game you need to invoke the Game class.  For Windows:
    ```
    runGame.bat
    ```
    For Mac or Linux, try doing:
    ```
    bash runGame.sh
    ```
    When you run it without any modification, you will suffer an exception and crash.  That is of course because you have not completed implementing CoffeeMakerQuestImpl.java!

1. To run the JUnit tests on CoffeeMakerQuestImpl, for Windows:
    ```
    runTest.bat
    ```
    For Mac or Linux, try doing:
    ```
    bash runTest.sh
    ```
    When you run it without any modification, you will get "ALL TESTS PASSED".  But don't get delirious.  That is because all your tests are currently empty.

1. To run the JUnit tests on CoffeeMakerQuestBuggy (included in the form of
   the coffeemaker-buggy.jar file), for Windows:
    ```
    runTestBuggy.bat
    ```
    For Mac or Linux, try doing:
    ```
    bash runTestBuggy.sh
    ```

Code coverage should be at an absolute minimum of 80% for each of your classes.

## Development Methodology

Like Exercise 2, we will try to apply the Test Driven Development (TDD) model
here.  Try writing the test case(s) FIRST before writing the code for a
feature.  This way, you will always have 100% test coverage for the code you
have written and are writing.  Hence, if you break any part of it in the course
of adding a feature or refactoring your code, you will know immediately.

## Expected Outcome

If you did a good job, you should see the following output when running runTest.bat (or runTest.sh):
```
ALL TESTS PASSED
```

Also, you should see the following output or similar when running runTestBuggy.bat (or runTestBuggy.sh):
```
TESTING BUGGY IMPLEMENTATION

testAddRoomAtNorthDuplicate(CoffeeMakerQuestTest): null
testGetCurrentRoom(CoffeeMakerQuestTest): expected null, but was:<Room@7225790e>
testAddRoomAtNorthUnique(CoffeeMakerQuestTest):
Argument(s) are different! Wanted:
room.setNorthDoor("North");
-> at CoffeeMakerQuestTest.testAddRoomAtNorthUnique(CoffeeMakerQuestTest.java:124)
Actual invocation has different arguments:
room.setNorthDoor("South");
-> at CoffeeMakerQuestBuggy.addRoomAtNorth(CoffeeMakerQuestBuggy.java:66)

testProcessCommandDWin(CoffeeMakerQuestTest): expected:<...gar.

You drink the [beverage and are ready to study!
You win]!
> but was:<...gar.

You drink the [air, as you have no coffee, sugar, or cream.
The air is invigorating, but not invigorating enough. You cannot study.
You lose]!
>
testProcessCommandDLose(CoffeeMakerQuestTest): expected:<...GAR!

You drink the [air, as you have no coffee, sugar, or cream.
The air is invigorating, but not invigorating enough. You cannot study.
You lose]!
> but was:<...GAR!

You drink the [beverage and are ready to study!
You win]!
>
testGetInstructionsString(CoffeeMakerQuestTest): expected:< INSTRUCTIONS [(N,S,L,I,D,H) ]> > but was:< INSTRUCTIONS []> >
testProcessCommandLCream(CoffeeMakerQuestTest):
Argument(s) are different! Wanted:
player.addItem(CREAM);
-> at CoffeeMakerQuestTest.testProcessCommandLCream(CoffeeMakerQuestTest.java:199)
Actual invocation has different arguments:
player.addItem(COFFEE);
-> at CoffeeMakerQuestBuggy.processCommand(CoffeeMakerQuestBuggy.java:151)

testProcessCommandI(CoffeeMakerQuestTest): expected:<[YOU HAVE NO COFFEE!
YOU HAVE NO CREAM!
YOU HAVE NO SUGAR!]
> but was:<[Player has nothing.]
>
testProcessCommandN(CoffeeMakerQuestTest): expected:<Mock for Room, hashCode: 1355316001> but was:<Mock for Room, hashCode: 1597462040>
testProcessCommandS(CoffeeMakerQuestTest): expected:<Mock for Room, hashCode: 403716510> but was:<Room@32d992b2>
testAddFirstRoom(CoffeeMakerQuestTest): null
testSetCurrentRoom(CoffeeMakerQuestTest): null

!!! - At least one failure, see above.```
```
This tells you that you have written your JUnit tests well so that they are able to find the bugs in CoffeeMakerQuestBuggy.

## Grading Breakdown

TBA soon.

## Submission

Each pairwise group will submit the exercise *once* to GradeScope, by *one member* of the group.  The submitting member will press the "View or edit group" link at the top-right corner of the assignment page after submission to add his/her partner.  That way, the feedback will be accessible to both of you.  I recommend that you divide the list of methods to implement / test into two halves and working on one half each.

You will do two submissions for this exercise.

1. You will create a github repository just for exercise 2.  Add your partner as a collaborator so both of you have access.  Make sure you keep the repository *PRIVATE* so that nobody else can access your repository.  This applies to all future submissions for this course.  Once you are done modifying code, don't forget to commit and push your changes to the github repository.  When you are done, submit your github repository to GradeScope at the "Deliverable 2 GitHub" link.  Once you submit, GradeScope will run the autograder to grade you and give feedback.  If you get deductions, fix your code based on the feedback and resubmit.  Repeat until you don't get deductions.

1. Create a screenshot of code coverage stats given by your IDE of choice and name it code_coverage.png. Example:

    https://github.com/wonsunahn/CS1632_Spring2020/blob/master/deliverables/2/code_coverage.png

    I used Eclipse to generate the screenshot.  Here is the user guide: https://www.eclemma.org/userdoc/launching.html.  It is just a click of a button and requires no extra installation.  You don't have to have 100% coverage for this exercise but you will have coverage requirements for your deliverable.  I have already created an Eclipse project for you in the exercise directory so you can just open that to run TestRunner using File > Open Projects from File System from the menu.  If you can't open the project for some reason, you need to create a new project using File > New > Java Project.  For those of you who are new to eclipse, you need to include the four JAR files under CommandLineJUnit/ as external JARs for it to compile.  You need to go to project properties > Java Build Path > Libraries and Add JARs or Add External JARs.  Also, don't create module-info.java when prompted.
    
    When you run the code coverage tool, make sure you run TestRunner, not RentACatImpl.  You can do that by clicking on and highlighting TestRunner.java before clicking on the code coverage button.  Alternatively, you can right click on TestRunner.java and click on the "Coverage as" item in the menu that pops up.  This is important.  If you run RentACat.java, you will be getting the code coverage while playing the game.

    After you have created the screenshot, save the picture to a PDF file and submit to GradeScope at the "Deliverable 2 Coverage" link.  Make sure the picture fits in one page for easy viewing and grading.
