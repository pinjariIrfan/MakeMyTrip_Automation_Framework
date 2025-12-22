MakeMyTrip Automation Assignment
This project is created as part of an automation assignment to demonstrate a simple automation framework using Selenium WebDriver with Java. The automation focuses on a common flight search flow on a travel website.
The purpose of this project is to show understanding of automation basics, framework structure, and the Page Object Model approach.
Technologies used in this project include Java, Selenium WebDriver, Maven, and TestNG.
The automated flow starts by launching the application and navigating to the Flights section. Source and destination locations are entered, and a travel date from the next month is selected. The search is performed and the cheapest and second cheapest flight details are identified and printed. After this, a new browser tab is opened in the same browser session and navigation to Google is completed.
Page Object Model is used to separate page-level actions from test logic. Page classes contain element locators and reusable methods, while test classes control the execution flow.
Condition-based waits are used to handle dynamically loaded elements, and basic validations are applied to ensure flight results are available before extracting prices.
To execute the project, clone the repository, import it as a Maven project, and run the TestNG test class.
