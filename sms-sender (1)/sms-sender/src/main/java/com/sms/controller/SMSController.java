package com.sms.controller;

import com.sms.model.SMS;
import com.sms.service.SmsService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/sms")
public class SMSController {

    private final SmsService smsService;

    public SMSController(SmsService smsService) {
        this.smsService = smsService;
    }

    @PostMapping("/send")
    public String sendSMS(@RequestBody SMS sms) {
        return smsService.processSMS(sms);
    }
}
