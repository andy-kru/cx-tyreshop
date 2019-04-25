package com.reply.tyreshop.jobs.dao.impl;

import com.reply.tyreshop.addons.callback.enums.CallbackStatusEnum;
import com.reply.tyreshop.addons.callback.model.CallbackModel;
import com.reply.tyreshop.jobs.dao.TyreshopCallbackJobDao;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@IntegrationTest
public class DefaultTyreshopCallbackJobDaoTest extends ServicelayerTransactionalTest {
    private CallbackModel callbackModel;

    @Resource
    TyreshopCallbackJobDao tyreshopCallbackJobDao;

    @Resource
    ModelService modelService;

    public DefaultTyreshopCallbackJobDaoTest() {
        super();
    }

    @Test
    public void getOldCallbackModels() {
        List<CallbackModel> callbackModels = tyreshopCallbackJobDao.getOldCallbackModels();
        Assert.assertEquals(callbackModels.size(), 1);
    }

    @Before
    public void initialize() {
        callbackModel = modelService.create(CallbackModel.class);
        callbackModel.setName("Text");
        callbackModel.setPhone("375336782343");
        callbackModel.setComment("Text");
        callbackModel.setCreationtime(new Date());
        callbackModel.setStatus(CallbackStatusEnum.UNPROCESSED);
        modelService.save(callbackModel);

        callbackModel = modelService.create(CallbackModel.class);
        callbackModel.setName("Text");
        callbackModel.setPhone("375336782343");
        callbackModel.setComment("Text");
        callbackModel.setCreationtime(new Date());
        callbackModel.setStatus(CallbackStatusEnum.PROCESSED);
        modelService.save(callbackModel);

        callbackModel = modelService.create(CallbackModel.class);
        callbackModel.setName("Text");
        callbackModel.setPhone("375336782343");
        callbackModel.setComment("Text");
        callbackModel.setCreationtime(new Date(1523456880000l));
        callbackModel.setStatus(CallbackStatusEnum.PROCESSED);
        modelService.save(callbackModel);

        callbackModel = modelService.create(CallbackModel.class);
        callbackModel.setName("Text");
        callbackModel.setPhone("375336782343");
        callbackModel.setComment("Text");
        callbackModel.setCreationtime(new Date(1523456880000l));
        callbackModel.setStatus(CallbackStatusEnum.UNPROCESSED);
        modelService.save(callbackModel);
    }
}