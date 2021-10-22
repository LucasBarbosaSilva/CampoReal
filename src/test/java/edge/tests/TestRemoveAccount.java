package edge.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edge.core.BaseTest;
import edge.core.Properties;
import edge.pages.AccountsPage;
import edge.pages.MenuPage;

public class TestRemoveAccount extends BaseTest {
    MenuPage menu = new MenuPage();
    AccountsPage accounts = new AccountsPage();
    
    @Test
    public void deleteAccounInUse(){   
        menu.goToListAccounts();
        
        accounts.clickOnDelete(Properties.CONTA_ALTERADA);        
        assertEquals("Conta em uso na movimentações", accounts.getTextAlertDanger()); 
    }
    
}
