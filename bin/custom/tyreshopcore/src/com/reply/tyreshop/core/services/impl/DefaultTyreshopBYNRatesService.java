package com.reply.tyreshop.core.services.impl;

import com.reply.tyreshop.core.dto.ExchangeDto;
import com.reply.tyreshop.core.services.TyreshopBYNRatesService;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

public class DefaultTyreshopBYNRatesService implements TyreshopBYNRatesService {

    @Resource
    ConfigurationService configurationService;

    private String uri;

    public void init(){
        uri = configurationService.getConfiguration().getString("exchangerates.uri");
    }

    @Override
    public double getExchangeRate(String currencyISO) throws RestClientException {
        RestTemplate restTemplate = new RestTemplate();
        ExchangeDto exchangeDto = restTemplate.getForObject(uri, ExchangeDto.class, currencyISO);
        return exchangeDto.getCur_OfficialRate();
    }
}
