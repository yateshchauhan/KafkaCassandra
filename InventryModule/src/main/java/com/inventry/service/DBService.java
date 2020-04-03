package com.inventry.service;

import com.inventry.bean.DbNotification;

import java.util.function.Supplier;

public class DBService implements Supplier<DbNotification> {

    private static TradeDao tradeDao = new TradeDao();
    private Trade trade;
    public DBService(Trade trade){
        this.trade = trade;
    }

    @Override
    public DbNotification get() {

        boolean status = tradeDao.addTrade(trade);

        return new DbNotification(trade.getTradeId(), status);
    }

    public static void dbMonitor(DbNotification notification){
        System.out.println(notification);

    }

}
