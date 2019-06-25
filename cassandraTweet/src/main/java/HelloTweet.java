import com.datastax.driver.core.*;
import com.datastax.driver.core.utils.UUIDs;

import java.util.UUID;

public class HelloTweet {
    public static void main(String[] args){
        System.out.println("Inicio");
        Cluster cluster = null;
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

            TweetRepository br = new TweetRepository(session);
            br.createTable();
            System.out.println("tabela");

            System.out.println("Inserindo Tweets");

            Tweet[] ts = new Tweet[5];

            for (int i = 0; i < 5; i++) {
                ts[i] = new Tweet(UUID.randomUUID(),"user"+i, "texto do user"+i,
                        LocalDate.fromMillisSinceEpoch(System.currentTimeMillis()));
                br.inserttweet(ts[i]);
            }


            br.selectAll(); // print na função

            for (int i = 0; i < 5; i++) {
                br.deletetweet(ts[i]);
            }

            //System.out.println("Tweets deletados");

            br.deleteTable("tweets");
            System.out.println("Tabela tweets deletada");

            sr.deleteKeyspace("twitter");
            System.out.println("Keyspace twitter deletada");
        } finally {
            if (cluster != null) {
                cluster.close();
            }
        }

    }
}
