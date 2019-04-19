package com.reply.tyreshop.facades.quicktyresearchcomponent;

import com.reply.tyreshop.core.model.QuickTyreSearchComponentModel;
import com.reply.tyreshop.facades.component.data.ClassAttributeAssignmentData;
import de.hybris.platform.catalog.model.classification.ClassAttributeAssignmentModel;


public interface QuickTyreSearchComponentFacade {

    QuickTyreSearchComponentModel getQuickTyreSearchComponentModel();
    ClassAttributeAssignmentData getClassAttributeAssignmentData(ClassAttributeAssignmentModel component);

}
