package com.inventry.service;

import com.inventry.bean.DbNotification;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.function.Supplier;

public class DBService implements Supplier<DbNotification> {

    private static String topicName = "TradeNotificationTopic";
    private static TradeDao tradeDao = new TradeDao();
    private Trade trade;
    public DBService(Trade trade){
        this.trade = trade;
    }

    @Override
    public DbNotification get() {

        boolean status = tradeDao.addTrade(trade);

        return new DbNotification(trade.getTradeId(), status , status ? "Persisted" : "Not persisted");
    }

    public static void dbMonitor(DbNotification notification){
        System.out.println(notification);

        KafkaProducer<String,DbNotification> producer =  DbNotificationQueueProducer.getCommonKafkaProducer();

        ProducerRecord<String, DbNotification> record = new ProducerRecord<String, DbNotification>(topicName, notification);
        producer.send(record);
        producer.flush();
        System.out.println("Sent to notification queue");
    }

}
