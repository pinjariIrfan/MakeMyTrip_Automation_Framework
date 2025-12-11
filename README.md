MakeMyTrip Automation Framework (Selenium + TestNG + Maven)

This project contains an automation framework for MakeMyTrip built using Java, Selenium WebDriver, TestNG, Maven, and the Page Object Model (POM) design pattern.

It automates the complete flight search flow and extracts the cheapest and second cheapest flight details as required in the assignment.

Project Structure (Simple Explanation)

pages/ → Contains Page Object classes for Home, Flights, and Results pages

base/ → Contains BaseTest to set up and close the browser

driver/ → Manages WebDriver

tests/ → Contains the main TestNG test

utils/ → Contains wait and browser utility functions

pom.xml → All dependencies (Selenium, TestNG, WebDriverManager)

testng.xml → Test execution file

What This Framework Automates

Navigate to MakeMyTrip
Opens the browser and loads the home page.

Click on Flights Section
Ensures the flights module is opened.

Enter Source & Destination
Search and select cities from auto-suggestions.

Select a Date for the Next Month
Automatically navigates the calendar and selects a valid travel date.

Click on Search
Starts the flight search.

Extract Cheapest Flight Details
Reads all flight cards, sorts based on price, and prints:

Airline name

Departure time

Arrival time

Price

Open a New Browser Tab & Navigate to Google
Uses driver control to open a new tab, switch to it, and load Google.
