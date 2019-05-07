package com.reply.tyreshop.facades.populators;

import com.reply.tyreshop.core.model.CardPaymentModeModel;
import com.reply.tyreshop.facades.product.data.CardPaymentModeData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class CardPaymentModeDataPopulator implements Populator<CardPaymentModeModel, CardPaymentModeData> {
    @Override
    public void populate(CardPaymentModeModel cardPaymentModeModel, CardPaymentModeData cardPaymentModeData) throws ConversionException {
        cardPaymentModeData.setCode(cardPaymentModeModel.getCode());
        cardPaymentModeData.setName(cardPaymentModeModel.getName());
    }
}
