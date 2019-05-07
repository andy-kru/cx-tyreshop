package com.reply.tyreshop.facades.order.impl;

import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commercefacades.order.impl.DefaultOrderFacade;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

public class TyreshopOrderFacade extends DefaultOrderFacade {
    private static final String ORDER_NOT_FOUND_FOR_USER_AND_BASE_STORE = "Order with guid %s not found for current user in current BaseStore";
    public Converter<OrderModel, OrderData> orderDataConverter;

    @Override
    protected Converter<OrderModel, OrderData> getOrderConverter() {
        return orderDataConverter;
    }

    public void setOrderDataConverter(Converter<OrderModel, OrderData> orderDataConverter) {
        this.orderDataConverter = orderDataConverter;
    }


}
