package com.reply.tyreshop.core.services.impl;

import com.reply.tyreshop.core.dto.ExchangeDTO;
import com.reply.tyreshop.core.exceptions.ExchangeRateRetrievalException;
import com.reply.tyreshop.core.services.TyreshopExchangeRateService;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class DefaultTyreshopExchangeRateService implements TyreshopExchangeRateService {

    private ConfigurationService configurationService;
    private RestOperations restOperations;

    @Override
    public Map<String, Double> getExchangeRates() throws ExchangeRateRetrievalException {
        String uri = getConfigurationService().getConfiguration().getString("exchangerates.uri");
        ResponseEntity<Collection<ExchangeDTO>> response;
        try {
            response = getRestOperations().exchange(uri, HttpMethod.GET, null,
                    new ParameterizedTypeReference<Collection<ExchangeDTO>>(){});
        }
        catch (RestClientException ex){
            throw new ExchangeRateRetrievalException();
        }
        Collection<ExchangeDTO> exchangeDTOCollection = response.getBody();
        Map<String, Double> exchangeRates = exchangeDTOCollection.stream().collect(
                Collectors.toMap(ExchangeDTO::getCurAbbreviation, ExchangeDTO::getCurOfficialRate));
        return exchangeRates;
    }

    @Required
    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @Required
    public void setRestOperations(RestOperations restOperations) {
        this.restOperations = restOperations;
    }

    public ConfigurationService getConfigurationService() {
        return configurationService;
    }

    public RestOperations getRestOperations() {
        return restOperations;
    }
}
