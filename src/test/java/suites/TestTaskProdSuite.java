package suites;

import cases.PetStoresCases;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * The type Test task local suite.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        PetStoresCases.class
})
public class TestTaskProdSuite {
}
