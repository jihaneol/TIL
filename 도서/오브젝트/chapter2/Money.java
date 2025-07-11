package chapter2;

import java.math.BigDecimal;

public class Money {
    public static final Money ZERO = Money.wons(0);

    private final BigDecimal amount;

    public Money(BigDecimal bigDecimal) {
        this.amount = bigDecimal;
    }

    public Money times(double percent) {
        return new Money(this.amount.multiply(
                BigDecimal.valueOf(percent) ));
    }

    public static Money wons(long amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public Money minus(Money amount) {
        return new Money(this.amount.subtract(amount.amount));
    }

    public void plus(Money money) {

    }
}
