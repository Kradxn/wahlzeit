package org.wahlzeit.suites;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.wahlzeit.handlers.TellFriendTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({TellFriendTest.class})
public class HandlersTestSuite {
}
