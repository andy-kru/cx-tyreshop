package com.reply.tyreshop.jobs.dao.impl;

import com.reply.tyreshop.addons.callback.enums.CallbackStatusEnum;
import com.reply.tyreshop.addons.callback.model.CallbackModel;
import com.reply.tyreshop.jobs.dao.TyreshopCallbackJobDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DefaultTyreshopCallbackJobDao implements TyreshopCallbackJobDao {

    @Autowired
    FlexibleSearchService flexibleSearchService;

    @Override
    public List<CallbackModel> getOldCallbackModels() {
        String query = "SELECT {" + CallbackModel.PK + "} FROM {" + CallbackModel._TYPECODE + "} WHERE {" + CallbackModel.CREATIONTIME + "} < ?date AND {" + CallbackModel.STATUS + "} = ( ?status )";
        final Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        FlexibleSearchQuery fQuery = new FlexibleSearchQuery(query);
        fQuery.addQueryParameter("date", cal.getTime());
        fQuery.addQueryParameter("status", CallbackStatusEnum.PROCESSED);
        SearchResult<CallbackModel> searchResult = flexibleSearchService.search(fQuery);
        return searchResult.getResult();
    }
}