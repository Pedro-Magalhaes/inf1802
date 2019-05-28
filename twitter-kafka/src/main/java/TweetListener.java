import org.apache.kafka.clients.producer.*;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

import java.util.logging.Logger;

public class TweetListener implements StatusListener {

    private static final Logger logger = Logger.getLogger(TweetListener.class.getName());
    KafkaProducer<String,Tweet> producer;
    private static final String topic = "tweet";

    public TweetListener(KafkaProducer<String,Tweet> pd) {
        this.producer = pd;
    }
    @Override
    public void onStatus(Status status) {
        Tweet t = new Tweet(status.getUser().getName(),status.getText(),status.getCreatedAt());
        logger.info("Tweet criado: " + t.toString());
        ProducerRecord<String, Tweet> record = new ProducerRecord<>(topic, t.getUserName() ,t);
        this.sendMessage(record);
    }

    @Override
    public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
        logger.info("delete");
    }

    @Override
    public void onTrackLimitationNotice(int i) {
        logger.info("track");
    }

    @Override
    public void onScrubGeo(long l, long l1) {

    }

    @Override
    public void onStallWarning(StallWarning stallWarning) {
        logger.info("stall");
    }

    @Override
    public void onException(Exception e) {
        e.printStackTrace();
        logger.info("exeption");
    }

    private void sendMessage(ProducerRecord<String,Tweet> pr) {
        this.producer.send(pr, new Callback() {
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if (e == null) {
                    logger.info("Exibindo os meta-dados sobre o envio da mensagem. \n" +
                            "Topico: " + recordMetadata.topic() + "\n" +
                            "Partição: " + recordMetadata.partition() + "\n" +
                            "Offset" + recordMetadata.offset());
                } else {
                    logger.severe("Erro no envio da mensagem: " + e.getMessage());
                }
            }
        }); // Envio assíncrono
        this.producer.flush();
    }

}
