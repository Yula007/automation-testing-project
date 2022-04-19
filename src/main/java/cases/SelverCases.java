package cases;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.AccessLevel;
import lombok.Setter;
import net.serenitybdd.core.photography.ScreenShooter;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.Qualifier;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import steps.SelverSteps;
import com.codeborne.selenide.junit5.TextReportExtension;

@RunWith(SerenityParameterizedRunner.class)

@UseTestDataFrom("src/test/resources/selver/SelverData.csv")

@ExtendWith({TextReportExtension.class})

public class SelverCases {


    @Steps
    SelverSteps steps;

    @Setter(AccessLevel.PRIVATE)
    String product,
            price;

    @Qualifier
    public String qualifier() {
        return product;
    }

    @BeforeAll
    static void setupAllureReports() {

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

    }

    @Test
    public void selverTest() {
        steps.setUp();
        steps.showProduct(product);
        steps.addProductToBasket(product);
        steps.checkBasketWithProdcut(product,price);
        steps.deleteProductFromBasket(product);
    }
}
