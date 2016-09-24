package model;

/**
 * Created by Knigh on 2016/9/13.
 */
public class PayDateAuthorModel {
    private double pay;
    private String date;
    private String author;

    public PayDateAuthorModel() {
    }

    public PayDateAuthorModel(double pay, String date, String author) {
        this.pay = pay;
        this.date = date;
        this.author = author;
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
}
