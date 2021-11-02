package com.gok.kafkaexample.controller;


import com.gok.kafkaexample.service.ProducerKafkaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public final class KafkaController {
    private final ProducerKafkaService producerService;

    public KafkaController(ProducerKafkaService producerService) {
        this.producerService = producerService;
    }

    @PostMapping(value = "/send")
    public void sendMessageToKafkaTopic(@RequestParam String message) {
        producerService.sendMessage(message);
    }
}