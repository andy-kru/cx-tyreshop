package com.reply.tyreshop.facades.populators;

import com.reply.tyreshop.core.model.QuickTyreSearchComponentModel;
import com.reply.tyreshop.facades.component.data.QuickTyreSearchComponentData;
import de.hybris.platform.converters.Populator;

public class QuickTyreSearchComponentPopulator implements Populator<QuickTyreSearchComponentModel, QuickTyreSearchComponentData> {

    public void populate(QuickTyreSearchComponentModel source, QuickTyreSearchComponentData target) {
        target.setTyreQuickSearchTitle(source.getTyreQuickSearchTitle());
        target.setTyreWidth(source.getTyreWidth());
        target.setTyreHeight(source.getTyreHeight());
        target.setTyreDiameter(source.getTyreDiameter());
        target.setTyreSeason(source.getTyreSeason());
        target.setTyreLoadIndex(source.getTyreLoadIndex());
        target.setTyreSpeedIndex(source.getTyreSpeedIndex());
        target.setQuickTyreSearchButton(source.getQuickTyreSearchButton());
        target.setTyreAttributes(source.getTyreAttributes());
    }
}
