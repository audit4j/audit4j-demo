package org.audt4j.demo.hibernate.service;

import java.util.Date;
import java.util.List;

import org.audt4j.demo.hibernate.model.Payment;

public interface PaymentService {

    void checkout(Payment payment);

    void savePayment(String userId, List<Item> items);

    void saveCreditCard(String validName, Date expiry, String cardNumber);

}
