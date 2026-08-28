🚀 ToDo App - Automated Test Suite
A hybrid test automation framework designed to validate both UI and API layers for a ToDo Web Application. This project demonstrates efficient test execution by leveraging API requests for state preparation (e.g., automated user registration and data setup) and Selenium WebDriver for UI assertions, significantly reducing overall test execution time.

🛠️ Tech Stack & Prerequisites
Language: Java 8+

Build Tool: Maven

Testing Framework: TestNG

Web Automation: Selenium WebDriver (Page Object Model design pattern)

API Testing / Setup: RestAssured

Reporting: Allure Report

🏗️ Architecture & Framework Highlights
Page Object Model (POM): Enforces clean separation between test scripts and page-specific UI elements or interactions.

API Data Injection: Utilizes API endpoints to bypass UI authentication and test setup overhead by directly injecting cookies (RestAssured to Browser) for faster execution.

Allure Integration: Annotated with @Feature, @Story, and @Description to generate detailed and visual test reporting.

Config Management: Centralized configuration handling using utility classes (ConfigUtils).

🧪 Test Scenarios Covered
Auth Feature:

Login Test: Validates successful user login via valid credentials using UI navigation.

Todo Feature:

Add Todo: Registers a user via API, injects cookies to bypass manual UI login, adds a task via API, and asserts task creation on the UI.

Delete Todo: Prepares test data via API calls and asserts the task deletion flow through UI interactions.

🚀 How to Run the Tests
Clone the repository:

Bash
git clone https://github.com/your-username/ToDoApp.git
cd ToDoApp
Run tests via Maven:

Bash
mvn clean test
Generate Allure Report:

Bash
mvn allure:serve
