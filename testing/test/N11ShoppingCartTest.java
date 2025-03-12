import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.*;

class N11ShoppingCartTest {

    public static WebDriver driver;

    @BeforeEach
    void before(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        options.addArguments("--whitelisted-ips=''");
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        driver.get("https://www.n11.com/");
    }

    N11ShoppingCart n11ShoppingCart = new N11ShoppingCart();

    @ParameterizedTest
    @ValueSource(ints = {1,52,53})
    void shopMultipleItems(int amount)  {
        assertTrue(n11ShoppingCart.shopMultipleItems(driver,"araba",amount));
    }

    @ParameterizedTest
    @CsvSource ({"10,1","10,9","10,10","10,100"})
    void removeMultipleItems(int amountAdded,int amountRemoved) {
        n11ShoppingCart.shopMultipleItems(driver,"araba",amountAdded);
        assertTrue(n11ShoppingCart.removeObjectFromCart(driver,amountRemoved));
    }

    @Test
    void shopPriceInCartComprasion(){
        assertTrue(n11ShoppingCart.shopPriceComprasion(
                driver,"Asus VivoBook 15 X1502ZA-EJ1070 i5-1235U 8 GB 512 " +
                        "GB SSD 15.6\" Dos FHD Dizüstü Bilgisayar"));
    }

    @AfterEach
    void after() {
        driver.close();
    }

}