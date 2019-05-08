package com.reply.tyreshop.paypal.facades.impl;

import com.reply.tyreshop.paypal.facades.PayPalFacade;
import com.reply.tyreshop.paypal.dto.PaymentApprovalData;
import com.reply.tyreshop.paypal.service.PayPalPaymentService;
import de.hybris.platform.core.model.user.AddressModel;
import org.springframework.beans.factory.annotation.Required;

public class DefaultPayPalFacade implements PayPalFacade {

    private PayPalPaymentService paymentService;

    @Override
    public String createPayment() {
        return paymentService.createPayment();
    }

    @Override
    public String executePayment(PaymentApprovalData paymentApprovalData, AddressModel billingAddress) {

        return paymentService.capturePayment(paymentApprovalData, billingAddress);
    }

    public PayPalPaymentService getPaymentService() {
        return paymentService;
    }

    @Required
    public void setPaymentService(PayPalPaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
