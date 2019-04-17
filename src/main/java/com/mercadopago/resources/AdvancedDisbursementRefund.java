package com.mercadopago.resources;

import com.mercadopago.core.MPBase;
import com.mercadopago.core.MPResourceArray;
import com.mercadopago.core.annotations.rest.GET;
import com.mercadopago.core.annotations.rest.POST;
import com.mercadopago.exceptions.MPException;

public class AdvancedDisbursementRefund extends MPBase {

    private transient String paymentId = null;

    private  transient Float amount = null;

    private transient String disbursementId = null;

    public String getPaymentId() {
        return paymentId;
    }

    public AdvancedDisbursementRefund setPaymentId(String paymentId) {
        this.paymentId = paymentId;
        return this;
    }

    public Float getAmount() {
        return amount;
    }

    public AdvancedDisbursementRefund setAmount(Float amount) {
        this.amount = amount;
        return this;
    }

    public String getDisbursementId() {
        return disbursementId;
    }

    public AdvancedDisbursementRefund setDisbursementId(String disbursement) {
        this.disbursementId = disbursement;
        return this;
    }

    @POST(path="/v1/advanced_payments/:payment_id/disbursements/:disbursement_id/refunds")
    public AdvancedDisbursementRefund save() throws MPException {
        return super.processMethod("save", WITHOUT_CACHE);
    }

}
