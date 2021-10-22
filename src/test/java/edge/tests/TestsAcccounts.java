package edge.tests;

import static org.junit.Assert.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import edge.core.BaseTest;
import edge.core.Properties;
import edge.pages.MenuPage;
import edge.pages.AccountsPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsAcccounts extends BaseTest{
    
    MenuPage menu = new MenuPage();
    AccountsPage accounts = new AccountsPage();
    
    @Test
    public void test1_CreateAccount(){   
        menu.goToCreateAccount();
        accounts.setName("Nova Conta");
        accounts.clickSave();
        assertEquals("Conta adicionada com sucesso!", accounts.getTextAlertSucess()); 
    }
    
    @Test
    public void test2_UpdateAccount(){   
        menu.goToListAccounts();
        accounts.clickOnUpdate("Nova Conta"); 
        accounts.setName(Properties.CONTA_ALTERADA);
        accounts.clickSave();
        assertEquals("Conta alterada com sucesso!", accounts.getTextAlertSucess()); 
    }

    @Test
    public void test3_InsertDuplicateAccount(){   
        menu.goToCreateAccount();
        accounts.setName(Properties.CONTA_ALTERADA);
        accounts.clickSave();
        assertEquals("Já existe uma conta com esse nome!", accounts.getTextAlertDanger()); 
    }
}
