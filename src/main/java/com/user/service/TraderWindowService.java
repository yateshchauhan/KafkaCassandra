package com.user.service;

import com.inventry.service.Trade;
import com.queue.TradePublisher;

public interface TraderWindowService {

    void newTrade(Trade trade, TradePublisher tradePublisher);

}
