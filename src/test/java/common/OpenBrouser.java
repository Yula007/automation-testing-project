package com.example;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class OpenBrouser {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    JavascriptExecutor js;
    @Before



    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:/B!n/Drivers/Silenium/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1400,1000));
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        js = (JavascriptExecutor) driver;
    }

     @Test
    public void testUntitledTestCase() throws Exception {
        driver.get("https://transport.tallinn.ee/#bus/ru");
        driver.findElement(By.id("tallinna-linn_trol")).click();
        driver.get("https://transport.tallinn.ee/#trol/ru");
        driver.findElement(By.xpath("//table[@id='tblRoutes']/tbody/tr[4]/td/a/span[2]")).click();
        driver.get("https://transport.tallinn.ee/#trol/5/a-b/ru");

    }

    @Test
    public void testUntitledTestCase02() throws Exception {
        driver.get("https://lifehacker.ru/");
        driver.findElement(By.linkText("Рубрики")).click();
        driver.findElement(By.xpath("//div[@id='main']/div/main/article/div/div/a[4]/div[2]")).click();
        driver.findElement(By.name("12 проблем Windows 11, которые легко исправить")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Сервисы'])[1]/following::*[name()='svg'][1]")).click();
        driver.findElement(By.xpath("//input[@type='search']")).click();
        driver.findElement(By.xpath("//input[@type='search']")).clear();
        driver.findElement(By.xpath("//input[@type='search']")).sendKeys("Windows");
    }


    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
