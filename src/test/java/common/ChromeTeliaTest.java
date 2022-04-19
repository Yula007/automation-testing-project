package common;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ChromeTeliaTest {

    private ChromeDriver driver;

    @Before
    public void setupClass() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }


    @Test
    public void test1() throws InterruptedException {
        Thread.sleep(3000);
        driver.manage().window().maximize();
        Thread.sleep(3000);
        driver.get("https://www.telia.ee/era/");
        Thread.sleep(3000);
        driver.get("https://www.telia.ee/ru/era/");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//span[contains(text(),'Согласиться со всеми файлами cookie')]")).click();
        Thread.sleep(3000);
        //driver.findElement(By.xpath("//span[contains(text(),'Подробнее')]")).click();то же ,что и ниже
        driver.findElement(By.xpath("//*[text()='Подробнее']")).click(); //попадаю на эст.страницу,хотя мануально папала бы на русскую
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[text()='Русский']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[text()='Войти']")).click();
        Thread.sleep(3000);
        WebElement iframeElement = driver.findElement(By.id("iFrameResizer0"));
        // driver.switchTo().frame("iFrameResizer0"); // не работает
        driver.switchTo().frame(iframeElement);
        driver.findElement(By.id("mobile-id-number")).sendKeys("77777");;
        Thread.sleep(3000);


    }

    @Test
    public void testUntitledTestCase() throws Exception {
        driver.get("https://www.telia.ee/era");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@id='allowAll']/span/span")).click();
        Thread.sleep(1000);
        driver.findElement(By.linkText("Русский")).click();
        Thread.sleep(1000);
        driver.get("https://www.telia.ee/ru/era");
        driver.findElement(By.xpath("//div[@id='3']/div[2]/div/div/div/div[2]/a/span/span")).click();
        driver.get("https://www.telia.ee/ru/uudised/telia-peatab-rahvusvaheliste-sanktsioonide-tottu-mitme-venekeelse-telekanali-edastamise");
        driver.findElement(By.xpath("//header[@id='header']/div[2]/div/div/div/div/div[2]/div[2]/ul/li[2]/a/div/span")).click();
        //ERROR: Caught exception [ERROR: Unsupported command [selectFrame | index=5 | ]]
        //driver.findElement(By.id("mobile-id-number")).sendKeys("77777"); // не может найти элемент из-за использования фреймов,
        // когда ищется элемент на всей странице,кроме вложенного фрейма(это вложенная HTML страница в  HTML странуцу)
        driver.findElement(By.id("mobile-id-number")).click();
        driver.findElement(By.id("mobile-id-number")).clear();
        Thread.sleep(1000);
        driver.findElement(By.id("mobile-id-number")).sendKeys("77777");
        Thread.sleep(1000);
    }
}
