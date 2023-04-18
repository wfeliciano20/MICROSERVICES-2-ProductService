package com.williamfeliciano.paymentservice.service;

import com.williamfeliciano.paymentservice.model.PaymentRequest;

public interface PaymentService {
    long doPayement(PaymentRequest paymentRequest);
}
