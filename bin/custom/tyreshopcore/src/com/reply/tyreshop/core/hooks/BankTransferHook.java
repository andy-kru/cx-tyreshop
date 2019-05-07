package com.reply.tyreshop.core.hooks;

public interface BankTransferHook {
    void beforePlaceOrder();
    void afterPlaceOrder();
}
