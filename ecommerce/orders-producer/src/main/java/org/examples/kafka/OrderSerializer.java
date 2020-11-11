package org.examples.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

public class OrderSerializer implements Serializer<Order> {

  @Override
  public byte[] serialize(String topic, Order data) {
    try {
      return new ObjectMapper().writeValueAsBytes(data);
    } catch (JsonProcessingException e) {
      throw new SerializationException("Error when serializing Order to byte[].", e);
    }
  }
}
