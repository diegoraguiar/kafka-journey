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
import org.apache.kafka.common.serialization.UUIDSerializer;
import org.kohsuke.randname.RandomNameGenerator;

@Slf4j
public class OrderProducer {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    final Order order = new Order(UUID.randomUUID(), new RandomNameGenerator().next());
    final KafkaProducer<UUID, Order> producer = new KafkaProducer<>(getProperties());
    final ProducerRecord<UUID, Order> record = new ProducerRecord<>("orders", order.getId(), order);
    producer.send(record, MessageCallback()).get();
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
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, UUIDSerializer.class.getName());
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, OrderSerializer.class.getName());
    return props;
  }
}
