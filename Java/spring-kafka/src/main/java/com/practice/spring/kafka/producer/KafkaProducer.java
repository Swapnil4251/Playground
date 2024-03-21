package com.practice.spring.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);
    private static final String KAFKA_TOPIC = "firstTopic";
    KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate =     kafkaTemplate;
    }

    public void sendMessage(String message) {
        LOGGER.info(String.format("Message sent %s", message));
        kafkaTemplate.send(KAFKA_TOPIC, message);
    }

    public static String getKafkaTopic() {
        return KAFKA_TOPIC;
    }
}
