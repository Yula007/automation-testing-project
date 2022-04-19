package suites;


import cases.CasinoCases;
import cases.DataBaseCases;
import cases.SelverCases;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * The type Test task local suite.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        SelverCases.class,
        DataBaseCases.class
})
public class TestTaskLocalSuite {
}
