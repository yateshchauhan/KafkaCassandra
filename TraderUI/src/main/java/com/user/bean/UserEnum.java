package com.user.bean;

public enum UserEnum {

    ID("id"),
    Name("name"),
    Type("type"),
    CreationDate("creationdate");

    public String col;
    UserEnum(String col){
        this.col = col;
    }

}
