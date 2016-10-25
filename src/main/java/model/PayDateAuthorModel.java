package model;

/**
 * Created by Knigh on 2016/9/13.
 */
public class PayDateAuthorModel {
    private double pay;
    private String date;
    private String author;
    private String remark;

    public PayDateAuthorModel() {
    }

    public PayDateAuthorModel(double pay, String date, String author, String remark) {
        this.pay = pay;
        this.date = date;
        this.author = author;
        this.remark = remark;
    }

    public double getPay() {
        return pay;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "PayDateAuthorModel{" +
                "pay=" + pay +
                ", date='" + date + '\'' +
                ", author='" + author + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
