import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class TweetConsumer implements LifecycleManager{

    private static Logger logger = LoggerFactory.getLogger(TweetConsumer.class.getName());
    private final KafkaConsumer<String ,Tweet> consumer;
    private boolean shouldContinue;
    private Thread threadConsumer;

    public TweetConsumer(){

        // Criar as propriedades do consumidor
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, TweetDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "tweet_consumer");
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        // Criar o consumidor
        this.consumer = new KafkaConsumer<>(properties);
        this.consumer.subscribe(Collections.singleton("tweet"));
        this.threadConsumer = getThreadConsumer();
    }

    public void start() {
        // Subscrever o consumidor para o nosso(s) tópico(s)
        this.shouldContinue = true;
        logger.info("started the consumer");
        this.threadConsumer = getThreadConsumer();
        this.threadConsumer.start();


    }

    public void stop() {
        this.shouldContinue = false;
        try {
            this.threadConsumer.join(0);
        } catch (InterruptedException e) {
            logger.info("Stoped the consumer");
        }

    }


    private Thread getThreadConsumer() {
        return new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        // Ler as mensagens
                        logger.info("Starting consumer thread!");
                        while (shouldContinue) {  // Apenas como demo, usaremos um loop infinito
                            ConsumerRecords<String, Tweet> poll = consumer.poll(Duration.ofMillis(1000));
                            for (ConsumerRecord record : poll) {
                                logger.info(record.topic() + " - " + record.partition() + " - " + record.value());
                            }
                        }
                    }
                }
        );
    }
}


