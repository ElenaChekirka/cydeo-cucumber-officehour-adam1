package com.cydeo.utilities;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BrowserUtils {

    //This class will be storing only the utility methods that can be used across the project

    //This method will accept int (in seconds) and execute Thread.sleep method for given duration.
    //Arg: int second

    public static void sleep(int second){
        second *= 1000;
        try{
            Thread.sleep(second);
        }catch (InterruptedException e){
        }
    }

    public static void switchWindowAndVerify(String expectedInUrl, String expectedInTitle){
        //This method should switch window and verify title

        Set<String> allWindows = Driver.getDriver().getWindowHandles();

        for (String each : allWindows) {
            Driver.getDriver().switchTo().window(each);
            System.out.println("Curent URL: " + Driver.getDriver().getCurrentUrl());

            if (Driver.getDriver().getCurrentUrl().contains(expectedInUrl)){
                break;
            }
        }
        String actualTitle = Driver.getDriver().getTitle();
        Assert.assertTrue(actualTitle.contains(expectedInTitle));
    }

    public static void verifyTitle(String expectedTitle){
        //This method will verify expected title with actual title

        String actualTitle = Driver.getDriver().getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);
    }

    public static void verifyTitleContains(String expectedInTitle){
        //This method will verify if actual title contains expected title

        String actualTitle = Driver.getDriver().getTitle();
        Assert.assertTrue(actualTitle.contains(expectedInTitle));
    }

    /*
   This method accepts WebElement target,
   and waits for that WebElement not to be displayed on the page
    */
    public static void waitForInvisibilityOf(WebElement target){
        //Create the object of 'WebDriverWait' class, and set up the constructor args
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));

        //use the 'wait' object with the proper syntax to create explicit wait conditions.
        wait.until(ExpectedConditions.invisibilityOf(target));
    }

    /*
    This method accepts String title,
    and waits for that Title to contain given String value.
     */
    public static void waitForTitleContains(String title){
        //Create the object of 'WebDriverWait' class, and set up the constructor args
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));

        //use the 'wait' object with the proper syntax to create explicit wait conditions.
        wait.until(ExpectedConditions.titleContains(title));
    }


    /**
     * This method accepts a dropdown element and returns a List<String> that contains all options values as String.
     * @param dropdownElement
     * @return actualMonth_as_STRING
     */

    public static List<String> dropdownOptions_as_String(WebElement dropdownElement) {

        Select month = new Select(dropdownElement);

        //Storing all the ACTUAL options into a List of WebElements
        List<WebElement> actualMonth_as_WebElement = month.getOptions();

        //Creating an EMPTY list of String to store ACTUAL <option> as String
        List<String> actualMonth_as_String = new ArrayList<>();

        //Looping through the List<WebElement>, getting all options' texts, and storing them into List<String>
        for (WebElement each : actualMonth_as_WebElement) {
            actualMonth_as_String.add(each.getText());
        }
        return actualMonth_as_String;
    }
}
