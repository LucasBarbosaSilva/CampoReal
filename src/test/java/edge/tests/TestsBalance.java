package edge.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edge.core.BaseTest;
import edge.core.Properties;
import edge.pages.BalancePage;
import edge.pages.MenuPage;

public class TestsBalance extends BaseTest{
    BalancePage balance = new BalancePage();
    MenuPage menu = new MenuPage();

    @Test
    public void checkBalance(){ 
        menu.goToHome();
        assertEquals("15.00", balance.getTextBalance(Properties.CONTA_ALTERADA));
    }
}   
