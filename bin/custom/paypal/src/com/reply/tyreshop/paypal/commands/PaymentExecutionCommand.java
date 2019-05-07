package com.reply.tyreshop.paypal.commands;

import com.paypal.api.payments.Payment;
import com.reply.tyreshop.paypal.dto.PaymentApprovalData;
import de.hybris.platform.payment.commands.Command;

public interface PaymentExecutionCommand extends Command<PaymentApprovalData, Payment> {
}
