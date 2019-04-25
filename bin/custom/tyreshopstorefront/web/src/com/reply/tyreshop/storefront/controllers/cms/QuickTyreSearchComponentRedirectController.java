package com.reply.tyreshop.storefront.controllers.cms;

import com.reply.tyreshop.core.dto.QuickTyreSearchAttributes;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

@Controller("QuickTyreSearchComponentRedirectController")
public class QuickTyreSearchComponentRedirectController
{
    @RequestMapping(value = "/quicktyresearch", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    protected String quickTyreSearchRedirect(QuickTyreSearchAttributes quickTyreSearchAttributes) throws UnsupportedEncodingException {
        String partUrl = "";
        for (Map.Entry<String, String> entry : quickTyreSearchAttributes.getRedirectAttributesMap().entrySet()) {
            if (!entry.getValue().isEmpty()){
                partUrl += ":" + entry.getKey() + ":" + entry.getValue();
            }
        }
        return "redirect:/Tyre-type/c/typeTyres?q=" + URLEncoder.encode(":price-asc" + partUrl, "UTF-8") + "&text=#";
    }
}
