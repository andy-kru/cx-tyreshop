package com.reply.tyreshop.jobs;


import com.reply.tyreshop.addons.callback.model.CallbackModel;
import com.reply.tyreshop.jobs.dao.TyreshopCallbackJobDao;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;

import java.util.List;

public class TyreshopCallbackJob extends AbstractJobPerformable<CronJobModel> {

    private TyreshopCallbackJobDao tyreshopCallbackJobDao;

    @Override
    public PerformResult perform(CronJobModel cronJobModel) {
        List<CallbackModel> callbackList = tyreshopCallbackJobDao.getOldCallbackModels();
        modelService.removeAll(callbackList);

        return new PerformResult(CronJobResult.SUCCESS,
                CronJobStatus.FINISHED);

    }

    public void setTyreshopCallbackJobDao(TyreshopCallbackJobDao tyreshopCallbackJobDao) {
        this.tyreshopCallbackJobDao = tyreshopCallbackJobDao;
    }
}
