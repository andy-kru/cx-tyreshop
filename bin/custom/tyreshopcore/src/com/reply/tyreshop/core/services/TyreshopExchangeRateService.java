package com.reply.tyreshop.core.services;

import com.reply.tyreshop.core.exceptions.ExchangeRateRetrievalException;

import java.util.Map;

public interface TyreshopExchangeRateService {
    Map<String, Double> getExchangeRates() throws ExchangeRateRetrievalException;
}
