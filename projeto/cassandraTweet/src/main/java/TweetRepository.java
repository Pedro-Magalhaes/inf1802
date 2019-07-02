import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TweetRepository {
    private static final Logger logger = Logger.getLogger(TweetRepository.class.getName());
    public static final String TABLE_NAME = "tweets";
    public static final String TABLE_NAME_BY_FAV_COUNT = TABLE_NAME + "_favoriteCount";


    private Session session;

    public TweetRepository(Session session) {
        this.session = session;
    }




    /*
    * Funçoes para a tabela por id ******************************************************
    * */

    public void createTable() {
        logger.info("Começando");
        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ")
                .append(TABLE_NAME).append("(")
                .append("id uuid PRIMARY KEY, ")
                .append("user text, ")
                .append("text text, ")
                .append("favoriteCount int, ")
                .append("isFavorited Boolean, ")
                .append("geolocation text, ")
                .append("contributors text, ")
                .append("source text, ")
                .append("isTruncated text, ")
                .append("date date);");

        final String query = sb.toString();
        logger.info(query);
        session.execute(query);
        logger.info("Finalizada");
    }



    public void inserttweet(Tweet tw) {
        logger.info("Começando");
        StringBuilder sb = new StringBuilder("INSERT INTO ")
                .append(TABLE_NAME)
                .append("(id, user, text, favoriteCount, isFavorited, date) ")
                .append("VALUES (").append(tw.getUuid()).append(", '")
                .append(tw.user).append("', '")
                .append(tw.text).append("', ")
                .append(tw.favoritedCount).append(", ")
                .append(tw.isFavorited).append(", '")
                .append(tw.createdAt).append("');");
        final String query = sb.toString();
        logger.info(query);
        session.execute(query);
        logger.info("Finalizada");
    }



    public List<Tweet> selectAll() {
        logger.info("Começando");
        StringBuilder sb = new StringBuilder("SELECT * FROM ")
                .append(TABLE_NAME).append(";");

        final String query = sb.toString();
        logger.info(query);
        ResultSet rs = session.execute(query);

        List<Tweet> tweets = new ArrayList<Tweet>();

        for (Row r : rs) {
            Tweet s = new Tweet(r.getUUID("id"),
                    r.getString("user"),
                    r.getString("text"),
                    r.getDate("date"),
                    r.getBool("isFavorited"),
                    r.getInt("favoriteCount"));
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
        logger.info(query);
        session.execute(query);
        logger.info("Finalizada");
    }

    public void deleteTable(String tableName) {
        logger.info("Começando");
        StringBuilder sb = new StringBuilder("DROP TABLE IF EXISTS ")
                .append(tableName).append(";");

        final String query = sb.toString();
        logger.info(query);
        session.execute(query);
        logger.info("Finalizada");
    }



    /*
     * Funçoes para a tabela por favorite count de cada usuario  ***********************************
     * os tweets de count == 0 não serao inseridos
     * Partition key sera o user com clustering key por favorited count decrescente
     * */

    public void createTableByFavCount() {
        logger.info("Começando");
        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ")
                .append(TABLE_NAME_BY_FAV_COUNT).append("(")
                .append("id uuid, ")
                .append("user text, ")
                .append("text text, ")
                .append("favoriteCount int, ")
                .append("date date, ")
                .append(" PRIMARY KEY (user, favoriteCount, id))")
                .append(" WITH CLUSTERING ORDER BY ( favoriteCount DESC, id ASC );");

        final String query = sb.toString();
        logger.info(query);
        session.execute(query);
        logger.info("Finalizada");
    }



    public void inserttweetByFavCount(Tweet tw) {
        logger.info("Começando");
        StringBuilder sb = new StringBuilder("INSERT INTO ")
                .append(TABLE_NAME_BY_FAV_COUNT)
                .append("(id, user, text, favoriteCount, date) ")
                .append("VALUES (").append(tw.getUuid()).append(", '")
                .append(tw.user).append("', '")
                .append(tw.text).append("', ")
                .append(tw.favoritedCount).append(", '")
                .append(tw.createdAt).append("');");
        final String query = sb.toString();
        logger.info(query);
        session.execute(query);
        logger.info("Finalizada");
    }



    public List<Tweet> selectAllByFavCount(String user) {
        logger.info("Começando");
        StringBuilder sb = new StringBuilder("SELECT * FROM ")
                .append(TABLE_NAME_BY_FAV_COUNT)
                .append(" WHERE USER = '").append(user)
                .append("' ;");

        final String query = sb.toString();
        logger.info(query);
        ResultSet rs = session.execute(query);

        List<Tweet> tweets = new ArrayList<Tweet>();

        for (Row r : rs) {
            Tweet s = new Tweet(r.getUUID("id"),
                    r.getString("user"),
                    r.getString("text"),
                    r.getDate("date"),
                    true,
                    r.getInt("favoriteCount"));
            logger.info(s.toString());
            tweets.add(s);

        }
        logger.info("Finalizada");
        return tweets;
    }


    public void deletetweetByFavCount(Tweet tw) {
        logger.info("Começando");
        StringBuilder sb = new StringBuilder("DELETE FROM ")
                .append(TABLE_NAME_BY_FAV_COUNT)
                .append(" WHERE id = ").append(tw.getUuid())
                .append(" AND ")
                .append(" favoriteCount = ").append(tw.favoritedCount)
                .append(" AND ")
                .append(" user = '").append(tw.user)
                .append("';");

        final String query = sb.toString();
        logger.info(query);
        session.execute(query);
        logger.info("Finalizada");
    }

    public void deleteTableByFavCount(String tableName) {
        logger.info("Começando");
        StringBuilder sb = new StringBuilder("DROP TABLE IF EXISTS ")
                .append(tableName).append(";");

        final String query = sb.toString();
        logger.info(query);
        session.execute(query);
        logger.info("Finalizada");
    }




    /*
    * Extra Functions   ***************************************
    */

    public void insertTweetBatch(Tweet tw) {
        logger.info("Começando");
        this.inserttweet(tw);
        this.inserttweetByFavCount(tw);
        logger.info("Finalizando");
    }
}