package com.reply.tyreshop.core.daos.impl;

import com.reply.tyreshop.core.daos.QuickTyreSearchComponentDao;
import com.reply.tyreshop.core.model.QuickTyreSearchComponentModel;
import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import java.util.List;

public class DefaultQuickTyreSearchComponentDao extends AbstractItemDao implements QuickTyreSearchComponentDao {

    public QuickTyreSearchComponentModel getQuickTyreSearchComponentModel() {

        String fsq = "SELECT {" + QuickTyreSearchComponentModel.PK + "} FROM {" + QuickTyreSearchComponentModel._TYPECODE + "}";
        FlexibleSearchQuery query = new FlexibleSearchQuery(fsq);
        List<QuickTyreSearchComponentModel> quickTyreSearchComponent = getFlexibleSearchService().<QuickTyreSearchComponentModel> search(query).getResult();

        return quickTyreSearchComponent.get(0);
    }
}
