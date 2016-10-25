package model;

/**
 * Created by Knigh on 2016/9/13.
 */
public class AddPayMoneyRequest extends BaseModel {
    private String username;
    private String paymoney;
    private String remarks;

    public AddPayMoneyRequest() {
    }

    public AddPayMoneyRequest(String username, String paymoney, String remarks) {
        this.username = username;
        this.paymoney = paymoney;
        this.remarks = remarks;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPaymoney() {
        return paymoney;
    }

    public void setPaymoney(String paymoney) {
        this.paymoney = paymoney;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "AddPayMoneyRequest{" +
                "username='" + username + '\'' +
                ", paymoney='" + paymoney + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
