package model;

import java.math.BigDecimal;

/**
 * Created by Knigh on 2016/9/8.
 */
public class PayRelationShip {
    private String fromName;
    private String toName;
    private BigDecimal money;

    public PayRelationShip() {
    }

    public PayRelationShip(String fromName, String toName, BigDecimal money) {
        this.fromName = fromName;
        this.toName = toName;
        this.money = money;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return fromName+"->"+toName+":"+money;
    }

}
