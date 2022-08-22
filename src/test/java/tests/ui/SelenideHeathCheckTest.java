package tests.ui;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.Test;

public class SelenideHeathCheckTest extends BaseUiTest{

    @Test
    public void selenideTest() {
        Selenide.open("https://google.com");
    }
}
