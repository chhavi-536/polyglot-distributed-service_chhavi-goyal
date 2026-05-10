package com.sms.model;

import lombok.Data;

@Data
public class SMS {

    private String userId;
    private String phoneNumber;
    private String message;
    private String status;
}
