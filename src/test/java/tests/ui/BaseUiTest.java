package tests.ui;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import main.configreader.PropertiesReader;
import main.ui.RozetkaUi;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;

public class BaseUiTest {
    @BeforeMethod
    public void baseUiSetup() throws MalformedURLException {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        map.put("enableVNC", true);
        map.put("enableVideo", true);
        PropertiesReader frameworkProperties = ConfigFactory.create(PropertiesReader.class);
        if (frameworkProperties.isRemote() == true) {

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("browserName", "chrome");
            capabilities.setCapability("browserVersion", "103.0");
            capabilities.setCapability("selenoid:options", map);
            RemoteWebDriver driver = new RemoteWebDriver(
                    URI.create("http://localhost:4444/wd/hub").toURL(),
                    capabilities
            );
            WebDriverRunner.setWebDriver(driver);
            Selenide.open(frameworkProperties.baseURL());
        } else {
            Selenide.open(frameworkProperties.baseURL());
        }
    }

    @Test(priority = 1)
    public void openingItemCart() {
        String searchPhrase = "AirPods Pro with MagSafe Charging Case 2021 (MLWK3TY/A)";
        RozetkaUi rozetkaPage = new RozetkaUi();
        $x("//input")
                .setValue(searchPhrase);
        $x("//button[contains(text(),'Знайти')]")
                .click();
        $x("//span[contains(text(),'Навушники AirPods Pro with MagSafe Charging')]")
                .click();
        $x("//button[contains(text(),'Купити')]")
                .isDisplayed();


    }

    @Test(priority = 2)
    public void checkingLogginIn() {
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
