package cases;

import lombok.AccessLevel;
import lombok.Setter;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import net.thucydides.junit.annotations.Qualifier;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.runner.RunWith;
import steps.CasinoSteps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@WithTags(
        @WithTag("suite:regression")
)


@RunWith(SerenityParameterizedRunner.class)


@UseTestDataFrom("src/test/resources/casino/CasinoData.csv")

/**
 * The type Casino new test.
 */
public class CasinoCases {

    /**
     * The Casino.
     */
    @Steps
    CasinoSteps casino;

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
    public int errorCounter = 0;
    private static final Logger log = LogManager.getLogger(CasinoCases.class);

    /**
     * The Base url.
     */
    public String baseUrl = System.getProperty("base.url");

    @Setter(AccessLevel.PRIVATE)
    String winCombination,
            amount;

    @Qualifier
    public String qualifier() {
        return winCombination;
    }


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
    public void casinoTest() {

        balance = casino.getBalance();
        casino.setCombination(winCombination);
        casino.startGame();
        casino.winTextCheck(amount, winCombination);
        casino.winCombinationBlinkCheck(winCombination);
        casino.paytableCheck(winCombination, amount);

        winAmount = Integer.parseInt(amount);

        if (winAmount != casino.getBalance() - balance + 1) {

            log.error("Incorrect win amount for combination {}, expected: {} actual: {}",
                    winCombination,
                    winAmount,
                    casino.getBalance() - balance + 1);
            assertEquals(winAmount,casino.getBalance() - balance + 1);
        }


   }

    /**
     * Teardown.
     */
    @After
    public void tearDown(){
        casino.teardown();

    }

}
