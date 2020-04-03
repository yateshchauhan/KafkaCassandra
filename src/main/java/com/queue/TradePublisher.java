package com.queue;

import com.inventry.service.Trade;
import com.utility.TradeAckMonitor;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Future;

public class TradePublisher<K,V> {

    private String topicName = "TradeUserTopic";
    private KafkaProducer<String,Trade> kafkaProducer;
    private static Logger log = LoggerFactory.getLogger(TradePublisher.class);
    private static TradeAckMonitor ackMonitor = new TradeAckMonitor();

    public TradePublisher(String userId){

        kafkaProducer = KafkaProducerFactory.getNewProducer(userId);
        ackMonitor.start();

    }

    public void send(Trade trade){

        ProducerRecord<String,Trade> record = new ProducerRecord<String,Trade>(topicName, trade.getTradeId(), trade);

        kafkaProducer.send(record);
        Future<RecordMetadata> futureResult = kafkaProducer.send(record);
        kafkaProducer.flush();
        ackMonitor.add(futureResult);
    }

    public void closeAckMonitor(){
        kafkaProducer.close();
        ackMonitor.close();

    }
}
