package model;

import java.util.List;

/**
 * Created by Knigh on 2016/9/3.
 */
public class CharacterModel extends BaseModel {

    private String Name;
    private List<Double> MoneyList;
    private double total;
    private List<String> dateList;
    private List<PayDateAuthorModel> payDateList;

    public CharacterModel() {
    }

    public CharacterModel(String name, List<Double> moneyList, double total,
                          List<String> dateList, List<PayDateAuthorModel> payDateList) {
        Name = name;
        MoneyList = moneyList;
        this.total = total;
        this.dateList = dateList;
        this.payDateList = payDateList;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<Double> getMoneyList() {
        return MoneyList;
    }

    public void setMoneyList(List<Double> moneyList) {
        MoneyList = moneyList;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<PayDateAuthorModel> getPayDateList() {
        return payDateList;
    }

    public void setPayDateList(List<PayDateAuthorModel> payDateList) {
        this.payDateList = payDateList;
    }

    public List<String> getDateList() {
        return dateList;
    }

    public void setDateList(List<String> dateList) {
        this.dateList = dateList;
    }

    @Override
    public String toString() {
        return "CharacterModel{" +
                "Name='" + Name + '\'' +
                ", MoneyList=" + MoneyList +
                ", total=" + total +
                ", dateList=" + dateList +
                ", payDateList=" + payDateList +
                '}';
    }
}
