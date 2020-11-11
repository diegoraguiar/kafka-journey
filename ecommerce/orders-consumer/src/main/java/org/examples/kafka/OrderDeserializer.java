package org.examples.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

public class OrderDeserializer implements Deserializer<Order> {

  @Override
  public Order deserialize(String topic, byte[] data) {
    try {
      return new ObjectMapper().readValue(data, Order.class);
    } catch (IOException e) {
      throw new SerializationException("Error when deserializing byte[] to Order.", e);
    }
  }
}
