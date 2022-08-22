package main;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public abstract class BasePage {

    public static WebDriver driver;
    protected static String pageUrl;
    public BasePage(WebDriver driver) {
        this.driver = WebDriverRunner.getWebDriver();
        PageFactory.initElements(driver, this);
    }


}