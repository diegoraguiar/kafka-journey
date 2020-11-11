package org.examples.kafka;

import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.kohsuke.randname.RandomNameGenerator;

@Slf4j
public class OrderProducer {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    final String key = UUID.randomUUID().toString();
    final String value = new RandomNameGenerator().next();
    final KafkaProducer<String, String> producer = new KafkaProducer<>(getProperties());
    final ProducerRecord<String, String> order = new ProducerRecord<>("orders", key, value);
    final ProducerRecord<String, String> orderConfirmation = new ProducerRecord<>("orders_confirmation", key, value);
    producer.send(order, MessageCallback()).get();
    producer.send(orderConfirmation, MessageCallback()).get();
  }

  private static Callback MessageCallback() {
    return (metadata, exception) -> {
      if (exception != null) {
        log.error("Message was not delivered. Exception: ", exception);
        return;
      }
      log.info(String.format("Message delivered. Topic: %s, Partition: %s", metadata.topic(), metadata.partition()));
    };
  }

  private static Properties getProperties() {
    final Properties props = new Properties();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    return props;
  }
}
