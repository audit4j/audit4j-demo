package org.audt4j.demo.spring.service.impl;

import java.util.Date;
import java.util.List;

import org.audit4j.core.annotation.Audit;
import org.audit4j.core.annotation.AuditField;
import org.audit4j.core.annotation.DeIdentify;
import org.audit4j.core.annotation.SelectionType;
import org.audt4j.demo.spring.model.Payment;
import org.audt4j.demo.spring.service.Item;
import org.audt4j.demo.spring.service.PaymentService;
import org.springframework.stereotype.Service;

@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {

    @Override
    @Audit(selection=SelectionType.MARKED)
    public void savePayment(@AuditField(field = "paymentUserId") String userId,
            @AuditField(field = "paymentItems") List<Item> items) {

    }

    @Override
    @Audit(selection=SelectionType.MARKED)
    public void saveCreditCard(@AuditField(field = "cardUserName") String validName,
            @AuditField(field = "expiryDate") Date expiry,
            @AuditField(field = "cardNumber") @DeIdentify(fromRight = 4) String cardNumber) {

    }

    @Override
    @Audit(action = "checkoutPayment")
    public void checkout(Payment payment) {

    }
}
