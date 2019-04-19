package com.reply.tyreshop.addons.callback.controllers.cms;

import com.reply.tyreshop.addons.callback.data.CallbackData;
import com.reply.tyreshop.addons.callback.dto.CallbackForm;
import com.reply.tyreshop.addons.callback.facades.CallbackComponentFacade;
import com.reply.tyreshop.addons.callback.model.CallbackComponentModel;
import de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller("CallbackComponentController")
@RequestMapping(value = "/view/" + CallbackComponentModel._TYPECODE + "Controller")
public class CallbackComponentController extends AbstractCMSAddOnComponentController<CallbackComponentModel> {

    @Resource(name = "callbackComponentFacade")
    private CallbackComponentFacade callbackComponentFacade;
    @Resource(name = "defaultCallbackDataConverter")
    private Converter<CallbackForm, CallbackData> callbackDataConverter;

    @Override
    protected void fillModel(HttpServletRequest request, Model model, CallbackComponentModel component) {
        model.addAttribute("callbackForm", new CallbackForm());
    }

    @RequestMapping(value = "/confirm-callback", method = RequestMethod.POST)
    private @ResponseBody
    Boolean confirmCallback(@ModelAttribute("callbackForm") @Valid CallbackForm callbackForm,
                            BindingResult result) {
        if (result.hasErrors()) {
            return false;
        }
        CallbackData callbackData = callbackDataConverter.convert(callbackForm);
        callbackComponentFacade.confirmCallback(callbackData);
        return true;
    }

}
