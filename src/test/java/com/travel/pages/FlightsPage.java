package com.travel.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FlightsPage {

    private WebDriver driver;

    private By fromPort = By.name("fromPort");
    private By toPort = By.name("toPort");
    private By findFlightsButton = By.xpath("//input[@type='submit']");

    public FlightsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectFromCity() {
        driver.findElement(fromPort).sendKeys("Boston");
    }

    public void selectToCity() {
        driver.findElement(toPort).sendKeys("London");
    }

    public void clickFindFlights() {
        driver.findElement(findFlightsButton).click();
    }
}
