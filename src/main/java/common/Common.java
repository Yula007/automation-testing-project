package common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.print.Book;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.*;

import java.io.File;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileOutputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.lang.String.valueOf;


/**
 * The type Common.
 */
public class Common {

    private static final Logger logger = LogManager.getLogger();
    public WebDriver driver;

    /**
     * Postgre sql connection result set.
     *
     * @param connectionString   the connection string
     * @param connectionUser     the connection user
     * @param connectionPassword the connection password
     * @param sql                the sql
     * @return the result set
     */
    public ResultSet PostgreSqlConnection(String connectionString,
                                          String connectionUser,
                                          String connectionPassword,
                                          String sql
    ) {
        ResultSet resultSet = null;
        try (Connection connection = DriverManager.getConnection(connectionString,connectionUser,connectionPassword))
        {

            logger.info("Connected to PostgreSQL database!");
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
        return resultSet;
    }

    /**
     * Crete pet json map.
     *
     * @param id            the id
     * @param category_id   the category id
     * @param category_name the category name
     * @param name          the name
     * @param photoUrls     the photo urls
     * @param tag_id        the tag id
     * @param tag_name      the tag name
     * @param status        the status
     * @return the map
     */
    public Map<String, Object> cretePetJson (Object id,
                                             Integer category_id,
                                             String category_name,
                                             String name,
                                             String[] photoUrls,
                                             Integer tag_id,
                                             String tag_name,
                                             String status ) {



        Map<String, Object> category = new HashMap<>();
        category.put("id", category_id );
        category.put("name", category_name);


        Map<Object, Object>  tags = new HashMap<>();
        tags.put("id", tag_id);
        tags.put("name", tag_name);

        Object [] tags_arr = {tags};

        Map<String, Object> jsonAsMapPet = new HashMap<>();
        jsonAsMapPet.put("id", id);
        jsonAsMapPet.put("category", category);
        jsonAsMapPet.put("name", name);
        jsonAsMapPet.put("photoUrls", photoUrls);
        jsonAsMapPet.put("tags", tags_arr);
        jsonAsMapPet.put("status", status);

        return  jsonAsMapPet;
    }

    /**
     * Crete user json map.
     *
     * @param id         the id
     * @param username   the username
     * @param firstName  the first name
     * @param lastName   the last name
     * @param password   the password
     * @param phone      the phone
     * @param userStatus the user status
     * @return the map
     */
    public Map<String, Object> creteUserJson (Object id,
                                              String username,
                                              String firstName,
                                              String lastName,
                                              String password,
                                              String phone,
                                              int userStatus) {





        Map<String, Object> jsonAsMapUser = new HashMap<>();
        jsonAsMapUser.put("id", id);
        jsonAsMapUser.put("username", username);
        jsonAsMapUser.put("firstName", firstName);
        jsonAsMapUser.put("lastName", lastName);
        jsonAsMapUser.put("password", password);
        jsonAsMapUser.put("phone", phone);
        jsonAsMapUser.put("userStatus", userStatus);

        return  jsonAsMapUser;
    }

    public String verifyLinks(String linkUrl) {


        try {

            URL url = new URL(linkUrl);

            //Now we will be creating url connection and getting the response code
            HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();
            httpURLConnect.setConnectTimeout(5000);
            httpURLConnect.connect();
            if (httpURLConnect.getResponseCode() >= 400) {
                logger.info(" {} - {} is a broken link", linkUrl, httpURLConnect.getResponseMessage());
                return (linkUrl + ","+ httpURLConnect.getResponseMessage());
            }

            //Fetching and Printing the response code obtained
            else {
                logger.info(" {} - {} ", linkUrl, httpURLConnect.getResponseMessage());
                return (linkUrl + ","+ httpURLConnect.getResponseMessage());

            }
        } catch (Exception e) {
            logger.error("Exception: {}", e);
        }

       return "exception,exception";
    }

    public void writeDataToExcel(ArrayList list, String excelFilePath) throws IOException {

        //Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Page Data");

        //This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put("1", new Object[]{"LINK", "RESPONSE"});
        int i = 0;
        while (i < list.size()) {
            String[] set = list.get(i).toString().split(",");
            data.put(valueOf(i + 2), new Object[]{set[0], set[1]});
            i++;
        }

        //Iterate over data and write to sheet
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset) {
            Row row = sheet.createRow(rownum++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String)
                    cell.setCellValue((String) obj);
                else if (obj instanceof Integer)
                    cell.setCellValue((Integer) obj);
            }
        }
        try {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(excelFilePath);
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WebDriver setUpDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        logger.info("Driver created");
        driver.manage().window().maximize();
        return driver;
    }

}
