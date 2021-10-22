package edge.pages;

import org.openqa.selenium.By;

import edge.core.BasePage;

public class ResumePage extends BasePage{
    public void selectMounthOctuber(){
        selectCombo("mes", "Outubro");
    }

    public void selectYear2020(){
        selectCombo("ano", "2020");
    }

    public void clickToSearch(){
        clickButtonByValue("Buscar");
    }

    public void deleteMoviment(){
        clickButton(By.xpath("//span[@class='glyphicon glyphicon-remove-circle']"));
    }

    public int getSizeList(){
        return getSizeTable("tabelaExtrato");
    }

    public String getTextAlertSucess(){
        return getTextBy(By.xpath("//div[@class='alert alert-success']"));  
    }

    public String getTextAlertDanger(){
        return getTextBy(By.xpath("//div[@class='alert alert-danger']"));
    }
}   
