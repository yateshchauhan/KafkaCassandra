package com.trading.bean;

public class TradeNotification {

    private TradeNotification(){}

    private String gsfRefId;
    private boolean status;
    private int tradeEvent;

    public String getGsfRefId() {
        return gsfRefId;
    }

    public boolean isStatus() {
        return status;
    }

    public int getTradeEvent() {
        return tradeEvent;
    }

    @Override
    public String toString() {
        return "TradeNotification{" +
                "gsfRefId='" + gsfRefId + '\'' +
                ", status=" + status +
                ", tradeEvent=" + tradeEvent +
                '}';
    }

    static class Builder{

        private TradeNotification notification;

        public TradeNotification build(){
            return notification;
        }

        public Builder(){
            notification = new TradeNotification();
        }
        public void setGsfRefId(String gsfRefId) {
            notification.gsfRefId = gsfRefId;
        }

        public void setStatus(boolean status) {
            notification.status = status;
        }

        public void setTradeEvent(int tradeEvent) {
            notification.tradeEvent = tradeEvent;
        }
    }
}
