package com.mercadopago.resources.datastructures.customer.card;

import com.mercadopago.core.MPBase;
import com.mercadopago.core.MPResourceArray;
import com.mercadopago.core.annotations.rest.GET;
import com.mercadopago.exceptions.MPException;

import java.util.List;

/**
 * Mercado Pago SDK
 * Card Payment Method class
 *
 * Created by Eduardo Paoletta on 12/15/16.
 */
public class PaymentMethod extends MPBase {

    private String id = null;
    private String name = null;
    private String paymentTypeId = null;
    private String thumbnail = null;
    private String secureThumbnail = null;
    private String status= null;
    private List<String> processingModes=null;

    public String getId() {
        return id;
    }

    public PaymentMethod setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PaymentMethod setName(String name) {
        this.name = name;
        return this;
    }

    public String getPaymentTypeId() {
        return paymentTypeId;
    }

    public PaymentMethod setPaymentTypeId(String paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
        return this;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public PaymentMethod setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    public String getSecureThumbnail() {
        return secureThumbnail;
    }

    public PaymentMethod setSecureThumbnail(String secureThumbnail) {
        this.secureThumbnail = secureThumbnail;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public PaymentMethod setStatus(String status) {
        this.status = status;
        return this;
    }

    public List<String> getProcessingModes() {
        return processingModes;
    }

    public PaymentMethod setProcessingModes(List<String> processingModes) {
        this.processingModes = processingModes;
        return this;
    }

    @GET(path = "/v1/payment_methods")
    public MPResourceArray all(Boolean useCache) throws MPException {
        return this.processMethodBulk("all", useCache);
    }
}
