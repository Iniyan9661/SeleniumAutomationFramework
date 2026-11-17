@login
Feature: OrangeHRM Login functionality
 As a user of OrangeHRM
 I want to log in to the application
 So that I can access the dashboard
 Background:
 Given the user is on the OrangeHRM login page
 @smoke @positive
 Scenario: Successful login with valid credentials
 When the user enters username "Admin" and password "admin123"
 And the user clicks the login button
 Then the user should be redirected to the dashboard
 @negative
 Scenario: Login fails with invalid password
 When the user enters username "Admin" and password "wrong123"
 And the user clicks the login button
 Then the error message "Invalid credentials" should be displayed
 @negative
 Scenario: Login fails with invalid username
 When the user enters username "InvalidUser" and password "admin123"
 And the user clicks the login button
 Then the error message "Invalid credentials" should be displayed
 @negative
 Scenario: Login fails with both fields empty
 When the user clicks the login button
 Then the required field validation should be displayed
 @negative
 Scenario Outline: Login with multiple invalid credential sets
 When the user enters username "<username>" and password "<password>"
 And the user clicks the login button
 Then the error message "Invalid credentials" should be displayed
 Examples:
 | username | password |
 | Admin | admin |
 | admin1 | admin123 |
 | test | test123 |
 @ui
 Scenario: Forgot password link is available
 When the user clicks the forgot your password link
 Then the reset password page should be displayed