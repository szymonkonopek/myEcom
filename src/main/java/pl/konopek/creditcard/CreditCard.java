package pl.konopek.creditcard;

import java.math.BigDecimal;

public class CreditCard {
    private BigDecimal balance;
    private BigDecimal credit;

    public CreditCard(String cardNumber) {
    }

    public void assignCredit(BigDecimal creditAmount) {
        if (isCreditAlreadyAssigned()) {
            throw new LimitAssignedTwiceException();
        }

        if (isBelowCreditThreshold(creditAmount)) {
            throw new CreditBelowThresholdException();
        }

        this.balance = creditAmount;
        this.credit = creditAmount;
    }

    private boolean isCreditAlreadyAssigned() {
        return credit != null;
    }

    private boolean isBelowCreditThreshold(BigDecimal creditAmount) {
        return creditAmount.compareTo(BigDecimal.valueOf(100)) < 0;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void withdraw(BigDecimal money) {
        this.balance = balance.subtract(money);
    }
}
