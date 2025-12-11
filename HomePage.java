package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    
    // Locators for MakeMyTrip
    @FindBy(xpath = "//li[@data-cy='menu_Flights']")
    private WebElement flightsMenu;
    
    @FindBy(xpath = "//span[@data-cy='closeModal']")
    private WebElement closeLoginPopup;
    
    public HomePage(WebDriver driver) {
        super(driver);
    }
    
    public void navigateToFlights() {
        try {
            // Close login popup if present
            if (closeLoginPopup.isDisplayed()) {
                closeLoginPopup.click();
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            // Popup not present, continue
        }
        
        flightsMenu.click();
        System.out.println("✓ Navigated to Flights section");
    }
}
