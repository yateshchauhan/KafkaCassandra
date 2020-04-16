package service;

import com.trading.bean.Trade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import queue.TradePublisher;
import utility.DateUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Trader {

    private static Logger log = LoggerFactory.getLogger(Trader.class);

    public static void main(String[] args) {

        log.info("Trader service started");
        System.out.println("Trader service started");


        TraderWindowService windowService = new TraderWindowServiceImpl();
        TradePublisher<String, Trade> tradeTradePublisher = null;
        try {
            BufferedReader reader = getReader();
            System.out.println("Plz do enter your userId");
            String userId = reader.readLine();
            tradeTradePublisher = TradePublisher.getInstance();
            while (true) {

                System.out.println("Do you want to book new trade (Yes/No)");

                String input = reader.readLine();
                if ("No".equalsIgnoreCase(input)) {
                    break;
                } else {
                    System.out.println("plz enter tradeId");
                    String tradeId = reader.readLine();
                    Trade trade = createTrade(tradeId);
                    windowService.newTrade(trade, tradeTradePublisher);
                }

            }
            tradeTradePublisher.closeKafkaProducer();
        }catch (Exception e){
            System.out.println("Failed while booking trade, "+e);
            if(tradeTradePublisher != null){
                tradeTradePublisher.closeKafkaProducer();
            }
        }

    }



    private static Trade createTrade(String tradeId){

        Trade trade = new Trade.Builder().setTradeId(tradeId).setTradeDate(DateUtils.converLocalDateToDate(LocalDate.now())).setTradeEvent("New").setTradeType("MI")
                .setStartDate(DateUtils.converLocalDateToDate(LocalDate.now())).setEndDate(DateUtils.converLocalDateToDate(LocalDate.now().plusDays(20))).setTradePrincipal(new BigDecimal(10000))
                .setOpenCash(new BigDecimal(500)).setQty(new BigDecimal(20)).build();
        return trade;

    }

    private static BufferedReader getReader(){

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        return reader;
    }
}
