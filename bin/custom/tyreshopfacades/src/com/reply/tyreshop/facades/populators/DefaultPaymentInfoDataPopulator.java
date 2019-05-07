package com.reply.tyreshop.facades.populators;

import de.hybris.platform.acceleratorservices.payment.cybersource.converters.populators.response.AbstractResultPopulator;
import de.hybris.platform.acceleratorservices.payment.cybersource.enums.CardTypeEnum;
import de.hybris.platform.acceleratorservices.payment.data.PaymentInfoData;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.apache.log4j.Logger;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

public class DefaultPaymentInfoDataPopulator implements Populator<PaymentInfoModel, PaymentInfoData> {
    private static final Logger LOG = Logger.getLogger(AbstractResultPopulator.class);
    private Converter<AddressModel, AddressData> addressConverter;

    @Override
    public void populate(PaymentInfoModel paymentInfoModel, PaymentInfoData paymentInfoData) throws ConversionException {
        if (paymentInfoModel == null)
        {
            return;
        }

        validateParameterNotNull(paymentInfoData, "Parameter [PaymentInfoData] target cannot be null");
        if(paymentInfoModel instanceof CreditCardPaymentInfoModel) {
            CreditCardPaymentInfoModel creditCardPaymentInfoModel = (CreditCardPaymentInfoModel) paymentInfoModel;
            paymentInfoData.setCardAccountNumber(creditCardPaymentInfoModel.getNumber());
            final CardTypeEnum cardType = CardTypeEnum.valueOf(creditCardPaymentInfoModel.getType().name().toLowerCase());
            if (cardType != null) {
                paymentInfoData.setCardCardType(cardType.getStringValue());
            }
            paymentInfoData.setCardExpirationMonth(getIntegerForString(creditCardPaymentInfoModel.getValidToMonth()));
            paymentInfoData.setCardExpirationYear(getIntegerForString(creditCardPaymentInfoModel.getValidToYear()));
            paymentInfoData.setCardIssueNumber(String.valueOf(creditCardPaymentInfoModel.getIssueNumber()));
            paymentInfoData.setCardStartMonth(creditCardPaymentInfoModel.getValidFromMonth());
            paymentInfoData.setCardStartYear(creditCardPaymentInfoModel.getValidFromYear());
            paymentInfoData.setCardAccountHolderName(creditCardPaymentInfoModel.getCcOwner());
        }
        paymentInfoData.setUser(paymentInfoModel.getUser());
        if (paymentInfoModel.getBillingAddress() != null)
        {
            paymentInfoData.setBillingAddress(addressConverter.convert(paymentInfoModel.getBillingAddress()));
        }

    }

    protected Integer getIntegerForString(final String value)
    {
        if (value != null && !value.isEmpty())
        {
            try
            {
                return Integer.valueOf(value);
            }
            catch (final Exception e)
            {
                LOG.debug("Error converting to Integer of String value: " + value, e);
            }
        }

        return null;
    }

    public void setAddressConverter(Converter<AddressModel, AddressData> addressConverter) {
        this.addressConverter = addressConverter;
    }
}
