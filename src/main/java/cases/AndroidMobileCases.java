package cases;

import lombok.AccessLevel;
import lombok.Setter;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.AndroidMobileSteps;

import java.net.MalformedURLException;

//@RunWith(SerenityRunner.class)
@RunWith(SerenityParameterizedRunner.class)

@UseTestDataFrom( "src/test/resources/emulators.csv" )

public class AndroidMobileCases {

    private static final Logger log = LogManager.getLogger();

    @Setter(AccessLevel.PRIVATE)
    String  deviceName,
            udId,
            platformVersion;


    @Steps
    AndroidMobileSteps steps;

    @Test
    public void apkInfoTest() throws MalformedURLException {

        steps.setup(deviceName,udId,platformVersion);
        steps.basicTest();
        steps.teardown();

    }


}
