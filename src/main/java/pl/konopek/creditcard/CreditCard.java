package pl.konopek.creditcard;

import java.math.BigDecimal;

public class CreditCard {
    String cardNumber;
    BigDecimal balance;
    public CreditCard(String s) {
        this.cardNumber = s;
    }

    public void assingLimit(BigDecimal ammount) {
        if (ammount.compareTo(BigDecimal.valueOf(100)) < 1){
            throw new CreditBelowThreshloldException();
        }
        this.balance = ammount;

    }

    public BigDecimal getBalance() {
        return BigDecimal.valueOf(1000);
    }
}
