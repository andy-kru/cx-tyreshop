package com.reply.tyreshop.storefront.controllers.cms;

import com.reply.tyreshop.core.model.QuickTyreSearchComponentModel;
import com.reply.tyreshop.facades.quicktyresearchcomponent.QuickTyreSearchComponentFacade;
import com.reply.tyreshop.storefront.controllers.ControllerConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.cms.AbstractCMSComponentController;
import de.hybris.platform.catalog.model.classification.ClassAttributeAssignmentModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller("QuickTyreSearchComponentController")
@RequestMapping(value = ControllerConstants.Actions.Cms.QuickTyreSearchComponent)
public class QuickTyreSearchComponentController extends AbstractCMSComponentController<QuickTyreSearchComponentModel>
{

    @Resource(name = "defaultQuickTyreSearchComponentFacade")
    private QuickTyreSearchComponentFacade defaultQuickTyreSearchComponentFacade;

    @Autowired
    FlexibleSearchService flexibleSearchService;

    protected void fillModel(final HttpServletRequest request, final Model model, final QuickTyreSearchComponentModel component)
    {
        model.addAttribute("quickTyreSearchData", defaultQuickTyreSearchComponentFacade.getQuickTyreSearchComponentData(component));

        for (ClassAttributeAssignmentModel classAttributeAssignment : component.getTyreAttributes().getAllClassificationAttributeAssignments()) {
            model.addAttribute(classAttributeAssignment.getClassificationAttribute().getName(Locale.ENGLISH).replaceAll("\\s",""), classAttributeAssignment.getAttributeValues());
        }
    }

    @Override
    protected String getView(QuickTyreSearchComponentModel component) {
        return ControllerConstants.Views.Cms.ComponentPrefix + StringUtils.lowerCase(getTypeCode(component));
    }

    @RequestMapping(value = "/quicktyresearch")
    private @ResponseBody String quickTyreSearchForm(@RequestParam Map<String, String> requestParams)
    {
        String value, code;
        String result = "?q=%3Aprice-asc";

        String fsq = "SELECT {" + ClassAttributeAssignmentModel.PK + "} FROM {" + ClassAttributeAssignmentModel._TYPECODE + "}";
        FlexibleSearchQuery query = new FlexibleSearchQuery(fsq);
        List<ClassAttributeAssignmentModel> classAttributeAssignmentList = flexibleSearchService.<ClassAttributeAssignmentModel>search(query).getResult();

        for (ClassAttributeAssignmentModel classAttributeAssignment :classAttributeAssignmentList) {
            value = requestParams.get(classAttributeAssignment.getClassificationAttribute().getName(Locale.ENGLISH).replaceAll("\\s",""));
            if (!value.isEmpty() && value != null ){
                code = classAttributeAssignment.getClassificationAttribute().getCode();
                result += getPath(value, code);
            }
        }
        return "/tyreshopstorefront/tyreshop/en/Tyre-type/c/typeTyres" + result + "&text=#";
    }

    private String getPath(String value, String code) {
        return "%3A" + code.replace(", ", "%2C+") + "%3A" + value;
    }
}
