package test.db.entity.dto;

import java.math.BigDecimal;

public class Transfer<T> {
    public final T from;
    public final T to;
    public final BigDecimal amount;

    public Transfer(T from, T to, BigDecimal amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }
}
