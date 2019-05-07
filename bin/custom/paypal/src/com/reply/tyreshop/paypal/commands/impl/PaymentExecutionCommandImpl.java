package com.reply.tyreshop.paypal.commands.impl;

import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.reply.tyreshop.paypal.commands.AbstractPaymentCommand;
import com.reply.tyreshop.paypal.commands.PaymentExecutionCommand;
import com.reply.tyreshop.paypal.dto.PaymentApprovalData;

public class PaymentExecutionCommandImpl extends AbstractPaymentCommand implements PaymentExecutionCommand {

    @Override
    public Payment perform(PaymentApprovalData paymentApprovalData) {
        Payment executedPayment = null;

        Payment payment = new Payment();
        payment.setId(paymentApprovalData.getPaymentId());

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(paymentApprovalData.getPayerId());

        try {
            APIContext apiContext = new APIContext(getClientId(), getClientSecret(), "sandbox");
            executedPayment = payment.execute(apiContext, paymentExecution);
        } catch (PayPalRESTException ignored) {

        }
        return executedPayment;
    }
}
