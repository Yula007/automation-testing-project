package common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * The type Chrome test.
 */
public class ChromeTest {

    private ChromeDriver driver;
    /**
     * The Temp text.
     */
    public String tempText;
    /**
     * The Error counter.
     */
    public int  errorCounter = 0;
    private static final Logger log = LogManager.getLogger(ChromeTest.class);
    /**
     * The Price 1.
     */
    public String PRICE_1 = System.getProperty("price.first");
    /**
     * The Price 2.
     */
    public String PRICE_2 = System.getProperty("price.second");
    /**
     * The Base url.
     */
    public String baseUrl = System.getProperty("base.url");

    /**
     * Sets class.
     */
    @Before
    public void setupClass() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    /**
     * Teardown.
     */
    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Test.
     */
    @Test
    public void test() {

        driver.get(baseUrl);
        driver.get("https://www.gaas.ee/hooldusteenuse-vorm/");
        driver.findElement(By.id("input_20_1")).sendKeys("Alex");
       
        tempText = driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(2)")).getText();
        if(!tempText.equals(PRICE_1)){
            errorCounter++;
            log.error("Gaasikatla hooldus (< 50 kW)\n" +
                    "Hind gaasilepingu kliendile. expected: {}, actual {}", PRICE_1, tempText);
        }
        tempText = driver.findElement(By.cssSelector("tr:nth-child(3) > td:nth-child(2)")).getText();
        if(!tempText.equals(PRICE_2)){
            errorCounter++;
            log.error("Gaasikatla hooldus (< 50 kW)\n" +
                    "Hind mittekliendile.: {}, actual {}",PRICE_2, tempText);
        }

        driver.findElement(By.xpath("//a[contains(text(),'Rohkem infot')]")).click();

        Assert.assertEquals(0,errorCounter);
    }
}
