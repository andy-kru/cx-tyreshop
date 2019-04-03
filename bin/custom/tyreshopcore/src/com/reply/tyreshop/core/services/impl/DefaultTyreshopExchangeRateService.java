package com.reply.tyreshop.core.services.impl;

import com.reply.tyreshop.core.dto.ExchangeDTO;
import com.reply.tyreshop.core.exceptions.ExchangeRateRetrievalException;
import com.reply.tyreshop.core.services.TyreshopExchangeRateService;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;

public class DefaultTyreshopExchangeRateService implements TyreshopExchangeRateService {

    private ConfigurationService configurationService;
    private RestOperations restOperations;

    @Override
    public ExchangeDTO[] getExchangeRates() throws ExchangeRateRetrievalException {
        String uri = configurationService.getConfiguration().getString("exchangerates.uri");
        ExchangeDTO[] exchangeDTOs;
        try {
            exchangeDTOs = restOperations.getForObject(uri, ExchangeDTO[].class);
        }
        catch (RestClientException ex){
            throw new ExchangeRateRetrievalException();
        }
        return exchangeDTOs;
    }

    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    public void setRestOperations(RestOperations restOperations) {
        this.restOperations = restOperations;
    }
}
