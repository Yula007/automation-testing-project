package common;


import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.*;


public class Tele2Test {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    JavascriptExecutor js;
    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:/B!n/System/Driver`s/WebDriversJava");
        driver = new ChromeDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        js = (JavascriptExecutor) driver;
    }

    @Test
    public void testUntitledTestCase() throws Exception {
        // система стринг для массива и индекса текста вместо ыутвлуны 38 строки.
        String[] names = {"Sergey", "Vasja", "Pupsik", "Bubu", "Dodo", "Pusispus", "Kuuuu", "Faaaayu"};
        String[] telefon = {"555312312", "321412312", "31234512512", "3122135765", "43634634653", "437457845", "987124123Э", "874169192093"};
        boolean[] telefonIndex = new boolean[0];
        int i;
        for (i = 0; i < telefon.length; i++) {
            System.out.println(telefonIndex[i]);

        driver.get("https://tele2.ee/ru");
        driver.findElement(By.xpath("//div[@id='__next']/div/div[2]/div[4]/div/div[3]/div[2]/div/div/div/h1")).click();
        driver.findElement(By.xpath("//div[@id='__next']/div/div[2]/div[4]/div/div[5]/div/div/div[2]/div[2]/p/span/a/span")).click();
        driver.findElement(By.xpath("//div[@id='TV-packets']/div/div[2]/div/div/div[2]/a/button")).click();
        driver.findElement(By.name("textinput-1586944490657")).click();
        driver.findElement(By.name("textinput-1586944490657")).clear();
        driver.findElement(By.name("textinput-1586944490657")).sendKeys(names); // стринг для массива текста 38 строки
        driver.findElement(By.name("phone")).click();
        driver.findElement(By.name("phone")).clear();
        driver.findElement(By.name("phone")).sendKeys(telefon[i]);
        driver.findElement(By.name("textinput-1586944490657")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Instagram'])[1]/following::*[name()='svg'][1]")).click();
        }
        driver.findElement(By.linkText("Помощь")).click();
        driver.findElement(By.id("html-body")).click();
        driver.get("https://tele2.ee/abi");
        driver.findElement(By.id("mui-52725")).click();
        driver.findElement(By.xpath("//li[@id='mui-52725-option-0']/div/a/div")).click();
        driver.findElement(By.id("mui-52725")).clear();
        driver.findElement(By.id("mui-52725")).sendKeys("E-poest ostu sooritamine");
        driver.findElement(By.id("html-body")).click();
        driver.get("https://tele2.ee/ru/abi/artikkel/360013818077-e-poest-ostu-sooritamine");
        driver.findElement(By.linkText("Э-магазин")).click();
        driver.get("https://tele2.ee/ru/pood");
        driver.findElement(By.xpath("//img[@alt='Huawei MateView GT 27']")).click();
        driver.findElement(By.xpath("//img[@alt='Tele2']")).click();

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
