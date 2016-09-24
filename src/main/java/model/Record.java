package model;

/**
 * Created by Knigh on 2016/9/24.
 */
public class Record extends BaseModel {
    private String charactername;
    private String money;
    private String date;
    private String author;

    public Record() {
    }

    public Record(String charactername, String money, String date, String author) {
        this.charactername = charactername;
        this.money = money;
        this.date = date;
        this.author = author;
    }

    public String getCharactername() {
        return charactername;
    }

    public void setCharactername(String charactername) {
        this.charactername = charactername;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
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

    @Override
    public String toString() {
        return "Record{" +
                "charactername='" + charactername + '\'' +
                ", money='" + money + '\'' +
                ", date='" + date + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
