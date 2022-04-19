package common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * The type Casino.
 */
public class Casino {

    private static final Logger log = LogManager.getLogger(Casino.class);
    /**
     * The Driver.
     */
    public WebDriver driver;
    /**
     * The Balance.
     */
    public int balance;

    public int errorCounter = 0;

    /**
     * Set up.
     *
     * @param baseUrl the base url
     */
    public void setUp(String baseUrl){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        log.info("Driver created");
        driver.get(baseUrl);
    }

    /**
     * Is element present boolean.
     *
     * @param by the by
     * @return the boolean
     */
    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Gets balance.
     *
     * @return the balance
     */
    public  int getBalance() {
        if(isElementPresent(By.id("balance-value"))) {
            balance = Integer.valueOf(driver.findElement(By.id("balance-value")).getAttribute("value"));
            log.info("Balance: {}", balance);
        }
        else
        {
            log.error("Page with balance is not found");
        }
        return balance;
    }

    /**
     * Set combination.
     *
     * @param combination the combination
     */
    public void setCombination(String combination){
        driver.findElement(By.id("testdata")).clear();
        driver.findElement(By.id("testdata")).sendKeys(combination);
    }

    /**
     * Start game.
     */
    public void startGame(){
        waitUntilElementClickable(By.id("spinButton"), 30);
        driver.findElement(By.id("spinButton")).click();
    }


    /**
     * Win text check int.
     *
     * @param winAmount      the win amount
     * @param winCombination the win combination
     * @return the int
     */
    public int winTextCheck(String winAmount, String winCombination){
        errorCounter = 0;
       if(driver.findElement(By.id("winbox")).isDisplayed()){
           if(!driver.findElement(By.id("winbox")).getText().equals("Win " + winAmount + " coins")){
              log.error("Incorrect win amount displayed for win combination {}, expected {}, actual {}",
               winCombination,
               "Win " + winAmount + " coins",
               driver.findElement(By.id("winbox")).getText());
              errorCounter++;
           }
       }
       else {
           log.error("Win amount is not displayed for win combination {}",
                   winCombination);
           errorCounter++;
       }
       return errorCounter;
    }


    /**
     * Win combination blink check int.
     *
     * @param winCombination the win combination
     * @return the int
     */
    public int winCombinationBlinkCheck(String winCombination){

        errorCounter = 0;
        String chartIn;
        String chartOut = "E";
        int relNumber = 0;

        for (int i = 0; i < winCombination.length(); i++) {

          relNumber = i + 1;
          chartIn = String.valueOf(winCombination.charAt(i));

         if(!chartIn.equals("0")) {

             if (isElementPresent(By.cssSelector("#reel" + relNumber  + " > div.notch.notch2.blinkme"))) {
                 chartOut = driver.findElement(By.cssSelector("#reel" + relNumber + " > div.notch.notch2.blinkme")).getText();
             } else {
                 errorCounter++;
                 log.error("Digit not blinked for win combination {} in tab {}",
                         winCombination, relNumber);
             }
             if (!chartOut.equals(chartIn)) {
                 errorCounter++;
                 log.error("Incorrect digit for win combination {} in tab {}, expected {} actual {} ",
                         winCombination, relNumber, chartIn, chartOut);
             }
         }

        }
        return errorCounter;
    }

    public int paytableCheck(String winCombination, String winAmount){
        errorCounter = 0;
       if(isElementPresent(By.cssSelector("tr.win" + winCombination.replace("0", "") + ".achievement"))) {
           if(!driver.findElement(By
                   .cssSelector("tr.win" + winCombination.replace("0", "") +
                           ".achievement > td:nth-child(1)"))
                   .getText().replace(" + ","").equals(winCombination.replace("0", ""))){
               errorCounter++;
               log.error("Paytable is displayed incorrect win combination {}",
                       winCombination);
           }

           if(!driver.findElement(By
                           .cssSelector("tr.win" + winCombination.replace("0", "") +
                                   ".achievement > td:nth-child(2)"))
                   .getText().equals(winAmount)){
               errorCounter++;
               log.error("Paytable is displayed incorrect win combination {}",
                       winCombination);
           }

       }
       else{
        errorCounter++;
        log.error("Paytable is not highlighted win combination {}",
                winCombination);
       }
        return errorCounter;
    }

    /**
     * Wait until element visible.
     *
     * @param by   the by
     * @param time the time
     */
    public void waitUntilElementVisible(By by, int time){
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(by)));
    }

    /**
     * Wait until element clickable.
     *
     * @param by   the by
     * @param time the time
     */
    public void waitUntilElementClickable(By by, int time){
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(by)));

    }

    /**
     * Teardown.
     */
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
