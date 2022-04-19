package steps;


import common.Common;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;

/**
 * The type Pet stores steps.
 */
public class PetStoresSteps {

    private static final Logger log = LogManager.getLogger(PetStoresSteps.class);
    private String API_SERVER_PET = System.getProperty("restapi.pet.url");
    private Response response;
    private Common common = new Common();

    /**
     * Search pet by id.
     *
     * @param id the id
     */
    @Step("Return pet name by id {0}")
    public void searchPetById(String id){
        response = SerenityRest.given()
                .accept("application/json")
                .when()
                .get(API_SERVER_PET +"pet/" + id);
    }

    /**
     * Search is executed successfully.
     */
    @Step ("Search is executed successfully")
    public void searchIsExecutedSuccessfully(){

        response.then().statusCode(200);
    }

    /**
     * Search is resource not found.
     */
    @Step ("Resource not found")
    public void searchIsResourceNotFound(){

        response.then().statusCode(404);
    }

    /**
     * Should find pet by id.
     *
     * @param id            the id
     * @param category_id   the category id
     * @param category_name the category name
     * @param name          the name
     * @param photoUrls     the photo urls
     * @param tags_id       the tags id
     * @param tags_name     the tags name
     * @param status        the status
     */
    @Step("API must return pet with name {3}")
    public void iShouldFindPetById(

            int id,
            Integer category_id,
            String category_name,
            String name,
            String [] photoUrls,
            Integer tags_id,
            String tags_name,
            String status
    )
    {
        response.then().body("id", is(id));
        response.then().body("category.name", is(category_name));
        response.then().body("category.id", is(category_id));
        response.then().body("name", is(name));
        response.then().body("photoUrls[0]", is(photoUrls[0]));
        response.then().body("photoUrls[1]", is(photoUrls[1]));
        response.then().body("tags[0].id", is(tags_id));
        response.then().body("tags[0].name", is(tags_name));
        response.then().body("status", is(status));
    }

    /*  POST  ​/pet Add a new pet to the store
            {
              "id": 0,
              "category": {
                "id": 0,
                "name": "string"
              },
              "name": "doggie",
              "photoUrls": [
                "string"
              ],
              "tags": [
                {
                  "id": 0,
                  "name": "string"
                }
              ],
              "status": "available"
            }
   */

    /**
     * Crete new pet.
     *
     * @param id            the id
     * @param category_id   the category id
     * @param category_name the category name
     * @param name          the name
     * @param photoUrls     the photo urls
     * @param tag_id        the tag id
     * @param tag_name      the tag name
     * @param status        the status
     */
    @Step("Add to shop pet with id {0}")
    public void creteNewPet(Object id,
                            Integer category_id,
                            String category_name,
                            String name,
                            String[] photoUrls,
                            Integer tag_id,
                            String tag_name,
                            String status

    ){

        Object cacheControl = "no-cache";

        Map<String, Object> jsonAsMap  = common.cretePetJson( id,
                                                    category_id,
                                                    category_name,
                                                    name,
                                                    photoUrls,
                                                    tag_id,
                                                    tag_name,
                                                    status);


        response = SerenityRest.given().accept("application/json")
                .contentType("application/json")
                //.header ( "Authorization", authorization.askJsonWebToken () )
                .header ("cache-control", cacheControl )
                .body(jsonAsMap).when().post ( API_SERVER_PET +  "pet/"  );

    }


    /**
     * Update pet.
     *
     * @param id            the id
     * @param category_id   the category id
     * @param category_name the category name
     * @param name          the name
     * @param photoUrls     the photo urls
     * @param tag_id        the tag id
     * @param tag_name      the tag name
     * @param status        the status
     */
    @Step("Update pet data with id {0}")
    public void updatePet(Object id,
                          Integer category_id,
                          String category_name,
                          String name,
                          String[] photoUrls,
                          Integer tag_id,
                          String tag_name,
                          String status

    ){

        Object cacheControl = "no-cache";

        Map<String, Object> jsonAsMap  = common.cretePetJson( id,
                category_id,
                category_name,
                name,
                photoUrls,
                tag_id,
                tag_name,
                status);

        response = SerenityRest.given().accept("application/json")
                .contentType("application/json")
                //.header ( "Authorization", authorization.askJsonWebToken () )
                .header ("cache-control", cacheControl )
                .body(jsonAsMap).when().put ( API_SERVER_PET +  "pet/"  );

    }

    /**
     * Delete pet from store.
     *
     * @param petId the pet id
     */
    @Step
    public void deletePetFromStore (Object petId){
        response = SerenityRest.given().accept("application/json")
                .when()
                .delete(API_SERVER_PET +  "pet/" + petId);
    }



    /*{
            "id": 0,
            "username": "string",
            "firstName": "string",
            "lastName": "string",
            "email": "string",
            "password": "string",
            "phone": "string",
            "userStatus": 0
    }*/

    /**
     * Crete new user.
     *
     * @param id         the id
     * @param username   the username
     * @param firstName  the first name
     * @param lastName   the last name
     * @param password   the password
     * @param phone      the phone
     * @param userStatus the user status
     */
    @Step("Add to shop new user with id {0}")
    public void creteNewUser(Object id,
                            String username,
                            String firstName,
                            String lastName,
                            String password,
                            String phone,
                            int userStatus

    ){

        Object cacheControl = "no-cache";

        Map<String, Object> jsonAsMap  = common.creteUserJson(
                                                                id,
                                                                username,
                                                                firstName,
                                                                lastName,
                                                                password,
                                                                phone,
                                                                userStatus
                                                            );


        response = SerenityRest.given().accept("application/json")
                .contentType("application/json")
                //.header ( "Authorization", authorization.askJsonWebToken () )
                .header ("cache-control", cacheControl )
                .body(jsonAsMap).when().post ( API_SERVER_PET +  "user/"  );

    }


}
