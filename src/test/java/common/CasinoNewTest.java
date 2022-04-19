package common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

/**
 * The type Casino new test.
 */
public class CasinoNewTest {

    /**
     * The Casino.
     */
    Casino casino = new Casino();

    /**
     * The Balance.
     */
    public int balance;

    /**
     * The Win amount.
     */
    public int winAmount;


    /**
     * The Temp text.
     */
    public String tempText;
    /**
     * The Error counter.
     */
    public int  errorCounter = 0;
    private static final Logger log = LogManager.getLogger(CasinoNewTest.class);

    /**
     * The Base url.
     */
    public String baseUrl = System.getProperty("base.url");

    /**
     * Sets class.
     */
    @Before
    public void setupClass() {
        casino.setUp(baseUrl);
    }

    /**
     * Casino test.
     */
    @Test
    public void casinoTest(){

        String [][] combinations = new String[][] {
                {"11100","60"},
                {"11110","80"},
                {"11111","100"},
                {"22200","120"},
                {"22220","160"},
                {"22222","180"},
        };

        for(int i = 0; i  < combinations.length; i++) {
            log.info("################### Start test for win-combination {} ###################",
                    combinations[i][0]);
            balance = casino.getBalance();
            casino.setCombination(combinations[i][0]);
            casino.startGame();

            errorCounter = errorCounter +
                casino.winTextCheck(combinations[i][1], combinations[i][0]);

            errorCounter = errorCounter +
                    casino.winCombinationBlinkCheck(combinations[i][0]);

            errorCounter = errorCounter +
                    casino.paytableCheck(combinations[i][0],combinations[i][1]);

            winAmount = Integer.parseInt(combinations[i][1]);

            if(winAmount != casino.getBalance() - balance + 1){
                errorCounter++;
                log.error("Incorrect win amount for combination {}, expected: {} actual: {}",
                        combinations[i][0],
                        winAmount,
                        casino.getBalance() - balance + 1 );
            }
            log.info("################### End test for win-combination {} ###################",
                    combinations[i][0]);
        }
        log.info("Amount of failed tests: {}",errorCounter);
        assertEquals(0, errorCounter);
    }

    /**
     * Casino test uno.
     */
    @Test
    public void casinoTestUno(){
            balance = casino.getBalance();
            casino.setCombination("11100");
            casino.startGame();
            assertEquals(60, casino.getBalance() - balance + 1);

    }


    /**
     * Teardown.
     */
    @After
    public void tearDown(){
        casino.teardown();

    }

}
