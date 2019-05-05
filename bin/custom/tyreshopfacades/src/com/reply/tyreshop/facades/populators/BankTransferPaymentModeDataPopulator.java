package com.reply.tyreshop.facades.populators;

import com.reply.tyreshop.core.model.BankTransferPaymentModeModel;
import com.reply.tyreshop.facades.product.data.BankTransferPaymentModeData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class BankTransferPaymentModeDataPopulator implements Populator<BankTransferPaymentModeModel, BankTransferPaymentModeData> {
    @Override
    public void populate(BankTransferPaymentModeModel bankTransferPaymentModeModel, BankTransferPaymentModeData bankTransferPaymentModeData) throws ConversionException {
        bankTransferPaymentModeData.setCode(bankTransferPaymentModeModel.getCode());
        bankTransferPaymentModeData.setName(bankTransferPaymentModeModel.getName());
    }
}
