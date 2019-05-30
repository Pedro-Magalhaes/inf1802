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

public class TweetConsumer {

    private static Logger logger = LoggerFactory.getLogger(TweetConsumer.class.getName());
    private KafkaConsumer<String ,Tweet> consumer;

    public TweetConsumer(String url){

        // Criar as propriedades do consumidor
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, url);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, TweetDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "tweet_consumer");
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // Criar o consumidor
        this.consumer = new KafkaConsumer<>(properties);

        // Subscrever o consumidor para o nosso(s) tópico(s)
        this.consumer.subscribe(Collections.singleton("tweet"));

        // Ler as mensagens
        while (true) {  // Apenas como demo, usaremos um loop infinito
            ConsumerRecords<String, Tweet> poll = this.consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord record : poll) {
                logger.info(record.topic() + " - " + record.partition() + " - " + record.value());
            }
        }
    }
}
