package com.fernandochahua.confluent.consumer;

import com.fernandochahua.confluent.Operation;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/operation")
@RequiredArgsConstructor
public class KafkaController {

  private final Producer producer;

  @PostMapping(value = "/publish")
  public void sendMessageToKafkaTopic(@RequestParam("operationType") String operationType,
                                      @RequestParam("amount") String amount,
                                      @RequestParam("username") String username,
                                      @RequestParam("sessionId") String sessionId) {
    this.producer.sendMessage(new Operation(operationType, amount, username, sessionId));
  }
}