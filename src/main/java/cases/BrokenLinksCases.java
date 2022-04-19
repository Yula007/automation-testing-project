package cases;

import lombok.AccessLevel;
import lombok.Setter;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.Qualifier;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.BrokenLinksSteps;

import java.io.IOException;

@RunWith(SerenityParameterizedRunner.class)

@UseTestDataFrom( "./src/test/resources/url.csv"  )

public class BrokenLinksCases {

    private static final Logger log = LogManager.getLogger(BrokenLinksCases.class);

    @Setter(AccessLevel.PRIVATE)
    String  siteName,
            url;

    @Qualifier
    public String qualifier() {
        return  url;
    }

    @Steps
    BrokenLinksSteps steps;

    @Test
    public void BrokenLinksTest() throws IOException {
        steps.setUp();
        steps.BrokenLinks(siteName, url);
    }

}