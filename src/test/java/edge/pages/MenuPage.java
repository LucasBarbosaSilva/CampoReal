package edge.pages;

import edge.core.BasePage;

public class MenuPage extends BasePage{
    public void goToCreateAccount(){
        clickLink("Contas");
        clickLink("Adicionar");
    }

    public void goToListAccounts(){
        clickLink("Contas");
        clickLink("Listar");
    }

    public void goToMoviment(){
        clickLink("Criar Movimentação");
    }

    public void goToHome(){
        clickLink("Home");
    }

    public void goToResume(){
        clickLink("Resumo Mensal");
    }

    public void exit(){
        clickLink("Sair");
    }
}
