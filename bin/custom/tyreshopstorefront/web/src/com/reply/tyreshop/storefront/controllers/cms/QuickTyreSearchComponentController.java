package com.reply.tyreshop.storefront.controllers.cms;

import com.reply.tyreshop.core.dto.QuickTyreSearchAttributes;
import com.reply.tyreshop.core.model.QuickTyreSearchComponentModel;
import com.reply.tyreshop.facades.component.data.ClassAttributeData;
import com.reply.tyreshop.storefront.controllers.ControllerConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.cms.AbstractCMSComponentController;
import de.hybris.platform.catalog.model.classification.ClassAttributeAssignmentModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller("QuickTyreSearchComponentController")
@RequestMapping(value = ControllerConstants.Actions.Cms.QuickTyreSearchComponent)
public class QuickTyreSearchComponentController extends AbstractCMSComponentController<QuickTyreSearchComponentModel>
{

    @Resource(name = "classAttributeAssignmentConverter")
    private Converter<ClassAttributeAssignmentModel, ClassAttributeData> classAttributeAssignmentConverter;

    @ModelAttribute("quickTyreSearchAttributes")
    public QuickTyreSearchAttributes getQuickTyreSearchAttributes() {
        return new QuickTyreSearchAttributes();
    }

    @Override
    protected void fillModel(HttpServletRequest request, Model model, QuickTyreSearchComponentModel component) {
        List<ClassAttributeData> classAttributeDataList = new ArrayList<>();
        for (ClassAttributeAssignmentModel classAttributeAssignment : component.getTyreAttributes().getAllClassificationAttributeAssignments()) {
            classAttributeDataList.add(classAttributeAssignmentConverter.convert(classAttributeAssignment));
        }
        model.addAttribute("dataList", classAttributeDataList);
    }

    @Override
    protected String getView(QuickTyreSearchComponentModel component) {
        return ControllerConstants.Views.Cms.ComponentPrefix + StringUtils.lowerCase(getTypeCode(component));
    }

}
