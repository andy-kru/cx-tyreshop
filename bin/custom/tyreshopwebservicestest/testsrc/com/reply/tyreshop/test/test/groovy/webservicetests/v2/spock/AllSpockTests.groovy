/*
 * [y] hybris Platform
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.reply.tyreshop.test.test.groovy.webservicetests.v2.spock

import de.hybris.bootstrap.annotations.IntegrationTest
import com.reply.tyreshop.test.setup.TestSetupUtils
import com.reply.tyreshop.test.test.groovy.webservicetests.v2.spock.access.AccessRightsTest
import com.reply.tyreshop.test.test.groovy.webservicetests.v2.spock.access.OAuth2Test
import com.reply.tyreshop.test.test.groovy.webservicetests.v2.spock.address.AddressTest
import com.reply.tyreshop.test.test.groovy.webservicetests.v2.spock.carts.CartDeliveryTest
import com.reply.tyreshop.test.test.groovy.webservicetests.v2.spock.carts.CartEntriesTest
import com.reply.tyreshop.test.test.groovy.webservicetests.v2.spock.carts.CartMergeTest
import com.reply.tyreshop.test.test.groovy.webservicetests.v2.spock.carts.CartPromotionsTest
import com.reply.tyreshop.test.test.groovy.webservicetests.v2.spock.carts.CartResourceTest
import com.reply.tyreshop.test.test.groovy.webservicetests.v2.spock.carts.CartVouchersTest
import com.reply.tyreshop.test.test.groovy.webservicetests.v2.spock.carts.GuestsTest
import com.reply.tyreshop.test.test.groovy.webservicetests.v2.spock.carts.OrderPlacementTest
import com.reply.tyreshop.test.test.groovy.webservicetests.v2.spock.carts.SavedCartFullScenarioTest
import com.reply.tyreshop.test.test.groovy.webservicetests.v2.spock.carts.SavedCartTest
import com.reply.tyreshop.test.test.groovy.webservicetests.v2.spock.catalogs.CatalogsResourceTest
import com.reply.tyreshop.test.test.groovy.webservicetests.v2.spock.customergroups.CustomerGroupsTest
import com.reply.tyreshop.test.test.groovy.webservicetests.v2.spock.errors.ErrorTest
import com.reply.tyreshop.test.test.groovy.webservicetests.v2.spock.export.ExportTest
import com.reply.tyreshop.test.test.groovy.webservicetests.v2.spock.filters.CartMatchingFilterTest
import com.reply.tyreshop.test.test.groovy.webservicetests.v2.spock.filters.UserMatchingFilterTest
import com.reply.tyreshop.test.test.groovy.webservicetests.v2.spock.flows.AddressBookFlow
import com.reply.tyreshop.test.test.groovy.webservicetests.v2.spock.flows.CartFlowTest
import com.reply.tyreshop.test.test.groovy.webservicetests.v2.spock.general.StateTest
import com.reply.tyreshop.test.test.groovy.webservicetests.v2.spock.misc.CardTypesTest
import com.reply.tyreshop.test.test.groovy.webservicetests.v2.spock.misc.CurrenciesTest
import com.reply.tyreshop.test.test.groovy.webservicetests.v2.spock.misc.DeliveryCountriesTest
import com.reply.tyreshop.test.test.groovy.webservicetests.v2.spock.misc.LanguagesTest
import com.reply.tyreshop.test.test.groovy.webservicetests.v2.spock.misc.LocalizationRequestTest
import com.reply.tyreshop.test.test.groovy.webservicetests.v2.spock.misc.TitlesTest
import com.reply.tyreshop.test.test.groovy.webservicetests.v2.spock.orders.OrdersTest
import com.reply.tyreshop.test.test.groovy.webservicetests.v2.spock.paymentdetails.PaymentsTest
import com.reply.tyreshop.test.test.groovy.webservicetests.v2.spock.products.ProductResourceTest
import com.reply.tyreshop.test.test.groovy.webservicetests.v2.spock.products.ProductsStockTest
import com.reply.tyreshop.test.test.groovy.webservicetests.v2.spock.promotions.PromotionsTest
import com.reply.tyreshop.test.test.groovy.webservicetests.v2.spock.stores.StoresTest
import com.reply.tyreshop.test.test.groovy.webservicetests.v2.spock.users.UserAccountTest
import com.reply.tyreshop.test.test.groovy.webservicetests.v2.spock.users.UserOrdersTest
import com.reply.tyreshop.test.test.groovy.webservicetests.v2.spock.users.UsersResourceTest
import com.reply.tyreshop.test.test.groovy.webservicetests.v2.spock.general.HeaderTests
import com.reply.tyreshop.test.test.groovy.webservicetests.v2.spock.consents.ConsentResourcesTest
import com.reply.tyreshop.test.test.groovy.webservicetests.v2.spock.countries.CountryResourcesTest

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.slf4j.LoggerFactory


@RunWith(Suite.class)
@Suite.SuiteClasses([
	AccessRightsTest, OAuth2Test, StateTest, CartDeliveryTest, CartMergeTest, CartEntriesTest, CartPromotionsTest,
	CartResourceTest, CartVouchersTest, GuestsTest, OrderPlacementTest, CatalogsResourceTest, CustomerGroupsTest, ErrorTest, ExportTest,
	AddressBookFlow, CartFlowTest, CardTypesTest, CurrenciesTest, DeliveryCountriesTest, LanguagesTest, LocalizationRequestTest, TitlesTest,
	OrdersTest, ProductResourceTest, ProductsStockTest, PromotionsTest, SavedCartTest ,SavedCartFullScenarioTest, StoresTest, UserAccountTest,
	AddressTest, UserOrdersTest, PaymentsTest, UsersResourceTest, CartMatchingFilterTest, UserMatchingFilterTest, HeaderTests, 
	ConsentResourcesTest, CountryResourcesTest])
@IntegrationTest
class AllSpockTests {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(AllSpockTests.class)

	@BeforeClass
	public static void setUpClass() {
		TestSetupUtils.loadData();
		TestSetupUtils.startServer();
	}

	@AfterClass
	public static void tearDown(){
		TestSetupUtils.stopServer();
		TestSetupUtils.cleanData();
	}

	@Test
	public static void testing() {
		//dummy test class
	}
}
