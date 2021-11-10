package com.gok.kafkaexample.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
/*
Tek topic birden fazla consumer çalıştırılması.s
 */
@Service
public final class ConsumerPartitionKafkaService {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerPartitionKafkaService.class);

    @KafkaListener(topics = "Logs2", groupId = "group_id")
    public void consume(String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        logger.info(String.format("Consumed message: %s Partition > %s", message,partition ));    }

    @KafkaListener(topics = "Logs2", groupId = "group_id")
    public void consume2(String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        logger.info(String.format("Consumed2 message: %s Partition > %s", message,partition ));
    }

    //pub sub
    @KafkaListener(topics = "raw_video_topic", groupId = "mobile_encoder_consumer_group")
    public void consume3(String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        logger.info(String.format("Consumed2 message: %s Partition > %s", message,partition ));
    }
    @KafkaListener(topics = "raw_video_topic", groupId = "hd_4k_8k_encoder_consumer_group")
    public void consume4(String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        logger.info(String.format("Consumed2 message: %s Partition > %s", message,partition ));
    }
    @KafkaListener(topics = "raw_video_topic", groupId = "hd_1k_2k_encoder_consumer_group")
    public void consume5(String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        logger.info(String.format("Consumed2 message: %s Partition > %s", message,partition ));
    }
}