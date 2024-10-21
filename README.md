# Extend_Task

## Overview
This project is designed to automate the testing of various API functionalities using the Rest Assured library and Java, targeting the ReqRes API. The test suite includes operations such as creating users, editing users, logging in, and listing users. It implements structured and reusable code for API interactions, test data generation, and helper utilities.

## Key Features:
- Automated test scripts for API interactions like Create User, Edit User, Login, and List Users.
- Assertions to verify response status codes and response body content.
- Logging integration using Extent Reports for detailed HTML reports, showing test execution results.
- Parameterized testing using data providers to pass multiple sets of data.
- Code structure that includes classes for test data, helper functions, and APIs.

## API Features Tested
- **Create User**: Sends a POST request to create a new user and validates the response status and body.
- **Edit User**: Updates an existing user's details via a PUT request and checks for successful updates.
- **Login**: Tests both valid and invalid login scenarios, checking the response for tokens in successful logins.
- **List Users**: Retrieves a list of users using a GET request and validates the response.

## Technologies Used
- **Java**: Programming language used for developing the test suite.
- **Rest Assured**: API testing library for handling HTTP requests and responses.
- **POJO Classes**: To manage request/response data structures.
- **Extent Reports**: For generating detailed HTML reports that include request and response logs.
