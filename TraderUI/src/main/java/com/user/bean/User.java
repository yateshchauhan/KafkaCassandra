package com.user.bean;


import org.springframework.stereotype.Component;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Component
public class User {

    private String name;
    private String type;
    private String id;
    private LocalDate creationDate;

    public String getName() {
        return name;
    }

    @NotNull(message = "name should not be null")
    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    @NotNull(message = "Type should not be null")
    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    @NotNull(message = "Id is not null")
    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
