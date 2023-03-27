package pl.konopek.creditcard;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

public class CreditcardTest {

    @Test //1.1 Can’t be lower than 100 PLN
    void cantAssignLimitBelow100(){
        CreditCard card1 = new CreditCard("123-123");
        CreditCard card2 = new CreditCard("123-123");
        CreditCard card3 = new CreditCard("123-123");

        assertThrows(CreditBelowThreshloldException.class,
                () -> card1.assingLimit(BigDecimal.valueOf(10)));

        assertThrows(CreditBelowThreshloldException.class,
                () -> card2.assingLimit(BigDecimal.valueOf(99)));

        assertDoesNotThrow(() -> card3.assingLimit(BigDecimal.valueOf(100)));
    }

    @Test //1.2 Can’t assign limit twice
    void assignLimitTwiceTimes(){
        CreditCard card = new CreditCard("123-123");

        assertDoesNotThrow(() -> card.assingLimit(BigDecimal.valueOf(100)));
        assertThrows(LimitWasAlreadySet.class, () -> card.assingLimit(BigDecimal.valueOf(200)));
    }

    @Test //2.1 Can’t be lower than 100 PLN
    void cantReassignLimitBelow100(){
        CreditCard card = new CreditCard("123-123");

        assertThrows(CreditBelowThreshloldException.class,
                () -> card.reassignLimit(BigDecimal.valueOf(10)));

        assertThrows(CreditBelowThreshloldException.class,
                () -> card.reassignLimit(BigDecimal.valueOf(99)));

        assertDoesNotThrow(() -> card.reassignLimit(BigDecimal.valueOf(100)));
    }

    @Test //3.1 Can’t withdraw over the limit
    void cantWithdrawOverLimit(){
        CreditCard card = new CreditCard("123-123");

        card.assignCredit(BigDecimal.valueOf(200));
        card.assingLimit(BigDecimal.valueOf(100));
        assertThrows(OverTheLimitWithdraw.class, () -> card.withdraw(BigDecimal.valueOf(150)));
    }

    @Test //3.2 Can’t withdraw when not enough money
    void cantWithdrawNotEnoughMoney(){
        CreditCard card = new CreditCard("123-123");

        card.assignCredit(BigDecimal.valueOf(10));
        assertThrows(NotEnoughBalance.class, () -> card.withdraw(BigDecimal.valueOf(50)));
    }

    @Test //3.3 Can’t withdraw over 10 times in billing cycle
    void cantWithdrawOverTenTimes(){
        CreditCard card = new CreditCard("123-123");

        card.assignCredit(BigDecimal.valueOf(1000));
        for (int i = 0; i <= 10; i ++){
            assertDoesNotThrow(() -> card.withdraw(BigDecimal.valueOf(10)));
        }
        assertThrows(TooManyWithdrawsInOneCycle.class, () -> card.withdraw(BigDecimal.valueOf(10)));
    }

//    @Test
//    void itAllowsToAskingCreditLimits(){
//        //Arrange
//        CreditCard card = new CreditCard("123-456");
//
//        //Act
//        card.assingLimit(BigDecimal.valueOf(1000));
//
//        //Assert
//        assertEquals(BigDecimal.valueOf(1000), card.getLimit());
//    }
}
