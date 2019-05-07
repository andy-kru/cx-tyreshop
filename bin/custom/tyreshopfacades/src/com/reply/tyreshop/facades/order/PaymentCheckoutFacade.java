package com.reply.tyreshop.facades.order;

import de.hybris.platform.acceleratorfacades.order.AcceleratorCheckoutFacade;
import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.model.ModelService;

public interface PaymentCheckoutFacade extends AcceleratorCheckoutFacade {
    PaymentModeModel getPaymentModeForCode(String code);

    ModelService getModelService();

    CustomerModel getCurrentUserForCheckout();

    CartFacade getCartFacade();

    void changeCart(AddressModel billingAddress, PaymentModeModel paymentModeModel,
                    Boolean calculated);

    void changePaymentModeForCart(PaymentModeModel paymentModeModel);


}
