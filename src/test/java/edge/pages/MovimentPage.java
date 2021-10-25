package edge.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import edge.core.BasePage;

import static edge.core.DriverFactory.getDriver;

public class MovimentPage extends BasePage {

    public void setTipMovimentCost(){
        selectCombo("tipo", "Receita");
    }

    public void setDateMoviment(String date){
        writing("data_transacao", date);
    }

    public void setDatePayment(String date){
        writing("data_pagamento", date);
    }

    public void setDescription(String description){
        writing("descricao", description);
    }

    public void setInterested(String interested){
        writing("interessado", interested);
    }

    public void setValue(String value){
        writing("valor", value);
    }
    
    public void setAccountOne(){
        selectCombo("conta", "Conta para movimentacoes");
    }

    public void setPaid(){
        clickRadio("status_pago");
    }

    public void clickSave(){
        clickButtonByText("Salvar");
    }

    public String getAlertSucess(){
        return getTextBy(By.xpath("//div[@class='alert alert-success']"));
    }

    public String getTextAlertDanger(){
        return getTextBy(By.xpath("//div[@class='alert alert-danger']"));
    }

    public List<String> getTextAllErrors(){
        List<WebElement> errors = getDriver().findElements(By.xpath("//div[@class='alert alert-danger']//li"));
        List<String> returnList = new ArrayList<String>();

        for(WebElement erro : errors){
            returnList.add(erro.getText());
        }

        return returnList;
    }
    
}
