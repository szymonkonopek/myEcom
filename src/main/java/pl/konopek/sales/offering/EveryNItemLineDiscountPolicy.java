package pl.konopek.sales.offering;

import java.math.BigDecimal;

public class EveryNItemLineDiscountPolicy {
    private final int quantityThreshold;

    public EveryNItemLineDiscountPolicy(int quantityThreshold) {

        this.quantityThreshold = quantityThreshold;
    }

    public OfferLine apply(OfferLine line) {
        if (!isApplicable(line)) {
            return line;
        }

        double i = Math.floor((double) line.getQuantity() / (double) quantityThreshold);

        BigDecimal lineTotal = line
                .getUnitPrice()
                .multiply(BigDecimal.valueOf(line.getQuantity()))
                .subtract(BigDecimal.valueOf(i).multiply(line.getUnitPrice()));

        return new OfferLine(line.getProductId(), line.getName(), line.getUnitPrice(), line.getQuantity(), lineTotal);
    }

    private boolean isApplicable(OfferLine line) {
        float i = (float) line.getQuantity() / (float) quantityThreshold;
        return i > 0;
    }
}
