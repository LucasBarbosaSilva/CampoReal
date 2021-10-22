package edge.core;

import java.util.List;

import static edge.core.DriverFactory.getDriver;

import java.util.ArrayList;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class BasePage {
    
    public String getRadicalPrime(By by){
        
        return getDriver().findElement(by).getAttribute("id") + ":";
    }
    
    public void writingBy(By by, String texto){
        getDriver().findElement(by).clear();
        getDriver().findElement(by).sendKeys(texto);
    }

    public void writing(String id_camp, String texto){
        this.writingBy(By.id(id_camp), texto);
    }

    public String getCampValue(String id_camp){  
        return getDriver().findElement(By.id(id_camp)).getAttribute("value");
    }

    public String getCampValueBy(By by){  
        return getDriver().findElement(by).getAttribute("value");
    }

    public void clickRadioBy(By by){
        getDriver().findElement(by).click();
    }
    
    public void clickRadio(String id){
        this.clickRadioBy(By.id(id));
    }

    public boolean isradioCheckedBy(By by){
        return getDriver().findElement(by).isSelected();
    }
    
    public boolean isradioChecked(String id){
        return isradioCheckedBy(By.id(id));
    }

    public void selectComboBy(By by, String value){
        WebElement element = getDriver().findElement(by);
        Select combo = new Select(element);
        combo.selectByVisibleText(value);
    }

    public void selectCombo(String id, String value){
        selectComboBy(By.id(id), value);
    }

    public void deselectCombo(String id, String value){
        WebElement element = getDriver().findElement(By.id(id));
        Select combo = new Select(element);
        combo.deselectByVisibleText(value);
    }

    public void selectComboPrime(String radical, String value){
        clickRadioBy(By.xpath("//*[@id='"+radical+"option_label']/../..//span"));   
        selectList(By.xpath("//*[@id='"+radical+"option_items']//li[.='"+value+"']"));

    }

    
    public String getComboValue(String id){
        WebElement element = getDriver().findElement(By.id(id));    
        Select combo = new Select(element);
        return combo.getFirstSelectedOption().getText();    
        
    }

    public WebElement clickButton(By by){
        WebElement button = getDriver().findElement(by);
        button.click();
        return button;
    }

    public WebElement clickButtonById(String id){
        return clickButton(By.id(id));
    }

    public void clickButtonByText(String text){
        clickButton(By.xpath("//*[.='"+text+"']"));
    }
    
    public void clickButtonByValue(String value){
        clickButton(By.xpath("//*[@value='"+value+"']"));
    }

    public void clickLink(By by){
        getDriver().findElement(by).click();
    }

    public void clickLink(String text){
        clickLink(By.linkText(text));
    }

    public String getTextBy(By by) {
        return getDriver().findElement(by).getText();
    }
    
    public int getLengthCombo(String id){
        WebElement element = getDriver().findElement(By.id(id));
        Select combo = new Select(element);
        return combo.getOptions().size();
    }

    public List<String> getAllSelected(String id){
        WebElement element = getDriver().findElement(By.id(id));
        Select combo = new Select(element);
        List<WebElement>  options = combo.getAllSelectedOptions();
        List<String> allElementsSelected = new ArrayList<String>();
        for (WebElement option: options) {
            allElementsSelected.add(option.getText());
        }
        
        return allElementsSelected;
    }

    public boolean findElementCombo(String id, String value) {
        WebElement element = getDriver().findElement(By.id(id));
        Select combo = new Select(element);
        List<WebElement> options = combo.getOptions();
        
        boolean finded = false;
        for (WebElement option: options){
            if(option.getText().equals(value)){
                finded = true;
                break;
            }
        }
        return finded;
    }

    public String alertTextAccept(){
        Alert alert = getDriver().switchTo().alert();
        String text = alert.getText();
        alert.accept();
        return text;
    }

    public Object runJS(String cmd, Object... params){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        return js.executeScript(cmd, params);
    }

    public void switchToFrame(String frame){
        getDriver().switchTo().frame(frame);
    }

    public void switchToDefault(){
        getDriver().switchTo().defaultContent();
    }

    public void switchWindow(String id){
        getDriver().switchTo().window(id);
    }

    public int findColumn(WebElement table, String nameColumn){
        List<WebElement> columns = table.findElements(By.xpath(".//th"));
        int idColumn = -1;
        for (int i = 0; i < columns.size(); i++){
            if (columns.get(i).getText().equals(nameColumn)){
                idColumn = i+1;
                break;
            }
        }
        return idColumn;
    }

    public int getIdRow(WebElement table, int idColumn, String value){
        List<WebElement> rows = table.findElements(By.xpath("./tbody/tr/td["+idColumn+"]"));
        int idRow = -1;
        for (int i = 0; i < rows.size(); i++){
            if (rows.get(i).getText().equals(value)){
                idRow = i+1;
                break;
            }
        }

        return idRow;
    }

    public WebElement getCellTable(String nameColumn, String name, String nameButton, String idTable){
        WebElement table =  getDriver().findElement(By.xpath("//*[@id='"+idTable+"']"));
        int idColumn = findColumn(table, nameColumn);
        
        //procurar registro
        int idRow = getIdRow(table, idColumn, name);

        //descobrir o botão
        int columnButton = findColumn(table, nameButton);

        //clicar no botão
        WebElement cell = table.findElement(By.xpath("./tbody/tr["+idRow+"]/td["+columnButton+"]"));
        return cell;
    }

    public WebElement getCellByIndex(int index, String nameButton, String idTable){
        WebElement table =  getDriver().findElement(By.xpath("//*[@id='"+idTable+"'  ]"));

        //descobrir o botão
        int columnButton = findColumn(table, nameButton);

        //clicar no botão
        WebElement cell = table.findElement(By.xpath("./tbody/tr["+index+"]/td["+columnButton+"]"));
        return cell;
    }

    public void clickOnButtonInTable(String nameColumn, String name, String nameButton, String idTable){
        WebElement cell = getCellTable(nameColumn, name, nameButton, idTable);
        cell.findElement(By.xpath(".//input")).click();
    }

    public int getSizeTable(String id){
        WebElement table =  getDriver().findElement(By.xpath("//*[@id='"+id+"'  ]"));
        List<WebElement> rows = table.findElements(By.xpath("//tbody//tr"));
        return rows.size();
    }

    public void selectList(By by){
        getDriver().findElement(by).click();
    }
    
}
