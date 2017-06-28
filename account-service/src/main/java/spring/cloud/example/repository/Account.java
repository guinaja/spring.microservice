package spring.cloud.example.repository;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "T_ACCOUNT")
@Getter
@Setter
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    public static Long nextId = 0L;

    @Id
    protected Long id;

    protected String number;

    @Column(name = "name")
    protected String owner;

    protected BigDecimal balance;

    protected Account() {
        balance = BigDecimal.ZERO;
    }

    public Account(String number, String owner) {
        id = getNextId();
        this.number = number;
        this.owner = owner;
        balance = BigDecimal.ZERO;
    }


    protected static Long getNextId() {
        synchronized (nextId) {
            return nextId++;
        }
    }

    @Override
    public String toString() {
        return number + " [" + owner + "]: $" + balance;
    }

}
