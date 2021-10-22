package edge.pages;

import edge.core.BasePage;

import static edge.core.DriverFactory.getDriver;

public class LoginPage extends BasePage{
    String server = "https://seubarriga.wcaquino.me/";
    
    public void goScreenHome(){
        getDriver().get(server);
    }
    
    public void setEmail(String email){
        writing("email", email );
    }

    public void setPassword(String password){
        writing("senha", password);
    }

    public void enter(){
        clickButtonByText("Entrar");
    }

    public void login(String email, String password ){
        setEmail(email);
        setPassword(password);
        enter();
    }
}
