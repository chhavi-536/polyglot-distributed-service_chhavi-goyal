package com.sms.service;
import com.sms.model.SMS;
import com.sms.service.RedisService;
import com.sms.service.VendorService;
import com.sms.kafka.SmsEventProducer;
import org.springframework.stereotype.Service;
@Service
public class SmsService {

    private final RedisService redisService;
    private final VendorService vendorService;
    private final SmsEventProducer producer;

    public SmsService(RedisService redisService,
                      VendorService vendorService,
                      SmsEventProducer producer) {
        this.redisService = redisService;
        this.vendorService = vendorService;
        this.producer = producer;
    }

    public String processSMS(SMS sms) {

        // 1. Block check
        if (redisService.isBlocked(sms.getPhoneNumber())) {
            return "Number is blocked";
        }

        // 2. Send via vendor
        boolean sent = vendorService.sendSMS(sms);

        if (!sent) {
            return "SMS failed at vendor";
        }

        // 3. Mark success
        sms.setStatus("SENT");

        // 4. Push to Kafka
        producer.publishEvent(sms);

        return "SMS sent and queued successfully";
    }
}
