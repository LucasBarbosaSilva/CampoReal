package edge.suits;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import edge.core.DriverFactory;
import edge.pages.LoginPage;
import edge.tests.TestRemoveAccount;
import edge.tests.TestsAcccounts;
import edge.tests.TestsBalance;
import edge.tests.TestsMovimentation;
import edge.tests.TestsResume;

@RunWith(Suite.class)
@SuiteClasses({
    TestsAcccounts.class,
    TestsMovimentation.class,
    TestRemoveAccount.class,
    TestsBalance.class,
    TestsResume.class
    
})
public class Suits {
    private static LoginPage login = new LoginPage();
    
    @BeforeClass
    public static void initialize(){
        login.goScreenHome();

        login.login("lucas.silva@edge.ufal.br", "lucas123");
    }
    
    @AfterClass
    public static void ends(){
        DriverFactory.killDriver();
    }
}
