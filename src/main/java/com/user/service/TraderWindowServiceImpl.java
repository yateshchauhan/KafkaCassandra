package com.user.service;

import com.inventry.service.Trade;
import com.queue.TradePublisher;

public class TraderWindowServiceImpl implements TraderWindowService{

    public void newTrade(Trade trade, TradePublisher tradePublisher) {

        tradePublisher.send(trade);

    }
}
