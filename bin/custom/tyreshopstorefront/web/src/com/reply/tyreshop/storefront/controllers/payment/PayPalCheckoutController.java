package com.reply.tyreshop.storefront.controllers.payment;

import com.reply.tyreshop.facades.order.PaymentCheckoutFacade;
import com.reply.tyreshop.paypal.dto.PaymentApprovalData;
import com.reply.tyreshop.paypal.facades.PayPalFacade;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractCheckoutController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.core.model.user.AddressModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

@Controller("PayPalCheckoutController")
public class PayPalCheckoutController extends AbstractCheckoutController {

    private static final String PAYMENT_METHOD = "/checkout/multi/payment-method/add";

    @Resource(name = "payPalFacade")
    private PayPalFacade payPalFacade;

    @Resource(name = "paymentCheckoutFacade")
    private PaymentCheckoutFacade paymentCheckoutFacade;

    @RequestMapping(value = "/payPal/checkoutWithPayPal", method = RequestMethod.GET)
    public String checkout(){
        return REDIRECT_PREFIX + payPalFacade.createPayment();
    }

    @RequestMapping(value = "/payPal/returnPayment", method = RequestMethod.GET, params = {"paymentId", "PayerID"})
    public String onReturn(
        @RequestParam(value = "paymentId") String paymentId,
        @RequestParam(value = "PayerID") String payerId)
    {
        PaymentApprovalData paymentApprovalData = new PaymentApprovalData();
        paymentApprovalData.setPaymentId(paymentId);
        paymentApprovalData.setPayerId(payerId);
        final AddressModel billingAddress = getCheckoutFacade().getModelService().create(AddressModel.class);
        String redirectUrl = payPalFacade.executePayment(paymentApprovalData, billingAddress);
        PaymentModeModel paymentModeModel = getCheckoutFacade().getPaymentModeForCode(
                getCheckoutFacade().getCheckoutCart().getPaymentModeCode());
        getCheckoutFacade().changeCart(billingAddress,paymentModeModel,Boolean.TRUE);
        return REDIRECT_PREFIX + redirectUrl;
    }

    @RequestMapping(value = "/payPal/cancelPayment", method = RequestMethod.GET)
    public String onCancel(final RedirectAttributes redirectModel){
        GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER,
                "checkout.error.payment.paypal.cancelled");
        return REDIRECT_PREFIX + PAYMENT_METHOD;
    }

    @RequestMapping(value = "/payPal/executedPayment", method = RequestMethod.GET)
    public String placeOrder(final Model model){
        final OrderData orderData;
        try {
            orderData = getCheckoutFacade().placeOrder();
        } catch (final Exception e) {
            GlobalMessages.addErrorMessage(model, "checkout.placeOrder.failed");
            return REDIRECT_PREFIX + PAYMENT_METHOD;
        }
        return redirectToOrderConfirmationPage(orderData);
    }

    @Override
    protected PaymentCheckoutFacade getCheckoutFacade() {
        return paymentCheckoutFacade;
    }
}
