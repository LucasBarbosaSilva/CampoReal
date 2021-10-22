package edge.tests;

import static org.junit.Assert.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import edge.core.BaseTest;
import edge.pages.MenuPage;
import edge.pages.ResumePage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
        assertEquals(0, resume.getSizeList()); 
    }
}
