package service;

import queue.TradePublisher;

public class TraderWindowServiceImpl implements TraderWindowService{

    public void newTrade(Trade trade, TradePublisher tradePublisher) {

        tradePublisher.send(trade);

    }
}
