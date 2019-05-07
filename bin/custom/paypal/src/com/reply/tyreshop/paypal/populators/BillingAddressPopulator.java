package com.reply.tyreshop.paypal.populators;

import com.paypal.api.payments.Address;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.i18n.daos.CountryDao;
import org.springframework.beans.factory.annotation.Required;

import java.util.Collection;

public class BillingAddressPopulator implements Populator<Address, AddressModel> {

    private CountryDao countryDao;

    @Override
    public void populate(Address source, AddressModel target) throws ConversionException {
        addCountry(source, target);
        target.setLine1(source.getLine1());
        target.setLine2(source.getLine2());
        target.setPostalcode(source.getPostalCode());
        target.setPhone1(source.getPhone());
    }

    private void addCountry(Address source, AddressModel target){
        Collection<CountryModel> countryModels = getCountryDao().findCountriesByCode(source.getCountryCode());
        if(!countryModels.isEmpty()){
            target.setCountry(countryModels.iterator().next());
        }
    }

    private CountryDao getCountryDao() {
        return countryDao;
    }

    @Required
    public void setCountryDao(CountryDao countryDao) {
        this.countryDao = countryDao;
    }
}
