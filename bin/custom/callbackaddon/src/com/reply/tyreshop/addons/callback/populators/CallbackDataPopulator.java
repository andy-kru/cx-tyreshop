package com.reply.tyreshop.addons.callback.populators;

import com.reply.tyreshop.addons.callback.data.CallbackData;
import com.reply.tyreshop.addons.callback.dto.CallbackForm;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class CallbackDataPopulator implements Populator<CallbackForm, CallbackData> {
    @Override
    public void populate(CallbackForm callbackForm, CallbackData callbackData) throws ConversionException {
        callbackData.setName(callbackForm.getName());
        callbackData.setPhone(callbackForm.getPhone());
        callbackData.setComment(callbackForm.getComment());
    }
}
