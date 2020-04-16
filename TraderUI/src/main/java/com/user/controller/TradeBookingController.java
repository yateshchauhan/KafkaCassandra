package com.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import queue.TradePublisher;
import com.trading.bean.Trade;
import service.TradeDao;
import service.TraderWindowService;
import service.TraderWindowServiceImpl;
import utility.TradeAckMonitor;

import java.util.List;

@RestController
public class TradeBookingController {

    private final Logger log = LoggerFactory.getLogger(TradeBookingController.class);

    @GetMapping(path="/trade/list")
    public List<Trade> getTradeList(){

        log.info("list all trade service invoked");
        TradeDao dao = new TradeDao();
        List<Trade> tradeList = dao.getTrades();
        log.info("Total available trades : {}",tradeList.size());

        return tradeList;
    }

    @PostMapping(path = "/trade/book")
    public ResponseEntity<String> tradeBooking(@RequestParam String userId, @RequestBody Trade trade){

        log.info("tradeBooking service invoked");
        TraderWindowService service = new TraderWindowServiceImpl();
        TradePublisher<String,Trade> tradePublisher = TradePublisher.getInstance();
        service.newTrade(trade, tradePublisher);
        tradePublisher.closeKafkaProducer();

        ResponseEntity<String> responseEntity = ResponseEntity.status(200).body("In process to create trade, plz do validate your ackTrade topic");
        log.info("tradeBooking service completed");
        return responseEntity;
    }

    @PostMapping(path = "/trade/close")
    public ResponseEntity<String> close(){

        TradePublisher<String,Trade> tradePublisher = TradePublisher.getInstance();
        if(tradePublisher != null){
            TradeAckMonitor ackMonitor = tradePublisher.getAckMonitor();
            if(ackMonitor != null)
                ackMonitor.close();
        }

        ResponseEntity<String> responseEntity = ResponseEntity.status(200).body("service triggered to close running threads");
        return responseEntity;
    }
}
