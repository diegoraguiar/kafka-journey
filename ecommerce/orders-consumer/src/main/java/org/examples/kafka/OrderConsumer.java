package org.examples.kafka;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.UUIDDeserializer;

@Slf4j
public class OrderConsumer {

  public static void main(String[] args) {
    final KafkaConsumer<UUID, Order> consumer = new KafkaConsumer<>(getProperties());
    consumer.subscribe(Collections.singleton("orders"));
    while (true) {
      final ConsumerRecords<UUID, Order> records = consumer.poll(Duration.ofSeconds(2L));
      records.forEach(record -> {
        log.info(String.format("Message received: %s", record.key()));
        log.info(String.format("Topic %s, Partition %s, Offset: %s", record.topic(), record.partition(), record.offset()));
        log.info(String.format("Message: %s", record.value()));
      });
    }
  }

  private static Properties getProperties() {
    final Properties props = new Properties();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, UUIDDeserializer.class.getName());
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, OrderDeserializer.class.getName());
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "order-consumer");
    props.put(ConsumerConfig.CLIENT_ID_CONFIG, UUID.randomUUID().toString());
    return props;
  }
}
