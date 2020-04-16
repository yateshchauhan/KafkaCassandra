package com.trading.bean;

public enum TradeEnum {

    TradeId("tradeid"),
    TradeEvent("tradeevent"),
    TradeType("tradetype"),
    StartDate("startdate"),
    EndDate("enddate"),
    CleanPrice("cleanprice"),
    Principal("principal"),
    Quantity("quantity"),
    TradeDate("tradedate");

    public String columnName;

    TradeEnum(String name){
        this.columnName = name;
    }

}
