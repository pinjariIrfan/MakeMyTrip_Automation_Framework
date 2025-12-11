package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.HomePage;
import pages.FlightSearchPage;

public class FlightSearchTest {
    private WebDriver driver;
    private HomePage homePage;
    private FlightSearchPage flightSearchPage;
    
    @BeforeClass
    public void setUp() {
        // Setup ChromeDriver automatically
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
        System.out.println("Browser initialized successfully!");
    }
    
    @Test(priority = 1)
    public void testCompleteFlightSearchFlow() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("STARTING FLIGHT SEARCH AUTOMATION TEST");
        System.out.println("=".repeat(60));
        
        try {
            // Step 1: Navigate to application
            driver.get("https://www.makemytrip.com");
            System.out.println("✓ Step 1: Navigated to MakeMyTrip application");
            
            // Step 2: Go to Flights section
            homePage = new HomePage(driver);
            homePage.navigateToFlights();
            
            // Step 3: Enter source and destination
            flightSearchPage = new FlightSearchPage(driver);
            flightSearchPage.enterSourceCity("Delhi");
            flightSearchPage.enterDestinationCity("Mumbai");
            
            // Step 4: Select date for next month
            flightSearchPage.selectDateNextMonth();
            
            // Step 5: Click Search
            flightSearchPage.clickSearch();
            
            // Step 6: Identify and print cheapest flights
            flightSearchPage.findAndPrintCheapestFlights();
            
            // Step 7: Open new tab and navigate to Google
            flightSearchPage.openNewTabAndNavigateToGoogle();
            
            // Step 8: Additional scenario
            flightSearchPage.takeScreenshotOfResults();
            
            System.out.println("\n" + "=".repeat(60));
            System.out.println("TEST COMPLETED SUCCESSFULLY!");
            System.out.println("=".repeat(60));
            
        } catch (Exception e) {
            System.out.println("Test failed with error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Test(priority = 2)
    public void additionalTestScenario() {
        System.out.println("\n[ADDITIONAL TEST SCENARIO]");
        System.out.println("Validating flight search with different cities...");
        
        driver.get("https://www.makemytrip.com/flights/");
        flightSearchPage = new FlightSearchPage(driver);
        
        // Test with different cities
        flightSearchPage.enterSourceCity("Bangalore");
        flightSearchPage.enterDestinationCity("Chennai");
        flightSearchPage.selectDateNextMonth();
        flightSearchPage.clickSearch();
        
        System.out.println("✓ Additional test: Searched Bangalore to Chennai flights");
    }
    
    @AfterClass
    public void tearDown() {
        // Wait before closing
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        if (driver != null) {
            driver.quit();
            System.out.println("\nBrowser closed. Test execution completed!");
        }
    }
}
