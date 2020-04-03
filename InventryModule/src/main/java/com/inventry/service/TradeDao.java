package com.inventry.service;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.inventry.bean.TradeEnum;
import com.utility.DateUtils;

import java.util.HashMap;
import java.util.Map;

public class TradeDao {

    private static Cluster cluster;
    private static String keySpace;

    static{
        keySpace = "TradeDB";
        String hostName = "127.0.0.1";
        int port = 9042;
        cluster = createCluster(hostName,port);
    }

    private static Cluster createCluster(String hostName, int port){

        Cluster cluster = Cluster.builder().addContactPoint(hostName).withPort(port).build();

        if(cluster == null){
            System.out.println("Cluster didn't initialized");
        }
        return cluster;

    }

    public void createDB(String dbName){

        String cql = "Create keyspace TradeDB with replication = {'Class':'SimpleStrategy','Replication_factor':1}";

        Session session = cluster.connect();
        ResultSet resultSet = session.execute(cql);

        System.out.println("DB schema created");
    }

    public boolean addTrade(Trade trade){

        System.out.println("TradeDao::addTrade method invoked");
        String cql = "insert into Trade(TradeId, TradeEvent, TradeType, StartDate, EndDate, TradeDate, CleanPrice,Principal,Quantity) " +
                "values(:tradeid,:tradeevent,:tradetype,:startdate,:enddate,:tradedate,:cleanprice,:principal,:quantity)";

        Map<String,Object> dataMap = new HashMap(){
            {
                put(TradeEnum.TradeId.columnName, trade.getTradeId());
                put(TradeEnum.TradeEvent.columnName, trade.getTradeEvent());
                put(TradeEnum.TradeType.columnName, trade.getTradeType());
                put(TradeEnum.StartDate.columnName, DateUtils.convertDate(trade.getStartDate()));
                put(TradeEnum.EndDate.columnName, DateUtils.convertDate(trade.getEndDate()));
                put(TradeEnum.TradeDate.columnName, DateUtils.convertDate(trade.getTradeDate()));
                put(TradeEnum.CleanPrice.columnName, trade.getOpenCash());
                put(TradeEnum.Principal.columnName, trade.getTradePrincipal());
                put(TradeEnum.Quantity.columnName, trade.getQty());
            }
        };
        Session session = null;
        boolean status = false;
        try {
            session = cluster.connect(keySpace);
            ResultSet resultSet = session.execute(cql, dataMap);

            status = resultSet != null ? resultSet.wasApplied() : false;
            System.out.println("DB insertion status : " + status);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("TradeDao::addTrade method completed");
        return status;

    }
}
