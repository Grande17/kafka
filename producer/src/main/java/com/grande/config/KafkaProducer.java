package com.grande.config;

import com.grande.entity.AccStatus;
import com.grande.entity.Data;
import com.grande.entity.UserDetails;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class KafkaProducer {

    private final ProducerFactory<String, Data> producerFactory;

    public void send(Data message)throws ExecutionException, InterruptedException{
        ProducerRecord<String,Data> record = new ProducerRecord<>("send", UUID.randomUUID().toString(),message);

        try (Producer<String,Data> producer = producerFactory.createProducer()){
            producer.send(record).get();
            System.out.println("Send");
        }
    }
    @PostConstruct
    void run() throws ExecutionException, InterruptedException {
        UserDetails userDetails = new UserDetails(1d,"Name","Surname");
        Data data = new Data(1d,userDetails, LocalDateTime.now(), AccStatus.ACTIVE);
        send(data);
    }
}
