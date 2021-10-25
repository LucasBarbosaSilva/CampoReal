package edge.core;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import edge.pages.LoginPage;

import static edge.core.DriverFactory.getDriver;
import static edge.core.DriverFactory.killDriver;


import java.io.File;
import java.io.IOException;

public class BaseTest {
    @Rule
    public TestName testName = new TestName();

    LoginPage login = new LoginPage();

    @Before
    public void initialize(){
        login.goScreenHome();

        login.login("lucas.silva@edge.ufal.br", "lucas123");
    }

    @After
    public void finalize() throws IOException{
        TakesScreenshot ss = (TakesScreenshot) getDriver();
        File file = ss.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("target"+ File.separator 
                    +"screenshots"+ File.separator + testName.getMethodName() + ".jpg"));
        
        if(Properties.CLOSE_BROWSER){
            killDriver();
        }
    }   
}
