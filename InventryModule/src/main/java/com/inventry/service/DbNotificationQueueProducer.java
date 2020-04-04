package com.inventry.service;

import com.inventry.bean.DbNotification;
import com.inventry.utility.DBNotificationSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class DbNotificationQueueProducer {

    private static Map<String,KafkaProducer> producerList = new ConcurrentHashMap<>();

    private KafkaProducer kafkaProducer;
    private static Properties getProps(){

        Properties props = new Properties();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, DBNotificationSerializer.class.getName());
        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
        props.put(ProducerConfig.ACKS_CONFIG,"all");
        props.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION,"5");
        props.put(ProducerConfig.RETRIES_CONFIG, "2");

        return props;
    }

    public KafkaProducer<String, DbNotification> getKafkaProducer(String userId){

        if(producerList.containsKey(userId)){
            return producerList.get(userId);
        }

        KafkaProducer<String, DbNotification> producer = new KafkaProducer<String, DbNotification>(getProps());

        producerList.put(userId,producer);
        return producer;
    }

    public static KafkaProducer<String, DbNotification> getCommonKafkaProducer(){

        String userId = "Shared";
        if(producerList.containsKey(userId)){
            return producerList.get(userId);
        }

        KafkaProducer<String, DbNotification> producer = new KafkaProducer<String, DbNotification>(getProps());

        producerList.put(userId,producer);
        return producer;
    }

    public static void closeSharedKafka(){
        String userId = "Shared";
        KafkaProducer producer = producerList.get(userId);
        if(producer != null){
            producer.close();
        }

    }

}
