package com.travel.tests;

import com.travel.base.BaseTest;
import com.travel.pages.FlightsPage;
import org.testng.annotations.Test;

public class FlightTest extends BaseTest {

    @Test
    public void flightSearchTest() {
        FlightsPage page = new FlightsPage(driver);

        page.selectFromCity();
        page.selectToCity();
        page.clickFindFlights();
    }
}
