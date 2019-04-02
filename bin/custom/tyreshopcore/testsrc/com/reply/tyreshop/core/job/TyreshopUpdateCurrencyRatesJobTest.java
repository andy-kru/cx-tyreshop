package com.reply.tyreshop.core.job;

import com.reply.tyreshop.core.services.TyreshopBYNRatesService;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.i18n.daos.CurrencyDao;
import de.hybris.platform.servicelayer.model.ModelService;
import org.apache.commons.configuration.Configuration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestClientException;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@UnitTest
public class TyreshopUpdateCurrencyRatesJobTest{

    @Mock
    private ModelService modelService;

    @Mock
    private ConfigurationService configurationService;

    @Mock
    private Configuration configuration;

    @Mock
    private CurrencyDao currencyDao;

    @Mock
    private TyreshopBYNRatesService currenciesRatesService;

    @InjectMocks
    private TyreshopUpdateCurrencyRatesJob tyreshopUpdateCurrencyRatesJob = new TyreshopUpdateCurrencyRatesJob();

    private List<CurrencyModel> currencyModelList;

    @Test
    public void testPerformPositive(){
        double expectedRate = 2.1218;
        doReturn(currencyModelList).when(currencyDao).findCurrencies();
        doReturn(configuration).when(configurationService).getConfiguration();
        doReturn("BYN").when(configuration).getString("exchangerates.currency");
        doReturn(expectedRate).when(currenciesRatesService).getExchangeRate("USD");
        PerformResult performResult = tyreshopUpdateCurrencyRatesJob.perform(new CronJobModel());
        verify(modelService).save(currencyModelList.get(1));
        if(!(performResult.getResult().equals(CronJobResult.SUCCESS) &&
                performResult.getStatus().equals(CronJobStatus.FINISHED) &&
                currencyModelList.get(1).getConversion().equals(expectedRate))){
            fail();
        }
    }

    @Test
    public void testPerformNegative(){
        double expectedRate = currencyModelList.get(1).getConversion();
        doReturn(currencyModelList).when(currencyDao).findCurrencies();
        doReturn(configuration).when(configurationService).getConfiguration();
        doReturn("BYN").when(configuration).getString("exchangerates.currency");
        doThrow(new RestClientException("")).when(currenciesRatesService).getExchangeRate("USD");
        PerformResult performResult = tyreshopUpdateCurrencyRatesJob.perform(new CronJobModel());
        if(!(performResult.getResult().equals(CronJobResult.FAILURE) &&
                performResult.getStatus().equals(CronJobStatus.FINISHED) &&
                currencyModelList.get(1).getConversion().equals(expectedRate))){
            fail();
        }
    }

    @Before
    public void setUp() {
        currencyModelList = new ArrayList<>();

        CurrencyModel bynCurrency = new CurrencyModel();
        bynCurrency.setIsocode("BYN");
        bynCurrency.setBase(true);
        bynCurrency.setDigits(2);
        bynCurrency.setConversion(0.0);
        currencyModelList.add(bynCurrency);

        CurrencyModel usdCurrency = new CurrencyModel();
        usdCurrency.setIsocode("USD");
        usdCurrency.setBase(false);
        usdCurrency.setDigits(2);
        usdCurrency.setConversion(1.0);
        currencyModelList.add(usdCurrency);
    }
}
