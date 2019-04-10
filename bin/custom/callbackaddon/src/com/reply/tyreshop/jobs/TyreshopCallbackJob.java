package com.reply.tyreshop.jobs;


import com.reply.tyreshop.addons.callback.enums.CallbackStatusEnum;
import com.reply.tyreshop.addons.callback.model.CallbackModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TyreshopCallbackJob extends AbstractJobPerformable<CronJobModel> {
    @Override
    public PerformResult perform(CronJobModel cronJobModel) {
        String query = "SELECT {" + CallbackModel.PK + "} FROM {" + CallbackModel._TYPECODE + "} WHERE {" + CallbackModel.CREATIONTIME + "} < ?date" ;
        final Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        FlexibleSearchQuery fQuery = new FlexibleSearchQuery(query);
        fQuery.addQueryParameter("date", cal.getTime());
        SearchResult<CallbackModel> searchResult = flexibleSearchService.search(fQuery);
        List<CallbackModel> callbackList = searchResult.getResult();
        for(CallbackModel callback : callbackList){
           if(callback.getStatus().contains(CallbackStatusEnum.PROCESSED)){
               modelService.remove(callback);
           }
        }
        return new PerformResult(CronJobResult.SUCCESS,
                CronJobStatus.FINISHED);

    }
}
