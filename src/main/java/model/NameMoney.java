package model;

import java.math.BigDecimal;

/**
 * Created by Knigh on 2016/9/8.
 */
public class NameMoney {
    private String name;
    private BigDecimal money;

    public NameMoney(String name, BigDecimal money) {
        this.name = name;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "NameMoney{" +
                "name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}
