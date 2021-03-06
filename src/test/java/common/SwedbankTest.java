package common;
// Generated by Selenium IDE
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import java.util.*;
public class SwedbankTest {
  private Map<String, Object> vars;
  @BeforeEach
  public void setUp() {
    Configuration.browser = "chrome";
    Configuration.timeout = 8000;
    vars = new HashMap<>();
  }
  @AfterEach
  public void tearDown() {
  }
  @Test
  public void swedbank() {
    open("https://swedbank.ee/business/");
    WebDriverRunner.getWebDriver().manage().window().setSize(new Dimension(2576, 1408));
    if($(".ui-cookie-consent__accept-all-button").isDisplayed()) {
      $(".ui-cookie-consent__accept-all-button").shouldBe(visible).click();
    }
    $("#business\\.cards > .ui-navitem__caption").click();
    $("#business\\.cards\\.bankCards\\.businessDebitCard > .ui-navitem__caption").click();
    $("#Tab1-control > .ui-tabs__control").should(exist);
    $("#Tab2-control > .ui-tabs__control").should(exist);
    $("#Tab3-control > .ui-tabs__control").should(exist);
    $("#Tab4-control > .ui-tabs__control").should(exist);
    $("#Tab5-control > .ui-tabs__control").should(exist);
    $("h1").shouldHave(text("Ärikliendi deebetkaart"));
    $("#Tab3-control > .ui-tabs__control").click();
    $("ui-curtains .ui-curtain__head svg").click();
    $("ui-table:nth-child(2) tr:nth-child(3) > .col").shouldHave(text("2,50 €"));
  }
}
