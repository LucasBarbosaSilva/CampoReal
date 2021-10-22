package edge.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import edge.core.BaseTest;
import edge.pages.MenuPage;
import edge.pages.MovimentPage;
import edge.utils.DateUtils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsMovimentation extends BaseTest{
    MenuPage menu = new MenuPage();    
    MovimentPage moviment = new MovimentPage();

    @Test
    public void test1_CreateMoviment(){
        menu.goToMoviment();

        String newDate = DateUtils.getDateFomated(new Date());

        moviment.setTipMovimentCost();
        moviment.setDateMoviment(newDate);
        moviment.setDatePayment(newDate);
        moviment.setDescription("Racha da coxinha de sábado");
        moviment.setInterested("Luiz");
        moviment.setValue("15.00");
        moviment.setAccountOne();
        moviment.setPaid();
        moviment.clickSave();

        assertEquals("Movimentação adicionada com sucesso!", moviment.getAlertSucess());

    }

    @Test
    public void test2_WithoutDateMoviment(){
        menu.goToMoviment();

        moviment.clickSave();
        List<String> errors = moviment.getTextAllErrors();
        assertTrue(errors.containsAll(Arrays.asList(
            "Data da Movimentação é obrigatório",
            "Data do pagamento é obrigatório",
            "Descrição é obrigatório",
            "Interessado é obrigatório",
            "Valor é obrigatório",
            "Valor deve ser um número"
        )));
        assertEquals(6, errors.size());
    }

    @Test
    public void test3_WithoutPaiment(){
        menu.goToMoviment();

        moviment.setDateMoviment("08/11/2020");
        moviment.clickSave();
        List<String> errors = moviment.getTextAllErrors();
        assertTrue(errors.containsAll(Arrays.asList(
            "Data do pagamento é obrigatório",
            "Descrição é obrigatório",
            "Interessado é obrigatório",
            "Valor é obrigatório",
            "Valor deve ser um número"
        )));
        assertEquals(5, errors.size());
    }

    @Test
    public void test4_WithoutDescription(){
        menu.goToMoviment();

        moviment.setDateMoviment("08/11/2020");
        moviment.setDatePayment("09/11/2020");
        moviment.clickSave();
        List<String> errors = moviment.getTextAllErrors();
        assertTrue(errors.containsAll(Arrays.asList(
            "Descrição é obrigatório",
            "Interessado é obrigatório",
            "Valor é obrigatório",
            "Valor deve ser um número"
        )));
        assertEquals(4, errors.size());
    }

    @Test
    public void test5_WithoutInterested(){
        menu.goToMoviment();

        moviment.setTipMovimentCost();
        moviment.setDateMoviment("08/11/2020");
        moviment.setDatePayment("09/11/2020");
        moviment.setDescription("PIX da pizza");
        moviment.clickSave();
        List<String> errors = moviment.getTextAllErrors();
        assertTrue(errors.containsAll(Arrays.asList(
            "Interessado é obrigatório",
            "Valor é obrigatório",
            "Valor deve ser um número"
        )));
        assertEquals(3, errors.size());
    }

    @Test
    public void test6_WithoutValue(){
        menu.goToMoviment();

        moviment.setTipMovimentCost();
        moviment.setDateMoviment("08/11/2020");
        moviment.setDatePayment("09/11/2020");
        moviment.setDescription("PIX da pizza");
        moviment.setInterested("Lucas");
        moviment.clickSave();
        List<String> errors = moviment.getTextAllErrors();
        assertTrue(errors.containsAll(Arrays.asList(
            "Valor é obrigatório",
            "Valor deve ser um número"
        )));
        assertEquals(2, errors.size());
    }

    @Test
    public void test7_ValueNoNumber(){
        menu.goToMoviment();

        moviment.setTipMovimentCost();
        moviment.setDateMoviment("08/11/2020");
        moviment.setDatePayment("09/11/2020");
        moviment.setDescription("PIX da pizza");
        moviment.setInterested("Lucas");
        moviment.setValue("12,00");
        moviment.clickSave();
        List<String> errors = moviment.getTextAllErrors();
        assertTrue(errors.containsAll(Arrays.asList(
            "Valor deve ser um número"
        )));
        assertEquals(1, errors.size());
    }

    @Test
    public void test8_MovientFuture(){
        menu.goToMoviment();
        
        String newDateFuture = DateUtils.getDateFomated(DateUtils.getDateDiffDays(10));

        moviment.setTipMovimentCost();
        moviment.setDateMoviment(newDateFuture);
        moviment.setDatePayment("09/10/2020");
        moviment.setDescription("Racha da coxinha de sábado");
        moviment.setInterested("Luiz");
        moviment.setValue("15.00");
        moviment.setAccountOne();
        moviment.setPaid();
        moviment.clickSave();
        
        moviment.clickSave();
        List<String> errors = moviment.getTextAllErrors();
        assertTrue(errors.contains("Data da Movimentação deve ser menor ou igual à data atual"));
        assertEquals(1, errors.size());
    }
}   

