package com.reply.tyreshop.jobs;


import com.reply.tyreshop.addons.callback.model.CallbackModel;
import com.reply.tyreshop.jobs.dao.TyreshopCallbackJobDao;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TyreshopCallbackJob extends AbstractJobPerformable<CronJobModel> {
    @Autowired
    TyreshopCallbackJobDao tyreshopCallbackJobDao;

    @Override
    public PerformResult perform(CronJobModel cronJobModel) {
        List<CallbackModel> callbackList = tyreshopCallbackJobDao.getOldCallbackModels();
        for (CallbackModel callback : callbackList) {
            modelService.remove(callback);
        }

        return new PerformResult(CronJobResult.SUCCESS,
                CronJobStatus.FINISHED);

    }
}
