package com.reply.tyreshop.paypal.service;

import com.reply.tyreshop.paypal.dto.PaymentApprovalData;
import de.hybris.platform.core.model.user.AddressModel;

public interface PayPalPaymentService {
    String createPayment();
    String capturePayment(PaymentApprovalData paymentApprovalData, AddressModel billingAddress);
}
