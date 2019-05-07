package com.reply.tyreshop.paypal.service.impl;

import com.paypal.api.payments.Address;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.reply.tyreshop.paypal.commands.PaymentCreationCommand;
import com.reply.tyreshop.paypal.commands.PaymentExecutionCommand;
import com.reply.tyreshop.paypal.dto.PaymentApprovalData;
import com.reply.tyreshop.paypal.service.PayPalPaymentService;
import de.hybris.platform.core.enums.CreditCardType;
import de.hybris.platform.core.enums.PaymentStatus;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.payment.commands.factory.CommandFactory;
import de.hybris.platform.payment.commands.factory.CommandFactoryRegistry;
import de.hybris.platform.payment.commands.factory.CommandNotSupportedException;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.model.ModelService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import java.util.ArrayList;
import java.util.List;

public class PayPalPaymentServiceImpl implements PayPalPaymentService {

    private static final Logger LOG = Logger.getLogger(PayPalPaymentServiceImpl.class);

    private String paymentProvider;
    private CommandFactoryRegistry commandFactoryRegistry;
    private CommandFactory commandFactory;
    private CartService cartService;
    private ModelService modelService;

    private Converter<Address, AddressModel> billingAddressConverter;

    @Override
    public String createPayment() {
        String redirectURL = "/checkout/cancelPayPal";
        PaymentCreationCommand paymentCreationCommand;
        try{
            paymentCreationCommand = getCommandFactory().createCommand(PaymentCreationCommand.class);
        }
        catch (CommandNotSupportedException e){
            LOG.error(e);
            return redirectURL;
        }

        CartModel cartModel = getCartService().getSessionCart();
        CurrencyModel currency = cartModel.getCurrency();
        Double totalPrice = cartModel.getTotalPrice();

        Amount amount = new Amount();
        amount.setCurrency(currency.getIsocode());
        amount.setTotal(totalPrice.toString());

        Payment result = paymentCreationCommand.perform(amount);
        if(result != null){
            for (Links link : result.getLinks()) {
                if (link.getRel().equalsIgnoreCase("approval_url")) {
                    redirectURL = link.getHref();
                }
            }
        }
        return redirectURL;
    }

    @Override
    public String capturePayment(PaymentApprovalData paymentApprovalData) {
        String redirectURL = "/checkout/cancelPayPal";
        PaymentExecutionCommand paymentExecutionCommand;
        try{
            paymentExecutionCommand = getCommandFactory().createCommand(PaymentExecutionCommand.class);
        }
        catch (CommandNotSupportedException e){
            LOG.error(e);
            return redirectURL;
        }
        Payment result = paymentExecutionCommand.perform(paymentApprovalData);
        if(result != null){
            CartModel cartModel = getCartService().getSessionCart();
            List<ItemModel> modelList = new ArrayList<>();

            CreditCardPaymentInfoModel cardPaymentInfoModel = getModelService().create(CreditCardPaymentInfoModel.class);
            cardPaymentInfoModel.setCode(result.getId());
            cardPaymentInfoModel.setUser(cartModel.getUser());
            cardPaymentInfoModel.setCcOwner(result.getPayer().getPayerInfo().getFirstName() + " " +
                    result.getPayer().getPayerInfo().getLastName());
            cardPaymentInfoModel.setNumber("4255200579259789");
            cardPaymentInfoModel.setType(CreditCardType.VISA);
            cardPaymentInfoModel.setValidToMonth("08");
            cardPaymentInfoModel.setValidToYear("21");
            Address billingAddress = result.getPayer().getPayerInfo().getShippingAddress();
            AddressModel addressModel = getModelService().create(AddressModel.class);
            addressModel.setOwner(cardPaymentInfoModel);
            billingAddressConverter.convert(billingAddress, addressModel);
            cardPaymentInfoModel.setBillingAddress(addressModel);
            modelList.add(addressModel);
            modelList.add(cardPaymentInfoModel);

            PaymentTransactionModel paymentTransactionModel = getModelService().create(PaymentTransactionModel.class);
            paymentTransactionModel.setPaymentProvider(getPaymentProvider());
            paymentTransactionModel.setInfo(cardPaymentInfoModel);
            modelList.add(paymentTransactionModel);

            getModelService().saveAll(modelList);
            cartModel.setPaymentInfo(cardPaymentInfoModel);
            cartModel.setPaymentStatus(PaymentStatus.PAID);
            List<PaymentTransactionModel> paymentTransactionModels = cartModel.getPaymentTransactions();
            List<PaymentTransactionModel> paymentTransactions = new ArrayList<>(paymentTransactionModels);
            paymentTransactions.add(paymentTransactionModel);
            cartModel.setPaymentTransactions(paymentTransactions);
            getModelService().save(cartModel);
            redirectURL = "/checkout/multi/summary/view";
        }
        return redirectURL;
    }

    public void init(){
        commandFactory = getCommandFactoryRegistry().getFactory(getPaymentProvider());
    }

    public CartService getCartService() {
        return cartService;
    }

    @Required
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    public String getPaymentProvider() {
        return paymentProvider;
    }

    @Required
    public void setPaymentProvider(String paymentProvider) {
        this.paymentProvider = paymentProvider;
    }

    public CommandFactoryRegistry getCommandFactoryRegistry() {
        return commandFactoryRegistry;
    }

    @Required
    public void setCommandFactoryRegistry(CommandFactoryRegistry commandFactoryRegistry) {
        this.commandFactoryRegistry = commandFactoryRegistry;
    }

    private CommandFactory getCommandFactory() {
        return commandFactory;
    }

    public ModelService getModelService() {
        return modelService;
    }

    @Required
    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    @Required
    public void setBillingAddressConverter(Converter<Address, AddressModel> billingAddressConverter) {
        this.billingAddressConverter = billingAddressConverter;
    }
}
