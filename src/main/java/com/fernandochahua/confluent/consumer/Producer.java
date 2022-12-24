package com.fernandochahua.confluent.consumer;


import com.fernandochahua.confluent.Operation;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@CommonsLog(topic = "Producer Logger")
public class Producer {

  @Value("${topic.name}")
  private String TOPIC;

  private final KafkaTemplate<String, Operation> kafkaTemplate;

  @Autowired
  public Producer(KafkaTemplate<String, Operation> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  void sendMessage(Operation operation) {
    operation.setUsername(Util.maskString(operation.getUsername(),5,12, '*'));
    this.kafkaTemplate.send(this.TOPIC, operation.getSessionId(), operation);
    log.info(String.format("Produced user -> %s", operation));
  }
}
