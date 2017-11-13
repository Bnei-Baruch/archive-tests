package PageObjects;

import helper.Class.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EventsList extends BaseClass {


    public EventsList(WebDriver driver) {
        super(driver);
    }

    public void clickElement(String selector){
        click(driver.findElement(By.cssSelector(selector)));
    }
}
