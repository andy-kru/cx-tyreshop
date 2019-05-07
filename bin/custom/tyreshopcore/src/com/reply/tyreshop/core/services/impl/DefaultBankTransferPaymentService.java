package com.reply.tyreshop.core.services.impl;

import com.reply.tyreshop.core.services.BankTransferPaymentService;
import de.hybris.platform.servicelayer.keygenerator.impl.PersistentKeyGenerator;

public class DefaultBankTransferPaymentService implements BankTransferPaymentService {
    private PersistentKeyGenerator bankTransferCodeGenerator;

    @Override
    public String getBankTransferPaymentReference() {
        return bankTransferCodeGenerator.generate().toString();
    }

    public void setBankTransferCodeGenerator(PersistentKeyGenerator bankTransferCodeGenerator) {
        this.bankTransferCodeGenerator = bankTransferCodeGenerator;
    }
}
