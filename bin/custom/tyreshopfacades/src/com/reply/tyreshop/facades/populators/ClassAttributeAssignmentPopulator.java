package com.reply.tyreshop.facades.populators;

import com.reply.tyreshop.facades.component.data.ClassAttributeData;
import com.reply.tyreshop.facades.component.data.ClassificationAttributeValueData;
import de.hybris.platform.catalog.model.classification.ClassAttributeAssignmentModel;
import de.hybris.platform.catalog.model.classification.ClassificationAttributeValueModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class ClassAttributeAssignmentPopulator implements Populator<ClassAttributeAssignmentModel, ClassAttributeData> {

    @Resource(name = "classificationAttributeValueConverter")
    private Converter<ClassificationAttributeValueModel, ClassificationAttributeValueData> classificationAttributeValueConverter;

    public void populate(ClassAttributeAssignmentModel source, ClassAttributeData target) {

        target.setCode(source.getClassificationAttribute().getCode());
        target.setName(source.getClassificationAttribute().getName());

        List<ClassificationAttributeValueData> attributesList = new ArrayList<>();
        for (ClassificationAttributeValueModel valueModel : source.getAttributeValues()) {
            attributesList.add(classificationAttributeValueConverter.convert(valueModel));
        }
        target.setAttributesList(attributesList);
    }
}
