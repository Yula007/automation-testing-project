package cases;


import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.PetStoresSteps;

/**
 * The type Pet stores cases.
 */
@WithTags(
        @WithTag("suite:petstore")
)

@RunWith(SerenityRunner.class)

public class PetStoresCases {

    private static final Logger log = LogManager.getLogger(PetStoresCases.class);

    /**
     * The Steps.
     */
    @Steps
    PetStoresSteps steps;

    /**
     * Find pet by id.
     */
    @Test
    public void findPetById(){
        steps.searchPetById("7777");
        steps.searchIsExecutedSuccessfully();
        steps.iShouldFindPetById(7777,
                23,
                "Gerl",
                "Marina",
                new String[] {"http://123.com", "http://124.com"},
                34,
                "QA6",
                "available");
    }

    /**
     * Add pet to the shop.
     */
    @Test
    public void addPetToTheShop (){
        steps.creteNewPet(
                7777,
                23,
                "Gerl",
                "Marina",
                new String[] {"http://123.com", "http://124.com"},
                34,
                "QA6",
                "available");
        steps.searchIsExecutedSuccessfully();
    }


    /**
     * Update pet by id.
     */
    @Test
    public void updatePetById (){
        steps.updatePet(7777,
                23,
                "Gerl",
                "New Marina",
                new String[] {"http://123.com", "http://124.com"},
                34,"QA6","available");


        steps.searchIsExecutedSuccessfully();
    }

    /**
     * Delete pet by id.
     */
    @Test
    public void deletePetById (){
        steps.deletePetFromStore("7777");
        steps.searchIsExecutedSuccessfully();
        steps.searchPetById("7777");
        steps.searchIsResourceNotFound();
    }

    /**
     * Full pet test.
     */
    @Test
    public void fullPetTest(){
        steps.deletePetFromStore("7777");
        steps.creteNewPet(7777,
                23,
                "Gerl",
                "Marina",
                new String[] {"http://123.com", "http://124.com"},
                34,"QA6","available");
        steps.searchIsExecutedSuccessfully();
        steps.iShouldFindPetById(7777,
                23,
                "Gerl",
                "Marina",
                 new String[] {"http://123.com", "http://124.com"},
                34,"QA6","available");
        steps.searchIsExecutedSuccessfully();
        steps.updatePet(7777,
                24,
                "New Gerl",
                "New Marina",
                new String[] {"http://new123.com", "http://new124.com"},
                35,"NewQA6","sold");
        steps.searchIsExecutedSuccessfully();
        steps.iShouldFindPetById(7777,
                24,
                "New Gerl",
                "New Marina",
                new String[] {"http://new123.com", "http://new124.com"},
                35,"NewQA6","sold");
        steps.searchIsExecutedSuccessfully();
        steps.deletePetFromStore("7777");
        steps.searchIsExecutedSuccessfully();
        steps.searchPetById("7777");
        steps.searchIsResourceNotFound();

    }

}
