package com.reply.tyreshop.addons.callback.facades.impl;

import com.reply.tyreshop.addons.callback.data.CallbackData;
import com.reply.tyreshop.addons.callback.enums.CallbackStatusEnum;
import com.reply.tyreshop.addons.callback.facades.CallbackComponentFacade;
import com.reply.tyreshop.addons.callback.model.CallbackModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Date;

public class DefaultCallbackComponentFacade implements CallbackComponentFacade {
    private Converter<CallbackData, CallbackModel> callbackModelReverseConverter;

    private ModelService modelService;

    @Override
    public void confirmCallback(CallbackData callbackData) {
        CallbackModel callbackModel = modelService.create(CallbackModel.class);
        callbackModel = callbackModelReverseConverter.convert(callbackData, callbackModel);
        callbackModel.setCreationtime(new Date());
        callbackModel.setStatus(CallbackStatusEnum.UNPROCESSED);
        modelService.save(callbackModel);
    }

    public void setCallbackModelReverseConverter(Converter<CallbackData, CallbackModel> callbackModelReverseConverter) {
        this.callbackModelReverseConverter = callbackModelReverseConverter;
    }

    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }
}
