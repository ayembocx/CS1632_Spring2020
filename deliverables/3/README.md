# CS 1632 - Software Quality Assurance
Spring Semester 2020

DUE: TBD

WARNING: This is posted just to give you an idea of what's coming.  Details are subject to change before release.

## Deliverable 3

For this assignment, you and a partner will write systems-level, automated black-box tests for a web app using the Katalon Automation Recorder, a revamped variant of the Selenium IDE. 

The web app is located here: https://cs1632ex.herokuapp.com/

## Format
Everyone should have a title page with:
* Your names
* The URL of your code and tests on GitHub
* The title "CS 1632 - DELIVERABLE 3: Automated Web Testing"

For the summary, add a description of issues you faced when writing these tests and problems you would expect going forward based on your experiences.  If any tests you wrote fail (some should!), they should be included here.

ON A SEPARATE PAGE, write the traceability matrix based on the requirements and test cases.

ON A SEPARATE PAGE, copy and paste the printout of the test execution results shown in the Log window.

ON A SEPARATE PAGE, write a defect report.  There should be at least 3 defects.  Each defect should contain all necessary components including REPRODUCTION STEPS, EXPECTED BEHAVIOR, OBSERVED BEHAVIOR, etc. described in Deliverable 1.  Also, below each defect report, attach a screenshot of the defect.  Screenshots are readily available if you click on the Screenshots window tab beside the Log window tab after running the test suite.  You can download individual screenshots in the .png format by clicking on each thumbnail.

There is no need to print out the code.  It should be shared with me and the TA on GitHub.  The code should be a saved Katalon test suite.  Note that this should be an HTML file.  Please make the test case names descriptive!

There should be a bare minimum of 10 tests, checking various base, edge, and corner cases.  There is a maximum of 20 tests.  However, do not focus on the number of tests too heavily; I am more concerned that you cover all the requirements and check a broad variety of cases based on equivalence classes.

## Requirements

Requirements for the application are listed in the `requirements.md` file in this directory.

Remember to check for implicit as well as explicit requirements!

You should have at least one test for each requirement.

## Defects

This is another buggy product.  You should find at least three defects and report them using the standard defect template (just like in the first deliverable).  These defects should be detected by your automated tests (i.e., there should be at least _three_ failures of your tests).

You may want to do some exploratory testing first to see what kind of issues are found before writing automated tests for them.

## Traceability Matrix

Please make a traceability matrix (just as in the first deliverable).  Tests should be identified by the name of the test case.

## Grading
* Summary and Testing concerns - 10% 
* Printout out of execution log - 5%
* Defect screenshots and reports - 15%
* Traceability matrix - 10%
* Katalon tests - 45%
* Defects are valid - 15%

Please review grading\_rubric.txt for details.

## Submission

Each pairwise group will submit the deliverable once to courseweb, by one member of the group. Under the "Course Documents" menu on the lefthand side, you will see an assignment named "Deliverable 3". Please upload a PDF format of your report. Don't forget your github url.

IMPORTANT: Please keep the github private and add the following users as collaborators: nikunjgoel95, wonsunahn.

Nik, our TA, will record the score for both of you on courseweb, along with feedback on where points have been deducted. You and your partner will get the same score. If you feel otherwise, let me know.

Please post on the discussion board, or email me at wahn@pitt.edu, or come to office hours to discuss any problems you have.
 
