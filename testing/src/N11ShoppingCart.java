import org.openqa.selenium.*;

import static java.lang.Integer.parseInt;


public class N11ShoppingCart {

    public boolean shopMultipleItems(WebDriver driver, String itemName, int amount) {

        try {

            N11Search search = new N11Search();
            search.searchN11(driver, itemName);
            Thread.sleep(1000);

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, 300);");

            WebElement chooseElement = driver.findElement(By.xpath("/html/body/div[1]/div[4]/div/div[2]/div[2]/section/div[2]/ul/li[1]/div/div/span"));
            chooseElement.click();
            Thread.sleep(1000);

            chooseElement = driver.findElement(By.xpath("/html/body/div[1]/header/div/div/div/div[2]/div[4]/a/i"));
            chooseElement.click();
            Thread.sleep(1000);

            chooseElement = driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/span"));
            chooseElement.click();
            Thread.sleep(1000);
            js.executeScript("window.scrollBy(0, 300);");

            chooseElement = driver.findElement(By.xpath("/html/body/div[1]/div[4]/div/div[1]/div[1]/div[2]/section/table[2]/tbody/tr/td[1]/div[3]/div[2]/div/span[1]"));
            for (int i = 1; i < amount; i++) {
                chooseElement.click();
            }
            Thread.sleep(5000);

            try {

            //çıkan uyarı mesajı stok bilgilendirme
            chooseElement = driver.findElement(By.xpath("/html/body/div[6]/div/span[2]"));
            System.out.println(chooseElement.getText());
            return true;

            } catch (NoSuchElementException a){

            //eklenen adet sayısı
            chooseElement = driver.findElement(By.name("quantity"));
            String value = chooseElement.getAttribute("value");
            return amount == parseInt(value);

            } catch (Exception ignored){
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean shopPriceComprasion(WebDriver driver, String itemName) {

        try {
            N11Search search = new N11Search();
            search.searchN11(driver, itemName);
            Thread.sleep(1000);

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, 300);");

            WebElement chooseElement = driver.findElement(By.xpath("/html/body/div[1]/div[4]/div/div[2]/div[2]/section/div[2]/ul/li[1]/div/div/span"));
            chooseElement.click();
            Thread.sleep(1000);

            chooseElement = driver.findElement(By.xpath("/html/body/div[1]/div[4]/div/div[2]/div[2]/section/div[2]/ul/li[1]/div/div/div/div[3]/div/span[1]"));
            String firstPrice = chooseElement.getText();

            chooseElement = driver.findElement(By.xpath("/html/body/div[1]/div[4]/div/div[2]/div[2]/section/div[2]/ul/li[1]/div/div/div/div[3]/div/span[3]/ins"));
            String discountPrice = chooseElement.getText();


            chooseElement = driver.findElement(By.xpath("/html/body/div[1]/header/div/div/div/div[2]/div[4]/a/i"));
            chooseElement.click();
            Thread.sleep(1000);

            chooseElement = driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/span"));
            chooseElement.click();
            Thread.sleep(1000);

            chooseElement = driver.findElement(By.xpath("/html/body/div[1]/div[4]/div/div[1]/div[2]/div/div/section/div/div[7]/span[2]"));
            Thread.sleep(1000);
            String cartPrice = chooseElement.getText();

            System.out.println("discountPrice: " + discountPrice + " - firstPrice: " + firstPrice + " - cartPrice: " + cartPrice);
            if (cartPrice.equals(discountPrice)) {
                return true;
            } else if (cartPrice.equals(firstPrice)) {
                System.out.println("Discount is not applied on the shopping cart.");
                return false;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public boolean removeObjectFromCart(WebDriver driver,int amount){

        try {

        WebElement chooseElement = driver.findElement(By.name("quantity"));
        int firstAmount = parseInt(chooseElement.getAttribute("value"));

        WebElement chooseElement2 = driver.findElement(By.xpath("/html/body/div[1]/div[4]/div/div[1]/div[1]/div[2]/section/table[2]/tbody/tr/td[1]/div[3]/div[2]/div/span[2]"));
        Thread.sleep(1000);

            for (int i = 0; i < amount; i++) {
                chooseElement2.click();
            }
        Thread.sleep(3000);

        chooseElement = driver.findElement(By.name("quantity"));
        int lastAmount = parseInt(chooseElement.getAttribute("value"));


        //10 adet ürün eklendiğinde ve 10 ürün çıkarıldığında value 10 a yani first amounta dönüyor
        if(firstAmount == lastAmount){
            System.out.println("En fazla 1 adete kadar çıkarılabiliyor.");
              return true;
        } else if (lastAmount == (firstAmount-amount)){
            System.out.println("Istenen miktarda ürün çıkarıldı.");
            return true;
        } else return false;

        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}




