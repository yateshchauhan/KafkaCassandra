package com.utility;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

public class TradeAckMonitor {

    private LinkedBlockingQueue<Future<RecordMetadata>> ackQueue = new LinkedBlockingQueue<Future<RecordMetadata>>(10);
    private static Logger log = LoggerFactory.getLogger(TradeAckMonitor.class);

    private Thread monitorThread;
    public TradeAckMonitor(){}

    public void start(){

        final TradeAckMonitor monitor = this;
        monitorThread = new Thread(){

                @Override
                public void run(){
                    monitor.run();
            }
        };
        monitorThread.start();
    }

    private void run(){

        try{
        while(true){

            Future<RecordMetadata> future = ackQueue.take();

            if(!future.isDone()){
                ackQueue.offer(future);
            }else{
                try {
                    RecordMetadata ack = future.get();
                    Date date = new Date(ack.timestamp());

                    System.out.println("TraderId length '"+ack.toString()+"' successfully send data to topic '"+ack.topic()+"', time "+ date);
                }catch(Exception e){
                    System.out.println("Failed while returning ack from blocking queue, "+e);
                    e.printStackTrace();
                }
            }

        }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void add(Future<RecordMetadata> future){
        try {
            if (future != null)
                ackQueue.put(future);
        }catch(InterruptedException e){
            System.out.println("Got interrupted while pushing data to queue"+e);
        }
    }

    public void close(){
        if(monitorThread != null && monitorThread.isAlive()){
            monitorThread.interrupt();
        }
    }
}
