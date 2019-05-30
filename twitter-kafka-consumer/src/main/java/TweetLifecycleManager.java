import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.io.Serializable;
import java.util.Properties;
import java.util.logging.Logger;

public class TweetLifecycleManager implements LifecycleManager, Serializable {
    private static final Logger logger = Logger.getLogger(TweetLifecycleManager.class.getName());
    private TweetConsumer consumer;

    public TweetLifecycleManager() {
        this.consumer = new TweetConsumer();


    }

    @Override
    public void start( String filter ) {
        this.producer = this.createProducer();
        this.twitterStream.addListener(new TweetListener(this.producer));
        this.twitterStream.filter(filter);
        logger.info("started the stream");
    }

    @Override
    public void stop() {
        this.twitterStream.cleanUp();
        this.twitterStream.clearListeners();
        this.producer.close();
        logger.info("stoped the stream");
    }




}
