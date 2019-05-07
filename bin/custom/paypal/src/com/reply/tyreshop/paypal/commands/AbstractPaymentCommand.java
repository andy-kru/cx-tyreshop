package com.reply.tyreshop.paypal.commands;

import de.hybris.platform.servicelayer.config.ConfigurationService;
import org.springframework.beans.factory.annotation.Required;

public class AbstractPaymentCommand {

    private ConfigurationService configurationService;

    protected String getClientId() {
        return configurationService.getConfiguration().getString("paypal.client.id");
    }

    protected String getClientSecret() {
        return configurationService.getConfiguration().getString("paypal.client.secret");
    }

    public ConfigurationService getConfigurationService() {
        return configurationService;
    }

    @Required
    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }
}
