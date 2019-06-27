import com.datastax.driver.core.*;
import com.datastax.driver.core.utils.UUIDs;

import java.util.Random;
import java.util.UUID;

public class HelloTweet {
    public static void main(String[] args){
        System.out.println("Inicio");
        Cluster cluster = null;
        TweetRepository br = null;
        try{
            cluster = Cluster.builder()
                    .addContactPoint("localhost")
                    .build();
            Session session = cluster.connect();

            ResultSet rs = session.execute("select release_version from system.local");
            Row row = rs.one();
            System.out.println("versão: " + row.getString("release_version"));

            KeyspaceRepository sr = new KeyspaceRepository(session);
            sr.createKeyspace("twitter","SimpleStrategy",1);
            System.out.println("keyspace twitter criado");
            sr.useKeyspace("twitter");
            System.out.println("usando keyspace twitter");

            br = new TweetRepository(session);
            br.createTable();
            br.createTableByFavCount();

            System.out.println("Inserindo Tweets");

            Tweet[] ts = new Tweet[5];
            final Random random = new Random();

            for (int i = 0; i < 5; i++) {
                int favCount = random.nextInt(100); // até 100 favoritos
                boolean isFav = favCount == 0 ? false : true;
                LocalDate dt = LocalDate.fromYearMonthDay(2019,01,20 + i);


                ts[i] = new Tweet(UUID.randomUUID(),"user", "texto do user "+i,
                        dt, isFav , favCount);
                ts[i].geolocation = "lat: 100,long:200";
                ts[i].contributors = "alguem";
                ts[i].isTruncated = favCount < 60 ? true : false; // 60% de chances de ser truncated
                ts[i].source = "https://www.twitter.com";
                br.insertTweetBatch(ts[i]);
            }


            br.selectAll(); // print na função
            br.selectAllByFavCount("user");

            for (int i = 0; i < 5; i++) {
                br.deletetweet(ts[i]);
                if(ts[i].isFavorited == true) {
                    br.deletetweetByFavCount(ts[i]);
                }
            }

            //System.out.println("Tweets deletados");


        } finally {
            if (cluster != null) {
                if(br != null) {
                    br.deleteTable(TweetRepository.TABLE_NAME);
                    br.deleteTable(TweetRepository.TABLE_NAME_BY_FAV_COUNT);
                    System.out.println("Tabelas deletadas");
                }

                cluster.close();
            }
        }

    }
}
