package com.reply.tyreshop.storefront.forms;

import de.hybris.platform.acceleratorstorefrontcommons.forms.SopPaymentDetailsForm;

public class TyreshopPaymentDetailsForm extends SopPaymentDetailsForm {
    private String paymentMethod;

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
