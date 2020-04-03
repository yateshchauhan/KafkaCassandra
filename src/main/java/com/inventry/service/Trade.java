package com.inventry.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class Trade {

    private String tradeId;
    private BigDecimal openCash;
    private BigDecimal qty;
    private BigDecimal tradePrincipal;
    private Date startDate;
    private Date endDate;
    private Date tradeDate;
    private String tradeEvent;
    private String tradeType;

    private Trade(){}

    public String getTradeType() {
        return tradeType;
    }

    public String getTradeId() {
        return tradeId;
    }

    public BigDecimal getOpenCash() {
        return openCash;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public BigDecimal getTradePrincipal() {
        return tradePrincipal;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public String getTradeEvent() {
        return tradeEvent;
    }

    public static class Builder{

        private Trade trade;

        public Builder(){
            trade = new Trade();
        }

        public Trade build(){
            return trade;
        }

        public Builder setTradeType(String tradeType) {
            trade.tradeType = tradeType;
            return this;
        }
        public Builder setTradeId(String tradeId) {
            trade.tradeId = tradeId;
            return this;
        }

        public Builder setOpenCash(BigDecimal openCash) {
            trade.openCash = openCash;return this;
        }

        public Builder setQty(BigDecimal qty) {
            trade.qty = qty;
            return this;
        }

        public Builder setTradePrincipal(BigDecimal tradePrincipal) {
            trade.tradePrincipal = tradePrincipal;
            return this;
        }

        public Builder setStartDate(Date startDate) {
            trade.startDate = startDate;
            return this;
        }

        public Builder setEndDate(Date endDate) {
            trade.endDate = endDate;
            return this;
        }

        public Builder setTradeDate(Date tradeDate) {
            trade.tradeDate = tradeDate;
            return this;
        }

        public Builder setTradeEvent(String tradeEvent) {
            trade.tradeEvent = tradeEvent;
            return this;
        }

    }

    @Override
    public String toString() {
        return "Trader{" +
                "tradeId='" + tradeId + '\'' +
                ", openCash=" + openCash +
                ", qty=" + qty +
                ", tradePrincipal=" + tradePrincipal +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", tradeDate=" + tradeDate +
                ", tradeEvent='" + tradeEvent +
                ", tradeType='" + tradeType + '\'' +
                '}';
    }
}
