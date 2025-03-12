import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class N11Login {
    public boolean login (WebDriver driver, String email, String password) {
        boolean result = false;

        try {
            WebElement loginButton = driver.findElement(By.xpath("/html/body/div[1]/header/div/div/div/div[2]/div[5]/div/div/div/a[2]"));
            loginButton.click();

            Thread.sleep(2000);

            WebElement emailInput = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div[2]/div[2]/form/div[1]/div[1]/input"));
            emailInput.sendKeys(email);

            Thread.sleep(1000);

            WebElement passwordInput = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div[2]/div[2]/form/div[1]/div[2]/input"));
            passwordInput.sendKeys(password);

            Thread.sleep(1000);

            WebElement clickButton = driver.findElement(By.id("loginButton"));
            clickButton.click();

            Thread.sleep(1000);

            try {
                driver.findElement(By.xpath("/html/body/div[1]/div[4]/div/div[1]/div[1]/div/a[12]/img"));
                result =  true;
            } catch (NoSuchElementException x) {
                result =  false;
            }

            

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return result;

    }
}