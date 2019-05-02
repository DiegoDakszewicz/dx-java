package com.mercadopago.resources.advanced;

import com.mercadopago.core.MPBase;
import com.mercadopago.core.annotations.rest.POST;
import com.mercadopago.exceptions.MPException;

public class AdvancedPaymentRefund extends MPBase {

    private transient String paymentId = null;

    private  transient Float amount = null;

    public String getPaymentId() {
        return paymentId;
    }

    public AdvancedPaymentRefund setPaymentId(String paymentId) {
        this.paymentId = paymentId;
        return this;
    }

    public Float getAmount() {
        return amount;
    }

    public AdvancedPaymentRefund setAmount(Float amount) {
        this.amount = amount;
        return this;
    }


    @POST(path="v1/advanced_payments/:payment_id/refunds")
    public AdvancedPaymentRefund save() throws MPException {
        return super.processMethod("save", WITHOUT_CACHE);
    }

}
