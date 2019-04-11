package com.reply.tyreshop.facades.quicktyresearchcomponent;

import com.reply.tyreshop.core.model.QuickTyreSearchComponentModel;
import com.reply.tyreshop.facades.component.data.QuickTyreSearchComponentData;


public interface QuickTyreSearchComponentFacade {

    /**
     * Gets quick tyre search component data.
     * @param component
     *           the model of the component
     * @return the {@link QuickTyreSearchComponentData}
     */
    QuickTyreSearchComponentData getQuickTyreSearchComponentData(QuickTyreSearchComponentModel component);
}
