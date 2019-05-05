package com.reply.tyreshop.facades.order.impl;

import com.reply.tyreshop.facades.order.PaymentCheckoutFacade;
import de.hybris.platform.acceleratorfacades.order.impl.DefaultAcceleratorCheckoutFacade;
import de.hybris.platform.acceleratorservices.payment.data.PaymentInfoData;
import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.order.PaymentModeService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;

import javax.annotation.Resource;
import java.util.UUID;

public class DefaultPaymentCheckoutFacade extends DefaultAcceleratorCheckoutFacade implements PaymentCheckoutFacade {

    @Resource(name = "paymentModeService")
    private PaymentModeService paymentModeService;

    @Resource(name = "cartService")
    private CartService cartService;

    @Resource(name = "defaultPaymentInfoDConverter")
    public Converter<PaymentInfoModel, PaymentInfoData> paymentInfoDataConverter;

    @Resource(name = "defaultTyreshopOrderConverter")
    public Converter<OrderModel, OrderData> orderDataConverter;

    @Override
    public PaymentModeModel getPaymentModeForCode(String code) {
        return paymentModeService.getPaymentModeForCode(code);
    }

    @Override
    public ModelService getModelService() {
        return super.getModelService();
    }

    @Override
    public CustomerModel getCurrentUserForCheckout() {
        return super.getCurrentUserForCheckout();
    }

    @Override
    public CartFacade getCartFacade() {
        return super.getCartFacade();
    }

    @Override
    public void changeCart(AddressModel billingAddress, PaymentModeModel paymentModeModel, Boolean calculated) {
        CartModel cartModel = cartService.getSessionCart();
        cartModel.setPaymentMode(paymentModeModel);
        final CustomerModel currentUserForCheckout = getCurrentUserForCheckout();
        PaymentInfoModel paymentInfoModel = getModelService().create(PaymentInfoModel.class);
        paymentInfoModel.setUser(currentUserForCheckout);
        paymentInfoModel.setCode(currentUserForCheckout.getUid() + "_" + UUID.randomUUID());
        paymentInfoModel.setBillingAddress(billingAddress);
        getModelService().save(paymentInfoModel);
        cartModel.setPaymentInfo(paymentInfoModel);
        cartModel.setCalculated(calculated);
        getModelService().save(cartModel);
    }

    @Override
    public void changePaymentModeForCart(PaymentModeModel paymentModeModel) {
        CartModel cartModel = cartService.getSessionCart();
        cartModel.setPaymentMode(paymentModeModel);
        getModelService().save(cartModel);
    }

    @Override
    public CartData getCheckoutCart() {
        final CartData cartData = getCartFacade().getSessionCart();
        if (cartData != null) {
            cartData.setDeliveryAddress(getDeliveryAddress());
            cartData.setDeliveryMode(getDeliveryMode());
            cartData.setCommonPaymentInfo(getPaymentInfo());
            cartData.setPaymentModeCode(getPaymentModeCode());
        }
        return cartData;
    }

    private String getPaymentModeCode() {
        final CartModel cart = getCart();
        final PaymentModeModel paymentModeModel = cart.getPaymentMode();
        if (cart != null && paymentModeModel != null) {
            return cart.getPaymentMode().getCode();
        }
        return null;
    }

    protected PaymentInfoData getPaymentInfo() {
        final CartModel cart = getCart();
        if (cart != null) {
            final PaymentInfoModel paymentInfo = cart.getPaymentInfo();
            return paymentInfoDataConverter.convert(paymentInfo, new PaymentInfoData());
        }
        return null;
    }

    @Override
    public boolean hasNoPaymentInfo() {
        final CartData cartData = getCheckoutCart();
        return cartData == null || cartData.getCommonPaymentInfo() == null;
    }

    @Override
    protected Converter<OrderModel, OrderData> getOrderConverter() {
        return orderDataConverter;
    }

}