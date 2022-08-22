package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    @BeforeMethod
    public void baseSetup() {
        System.out.println("Base setup");
    }

}
