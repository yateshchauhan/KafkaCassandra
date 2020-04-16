package queue;


import utility.TradeSerializer;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class KafkaProducerFactory {

    private static Logger log = LoggerFactory.getLogger(KafkaProducerFactory.class);

    private static Map<String, KafkaProducer> producerMap = new ConcurrentHashMap();

    private static Properties getProducerProps(){

        Properties props = new Properties();
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, TradeSerializer.class.getName());
        props.setProperty(ProducerConfig.ACKS_CONFIG, "all");//0/1/all
        props.setProperty(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
        props.setProperty(ProducerConfig.RETRIES_CONFIG,"2");
        props.setProperty(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION,"5");//1/5

        return props;

    }

    public static <K,V> KafkaProducer<K,V> getNewProducer(String userId){

        if(StringUtils.isBlank(userId)){
            log.info("UserId can't be null/empty");
            return null;
        }

        KafkaProducer<K, V> producer = new KafkaProducer(getProducerProps());

        producerMap.put(userId, producer);

        return producer;
    }

    public static <K,V> KafkaProducer<K,V> getProducer(String userId){

        KafkaProducer<K,V> producer = null;

        if(StringUtils.isNotBlank(userId))
            producer = producerMap.get(userId);

        return producer;
    }

}
