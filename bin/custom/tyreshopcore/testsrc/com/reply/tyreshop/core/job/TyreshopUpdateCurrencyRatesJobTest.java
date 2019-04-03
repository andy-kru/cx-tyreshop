package com.reply.tyreshop.core.job;

import com.reply.tyreshop.core.dto.ExchangeDTO;
import com.reply.tyreshop.core.exceptions.ExchangeRateRetrievalException;
import com.reply.tyreshop.core.services.TyreshopExchangeRateService;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.i18n.daos.CurrencyDao;
import de.hybris.platform.servicelayer.model.ModelService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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
    private CurrencyDao currencyDao;

    @Mock
    private TyreshopExchangeRateService exchangeRateService;

    @InjectMocks
    private TyreshopUpdateCurrencyRatesJob tyreshopUpdateCurrencyRatesJob = new TyreshopUpdateCurrencyRatesJob();

    private List<CurrencyModel> currencyModelList;
    private List<CurrencyModel> baseCurrencyModelList;
    private ExchangeDTO[] exchangeDTOs;

    @Test
    public void testPerformPositive() throws ExchangeRateRetrievalException {
        double expectedConversion = exchangeDTOs[0].getCurOfficialRate();
        CurrencyModel usdCurrencyModel = currencyModelList.get(1);
        doReturn(exchangeDTOs).when(exchangeRateService).getExchangeRates();
        doReturn(currencyModelList).when(currencyDao).findCurrencies();
        doReturn(baseCurrencyModelList).when(currencyDao).findBaseCurrencies();
        PerformResult performResult = tyreshopUpdateCurrencyRatesJob.perform(new CronJobModel());
        verify(modelService).saveAll(anyListOf(ExchangeDTO.class));
        if(!(performResult.getResult().equals(CronJobResult.SUCCESS) &&
                performResult.getStatus().equals(CronJobStatus.FINISHED) &&
                usdCurrencyModel.getConversion().equals(expectedConversion))){
            fail();
        }
    }

    @Test
    public void testPerformNegative() throws ExchangeRateRetrievalException {
        CurrencyModel usdCurrencyModel = currencyModelList.get(1);
        double defaultConversion = usdCurrencyModel.getConversion();
        doThrow(new ExchangeRateRetrievalException()).when(exchangeRateService).getExchangeRates();
        PerformResult performResult = tyreshopUpdateCurrencyRatesJob.perform(new CronJobModel());
        verifyZeroInteractions(currencyDao);
        verifyZeroInteractions(modelService);
        if(!(performResult.getResult().equals(CronJobResult.FAILURE) &&
                performResult.getStatus().equals(CronJobStatus.FINISHED) &&
                usdCurrencyModel.getConversion().equals(defaultConversion))){
            fail();
        }
    }

    @Before
    public void setUp() {
        currencyModelList = new ArrayList<>();
        baseCurrencyModelList = new ArrayList<>();

        CurrencyModel bynCurrency = new CurrencyModel();
        bynCurrency.setIsocode("BYN");
        bynCurrency.setBase(true);
        bynCurrency.setDigits(2);
        bynCurrency.setConversion(0.0);
        currencyModelList.add(bynCurrency);
        baseCurrencyModelList.add(bynCurrency);

        CurrencyModel usdCurrency = new CurrencyModel();
        usdCurrency.setIsocode("USD");
        usdCurrency.setBase(false);
        usdCurrency.setDigits(2);
        usdCurrency.setConversion(1.0);
        currencyModelList.add(usdCurrency);

        exchangeDTOs = new ExchangeDTO[2];

        ExchangeDTO usdDTO = new ExchangeDTO();
        usdDTO.setCurAbbreviation("USD");
        usdDTO.setCurOfficialRate(2.1218);
        exchangeDTOs[0] = usdDTO;

        ExchangeDTO eurDTO = new ExchangeDTO();
        eurDTO.setCurAbbreviation("EUR");
        eurDTO.setCurOfficialRate(2.6182);
        exchangeDTOs[1] = eurDTO;
    }
}
