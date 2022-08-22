package tests.ui;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import main.RozetkaUi;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.BaseTest;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;
import static main.BasePage.driver;

public class BaseUiTest extends BaseTest {
    @BeforeMethod
    public void baseUiSetup() throws MalformedURLException {
        boolean isRemote = Boolean.parseBoolean(System.getProperty("remote"));//need to read from properties
        String baseUrl = "https://rozetka.com.ua/";//need to read from properties
        if (isRemote == true) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("browserName", "chrome");
            capabilities.setCapability("browserVersion", "103.0");
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
            RemoteWebDriver driver = new RemoteWebDriver(
                    URI.create("http://localhost:4444/wd/hub").toURL(),
                    capabilities
            );
            WebDriverRunner.setWebDriver(driver);
            open(baseUrl);
        } else {
            open(baseUrl);
        }
    }

    @Test(priority = 1)
    public void openingItemCart() {
        String searchPhrase = "iphone 13 pro";
        RozetkaUi rozetkaPage = new RozetkaUi();
        $x("//input")
                .setValue(searchPhrase);
        $x("//button[contains(text(),'Знайти')]")
                .click();
        $x("//span[contains(text(),'Мобільний телефон Apple iPhone 13 Pro 128 GB Graphite')]")
                .click();


    }

    @Test(priority = 2)
    public void logginIn() {
        $("buy-button button button--with-icon button--green button--medium ng-star-inserted")
                .isDisplayed();
        $x("//button[@class=\"header__button ng-star-inserted\"]")
                .click();
        $("modal__content")
                .isDisplayed();
        $x("//input[@type=\"email\"]")
                .isDisplayed();
        $x("//input[@type=\"password\"]")
                .isDisplayed();
        $("button button--large button--green auth-modal__submit ng-star-inserted")
                .isDisplayed();

    }

}
