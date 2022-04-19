package steps;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.serenitybdd.core.photography.ScreenShooter;
import net.thucydides.core.annotations.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Rule;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * The type Casino.
 */
public class CasinoSteps {

    @Rule
    public ScreenShooter makeScreenshotOnFailure;

    private static final Logger log = LogManager.getLogger(CasinoSteps.class);
    /**
     * The Driver.
     */
    public WebDriver driver;
    /**
     * The Balance.
     */
    public int balance;



    /**
     * Set up.
     *
     * @param baseUrl the base url
     */
    @Step("Open Chrome and navigate to {0}")
    public void setUp(String baseUrl){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
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
    @Step("Get balance")
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
    @Step("Set win combination to {0}")
    public void setCombination(String combination){
        driver.findElement(By.id("testdata")).clear();
        driver.findElement(By.id("testdata")).sendKeys(combination);
    }

    /**
     * Start game.
     */
    @Step("Start game")
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
    @Step("Win test check for win combination {1}")
    public  void winTextCheck(String winAmount, String winCombination){

       if(driver.findElement(By.id("winbox")).isDisplayed()){
           if(!driver.findElement(By.id("winbox")).getText().equals("Win " + winAmount + " coins")){
              log.error("Incorrect win amount displayed for win combination {}, expected {}, actual {}",
               winCombination,
               "Win " + winAmount + " coins",
               driver.findElement(By.id("winbox")).getText());
               assertEquals("Win " + winAmount + " coins",driver.findElement(By.id("winbox")).getText());
           }
       }
       else {
           log.error("Win amount is not displayed for win combination {}",
                   winCombination);
           assertTrue(false);
       }
    }


    /**
     * Win combination blink check int.
     *
     * @param winCombination the win combination
     * @return the int
     */
    @Step("Win combination blink check for win combination {0}")
    public void winCombinationBlinkCheck(String winCombination){

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
                 log.error("Digit not blinked for win combination {} in tab {}",
                         winCombination, relNumber);
                 assertTrue(false);
             }
             if (!chartOut.equals(chartIn)) {
                  log.error("Incorrect digit for win combination {} in tab {}, expected {} actual {} ",
                         winCombination, relNumber, chartIn, chartOut);
                  assertEquals(chartIn, chartOut );
             }
         }

        }
    }

    /**
     * Paytable check int.
     *
     * @param winCombination the win combination
     * @param winAmount      the win amount
     * @return the int
     */
    @Step("Paytable check for win combination {0}")
    public void paytableCheck(String winCombination, String winAmount){

       if(isElementPresent(By.cssSelector("tr.win" + winCombination.replace("0", "") + ".achievement"))) {
           if(!driver.findElement(By
                   .cssSelector("tr.win" + winCombination.replace("0", "") +
                           ".achievement > td:nth-child(1)"))
                   .getText().replace(" + ","").equals(winCombination.replace("0", ""))){
                  log.error("Paytable is displayed incorrect win combination {}",
                       winCombination);
                  assertEquals(winCombination.replace("0", ""),driver.findElement(By
                                  .cssSelector("tr.win" + winCombination.replace("0", "") +
                                          ".achievement > td:nth-child(1)"))
                          .getText().replace(" + ","") );
           }

           if(!driver.findElement(By
                           .cssSelector("tr.win" + winCombination.replace("0", "") +
                                   ".achievement > td:nth-child(2)"))
                   .getText().equals(winAmount)){
               log.error("Paytable is displayed incorrect win combination {}",
                       winCombination);
               assertEquals(winAmount,driver.findElement(By
                       .cssSelector("tr.win" + winCombination.replace("0", "") +
                               ".achievement > td:nth-child(2)")));
           }

       }
       else{
          log.error("Paytable is not highlighted win combination {}",
                winCombination);
          assertTrue(false);
       }

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
    @Step("Teardown")
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
