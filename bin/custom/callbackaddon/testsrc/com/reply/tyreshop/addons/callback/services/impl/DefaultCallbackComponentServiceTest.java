package com.reply.tyreshop.addons.callback.services.impl;


import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

import com.reply.tyreshop.addons.callback.model.CallbackModel;

import com.reply.tyreshop.addons.callback.services.CallbackComponentService;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.model.ModelService;

import de.hybris.platform.testframework.HybrisJUnit4TransactionalTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;




@RunWith(MockitoJUnitRunner.class)
@UnitTest
public class DefaultCallbackComponentServiceTest
{
	@Mock
	ModelService modelService;

	@InjectMocks
    CallbackComponentService callbackComponentService = new DefaultCallbackComponentService();

	@Test
	public void testName()
	{
		String name = "Andrey";
		String result = callbackComponentService.testName(name);
		Assert.assertEquals(result, "Okay");
		name = "123";
		result = callbackComponentService.testName(name);
		Assert.assertEquals(result, "Bad");
		name = "Andrey123";
		result = callbackComponentService.testName(name);
		Assert.assertEquals(result, "Bad");
		name = "Андрей";
		result = callbackComponentService.testName(name);
		Assert.assertEquals(result, "Okay");
		name = "Андрей123";
		result = callbackComponentService.testName(name);
		Assert.assertEquals(result, "Bad");
	}

	@Test
	public void confirmCallback()
	{
		doReturn(new CallbackModel()).when(modelService).create(CallbackModel.class);
		doNothing().when(modelService).save(Matchers.any(CallbackModel.class));
		final String result = callbackComponentService.confirmCallback("Andrey", "3753364623123", "test");
		Assert.assertEquals(result, "Okay");
	}
}
