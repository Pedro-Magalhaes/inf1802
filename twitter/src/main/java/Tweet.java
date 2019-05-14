import java.util.Date;

public class Tweet {
    private String userName;
    private String text;
    private Date date;

    public Tweet() {
    }

    public Tweet(String userName, String text, Date date) {
        this.userName = userName;
        this.text = text;
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return this.userName + ":  " + this.text + "\n " + this.date.toGMTString();
    }
}
