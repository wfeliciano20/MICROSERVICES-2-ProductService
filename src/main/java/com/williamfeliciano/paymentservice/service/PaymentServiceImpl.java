package com.williamfeliciano.paymentservice.service;

import com.williamfeliciano.paymentservice.entity.TransactionDetails;
import com.williamfeliciano.paymentservice.model.PaymentMode;
import com.williamfeliciano.paymentservice.model.PaymentRequest;
import com.williamfeliciano.paymentservice.model.PaymentResponse;
import com.williamfeliciano.paymentservice.repository.TransactionDetailsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
@Log4j2
public class PaymentServiceImpl implements PaymentService{

    private TransactionDetailsRepository transactionDetailsRepository;
    @Override
    public long doPayement(PaymentRequest paymentRequest) {
        log.info("Recording payment details {}",paymentRequest);
        TransactionDetails transactionDetails =
                TransactionDetails.builder()
                        .paymentDate(Instant.now())
                        .amount(paymentRequest.getAmount())
                        .paymentMode(paymentRequest.getPaymentMode().name())
                        .orderId(paymentRequest.getOrderId())
                        .paymentStatus("SUCCESS")
                        .referenceNumber(paymentRequest.getReferenceNumber())
                        .build();

        transactionDetailsRepository.save(transactionDetails);

        log.info("Transaction completed with id {}",transactionDetails.getId());
        return transactionDetails.getId();
    }

    @Override
    public PaymentResponse getPaymentDetailsByOrderId(long orderId) {
        TransactionDetails transactionDetails = transactionDetailsRepository.findByOrderId(orderId);
        return PaymentResponse.builder()
                .paymentId(transactionDetails.getId())
                .paymentStatus(transactionDetails.getPaymentStatus())
                .paymentDate(transactionDetails.getPaymentDate())
                .amount(transactionDetails.getAmount())
                .orderId(transactionDetails.getOrderId())
                .paymentMode(PaymentMode.valueOf(transactionDetails.getPaymentMode()))
                .build();
    }
}
