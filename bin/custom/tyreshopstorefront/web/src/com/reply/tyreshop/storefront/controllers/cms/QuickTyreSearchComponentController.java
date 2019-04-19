package com.reply.tyreshop.storefront.controllers.cms;

import com.reply.tyreshop.core.model.QuickTyreSearchComponentModel;
import com.reply.tyreshop.core.services.QuickTyreSearchComponentService;
import com.reply.tyreshop.facades.component.data.ClassAttributeAssignmentData;
import com.reply.tyreshop.facades.quicktyresearchcomponent.QuickTyreSearchComponentFacade;
import com.reply.tyreshop.storefront.controllers.ControllerConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.catalog.model.classification.ClassAttributeAssignmentModel;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller("QuickTyreSearchComponentController")
public class QuickTyreSearchComponentController extends AbstractPageController
{

    @Autowired
    private QuickTyreSearchComponentFacade quickTyreSearchComponentFacade;

    @RequestMapping(value = ControllerConstants.Actions.Cms.QuickTyreSearchComponent)
    protected String fillModel(Model model)
    {
        List<ClassAttributeAssignmentData> classAttributeAssignmentDataList = new ArrayList<>();
        QuickTyreSearchComponentModel component = quickTyreSearchComponentFacade.getQuickTyreSearchComponentModel();

        for (ClassAttributeAssignmentModel classAttributeAssignment : component.getTyreAttributes().getAllClassificationAttributeAssignments()) {
            ClassAttributeAssignmentData data = quickTyreSearchComponentFacade.getClassAttributeAssignmentData(classAttributeAssignment);
            classAttributeAssignmentDataList.add(data);
        }
        model.addAttribute("dataList", classAttributeAssignmentDataList);

        return ControllerConstants.Views.Cms.ComponentPrefix + StringUtils.lowerCase(QuickTyreSearchComponentModel._TYPECODE);
    }

    @RequestMapping(value = "/quicktyresearch", method = {RequestMethod.POST})
    protected String quickTyreSearchForm(HttpServletRequest request) throws UnsupportedEncodingException {

        QuickTyreSearchComponentModel component = quickTyreSearchComponentFacade.getQuickTyreSearchComponentModel();
        String partUrl = "";

        for (ClassAttributeAssignmentModel classAttributeAssignment : component.getTyreAttributes().getAllClassificationAttributeAssignments()) {
            String value = request.getParameter(classAttributeAssignment.getClassificationAttribute().getCode());
            if (value != null && !value.isEmpty()){
                String code = classAttributeAssignment.getClassificationAttribute().getCode();
                partUrl += ":" + code + ":" + value;
            }
        }
        return "redirect:/Tyre-type/c/typeTyres?q=" + URLEncoder.encode(":price-asc" + partUrl, "UTF-8") + "&text=#";
    }

}
