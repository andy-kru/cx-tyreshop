package com.reply.tyreshop.paypal.commands;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payment;
import de.hybris.platform.payment.commands.Command;

public interface PaymentCreationCommand extends Command<Amount, Payment> {
}
