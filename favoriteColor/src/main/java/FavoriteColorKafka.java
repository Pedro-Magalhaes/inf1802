import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KStreamBuilder;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.KeyValueMapper;

import java.util.Arrays;
import java.util.Properties;
import java.util.logging.Logger;

public class FavoriteColorKafka {
    static final String inputColorStream = "favorite-color-input";
    static final String consolidateColorStream = "favorite-color-consolidate";
    static final String outputColorStream = "favorite-color-output";
    static Properties config;
    static KStreamBuilder builder;
    private static final Logger logger = Logger.getLogger(FavoriteColorKafka.class.getName());

    public static void main(String[] args) {
        init();
        createProcessStream();
        start();

    }

    private static void init() {
        config = new Properties();
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, "wordcount-application");
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        builder = new KStreamBuilder();
        logger.info("Init complete");
    }

    private static void createProcessStream() {
        // 1 - Stream lido do Kafka
        KStream<String, String> colorLines = builder.stream(inputColorStream);
        colorLines
                .flatMapValues(textLine -> Arrays.asList(textLine.split(",")))
                .selectKey((key, value) -> key)
                .mapValues(colorLine -> colorLine.toLowerCase().trim())
                .filter( (k,v) -> v.equalsIgnoreCase("vermelho") ||
                                  v.equalsIgnoreCase("azul") ||
                                  v.equalsIgnoreCase("verde"))
                .to(Serdes.String(), Serdes.String(), consolidateColorStream);

        KTable<String, String> colorTable = builder.table(consolidateColorStream);

        colorTable.groupBy( (key,value) -> new KeyValue<>(value,value)).count().to(outputColorStream);




    }
    private static void start() {

        KafkaStreams streams = new KafkaStreams(builder, config);
        streams.start();
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
        logger.info("kafka stream started");
    }
}
