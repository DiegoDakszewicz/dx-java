package com.mercadopago.resources.advanced;

import com.mercadopago.core.MPBase;
import com.mercadopago.core.annotations.rest.POST;
import com.mercadopago.exceptions.MPException;

public class AdvancedPaymentRefund extends MPBase {

    private transient Float amount = null;

    private String id;

    private String status;

    public String getId() {
        return id;
    }

    public AdvancedPaymentRefund setId(String paymentId) {
        this.id = paymentId;
        return this;
    }

    public Float getAmount() {
        return amount;
    }

    public AdvancedPaymentRefund setAmount(Float amount) {
        this.amount = amount;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public AdvancedPaymentRefund setStatus(String status) {
        this.status = status;
        return this;
    }

    @POST(path = "/v1/advanced_payments/:id/refunds")
    public AdvancedPaymentRefund save() throws MPException {
        return super.processMethod("save", WITHOUT_CACHE);
    }

}
