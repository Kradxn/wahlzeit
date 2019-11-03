package org.wahlzeit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.wahlzeit.suites.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        EmailServiceTestSuite.class,
        UtilsTestSuite.class,
        HandlersTestSuite.class,
        LogBuilderTestSuite.class,
        ModelTestSuite.class,
})
public class AllTests {
}
