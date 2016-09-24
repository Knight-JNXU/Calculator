package model;

/**
 * Created by Knigh on 2016/9/13.
 */
public class AddCharacterRequest extends BaseModel {
    private String username;
    private String paymoney;

    public AddCharacterRequest() {
    }

    public AddCharacterRequest(String username, String paymoney) {
        this.username = username;
        this.paymoney = paymoney;
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
}
