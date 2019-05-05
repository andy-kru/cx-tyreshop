package com.reply.tyreshop.core.hooks.impl;

import com.reply.tyreshop.core.hooks.BankTransferHook;

import com.reply.tyreshop.core.services.BankTransferPaymentService;
import de.hybris.platform.commerceservices.strategies.GenerateMerchantTransactionCodeStrategy;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.ArrayList;
import java.util.List;

public class DefaultBankTransferHook implements BankTransferHook {
    private ModelService modelService;
    private CartService cartService;
    private GenerateMerchantTransactionCodeStrategy generateMerchantTransactionCodeStrategy;
    private BankTransferPaymentService bankTransferPaymentService;

    @Override
    public void beforePlaceOrder() {
        PaymentTransactionModel paymentTransaction = modelService.create(PaymentTransactionModel.class);
        final CartModel cartModel = cartService.getSessionCart();
        final String code = generateMerchantTransactionCodeStrategy.generateCode(cartModel);
        paymentTransaction.setCode(code);
        paymentTransaction.setVersionID(bankTransferPaymentService.getBankTransferPaymentReference());
        List<PaymentTransactionModel> paymentTransactionModels = new ArrayList<>();
        paymentTransactionModels.addAll(cartModel.getPaymentTransactions());
        paymentTransactionModels.add(paymentTransaction);
        cartModel.setPaymentTransactions(paymentTransactionModels);
    }

    @Override
    public void afterPlaceOrder() {

    }


    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    public void setGenerateMerchantTransactionCodeStrategy(GenerateMerchantTransactionCodeStrategy generateMerchantTransactionCodeStrategy) {
        this.generateMerchantTransactionCodeStrategy = generateMerchantTransactionCodeStrategy;
    }

    public void setBankTransferPaymentService(BankTransferPaymentService bankTransferPaymentService) {
        this.bankTransferPaymentService = bankTransferPaymentService;
    }
}
