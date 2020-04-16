package com.inventry.service;

import com.trading.bean.Trade;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TradeManager {

    private static ExecutorService executorService;

    public static ExecutorService getExecutor(){
        if(executorService == null){
            executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        }
        return executorService;
    }

    public void process(Trade trade){

        CompletableFuture.supplyAsync(new DBService(trade), getExecutor()).thenAccept(DBService::dbMonitor);
    }


}
