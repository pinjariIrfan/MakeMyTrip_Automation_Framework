package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(xpath = "//li[@data-cy='menu_Flights']")
    private WebElement flightsMenu;

    @FindBy(xpath = "//span[@data-cy='closeModal']")
    private WebElement closeLoginPopup;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void navigateToFlights() {

        try {
            // Close login popup if visible
            if (closeLoginPopup.isDisplayed()) {
                closeLoginPopup.click();
                Thread.sleep(800);   // fresher level wait is ok here
            }
        } catch (Exception e) {
            // popup not present, ignore and continue
        }

        waitForElement(flightsMenu);
        flightsMenu.click();
        System.out.println("Navigated to Flights section");
    }
}