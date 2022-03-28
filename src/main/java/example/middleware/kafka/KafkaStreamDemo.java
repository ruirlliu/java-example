//package example.middleware.kafka;
//
//import org.apache.kafka.common.serialization.Serdes;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.apache.kafka.streams.KafkaStreams;
//import org.apache.kafka.streams.StreamsBuilder;
//import org.apache.kafka.streams.StreamsConfig;
//import org.apache.kafka.streams.kstream.KStream;
//import org.apache.kafka.streams.kstream.KTable;
//import org.apache.kafka.streams.kstream.Materialized;
//import org.apache.kafka.streams.kstream.Produced;
//
//import java.util.Arrays;
//import java.util.Properties;
//
///**
// * @author lr
// * @date 2020/11/25
// */
//public class KafkaStreamDemo {
//
//    public static void main(String[] args) {
//
//        Properties props = new Properties();
//        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "test-consumer-group");
//        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, StringDeserializer.class.getName());
//        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, StringDeserializer.class.getName());
//
//        StreamsBuilder builder = new StreamsBuilder();
//        KStream<String, String> textLines = builder.stream("newtest");
//        KTable<String, Long> wordCounts = textLines
//                .flatMapValues(textLine -> Arrays.asList(textLine.toLowerCase().split("\\W+")))
//                .groupBy((key, word) -> word)
//                .count(Materialized.as("counts-store"));
//        wordCounts.toStream().to("newtest", Produced.with(Serdes.String(), Serdes.Long()));
//
//        KafkaStreams kafkaStreams = new KafkaStreams(builder.build(), props);
//        kafkaStreams.start();
//    }
//}
