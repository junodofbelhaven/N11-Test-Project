import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.*;

class N11LoginTest {
    static WebDriver driver;

    @BeforeAll
    static void needed(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        options.addArguments("--whitelisted-ips=''");
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        driver.get("https://www.n11.com/");
    }

    @Test
    void loginFailNoSuchMailTest() {
        String email = "username@gmail.com";
        String password = "12345asdfg";
        N11Login n11Login = new N11Login();
        assertFalse(n11Login.login(driver, email, password));
    }

    @Test
    void loginFailWrongInputTest() {
        String email = "a";
        String password = "a";
        N11Login n11Login = new N11Login();
        assertFalse(n11Login.login(driver, email, password));
    }

    @Test
    void loginSuccessTest() {
        String email = "hejaxew261@mfyax.com";
        String password = "Anilkebab1";
        N11Login n11Login = new N11Login();
        assertTrue(n11Login.login(driver, email, password));
    }

    @AfterAll
    static void after() {
        driver.quit();
    }


}