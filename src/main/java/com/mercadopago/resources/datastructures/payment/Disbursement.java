package com.mercadopago.resources.datastructures.payment;

import com.mercadopago.core.MPBase;
import com.mercadopago.core.annotations.rest.PUT;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.AdvancedPayment;

import java.util.Date;

/**
 * JSON Example
 *  {
 *  *             "amount": 200.12,
 *  *                 "external_reference": "",
 *  *                 "collector_id": 328310637,
 *  *                 "application_fee": 20.0,
 *  *                 "money_release_days": 3,
 *  *                 "additional_info": {
 *  *             "items": [
 *  *             {
 *  *                 "id": "item-ID-1234",
 *  *                     "title": "Title of what you are paying for",
 *  *                     "picture_url": "https://www.mercadopago.com/logomp3.gif",
 *  *                     "description": "Item description",
 *  *                     "category_id": "art",
 *  *                     "quantity": 1,
 *  *                     "unit_price": 100
 *  *             }
 *  *                     ],
 *  *             "shipments": {
 *  *                 "receiver_address": {
 *  *                     "zip_code": "5700",
 *  *                             "street_name": "Street",
 *  *                             "street_number": 123,
 *  *                             "floor": 4,
 *  *                             "apartment": "C"
 *  *                 }
 *  *             }
 *  *         }
 *  *         }
 */

public class Disbursement extends MPBase{

    private String id = null;
    private transient String paymentId;
    private Float amount = null;
    private String externalReference = null;
    private Integer collectorId = null;
    private Float applicationFee = null;
    private Date moneyReleaseDate = null;
    private AdditionalInfo additionalInfo=null;

    public String getId() {
        return id;
    }

    public Disbursement setId(String id) {
        this.id = id;
        return this;
    }


    public String getPaymentId() {
        return paymentId;
    }

    public Disbursement setPaymentId(String paymentId) {
        this.paymentId = paymentId;
        return this;
    }

    public Float getAmount() {
        return amount;
    }

    public Disbursement setAmount(Float amount) {
        this.amount = amount;
        return this;
    }

    public String getExternalReference() {
        return externalReference;
    }

    public Disbursement setExternalReference(String externalReference) {
        this.externalReference = externalReference;
        return this;
    }

    public Integer getCollectorId() {
        return collectorId;
    }

    public Disbursement setCollectorId(Integer collectorId) {
        this.collectorId = collectorId;
        return this;
    }

    public Float getApplicationFee() {
        return applicationFee;
    }

    public Disbursement setApplicationFee(Float applicationFee) {
        this.applicationFee = applicationFee;
        return this;
    }

    public Date getMoneyReleaseDate() {
        return moneyReleaseDate;
    }

    public Disbursement setMoneyReleaseDate(Date moneyReleaseDate) {
        this.moneyReleaseDate = moneyReleaseDate;
        return this;
    }

    public AdditionalInfo getAdditionalInfo() {
        return additionalInfo;
    }

    public Disbursement setAdditionalInfo(AdditionalInfo additionalInfo) {
        this.additionalInfo = additionalInfo;
        return this;
    }


    @PUT(path = "/v1/advanced_payments/:payment_id/disbursements/:id/disburses")
    public Disbursement update() throws MPException {
        return super.processMethod("update", WITHOUT_CACHE);
    }
}
