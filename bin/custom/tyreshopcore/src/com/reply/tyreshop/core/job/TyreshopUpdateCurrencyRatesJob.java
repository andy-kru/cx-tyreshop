package com.reply.tyreshop.core.job;

import com.reply.tyreshop.core.dto.ExchangeDTO;
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

import java.util.ArrayList;
import java.util.List;

public class TyreshopUpdateCurrencyRatesJob extends AbstractJobPerformable<CronJobModel> {

    private static final Logger LOG = Logger.getLogger(TyreshopUpdateCurrencyRatesJob.class);

    private CurrencyDao currencyDao;
    private TyreshopExchangeRateService exchangeRateService;

    @Override
    public PerformResult perform(CronJobModel cronJobModel) {
        ExchangeDTO[] exchangeDTOs;
        try {
            exchangeDTOs = exchangeRateService.getExchangeRates();
        }
        catch (ExchangeRateRetrievalException ex){
            LOG.error("Error receiving exchange rates!");
            return new PerformResult(CronJobResult.FAILURE, CronJobStatus.FINISHED);
        }
        List<CurrencyModel> allCurrencies = currencyDao.findCurrencies();
        List<CurrencyModel> currencies = new ArrayList<>(allCurrencies);
        List<CurrencyModel> baseCurrencies = currencyDao.findBaseCurrencies();
        currencies.removeAll(baseCurrencies);
        List<CurrencyModel> updated = new ArrayList<>();
        for(ExchangeDTO exchangeDto : exchangeDTOs){
            for(CurrencyModel currencyModel : currencies){
                if(currencyModel.getIsocode().equals(exchangeDto.getCurAbbreviation())){
                    currencyModel.setConversion(exchangeDto.getCurOfficialRate());
                    updated.add(currencyModel);
                    currencies.remove(currencyModel);
                    break;
                }
            }
            if(currencies.isEmpty()){
                break;
            }
        }
        modelService.saveAll(updated);
        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }

    public void setCurrencyDao(CurrencyDao currencyDao) {
        this.currencyDao = currencyDao;
    }

    public void setExchangeRateService(TyreshopExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }
}
