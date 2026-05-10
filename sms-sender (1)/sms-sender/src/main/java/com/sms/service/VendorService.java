package com.sms.service;

import com.sms.model.SMS;
import org.springframework.stereotype.Service;

@Service
public class VendorService {

    public boolean sendSMS(SMS sms) {
        // Mock external API call
        System.out.println("Sending SMS via 3rd party vendor to: " + sms.getPhoneNumber());

        // simulate success
        return true;
    }
}
