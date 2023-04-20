package com.williamfeliciano.paymentservice.service;

import com.williamfeliciano.paymentservice.model.PaymentRequest;
import com.williamfeliciano.paymentservice.model.PaymentResponse;

public interface PaymentService {
    long doPayement(PaymentRequest paymentRequest);

    PaymentResponse getPaymentDetailsByOrderId(long orderId);
}
