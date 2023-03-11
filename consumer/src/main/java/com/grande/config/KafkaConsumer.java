package com.grande.config;

import com.grande.entity.Data;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    @KafkaListener(topics = "send",groupId = "mm",containerFactory = "kafkaProcessingListenerContainerFactory")
    public void listenTopic(ConsumerRecord<String, Data>consumerRecord, Acknowledgment acknowledgment){
        System.out.println("Received");
        System.out.println(consumerRecord.value().getId()+" " +
                consumerRecord.value().getUserDetails().getId()+" " +
                consumerRecord.value().getUserDetails().getName()+" " +
                consumerRecord.value().getUserDetails().getSurname()+" " +
                consumerRecord.value().getRegistrationDate().toString()+" " +
                consumerRecord.value().getStatus().toString());
        acknowledgment.acknowledge();
    }
}
