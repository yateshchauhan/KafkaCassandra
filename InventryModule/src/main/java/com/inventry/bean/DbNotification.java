package com.inventry.bean;

public class DbNotification {

    private String tradeId;
    private boolean status;
    private String message;

    public DbNotification(String tradeId, boolean status, String message){
        this.tradeId = tradeId;
        this.status = status;
        this.message = message;
    }

    public String getTradeId() {
        return tradeId;
    }

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString(){
        return "TradeId : "+tradeId+" Is "+(status ? "persisted" : "not persisted");
    }
}
