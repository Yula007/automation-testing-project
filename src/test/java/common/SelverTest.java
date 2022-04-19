package common;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;

public class SelverTest {

    @Before
    public void setUp() {
        Configuration.browser = "chrome";
        Configuration.headless = true;
    }
    @After
    public void tearDown() {
    }
    @Test
    public void selver() {
        open("https://www.selver.ee/");
        WebDriverRunner.getWebDriver().manage().window().setSize(new Dimension(1934, 1046));
        $(byName("q")).click();
        $(byName("q")).val("Moet");
        $(".klevu-name").click();
        $(".ProductName").shouldHave(text("Moet & Chandon Nectar Imperial 75 cl karbis"));
        $(".Product__button").should(exist);
        $(".Product__button").click();
        actions().moveToElement($(".Product__button")).perform();
        $(".ProductNotification__info").shouldHave(text("1 tk ostukorvis"));
        $(".Microcart__text").click();
        $(byLinkText("Moet & Chandon Nectar Imperial 75 cl karbis")).should(exist);
        $(".Cart__sidebar div:nth-child(4) .Line__value").shouldHave(text("45,90 €"));
        $(".RemoveButton use").click();
        $(".empty-products__title").shouldHave(text("Ostukorvis pole tooteid."));
    }
}

