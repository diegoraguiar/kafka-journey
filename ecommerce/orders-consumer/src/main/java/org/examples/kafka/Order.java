package org.examples.kafka;

import java.util.UUID;
import lombok.Data;

@Data
public class Order {

  private final UUID id;
  private final String customerName;
}
