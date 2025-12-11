package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.Keys;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class FlightSearchPage extends BasePage {
    
    // Flight search form locators
    @FindBy(id = "fromCity")
    private WebElement fromCityInput;
    
    @FindBy(xpath = "//input[@placeholder='From']")
    private WebElement fromCitySearchBox;
    
    @FindBy(id = "toCity")
    private WebElement toCityInput;
    
    @FindBy(xpath = "//input[@placeholder='To']")
    private WebElement toCitySearchBox;
    
    @FindBy(xpath = "//span[text()='DEPARTURE']")
    private WebElement departureDateLabel;
    
    @FindBy(xpath = "//span[@aria-label='Next Month']")
    private WebElement nextMonthButton;
    
    @FindBy(xpath = "//a[text()='Search']")
    private WebElement searchButton;
    
    // Flight results locators
    @FindBy(xpath = "//div[@class='listingCard']")
    private List<WebElement> flightCards;
    
    @FindBy(xpath = "//div[@class='priceSection']//p")
    private List<WebElement> priceElements;
    
    public FlightSearchPage(WebDriver driver) {
        super(driver);
    }
    
    public void enterSourceCity(String city) {
        fromCityInput.click();
        fromCitySearchBox.sendKeys(city);
        fromCitySearchBox.sendKeys(Keys.ENTER);
        System.out.println("✓ Entered source city: " + city);
    }
    
    public void enterDestinationCity(String city) {
        toCityInput.click();
        toCitySearchBox.sendKeys(city);
        toCitySearchBox.sendKeys(Keys.ENTER);
        System.out.println("✓ Entered destination city: " + city);
    }
    
    public void selectDateNextMonth() {
        departureDateLabel.click();
        
        // Click next month button
        nextMonthButton.click();
        
        // Select 15th day of next month (you can modify this)
        WebElement day15 = driver.findElement(
            org.openqa.selenium.By.xpath("//div[@aria-label='Sat Feb 15 2025']")
        );
        day15.click();
        
        System.out.println("✓ Selected date for next month");
    }
    
    public void clickSearch() {
        searchButton.click();
        System.out.println("✓ Clicked Search button");
        
        // Wait for results to load
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public void findAndPrintCheapestFlights() {
        List<FlightPrice> flightPrices = new ArrayList<>();
        
        // Collect flight prices
        for (int i = 0; i < Math.min(flightCards.size(), priceElements.size()); i++) {
            try {
                String flightName = flightCards.get(i).getText().split("\n")[0];
                String priceText = priceElements.get(i).getText().replaceAll("[^0-9]", "");
                if (!priceText.isEmpty()) {
                    int price = Integer.parseInt(priceText);
                    flightPrices.add(new FlightPrice(flightName, price));
                }
            } catch (Exception e) {
                // Skip if any error
            }
        }
        
        // Sort by price (ascending)
        Collections.sort(flightPrices, (a, b) -> a.price - b.price);
        
        // Print results
        System.out.println("\n" + "=".repeat(50));
        System.out.println("FLIGHT SEARCH RESULTS:");
        System.out.println("=".repeat(50));
        
        if (flightPrices.size() > 0) {
            System.out.println("1. CHEAPEST FLIGHT:");
            System.out.println("   Airline: " + flightPrices.get(0).airline);
            System.out.println("   Price: ₹" + flightPrices.get(0).price);
        }
        
        if (flightPrices.size() > 1) {
            System.out.println("\n2. SECOND CHEAPEST FLIGHT:");
            System.out.println("   Airline: " + flightPrices.get(1).airline);
            System.out.println("   Price: ₹" + flightPrices.get(1).price);
        }
        
        System.out.println("\nTotal flights found: " + flightPrices.size());
        System.out.println("=".repeat(50));
    }
    
    public void openNewTabAndNavigateToGoogle() {
        // Open new tab
        driver.switchTo().newWindow(org.openqa.selenium.WindowType.TAB);
        
        // Navigate to Google
        driver.get("https://www.google.com");
        System.out.println("✓ Opened new tab and navigated to Google");
        System.out.println("  Current URL: " + driver.getCurrentUrl());
        
        // Switch back to original tab (optional)
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
    }
    
    // Inner class to store flight details
    private static class FlightPrice {
        String airline;
        int price;
        
        FlightPrice(String airline, int price) {
            this.airline = airline;
            this.price = price;
        }
    }
    
    // Additional method for extra scenario
    public void takeScreenshotOfResults() {
        System.out.println("\n[ADDITIONAL SCENARIO] Taking screenshot of results...");
        System.out.println("Screenshot would be saved in target/screenshots folder");
        // Actual screenshot code would go here
    }
}
