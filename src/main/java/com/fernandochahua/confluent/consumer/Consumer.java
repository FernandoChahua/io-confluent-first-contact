package com.fernandochahua.confluent.consumer;

import com.fernandochahua.confluent.Operation;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@CommonsLog(topic = "Consumer Logger")
public class Consumer {

  @Value("${topic.name}")
  private String topicName;

  @KafkaListener(topics = "confluent-1")
  public void consume(ConsumerRecord<String, Operation> record) {
    log.info(String.format("Consumed message -> %s %s %d", record.value(), record.key(), record.partition()));
  }
}