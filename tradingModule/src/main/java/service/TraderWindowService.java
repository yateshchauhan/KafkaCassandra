package service;

import com.trading.bean.Trade;
import queue.TradePublisher;

public interface TraderWindowService {

    void newTrade(Trade trade, TradePublisher tradePublisher);

}
