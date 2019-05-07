package com.reply.tyreshop.storefront.controllers.payment;

import com.reply.tyreshop.paypal.dto.PaymentApprovalData;
import com.reply.tyreshop.paypal.facades.PayPalFacade;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.AbstractController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

@Controller("PayPalCheckoutController")
@RequestMapping("/checkout")
public class PayPalCheckoutController extends AbstractController {

    private static final String PAYMENT_METHOD = "/checkout/multi/payment-method/add";

    @Resource(name = "payPalFacade")
    private PayPalFacade payPalFacade;

    @RequestMapping(value = "/checkoutWithPayPal", method = RequestMethod.GET)
    public String checkout(){
        return REDIRECT_PREFIX + payPalFacade.createPayment();
    }

    @RequestMapping(value = "/returnPayPal", method = RequestMethod.GET, params = {"paymentId", "PayerID"})
    public String onReturn(
        @RequestParam(value = "paymentId") String paymentId,
        @RequestParam(value = "PayerID") String payerId)
    {
        PaymentApprovalData paymentApprovalData = new PaymentApprovalData();
        paymentApprovalData.setPaymentId(paymentId);
        paymentApprovalData.setPayerId(payerId);
        return REDIRECT_PREFIX + payPalFacade.executePayment(paymentApprovalData);
    }

    @RequestMapping(value = "/cancelPayPal", method = RequestMethod.GET)
    public String onCancel(final RedirectAttributes redirectModel){
        GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER,
                "checkout.error.payment.paypal.cancelled");
        return REDIRECT_PREFIX + PAYMENT_METHOD;
    }
}
