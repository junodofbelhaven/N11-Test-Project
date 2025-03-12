import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class N11Search {
    public boolean searchN11(WebDriver driver, String searchString) {
        boolean existence;

        try {
        WebElement searchElement= driver.findElement(By.xpath("/html/body/div[1]/header/div/div/div/div[1]/div/div/div[1]/form/input[1]"));
        searchElement.click();


        Thread.sleep(1000);

        WebElement searchInput = driver.findElement(By.xpath("/html/body/div[1]/header/div/div/div/div[1]/div/div/div[1]/form/input[1]"));
        searchInput.clear();
        searchInput.sendKeys(searchString);


        Thread.sleep(1000);

        WebElement searchButton = driver.findElement(By.xpath("/html/body/div[1]/header/div/div/div/div[1]/div/div/a/span"));
        searchButton.click();

        Thread.sleep(1000);

        try {
            driver.findElement(By.xpath("/html/body/div[1]/div[4]/div/div[2]/div[2]/section/div[2]/ul/li/div/div/a/h3"));
            existence = true;
        } catch (NoSuchElementException e) {
            existence = false;
        }


        } catch (InterruptedException e) {
           throw new RuntimeException(e);
        }

        return existence;
    }
}
