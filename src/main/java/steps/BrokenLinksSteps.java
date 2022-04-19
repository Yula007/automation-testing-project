package steps;


import common.Common;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BrokenLinksSteps {

    private static final Logger log = LogManager.getLogger(BrokenLinksSteps.class);
    private WebDriver driver;
    private Common common = new Common();
    private static final SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());


    @Step("Open web browser")
    public void setUp(){
        driver = common.setUpDriver();
    }

    @Step("Find all link from {}")
    public void BrokenLinks(String siteName, String webUrl) throws IOException {

        ArrayList<String> linksList = new ArrayList<String>();
        driver.get(webUrl);

        //Storing the links in a list and traversing through the links
        List<WebElement> links = driver.findElements(By.tagName("a"));

        // This line will print the number of links and the count of links.
        log.info("No of links are {}", links.size());

        //checking the links fetched.
        for(int i=0;i<links.size();i++)
        {
            WebElement E1= links.get(i);
            String url= E1.getAttribute("href");
            linksList.add(common.verifyLinks(url));
        }

        File directory = new File("./src/test/resources/output");

        if (!directory.exists()) {
            directory.mkdir();
        }

        common.writeDataToExcel(linksList,"./src/test/resources/output/" + siteName + "-" + date.format(timestamp) +".xlsx");
        driver.quit();
    }

}
