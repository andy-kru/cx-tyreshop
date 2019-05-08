package com.reply.tyreshop.paypal.commands.impl;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.reply.tyreshop.paypal.commands.AbstractPaymentCommand;
import com.reply.tyreshop.paypal.commands.PaymentCreationCommand;

import java.util.ArrayList;
import java.util.List;

public class PaymentCreationCommandImpl extends AbstractPaymentCommand implements PaymentCreationCommand {

    @Override
    public Payment perform(Amount amount){

        final String RETURN_URL = "https://tyreshop.local:9002/tyreshopstorefront/payPal/returnPayment";
        final String CANCEL_URL = "https://tyreshop.local:9002/tyreshopstorefront/payPal/cancelPayment";

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setReturnUrl(RETURN_URL);
        redirectUrls.setCancelUrl(CANCEL_URL);
        payment.setRedirectUrls(redirectUrls);

        Payment createdPayment = null;
        try {
            APIContext apiContext = new APIContext(getClientId(), getClientSecret(), "sandbox");
            createdPayment = payment.create(apiContext);
        } catch (PayPalRESTException ignored) {

        }
        return createdPayment;
    }
}
