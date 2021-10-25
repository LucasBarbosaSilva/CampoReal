package edge.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edge.core.BaseTest;
import edge.pages.MenuPage;
import edge.pages.AccountsPage;

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
        accounts.clickOnUpdate("Conta para alterar"); 
        accounts.setName("Conta alterada");
        accounts.clickSave();
        assertEquals("Conta alterada com sucesso!", accounts.getTextAlertSucess()); 
    }

    @Test
    public void test3_InsertDuplicateAccount(){   
        menu.goToCreateAccount();
        accounts.setName("Conta mesmo nome");
        accounts.clickSave();
        assertEquals("Já existe uma conta com esse nome!", accounts.getTextAlertDanger()); 
    }
}
