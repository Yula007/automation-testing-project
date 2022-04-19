package steps;

import common.Common;
import net.thucydides.core.annotations.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DataBaseSteps {
    private ResultSet resultSet = null;
    private static final Logger logger = LogManager.getLogger();
    private Common common = new Common();
    private String sql;
    private String connectionUrl = "jdbc:postgresql://127.0.0.1:5555/dvdrental";
    private String connectionUser = "DEVELOPER";
    private String connectionPassword = "developer";
    private int errorCount = 1;

    @Step("Display all actors with first later {0}")
    public void actorTest(String firstName, String lastName) throws SQLException {



        sql= "SELECT  DISTINCT actor.first_name, actor.last_name\n" +
                "FROM actor JOIN film_actor ON actor.actor_id=film_actor.actor_id\n" +
                "WHERE actor.first_name = '####FIRST####' AND actor.last_name = '####LAST####'";

        resultSet = common.PostgreSqlConnection(
                connectionUrl,
                connectionUser,
                connectionPassword,
                sql.replace("####FIRST####", firstName ).replace("####LAST####",lastName)
        );
        logger.info(resultSet);

        while (resultSet.next()) {
            logger.info("First name: {}", resultSet.getString("first_name"));
            assertEquals(firstName,resultSet.getString("first_name"));
            logger.info("Last name {}", resultSet.getString("last_name"));
            assertEquals(lastName,resultSet.getString("last_name"));
            errorCount = 0;
        }
        if(errorCount == 1) {
            logger.error("User with first name {} and last name {} not found",firstName,lastName);
            assertTrue(errorCount == 0);
        }

    }
}
