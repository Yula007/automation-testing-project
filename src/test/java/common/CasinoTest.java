package common;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.*;
import java.util.stream.IntStream;


/**
 * The type Casino test.
 */
public class CasinoTest {

    /**
     * The Driver.
     */
    WebDriver driver = new SafariDriver();

    /**
     * Sets class.
     */
    @Before
    public void setupClass() {

    }


    /**
     * Test.
     *
     * @throws InterruptedException the interrupted exception
     */
    @Test
    public void test() throws InterruptedException {
        driver.get("file:///Users/sdasd/qa-group-vi/index.html");

        for (int i = 11111; i <= 55555; i++) {
            String temp = Integer.toString(i);
            int[] numbers = new int[temp.length()];
            for (int a = 0; a < temp.length(); a++) {
                numbers[a] = temp.charAt(a) - '0';
            }

            if (IntStream.of(numbers).anyMatch(x -> x == 0 || x == 9 || x == 8 || x == 7 || x == 6)) continue;


            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put("1", 0);
            map.put("2", 0);
            map.put("3", 0);
            map.put("4", 0);
            map.put("5", 0);

            IntStream.of(numbers).forEach(x -> map.put(Integer.toString(x), map.get(Integer.toString(x)) + 1));

            boolean isWinning = false;
            int coins = 0;

            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getValue() >= 3) {
                    isWinning = true;
                    coins = Integer.parseInt(entry.getKey()) * entry.getValue() * 20;
                }
            }

            driver.findElement(By.id("testdata")).clear();
            driver.findElement(By.id("testdata")).sendKeys(Integer.toString(i));
            driver.findElement(By.id("spinButton")).click();
            WebElement wait = new WebDriverWait(driver, Duration.ofMillis(4000)).until(ExpectedConditions.elementToBeClickable(By.id("spinButton")));
            wait.click();

            if (isWinning) {
                Boolean isDisplayed = driver.findElement(By.id("winbox")).isDisplayed();
                Assert.assertEquals(true, isDisplayed);
                String winText = driver.findElement(By.id("winbox")).getText();
                Assert.assertEquals("Win " + coins + " coins", winText);
            } else {
                Boolean isDisplayed = driver.findElement(By.id("winbox")).isDisplayed();
                Assert.assertEquals(false, isDisplayed);
            }
        }
    }
}
