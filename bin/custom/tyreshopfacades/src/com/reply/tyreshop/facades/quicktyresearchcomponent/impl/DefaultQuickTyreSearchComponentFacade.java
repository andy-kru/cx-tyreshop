package com.reply.tyreshop.facades.quicktyresearchcomponent.impl;

import com.reply.tyreshop.core.model.QuickTyreSearchComponentModel;
import com.reply.tyreshop.core.services.QuickTyreSearchComponentService;
import com.reply.tyreshop.facades.component.data.ClassAttributeAssignmentData;
import com.reply.tyreshop.facades.quicktyresearchcomponent.QuickTyreSearchComponentFacade;
import de.hybris.platform.catalog.model.classification.ClassAttributeAssignmentModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.springframework.beans.factory.annotation.Required;

public class DefaultQuickTyreSearchComponentFacade implements QuickTyreSearchComponentFacade {

    private QuickTyreSearchComponentService quickTyreSearchComponentService;
    private Converter<ClassAttributeAssignmentModel, ClassAttributeAssignmentData> classificationAttributeAssignmentConverter;

    @Override
    public QuickTyreSearchComponentModel getQuickTyreSearchComponentModel() {
        return  quickTyreSearchComponentService.getQuickTyreSearchComponentModel();
    }

    @Override
    public ClassAttributeAssignmentData getClassAttributeAssignmentData(ClassAttributeAssignmentModel component) {
        return classificationAttributeAssignmentConverter.convert(component);
    }

    public QuickTyreSearchComponentService getQuickTyreSearchComponentService() {
        return quickTyreSearchComponentService;
    }

    public void setQuickTyreSearchComponentService(QuickTyreSearchComponentService quickTyreSearchComponentService) {
        this.quickTyreSearchComponentService = quickTyreSearchComponentService;
    }

    public Converter<ClassAttributeAssignmentModel, ClassAttributeAssignmentData> getClassificationAttributeAssignmentConverter() {
        return classificationAttributeAssignmentConverter;
    }

    @Required
    public void setClassAttributeAssignmentConverter(final Converter<ClassAttributeAssignmentModel, ClassAttributeAssignmentData> classificationAttributeAssignmentConverter)
    {
        this.classificationAttributeAssignmentConverter = classificationAttributeAssignmentConverter;
    }
}
