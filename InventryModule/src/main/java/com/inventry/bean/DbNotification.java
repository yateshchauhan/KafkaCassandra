package com.inventry.bean;

public class DbNotification {

    private String tradeId;
    private boolean status;

    public DbNotification(String tradeId, boolean status){
        this.tradeId = tradeId;
        this.status = status;
    }

    @Override
    public String toString(){
        return "TradeId : "+tradeId+" Is "+(status ? "persisted" : "not persisted");
    }
}
