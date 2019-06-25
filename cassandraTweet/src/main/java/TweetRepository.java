import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TweetRepository {
    private static final Logger logger = Logger.getLogger(TweetRepository.class.getName());
    private static final String TABLE_NAME = "tweets";


    private Session session;

    public TweetRepository(Session session) {
        this.session = session;
    }

    public void createTable() {
        logger.info("<funcao createTable>: Começando");
        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ")
                .append(TABLE_NAME).append("(")
                .append("id uuid PRIMARY KEY, ")
                .append("user text, ")
                .append("text text, ")
                .append("date date);");

        final String query = sb.toString();
        session.execute(query);
        logger.info("<Finalizada");
    }



    public void inserttweet(Tweet tw) {
        logger.info("Começando");
        StringBuilder sb = new StringBuilder("INSERT INTO ")
                .append(TABLE_NAME)
                .append("(id, user, text, date) ")
                .append("VALUES (").append(tw.getUuid()).append(", '")
                .append(tw.user).append("', '")
                .append(tw.text).append("', '")
                .append(tw.createdAt).append("');");
        final String query = sb.toString();
        session.execute(query);
        logger.info("Finalizada");
    }



    public List<Tweet> selectAll() {
        logger.info("Começando");
        StringBuilder sb = new StringBuilder("SELECT * FROM ")
                .append(TABLE_NAME).append(";");

        final String query = sb.toString();

        ResultSet rs = session.execute(query);

        List<Tweet> tweets = new ArrayList<Tweet>();

        for (Row r : rs) {
            Tweet s = new Tweet(r.getUUID("id"),
                    r.getString("user"),
                    r.getString("text"),
                    r.getDate("date"));
            logger.info(s.toString());
            tweets.add(s);

        }
        logger.info("Finalizada");
        return tweets;
    }


    public void deletetweet(Tweet tw) {
        logger.info("Começando");
        StringBuilder sb = new StringBuilder("DELETE FROM ")
                .append(TABLE_NAME)
                .append(" WHERE id = ").append(tw.getUuid()).append(";");

        final String query = sb.toString();

        session.execute(query);
        logger.info("Finalizada");
    }

    public void deleteTable(String tableName) {
        logger.info("Começando");
        StringBuilder sb = new StringBuilder("DROP TABLE IF EXISTS ")
                .append(tableName).append(";");

        final String query = sb.toString();

        session.execute(query);
        logger.info("Finalizada");
    }
}