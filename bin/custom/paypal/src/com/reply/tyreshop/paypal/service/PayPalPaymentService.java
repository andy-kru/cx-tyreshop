package com.reply.tyreshop.paypal.service;

import com.reply.tyreshop.paypal.dto.PaymentApprovalData;

public interface PayPalPaymentService {
    String createPayment();
    String capturePayment(PaymentApprovalData paymentApprovalData);
}
