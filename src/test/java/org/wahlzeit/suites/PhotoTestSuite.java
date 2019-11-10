package org.wahlzeit.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.wahlzeit.services.BicyclePhotoPerstsistentTest;
import org.wahlzeit.services.EmailAddressTest;
import org.wahlzeit.services.PhotoPerstsistentTest;
import org.wahlzeit.services.mailing.EmailServiceTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({PhotoPerstsistentTest.class, BicyclePhotoPerstsistentTest.class})
public class PhotoTestSuite {
}
