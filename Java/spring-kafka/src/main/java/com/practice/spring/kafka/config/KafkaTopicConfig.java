package com.practice.spring.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    public NewTopic firstTopic() {
        return TopicBuilder.name("firstTopic").build();
    }
}
