package com.reply.tyreshop.core.services;

import com.reply.tyreshop.core.dto.ExchangeDTO;
import com.reply.tyreshop.core.exceptions.ExchangeRateRetrievalException;

public interface TyreshopExchangeRateService {
    ExchangeDTO[] getExchangeRates() throws ExchangeRateRetrievalException;
}
