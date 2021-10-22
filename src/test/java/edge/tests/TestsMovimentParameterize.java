package edge.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

// import edge.core.BaseTest;
import edge.core.Properties;
import edge.pages.LoginPage;
import edge.pages.MenuPage;
import edge.pages.MovimentPage;

import static edge.core.DriverFactory.getDriver;
import static edge.core.DriverFactory.killDriver;

@RunWith(Parameterized.class)
public class TestsMovimentParameterize {
    @Parameter(value = 1)
    public boolean isCost;
    @Parameter(value = 2)
    public String dateMoviment;
    @Parameter(value = 3)
    public String datePayment;
    @Parameter(value = 4)
    public String description;
    @Parameter(value = 5)
    public String interested;
    @Parameter(value = 6)
    public String value;
    @Parameter(value = 7)
    public int numberAcount;
    @Parameter(value = 8)
    public boolean isPaid;
    @Parameter(value = 9)
    public List<String> errors;
    @Parameter(value = 10)
    public int lengthArray;

    @Rule
    public TestName testName = new TestName();
    
    private MenuPage menu = new MenuPage();
    private MovimentPage moviment = new MovimentPage();    
    private LoginPage login = new LoginPage();

    @Before
    public void initialize(){
        login.goScreenHome();

        login.login("lucas.silva@edge.ufal.br", "lucas123");
        // assertEquals("Bem vindo, Lucas!", login.getTextBy(By.xpath("//*[@role='alert']"))); 
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

    @Parameters
    public static Collection<Object[]> getCollection(){
        return Arrays.asList(new Object[][]{
            {false, "", "", "", "", "", 0, false, new String[]{
                "Data da Movimentação é obrigatório",
                "Data do pagamento é obrigatório",
                "Descrição é obrigatório",
                "Interessado é obrigatório",
                "Valor é obrigatório",
                "Valor deve ser um número"
            }, 6 },
            {false, "13/11/2001", "", "", "", "", 1, false, new String[]{
                "Data do pagamento é obrigatório",
                "Descrição é obrigatório",
                "Interessado é obrigatório",
                "Valor é obrigatório",
                "Valor deve ser um número"
            }, 5 },
            {true, "13/11/2001", "14/11/2001", "", "", "", 1, true, new String[]{
                "Descrição é obrigatório",
                "Interessado é obrigatório",
                "Valor é obrigatório",
                "Valor deve ser um número"
            }, 4 },
            {true, "14/11/2001", "15/11/2001", "Coxinha e coca", "", "", 1, true, new String[]{
                "Interessado é obrigatório",
                "Valor é obrigatório",
                "Valor deve ser um número"
            }, 3 },
            {true, "14/11/2001", "15/11/2001", "Festa das crianças", "Luana", "", 1, false, new String[]{
                "Valor é obrigatório",
                "Valor deve ser um número"
            }, 2 },
            {true, "14/11/2001", "15/11/2001", "Raxa dos coroinhas", "Luana", "12,00", 1, true, new String[]{
                "Valor deve ser um número"
            }, 1 },
            {false, "14/11/2001", "15/11/2001", "Agora deu certo", "Luana", "12.00", 1, true, new String[]{
            }, 0 },
            
        });
    }

    @Test
    public void testBusinessRules(){
        menu.goToMoviment();

        if(isCost){ moviment.setTipMovimentCost(); }
        moviment.setDateMoviment(dateMoviment);
        moviment.setDatePayment(datePayment);
        moviment.setDescription(description);
        moviment.setInterested(interested);
        moviment.setValue(value);
        
        switch (numberAcount){
            case 1: moviment.setAccountOne();
        }
        
        if (isPaid){ moviment.setPaid(); }
        
        moviment.clickSave();
        assertTrue(errors.containsAll(errors));

        assertEquals(lengthArray, errors.size());
        if(errors.size() == 0){
            assertEquals("Movimentação adicionada com sucesso!", moviment.getAlertSucess());
        }
    }
}
