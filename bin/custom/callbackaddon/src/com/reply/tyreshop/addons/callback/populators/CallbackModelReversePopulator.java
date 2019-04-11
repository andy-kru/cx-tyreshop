package com.reply.tyreshop.addons.callback.populators;

import com.reply.tyreshop.addons.callback.data.CallbackData;
import com.reply.tyreshop.addons.callback.model.CallbackModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class CallbackModelReversePopulator implements Populator<CallbackData, CallbackModel> {
    @Override
    public void populate(CallbackData callbackData, CallbackModel callbackModel) throws ConversionException {
        callbackModel.setName(callbackData.getName());
        callbackModel.setPhone(callbackData.getPhone());
        callbackModel.setComment(callbackData.getComment());
    }
}
