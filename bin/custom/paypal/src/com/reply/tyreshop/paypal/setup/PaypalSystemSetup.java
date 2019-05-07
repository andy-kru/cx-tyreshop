/*
 * [y] hybris Platform
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company. All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.reply.tyreshop.paypal.setup;

import static com.reply.tyreshop.paypal.constants.PaypalConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import com.reply.tyreshop.paypal.constants.PaypalConstants;
import com.reply.tyreshop.paypal.service.PaypalService;


@SystemSetup(extension = PaypalConstants.EXTENSIONNAME)
public class PaypalSystemSetup
{
	private final PaypalService paypalService;

	public PaypalSystemSetup(final PaypalService paypalService)
	{
		this.paypalService = paypalService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		paypalService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return PaypalSystemSetup.class.getResourceAsStream("/paypal/sap-hybris-platform.png");
	}
}
