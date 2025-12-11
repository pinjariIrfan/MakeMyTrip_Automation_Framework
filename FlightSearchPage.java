package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FlightSearchPage extends BasePage {

    @FindBy(xpath = "//label[@for='fromCity']")
    private WebElement fromCityInput;

    @FindBy(xpath = "//input[@placeholder='From']")
    private WebElement fromInputBox;

    @FindBy(xpath = "//label[@for='toCity']")
    private WebElement toCityInput;

    @FindBy(xpath = "//input[@placeholder='To']")
    private WebElement toInputBox;

    @FindBy(xpath = "//div[@aria-label='Fri Jan 10 2025']")
    private WebElement departureDate;

    @FindBy(xpath = "//a[text()='Search']")
    private WebElement searchButton;

    @FindBy(xpath = "//p[@data-cy='price']")
    private List<WebElement> priceList;

    @FindBy(xpath = "//p[@class='appendBottom5 font14 blackText']")
    private WebElement flightName;

    @FindBy(xpath = "//span[@class='bgProperties icon20 overlayCrossIcon']")
    private WebElement closeAdPopup;

    public FlightSearchPage(WebDriver driver) {
        super(driver);
    }

    // Enter source and destination cities
    public void enterCities(String fromCity, String toCity) throws InterruptedException {

        waitForElement(fromCityInput);
        fromCityInput.click();
        fromInputBox.sendKeys(fromCity);
        Thread.sleep(800);
        fromInputBox.sendKeys(Keys.ENTER);

        waitForElement(toCityInput);
        toCityInput.click();
        toInputBox.sendKeys(toCity);
        Thread.sleep(800);
        toInputBox.sendKeys(Keys.ENTER);

        System.out.println("From and To cities selected");
    }

    // Select fixed date (you can parameterize later)
    public void selectDate() {
        waitForElement(departureDate);
        departureDate.click();
        System.out.println("Departure date selected");
    }

    // Hit search button
    public void clickSearch() throws InterruptedException {
        waitForElement(searchButton);
        searchButton.click();
        System.out.println("Clicked on Search button");
        Thread.sleep(3000); // simplest wait on fresher level
    }

    // Extract minimum price from the available flights
    public int getMinimumFlightPrice() {

        try {
            closeAdPopup.click();  // close any ad if appears
        } catch (Exception ignored) {}

        waitForElementList(priceList);

        List<Integer> prices = new ArrayList<>();

        for (WebElement price : priceList) {
            String txt = price.getText().replace("₹", "").replace(",", "").trim();
            if (!txt.isEmpty()) {
                prices.add(Integer.parseInt(txt));
            }
        }

        if (prices.isEmpty()) {
            System.out.println("No prices found!");
            return -1;
        }

        int minPrice = Collections.min(prices);
        System.out.println("Minimum Price: " + minPrice);

        return minPrice;
    }

    // Extract flight name from details page
    public String getFlightName() {

        try {
            return flightName.getText();
        } catch (Exception e) {
            return "Flight Name Not Visible";
        }
    }

    // Open new tab for Google search
    public void openGoogleInNewTab() {
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.open('https://www.google.com','_blank');");
        System.out.println("Opened Google in new tab");
    }

    // Switch to new tab
    public void switchToNewTab() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        System.out.println("Switched to second tab");
    }

    // Perform Google search
    public void searchOnGoogle(String text) throws InterruptedException {
        WebElement googleBox = driver.findElement(By.name("q"));
        googleBox.sendKeys(text);
        Thread.sleep(700);
        googleBox.sendKeys(Keys.ENTER);
        System.out.println("Searched on Google: " + text);
    }
}