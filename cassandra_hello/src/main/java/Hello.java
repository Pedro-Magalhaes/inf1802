import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.utils.UUIDs;

public class Hello {
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
            System.out.println(row.getString("release_version"));

            KeyspaceRepository sr = new KeyspaceRepository(session);
            sr.createKeyspace("library","SimpleStrategy",1);
            System.out.println("keyspace criado");
            sr.useKeyspace("library");
            System.out.println("usando keyspace library");

            BookRepository br = new BookRepository(session);
            br.createTable();
            br.createTableBooksByTitle();
            System.out.println("tabelas criadas");

            try {
                br.alterTablebooks("publisher","text");
                System.out.println("coluna publisher adicionada");
            } catch (Exception e) {
                System.out.println("coluna publisher ja existia");
            }


            Book book = new Book(UUIDs.timeBased(),"java 1","alguem","programming");
            try {
                br.insertbookBatch(book);
            } catch (Exception e) {
                System.out.println("livro " + book.getTitle() + " ja existe");
            }
            Book book2 = new Book(UUIDs.timeBased(),"java 2","alguem","programming");
            try {
                br.insertbookBatch(book2);
                System.out.println("livro java1 e 2 adicionados");
            } catch (Exception e) {
                System.out.println("livro " + book2.getTitle() + " ja existe");
            }


            br.selectAll(); // print na função

            br.deletebookByTitle("java 1");
            System.out.println("Livro java 1 deletado");

            br.deleteTable("books");
            br.deleteTable("booksByTitle");
            System.out.println("Tabelas deletadas");

            sr.deleteKeyspace("library");
            System.out.println("Keyspace library deletada");
        } finally {
            if (cluster != null) {
                cluster.close();
            }
        }

    }
}
