package cases;

import lombok.AccessLevel;
import lombok.Setter;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.Qualifier;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.DataBaseSteps;

import java.sql.SQLException;

@RunWith(SerenityParameterizedRunner.class)

@UseTestDataFrom( "./src/test/resources/SqlData.csv"  )

public class DataBaseCases {


    @Setter(AccessLevel.PRIVATE)
    String  firstName,
            lastName;

    @Steps
    DataBaseSteps steps;

    @Qualifier
    public String qualifier() {
        return  firstName;
    }


    @Test
    public void dvdRentalTest() throws SQLException {

        steps.actorTest(firstName,lastName);
    }


}
