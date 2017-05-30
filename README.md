# QA Case Study
This repository contains the solution for QA Case stady saved in initial-task.docx file


**Task 1. SQL query.pdf** - file with a solution for 1st task 

**Task 2. Medium API - Test session report.pdf** - test plan and result of a manual test session for task #2 in bonial-qa-api.docx file

## Medium.com API auto-tests
This folder contains automated tests for tags test cases for POST /post, and also one test for GET /me end points

### How to start

1. Install Maven on your environement (https://maven.apache.org/install.html)
1. Clone this repository to local folder
1. Switch to **mediumapitest** folder and run following command:
```
mvn test -P AllApiTests - to run all available tests
mvn test -P TagsTest - to run test only for Tags suite
```

To edit or extend test suits use any developer IDE you prefer.
