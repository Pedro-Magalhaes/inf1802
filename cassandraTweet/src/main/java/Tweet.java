import com.datastax.driver.core.LocalDate;

import java.util.UUID;

public class Tweet {

    public UUID uuid;
    public String user;
    public String text;
    public LocalDate createdAt;

    public Tweet() {  }

    public Tweet(String user, String text, LocalDate createdAt) {
        this.uuid = UUID.randomUUID();
        this.user = user;
        this.text = text;
        this.createdAt = createdAt;
    }

    public Tweet(UUID uuid, String user, String text, LocalDate createdAt) {
        this.uuid = uuid;
        this.user = user;
        this.text = text;
        this.createdAt = createdAt;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
    @Override
    public String toString() {
        return "tweet id: " + uuid +
                " From user: " + user +
                " Date: " + createdAt.toString() +
                " \n " + text;
    }
}
