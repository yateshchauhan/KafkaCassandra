package service;

import queue.TradePublisher;

public interface TraderWindowService {

    void newTrade(Trade trade, TradePublisher tradePublisher);

}
