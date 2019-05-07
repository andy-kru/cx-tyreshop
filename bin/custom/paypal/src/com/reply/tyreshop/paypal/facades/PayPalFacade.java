package com.reply.tyreshop.paypal.facades;

import com.reply.tyreshop.paypal.dto.PaymentApprovalData;

public interface PayPalFacade {
    String createPayment();
    String executePayment(PaymentApprovalData paymentApprovalData);
}
