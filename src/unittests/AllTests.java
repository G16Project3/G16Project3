package unittests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ homeFuelTestBetween600to800.class, homeFuelTestOver800.class, homeFuelTestUnder600.class,
		homeFuelTestUrgent.class, TrackOrdersUnitTest.class })
public class AllTests {

}
