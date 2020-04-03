package com.inventry.service;

import com.inventry.utility.TradeDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Arrays;
import java.util.Properties;

public class TopicConsumerFactory<K,V> {

    private KafkaConsumer<K,V> kafkaConsumer;
    private String topic = "TradeUserTopic";

    private Properties getProperties(){

        Properties props = new Properties();
        props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.setProperty(ConsumerConfig.GROUP_ID_CONFIG,"TradeInventoryConsumer");
        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, TradeDeserializer.class.getName());
        props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"latest");
        return props;

    }

    public KafkaConsumer<K,V> createKafkaConsumer(){

        KafkaConsumer<K,V> kafkaConsumer = new KafkaConsumer<K, V>(getProperties());
        kafkaConsumer.subscribe(Arrays.asList(topic));

        return kafkaConsumer;
    }

    public void close(){
        if(kafkaConsumer != null){
            kafkaConsumer.close();
        }
    }


}
