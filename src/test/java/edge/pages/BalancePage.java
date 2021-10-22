package edge.pages;

import edge.core.BasePage;

public class BalancePage extends BasePage{
    public String getTextBalance(String nameAccount){
        return getCellTable("Conta", nameAccount, "Saldo", "tabelaSaldo").getText();
    }
}
