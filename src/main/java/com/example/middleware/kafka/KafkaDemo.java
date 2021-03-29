package com.example.middleware.kafka;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.function.Predicate;

/**
 * @author lr
 * @date 2020/11/24
 */
public class KafkaDemo {

    public static void main(String[] args) {
        System.out.println(Instant.ofEpochMilli(1606875356759L));
        System.out.println(LocalDateTime.ofInstant(Instant.ofEpochMilli(1606875356759L), ZoneId.systemDefault()));
        poll();
//        poll();
    }


    public static void test() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, "");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // ... set additional consumer properties (optional)
        KafkaConsumer<Integer, String> consumer = new KafkaConsumer<>(props);
//        consumer.offsetsForTimes(null, null);
        Set<TopicPartition> partitions = consumer.assignment();
        System.out.println(partitions.size());
        for (TopicPartition partition : partitions) {
            System.out.println("*******");
            System.out.println(partition.toString());
        }
    }

    public static  void poll() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test-consumer-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, Integer.MAX_VALUE);
        // ... set additional consumer properties (optional)
        //        consumer.offsetsForTimes(null, null);
        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {
            consumer.subscribe(Collections.singletonList("test"), new ConsumerRebalanceListener() {
                @Override
                public void onPartitionsRevoked(Collection<TopicPartition> collection) {

                }
                @Override
                public void onPartitionsAssigned(Collection<TopicPartition> collection) {
                    Map<TopicPartition, Long> topicPartitionLongMap = consumer.beginningOffsets(collection);
                    Map<TopicPartition, Long> topicPartitionLongMap1 = consumer.endOffsets(collection);
                    consumer.seekToBeginning(collection);
                }
            });
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofDays(10));
            System.out.println("count ==" + records.count());
            records.forEach(record -> {
                System.out.println(record.timestamp());
                // ... do something with record
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
            });
//            }
        } catch (WakeupException e) {
            // Using wakeup to close consumer
        }

    }


    private static Predicate<ConsumerRecord<String, String>> predicate(long statTime, long endTime, String ns, String appName, String podName, String keyWord) {

        return record -> {
            if (statTime > 0 && endTime > 0) {
                long timestamp = record.timestamp();
                if (timestamp < statTime || timestamp > endTime) {
                    return false;
                }
            }
            String value = record.value();
            if (!JSONUtil.isJsonObj(value)) {
                return false;
            }
            JSONObject object = JSONObject.parseObject(value);
            String message = object.getString("message");
            if (keyWord != null) {
                if (message == null || !message.contains(keyWord)) {
                    return false;
                }
            }
            JSONObject kubernetes = object.getJSONObject("kubernetes");
            if (kubernetes == null) {
                return false;
            }
            if (ns != null && !ns.equals(kubernetes.getString("namespace"))) {
                return false;
            }
            if (appName != null) {
                JSONObject labels = kubernetes.getJSONObject("labels");
                if (labels == null || !appName.equals(labels.getString("applicationName"))) {
                    return false;
                }
            }
            if (podName != null) {
                JSONObject pod = kubernetes.getJSONObject("pod");
                if (pod == null || !podName.equals(pod.getString("name"))) {
                    return false;
                }
            }
            return true;
        };
    }




}
