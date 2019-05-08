package com.reply.tyreshop.facades.populators;

import com.reply.tyreshop.core.jalo.BankTransferPaymentMode;
import com.reply.tyreshop.core.model.BankTransferPaymentModeModel;
import de.hybris.platform.acceleratorservices.payment.data.PaymentInfoData;
import de.hybris.platform.commercefacades.order.converters.populator.OrderPopulator;
import de.hybris.platform.commercefacades.order.data.AbstractOrderData;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.springframework.util.Assert;

public class TyreshopOrderPopulator extends OrderPopulator {
    public Converter<PaymentInfoModel, PaymentInfoData> paymentInfoDataConverter;

    @Override
    public void populate(OrderModel source, OrderData target) {
        Assert.notNull(source, "Parameter source cannot be null.");
        Assert.notNull(target, "Parameter target cannot be null.");

        addCommon(source, target);
        addDetails(source, target);
        addTotals(source, target);
        addEntries(source, target);
        addPromotions(source, target);
        addDeliveryAddress(source, target);
        addDeliveryMethod(source, target);
        addPaymentInformation(source, target);
        addPaymentModeCode(source, target);
        checkForGuestCustomer(source, target);
        addDeliveryStatus(source, target);
        addPrincipalInformation(source, target);
        if(source.getPaymentMode() instanceof BankTransferPaymentModeModel){
            addBankTrasferReference(source, target);
        }

        if (source.getQuoteReference() != null)
        {
            target.setQuoteCode(source.getQuoteReference().getCode());
        }
    }

    private void addBankTrasferReference(OrderModel source, OrderData target) {
        if(!source.getPaymentTransactions().isEmpty())
            target.setBankTransferReference(source.getPaymentTransactions().get(0).getVersionID().toString());
    }

    private void addPaymentModeCode(OrderModel source, OrderData target) {
        PaymentModeModel paymentModeModel = source.getPaymentMode();
        if(paymentModeModel != null)
            target.setPaymentModeCode(paymentModeModel.getCode());
    }

    @Override
    protected void addPaymentInformation(AbstractOrderModel source, AbstractOrderData prototype) {
        final PaymentInfoModel paymentInfo = source.getPaymentInfo();
        if(paymentInfo != null)
            prototype.setCommonPaymentInfo(paymentInfoDataConverter.convert(paymentInfo, new PaymentInfoData()));
    }

    public void setPaymentInfoDataConverter(Converter<PaymentInfoModel, PaymentInfoData> paymentInfoDataConverter) {
        this.paymentInfoDataConverter = paymentInfoDataConverter;
    }
}
