package edge.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edge.core.BaseTest;
import edge.pages.MenuPage;
import edge.pages.ResumePage;

public class TestsResume extends BaseTest{
    MenuPage menu = new MenuPage();
    ResumePage resume = new ResumePage();
    
    @Test
    public void test1_deleteMoviment(){
        menu.goToResume();
        resume.deleteMoviment();
        assertEquals("Movimentação removida com sucesso!", resume.getTextAlertSucess());      
    }

    @Test
    public void test2_ResumeEmpty(){
        menu.goToResume();
        resume.selectMounthOctuber();
        resume.selectYear2020();
        resume.clickToSearch(); 
        assertEquals(0, resume.getSizeList()); 
    }
}
