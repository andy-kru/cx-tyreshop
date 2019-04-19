package com.reply.tyreshop.facades.populators;

import com.reply.tyreshop.facades.component.data.ClassAttributeAssignmentData;
import de.hybris.platform.catalog.model.classification.ClassAttributeAssignmentModel;
import de.hybris.platform.converters.Populator;

public class ClassAttributeAssignmentPopulator implements Populator<ClassAttributeAssignmentModel, ClassAttributeAssignmentData> {

    public void populate(ClassAttributeAssignmentModel source, ClassAttributeAssignmentData target) {
        target.setClassificationAttribute(source.getClassificationAttribute());
        target.setAttributeValues(source.getAttributeValues());
    }
}
