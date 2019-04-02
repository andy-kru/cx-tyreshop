package com.reply.tyreshop.core.job;

import com.reply.tyreshop.core.services.TyreshopBYNRatesService;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.i18n.daos.CurrencyDao;
import de.hybris.platform.servicelayer.model.ModelService;
import org.apache.log4j.Logger;
import org.springframework.web.client.RestClientException;

import javax.annotation.Resource;
import java.util.List;

public class TyreshopUpdateCurrencyRatesJob extends AbstractJobPerformable<CronJobModel> {

    private static final Logger LOG = Logger.getLogger(TyreshopUpdateCurrencyRatesJob.class);

    @Resource
    ModelService modelService;

    @Resource
    ConfigurationService configurationService;

    private CurrencyDao currencyDao;
    private TyreshopBYNRatesService currenciesRatesService;

    @Override
    public PerformResult perform(CronJobModel cronJobModel) {
        boolean success = true;
        List<CurrencyModel> currencies = currencyDao.findCurrencies();
        for (CurrencyModel currency : currencies){
            if(!currency.getIsocode().equals(
                    configurationService.getConfiguration().getString("exchangerates.currency"))){
                try{
                    currency.setConversion(currenciesRatesService.getExchangeRate(currency.getIsocode()));
                    modelService.save(currency);
                }
                catch (RestClientException ex){
                    success = false;
                    LOG.error("Failed getting exchange rate for " + currency.getIsocode(), ex);
                }
            }
        }
        return new PerformResult(success ? CronJobResult.SUCCESS : CronJobResult.FAILURE, CronJobStatus.FINISHED);
    }

    public void setCurrencyDao(CurrencyDao currencyDao) {
        this.currencyDao = currencyDao;
    }

    public void setCurrenciesRatesService(TyreshopBYNRatesService currenciesRatesService) {
        this.currenciesRatesService = currenciesRatesService;
    }

    @Override
    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }
}
