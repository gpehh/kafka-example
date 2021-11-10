package com.gok.kafkaexample.controller;


import com.gok.kafkaexample.service.ConsumerPartitionKafkaService;
import com.gok.kafkaexample.service.ProducerKafkaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public final class KafkaController {
    private final ProducerKafkaService producerService;
    private final ConsumerPartitionKafkaService consumerPartitionKafkaService;

    public KafkaController(ProducerKafkaService producerService, ConsumerPartitionKafkaService consumerPartitionKafkaService) {
        this.producerService = producerService;
        this.consumerPartitionKafkaService = consumerPartitionKafkaService;
    }



    @PostMapping(value = "/send")
    public void sendMessageToKafkaTopic(@RequestParam String message) {
        producerService.sendMessage(message);
    }
}