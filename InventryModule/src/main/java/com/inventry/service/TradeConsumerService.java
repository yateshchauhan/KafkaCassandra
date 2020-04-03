package com.inventry.service;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;

public class TradeConsumerService {

    public static void main(String[] args) {
        System.out.println("Inventory consumer is up now");

        final TopicConsumerFactory<String,Trade> factory = new TopicConsumerFactory();

        KafkaConsumer<String,Trade> kafkaConsumer = factory.createKafkaConsumer();
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run(){
                factory.close();
                System.out.println("Consumer got closed");
            }
        });
        while(true){

            ConsumerRecords<String,Trade> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(5));

            if(consumerRecords != null){

                consumerRecords.forEach((consumerRecord)->{
                    String key = consumerRecord.key();
                    Trade trade = consumerRecord.value();
                    System.out.println("Consumed, Topic :"+consumerRecord.topic()+" key : "+key+", value : "+trade);
                    TradeManager tradeManager = new TradeManager();
                    tradeManager.process(trade);
                });
            }

        }

    }

}
