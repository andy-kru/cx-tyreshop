package com.reply.tyreshop.core.dto;

import java.util.HashMap;
import java.util.Map;

public class QuickTyreSearchAttributes {

    private Map<String, String> redirectAttributesMap = new HashMap<>();;

    public Map<String, String> getRedirectAttributesMap() {
        return redirectAttributesMap;
    }

    public void setRedirectAttributesMap(Map<String, String> redirectAttributesMap) {
        this.redirectAttributesMap = redirectAttributesMap;
    }

}
