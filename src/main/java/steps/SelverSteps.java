package steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import net.serenitybdd.core.photography.ScreenShooter;
import net.thucydides.core.annotations.Step;
import org.junit.After;
import org.junit.Before;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.Dimension;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;


public class SelverSteps {

    @Rule
    public ScreenShooter makeScreenshotOnFailure;


    @Step("Configurate and open browser")
    public void setUp() {
        Configuration.browser = "chrome";
        Configuration.headless = false;
        Configuration.remote = "http://127.0.0.1:4444";
        Configuration.reportsFolder = "test-result/reports";
        open("https://www.selver.ee/");
        WebDriverRunner.getWebDriver().manage().window().setSize(new Dimension(1934, 1046));
    }
    @Step("Close browser")
    public void tearDown() {
    }

    @Step("Show product {0}")
    public void showProduct(String product) {

        $(byName("q")).click();
        $(byName("q")).val(product);
        $(".klevu-name").click();
        $(".ProductName").shouldHave(text(product));
        $(".Product__button").should(exist);
    }

    @Step("Add product {0} to basket")
    public void addProductToBasket(String product) {
        $(".Product__button").click();
        actions().moveToElement($(".Product__button")).perform();
        $(".ProductNotification__info").shouldHave(text("1 tk ostukorvis"));
    }

    @Step("Check basket with product {0} and price {1}")
    public void checkBasketWithProdcut(String product, String price){
        $(".Microcart__text").click();
        $(byLinkText(product)).should(exist);
        $(".Cart__sidebar div:nth-child(4) .Line__value").shouldHave(text(price));
    }

    @Step("Delete product {0} from basket")
    public void deleteProductFromBasket(String product)
    {
        $(".RemoveButton use").click();
        $(".empty-products__title").shouldHave(text("Ostukorvis pole tooteid."));
    }
}

