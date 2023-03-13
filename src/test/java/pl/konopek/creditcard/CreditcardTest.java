package pl.konopek.creditcard;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

public class CreditcardTest {
    @Test
    void itAllowsToAskingCreditLimit(){
        //Arrange
        CreditCard card = new CreditCard("123-456");

        //Act
        card.assingLimit(BigDecimal.valueOf(1000));

        //Assert
        assertEquals(card.getBalance(), BigDecimal.valueOf(1000));
    }
    @Test
    void testDoubleAndFloats(){
        double x1 =0.03;
        double x2 = 0.01;
        double result = x1 - x2;

        System.out.println(result);
    }

    @Test
    void itAllowsToAskingCreditLimits(){
        //Arrange
        CreditCard card = new CreditCard("123-456");

        //Act
        card.assingLimit(BigDecimal.valueOf(1000));

        //Assert
        assertEquals(BigDecimal.valueOf(1000), card.getBalance());
    }

    @Test
    void cantAssignLimitBelow100(){
        CreditCard card = new CreditCard("123-123");
        try {
            card.assingLimit(BigDecimal.valueOf(50));
            fail("should throw exception");
        } catch (CreditBelowThreshloldException e){
            assertTrue(true);
        }

        assertThrows(CreditBelowThreshloldException.class,
                () -> card.assingLimit(BigDecimal.valueOf(10)));
    }
}
