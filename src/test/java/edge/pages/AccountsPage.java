package edge.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import edge.core.BasePage;

public class AccountsPage extends BasePage{
    public void setName(String value){
        writing("nome", value);
    }

    public void clickSave(){
        clickButtonByText("Salvar");
    }

    public String getTextAlertSucess(){
        return getTextBy(By.xpath("//div[@class='alert alert-success']"));  
    }

    public String getTextAlertDanger(){
        return getTextBy(By.xpath("//div[@class='alert alert-danger']"));
    }

    public void clickOnUpdate( String nameAccount ){
        WebElement cell = getCellTable("Conta", nameAccount, "Ações", "tabelaContas");
        cell.findElement(By.xpath(".//span[@class='glyphicon glyphicon-edit']")).click();;
    }

    public void clickOnDelete(String nameAccount){
        WebElement cell = getCellTable("Conta", nameAccount, "Ações", "tabelaContas");
        cell.findElement(By.xpath(".//span[@class='glyphicon glyphicon-remove-circle']")).click();
    }
    
}
