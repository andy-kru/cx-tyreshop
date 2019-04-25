package com.reply.tyreshop.facades.populators;

import com.reply.tyreshop.facades.component.data.ClassificationAttributeValueData;
import de.hybris.platform.catalog.model.classification.ClassificationAttributeValueModel;
import de.hybris.platform.converters.Populator;

public class ClassificationAttributeValuePopulator implements Populator<ClassificationAttributeValueModel, ClassificationAttributeValueData> {

    public void populate(ClassificationAttributeValueModel source, ClassificationAttributeValueData target) {

        target.setCode(source.getCode());
        target.setName(source.getName());

    }
}
