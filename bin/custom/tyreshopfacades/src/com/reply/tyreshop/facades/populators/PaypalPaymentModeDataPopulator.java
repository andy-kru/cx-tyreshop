package com.reply.tyreshop.facades.populators;

import com.reply.tyreshop.core.model.PaypalPaymentModeModel;
import com.reply.tyreshop.facades.product.data.PaypalPaymentModeData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class PaypalPaymentModeDataPopulator implements Populator<PaypalPaymentModeModel, PaypalPaymentModeData> {
    @Override
    public void populate(PaypalPaymentModeModel paypalPaymentModeModel, PaypalPaymentModeData paypalPaymentModeData) throws ConversionException {
        paypalPaymentModeData.setCode(paypalPaymentModeModel.getCode());
        paypalPaymentModeData.setName(paypalPaymentModeModel.getName());
    }
}
