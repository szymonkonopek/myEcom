package pl.konopek.creditcard;

import java.math.BigDecimal;

public class CreditCard {
    String cardNumber;
    BigDecimal balance;
    Boolean isAssigned;
    BigDecimal withdrawsInCycle;
    BigDecimal limit;
    public CreditCard(String s) {
        this.cardNumber = s;
        this.isAssigned = false;
        this.withdrawsInCycle = BigDecimal.valueOf(0);
    }

    public void assingLimit(BigDecimal ammount) {
        if (ammount.compareTo(BigDecimal.valueOf(100)) < 0){ //
            throw new CreditBelowThreshloldException();
        }

        if (this.isAssigned){
            throw new LimitWasAlreadySet();
        }

        this.limit = ammount;
        this.isAssigned = true;
    }

    public void assignCredit(BigDecimal ammount){this.balance = ammount;}

    public void reassignLimit(BigDecimal ammount) {
        if (ammount.compareTo(BigDecimal.valueOf(100)) < 0){ //
            throw new CreditBelowThreshloldException();
        }

        this.limit = ammount;
    }

//    public BigDecimal getBalance() {return balance;}
//    public BigDecimal getLimit() {return limit;}

    public void withdraw(BigDecimal ammount){
        if (this.balance.compareTo(ammount) < 0){
            throw new NotEnoughBalance();
        }

        if (ammount.compareTo(BigDecimal.valueOf(100)) > 0){
            throw new OverTheLimitWithdraw();
        }

        if (withdrawsInCycle.compareTo(BigDecimal.valueOf(10)) > 0){
            throw new TooManyWithdrawsInOneCycle();
        }

        this.balance = this.balance.subtract(ammount);
        this.withdrawsInCycle = this.withdrawsInCycle.add(BigDecimal.valueOf(1));
    }
}
