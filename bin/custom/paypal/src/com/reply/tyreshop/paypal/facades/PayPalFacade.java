package com.reply.tyreshop.paypal.facades;

import com.reply.tyreshop.paypal.dto.PaymentApprovalData;
import de.hybris.platform.core.model.user.AddressModel;

public interface PayPalFacade {
    String createPayment();
    String executePayment(PaymentApprovalData paymentApprovalData, AddressModel billingAddress);
}
