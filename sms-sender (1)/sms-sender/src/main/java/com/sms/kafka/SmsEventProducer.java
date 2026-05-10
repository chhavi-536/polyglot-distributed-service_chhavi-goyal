package com.sms.kafka;

import com.sms.model.SMS;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SmsEventProducer {

    private final KafkaTemplate<String, SMS> kafkaTemplate;
    private static final String TOPIC = "sms-topic";

    public SmsEventProducer(KafkaTemplate<String, SMS> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishEvent(SMS sms) {
        kafkaTemplate.send(TOPIC, sms);
        System.out.println("SMS event published to Kafka");
    }
}

