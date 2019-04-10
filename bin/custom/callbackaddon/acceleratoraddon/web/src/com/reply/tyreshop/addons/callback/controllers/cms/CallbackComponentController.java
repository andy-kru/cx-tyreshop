package com.reply.tyreshop.addons.callback.controllers.cms;

import com.reply.tyreshop.addons.callback.model.CallbackComponentModel;

import com.reply.tyreshop.addons.callback.services.CallbackComponentService;
import com.reply.tyreshop.addons.callback.services.impl.DefaultCallbackComponentService;
import de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller("CallbackComponentController")
@RequestMapping(value = "/view/" + CallbackComponentModel._TYPECODE + "Controller")
public class CallbackComponentController extends AbstractCMSAddOnComponentController<CallbackComponentModel> {
    @Autowired
    private DefaultCallbackComponentService defaultCallbackComponentService;

    @Override
    protected void fillModel(HttpServletRequest request, Model model, CallbackComponentModel component) {
        model.addAttribute("headText", component.getHeadText());
        model.addAttribute("customerName", component.getCustomerName());
        model.addAttribute("inputName", component.getInputName());
        model.addAttribute("customerPhone", component.getCustomerPhone());
        model.addAttribute("comment", component.getComment());
        model.addAttribute("inputComment", component.getInputComment());
        model.addAttribute("confirmB", component.getConfirmB());
        model.addAttribute("closeB", component.getCloseB());
        model.addAttribute("openB", component.getOpenB());
        model.addAttribute("confirmationText", component.getConfirmationText());
    }
    @RequestMapping(value = "/confirm-callback")
    private @ResponseBody String confirmCallback(@RequestParam Map<String, String> requestParams){
        String name = requestParams.get("name");
        String phone = requestParams.get("phone");
        String comment = requestParams.get("comment");
        return defaultCallbackComponentService.confirmCallback(name,phone,comment);
    }

    @RequestMapping(value = "/test-name")
    private @ResponseBody String testName(@RequestParam Map<String, String> requestParams){
        String name = requestParams.get("name");
        return defaultCallbackComponentService.testName(name);
    }

    public void setDefaultCallbackComponentService(DefaultCallbackComponentService defaultCallbackComponentService) {
        this.defaultCallbackComponentService = defaultCallbackComponentService;
    }
}
