package com.user.bean;

import org.springframework.stereotype.Component;

@Component
public class UserResponse {

    private String userId;
    private String userType;
    private Boolean status;

    public UserResponse(String userId, String userType, Boolean status) {
        this.userId = userId;
        this.userType = userType;
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserType() {
        return userType;
    }

    public Boolean getStatus() {
        return status;
    }
}
