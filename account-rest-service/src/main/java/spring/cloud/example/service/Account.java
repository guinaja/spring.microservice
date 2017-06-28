package spring.cloud.example.service;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@JsonRootName("Account")
public class Account {

    protected Long id;
    protected String number;
    protected String owner;
    protected BigDecimal balance;

    /**
     * Default constructor for JPA only.
     */
    protected Account() {
        balance = BigDecimal.ZERO;
    }

    public BigDecimal getBalance() {
        return balance.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    protected void setBalance(BigDecimal value) {
        balance = value;
        balance.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    @Override
    public String toString() {
        return number + " [" + owner + "]: $" + balance;
    }

}
