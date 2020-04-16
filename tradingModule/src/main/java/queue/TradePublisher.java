package queue;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.trading.bean.Trade;
import utility.TradeAckMonitor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

public class TradePublisher<K,V> {

    private String topicName = "TradeUserTopic";
    private KafkaProducer<String, Trade> kafkaProducer;
    private static Logger log = LoggerFactory.getLogger(TradePublisher.class);
    private static TradeAckMonitor ackMonitor = new TradeAckMonitor();
    private volatile static TradePublisher tradePublisher;
    private List<KafkaProducer> kafkaProducerList = new ArrayList<>();
    private static String defaultUserId = "default";

    public static <K,V> TradePublisher<K,V> getInstance(){
        if(tradePublisher == null){
            synchronized (TradePublisher.class) {
                if(tradePublisher == null)
                    tradePublisher = new TradePublisher("Default");
            }
        }
        return tradePublisher;
    }

    public void createKafkaProducer(){
        kafkaProducer = KafkaProducerFactory.getNewProducer(defaultUserId);
        kafkaProducerList.add(kafkaProducer);
    }
    private TradePublisher(String userId){

        ackMonitor.start();

    }

    public void send(Trade trade){

        ProducerRecord<String,Trade> record = new ProducerRecord<String,Trade>(topicName, trade.getTradeId(), trade);
        createKafkaProducer();
        kafkaProducer.send(record);
        Future<RecordMetadata> futureResult = kafkaProducer.send(record);
        kafkaProducer.flush();
        ackMonitor.add(futureResult);
    }

    public void closeKafkaProducer(){

        for(KafkaProducer kafkaProducer : kafkaProducerList) {
            kafkaProducer.close();
        }
        kafkaProducerList.clear();

    }

    public TradeAckMonitor getAckMonitor(){
        return ackMonitor;
    }
}
