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
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
        System.out.println("Browser initialized!");
    }

    @Test(priority = 1)
    public void testCompleteFlightSearchFlow() throws InterruptedException {
        System.out.println("\n===== STARTING FLIGHT SEARCH TEST =====");

        // Step 1: Navigate to MakeMyTrip
        driver.get("https://www.makemytrip.com");
        System.out.println("✓ Navigated to MakeMyTrip");

        // Step 2: Go to Flights section
        homePage = new HomePage(driver);
        homePage.navigateToFlights();

        // Step 3: Enter source and destination
        flightSearchPage = new FlightSearchPage(driver);
        flightSearchPage.enterCities("Delhi", "Mumbai");

        // Step 4: Select date
        flightSearchPage.selectDate();

        // Step 5: Click Search
        flightSearchPage.clickSearch();

        // Step 6: Find and print cheapest flight
        flightSearchPage.getMinimumFlightPrice();

        // Step 7: Open Google in new tab
        flightSearchPage.openGoogleInNewTab();
        flightSearchPage.switchToNewTab();
        flightSearchPage.searchOnGoogle("MakeMyTrip flights");

        System.out.println("===== TEST COMPLETED =====\n");
    }

    @Test(priority = 2)
    public void additionalTestScenario() throws InterruptedException {
        System.out.println("\n===== ADDITIONAL TEST SCENARIO =====");

        driver.get("https://www.makemytrip.com/flights/");
        flightSearchPage = new FlightSearchPage(driver);

        flightSearchPage.enterCities("Bangalore", "Chennai");
        flightSearchPage.selectDate();
        flightSearchPage.clickSearch();

        System.out.println("✓ Additional test: Searched Bangalore to Chennai flights");
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000); // simple wait before closing
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed. Test finished!");
        }
    }
}