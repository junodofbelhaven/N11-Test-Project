import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.*;

class N11SearchTest {

    public static WebDriver driver;

    @BeforeAll
    static void before(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        options.addArguments("--whitelisted-ips=''");
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        driver.get("https://www.n11.com/");
    }

    N11Search n11Search = new N11Search();


    @Test
    void searchItem() {
        String name = "masa";
        assertTrue(n11Search.searchN11(driver, name));
    }

    @Test
    void searchStringLimitTest() {
        String name = "";
        for(int i = 0; i < 40; i++) {
            name+="a";
        }
        assertFalse(n11Search.searchN11(driver, name));
    }

    @Test
    void searchStringLimitTest2() {
        String name = "";
        for(int i = 0; i < 100; i++) {
            name+="a";
        }
        assertFalse(n11Search.searchN11(driver, name));
    }

    @Test
    void searchStringLimitTest3() {
        String name = "";
        for(int i = 0; i < 1000; i++) {
            name+="a";
        }
        assertFalse(n11Search.searchN11(driver, name));
    }

    @Test
    void searchEmptyString() {
        String name = "";
        assertFalse(n11Search.searchN11(driver, name));
    }

    @Test
    void searchSpecificItem() {
        String name = "HP Pro 240 G9 6D384EA i5-1235U 8 GB 512 GB 23.8\" Dos FHD AIO Masaüstü Bilgisayar";
        assertTrue(n11Search.searchN11(driver, name));
    }

    @ParameterizedTest
    @CsvSource({"sAMSUNg 55cu8000 55\" 4k ulTrA hD sMARt leD tV," +
            "Samsung 55CU8000 55\" 4K Ultra HD Smart LED TV"})
    void searchUpperLowerCaseTest(String input, String expected) {
        assertEquals(n11Search.searchN11(driver, expected),n11Search.searchN11(driver, input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"ayfon","arabBaa","azuz"})
    void typoTest(String name) {
        assertTrue(n11Search.searchN11(driver, name));
    }

    @Test
    void randomTest(){
        String randomString = "asdknrejqnfqhwbefhqbwejdrghjvgtrkhvrgkvöhgr";
        assertFalse(n11Search.searchN11(driver,randomString));
    }

    @AfterAll
    static void after() {
        driver.close();
    }
}