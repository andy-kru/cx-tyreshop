package com.reply.tyreshop.core.job;

import com.reply.tyreshop.core.dto.ExchangeDTO;
import com.reply.tyreshop.core.exceptions.ExchangeRateRetrievalException;
import com.reply.tyreshop.core.services.TyreshopExchangeRateService;
import com.reply.tyreshop.core.services.impl.DefaultTyreshopExchangeRateService;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@UnitTest
public class DefaultTyreshopExchangeRateServiceTest {

    private static final Logger LOG = Logger.getLogger(TyreshopUpdateCurrencyRatesJobTest.class);

    @Mock
    private ConfigurationService configurationService;

    @Mock
    private Configuration configuration;

    @Mock
    private RestOperations restOperations;

    @InjectMocks
    private TyreshopExchangeRateService tyreshopExchangeRateService = new DefaultTyreshopExchangeRateService();

    private Collection<ExchangeDTO> exchangeDTOCollection;
    private ResponseEntity<Collection<ExchangeDTO>> responseEntity;

    @Test
    public void testGetExchangeRatesPositive() throws ExchangeRateRetrievalException {
        setUp();
        String uri = "";
        doReturn(configuration).when(configurationService).getConfiguration();
        doReturn(uri).when(configuration).getString("exchangerates.uri");
        doReturn(responseEntity).when(restOperations).exchange(uri, HttpMethod.GET, null,
                new ParameterizedTypeReference<Collection<ExchangeDTO>>(){});
        Map<String, Double> result = tyreshopExchangeRateService.getExchangeRates();
        Assert.assertEquals(result.size(), exchangeDTOCollection.size());
        for(ExchangeDTO exchangeDTO : exchangeDTOCollection){
            Assert.assertEquals(exchangeDTO.getCurOfficialRate(), result.get(exchangeDTO.getCurAbbreviation()));
        }
    }

    @Test
    public void testGetExchangeRatesNegative() {
        String uri = "";
        doReturn(configuration).when(configurationService).getConfiguration();
        doReturn(uri).when(configuration).getString("exchangerates.uri");
        doThrow(new RestClientException("")).when(restOperations).exchange(uri, HttpMethod.GET, null,
                new ParameterizedTypeReference<Collection<ExchangeDTO>>(){});
        try{
            tyreshopExchangeRateService.getExchangeRates();
            fail();
        }
        catch (ExchangeRateRetrievalException ex){
            LOG.info("ExchangeRateRetrievalException", ex);
        }
    }

    private void setUp() {
        exchangeDTOCollection = new ArrayList<>();

        ExchangeDTO usdDTO = new ExchangeDTO();
        usdDTO.setCurAbbreviation("USD");
        usdDTO.setCurOfficialRate(2.1218);
        exchangeDTOCollection.add(usdDTO);

        ExchangeDTO eurDTO = new ExchangeDTO();
        eurDTO.setCurAbbreviation("EUR");
        eurDTO.setCurOfficialRate(2.6182);
        exchangeDTOCollection.add(eurDTO);

        responseEntity = new ResponseEntity<>(exchangeDTOCollection, HttpStatus.OK);
    }
}
