package test.db.entity;

import io.ebean.annotation.FetchPreference;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "account")
public class Account extends AbstractEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "money_sum")
    private BigDecimal moneySum = BigDecimal.ZERO;

    @Version
    private long version;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getMoneySum() {
        return moneySum;
    }

    public void setMoneySum(BigDecimal moneySum) {
        this.moneySum = moneySum;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(user, account.user) &&
                Objects.equals(moneySum, account.moneySum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, moneySum, version);
    }

    @Override
    public String toString() {
        return "Account{" +
                "user=" + user +
                ", moneySum=" + moneySum +
                ", version=" + version +
                '}';
    }
}
