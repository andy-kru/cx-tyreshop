package com.reply.tyreshop.addons.callback.services.impl;

import com.reply.tyreshop.addons.callback.enums.CallbackStatusEnum;
import com.reply.tyreshop.addons.callback.model.CallbackModel;
import com.reply.tyreshop.addons.callback.services.CallbackComponentService;
import de.hybris.platform.servicelayer.model.ModelService;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DefaultCallbackComponentService implements CallbackComponentService {
    @Resource(name = "modelService")
    private ModelService modelService;

    @Override
    public String testName(String name) {
        Pattern regexp = Pattern.compile("^[а-яёА-ЯЁa-zA-Z]+$");
        Matcher m = regexp.matcher(name);
        if(!m.matches())
        {
            return "Bad";
        }
        return "Okay";
    }

    @Override
    public String confirmCallback(String name, String phone, String comment) {
        CallbackModel model = modelService.create(CallbackModel.class);
        model.setName(name);
        model.setPhone(phone);
        model.setComment(comment);
        model.setCreationtime(new Date());
        model.setStatus(Collections.singleton(CallbackStatusEnum.UNPROCESSED));
        modelService.save(model);
        String result = "Okay";
        return result;
    }
}
