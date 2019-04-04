package com.reply.tyreshop.core.job;

import com.reply.tyreshop.core.exceptions.ExchangeRateRetrievalException;
import com.reply.tyreshop.core.services.TyreshopExchangeRateService;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.i18n.daos.CurrencyDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TyreshopUpdateCurrencyRatesJob extends AbstractJobPerformable<CronJobModel> {

    private static final Logger LOG = Logger.getLogger(TyreshopUpdateCurrencyRatesJob.class);

    private CurrencyDao currencyDao;
    private TyreshopExchangeRateService exchangeRateService;

    @Override
    public PerformResult perform(CronJobModel cronJobModel) {
        Map<String, Double> exchangeRates;
        try {
            exchangeRates = getExchangeRateService().getExchangeRates();
        }
        catch (ExchangeRateRetrievalException ex){
            LOG.error("Error receiving exchange rates!");
            return new PerformResult(CronJobResult.FAILURE, CronJobStatus.ABORTED);
        }
        List<CurrencyModel> currencies = getCurrencyDao().findCurrencies();
        List<CurrencyModel> updated = new ArrayList<>();
        for(CurrencyModel currencyModel : currencies){
            if(exchangeRates.containsKey(currencyModel.getIsocode())){
                currencyModel.setConversion(exchangeRates.get(currencyModel.getIsocode()));
                updated.add(currencyModel);
            }
        }
        modelService.saveAll(updated);
        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }

    @Required
    public void setCurrencyDao(CurrencyDao currencyDao) {
        this.currencyDao = currencyDao;
    }

    @Required
    public void setExchangeRateService(TyreshopExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    public CurrencyDao getCurrencyDao() {
        return currencyDao;
    }

    public TyreshopExchangeRateService getExchangeRateService() {
        return exchangeRateService;
    }
}
