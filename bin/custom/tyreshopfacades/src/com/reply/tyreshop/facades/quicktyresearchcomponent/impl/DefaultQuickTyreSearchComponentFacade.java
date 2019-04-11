package com.reply.tyreshop.facades.quicktyresearchcomponent.impl;

import com.reply.tyreshop.core.model.QuickTyreSearchComponentModel;
import com.reply.tyreshop.facades.component.data.QuickTyreSearchComponentData;
import com.reply.tyreshop.facades.quicktyresearchcomponent.QuickTyreSearchComponentFacade;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.springframework.beans.factory.annotation.Required;

public class DefaultQuickTyreSearchComponentFacade implements QuickTyreSearchComponentFacade {

    private Converter<QuickTyreSearchComponentModel, QuickTyreSearchComponentData> defaultQuickTyreSearchComponentConverter;

    @Override
    public QuickTyreSearchComponentData getQuickTyreSearchComponentData(QuickTyreSearchComponentModel component) {

        return getQuickTyreSearchComponentConverter().convert(component);
    }

    protected Converter<QuickTyreSearchComponentModel, QuickTyreSearchComponentData> getQuickTyreSearchComponentConverter()
    {
        return defaultQuickTyreSearchComponentConverter;
    }

    @Required
    public void setDefaultQuickTyreSearchComponentConverter(final Converter<QuickTyreSearchComponentModel, QuickTyreSearchComponentData> defaultQuickTyreSearchComponentConverter)
    {
        this.defaultQuickTyreSearchComponentConverter = defaultQuickTyreSearchComponentConverter;
    }
}
