package com.mercadopago.resources;

import com.google.gson.JsonObject;
import com.mercadopago.core.MPBase;
import com.mercadopago.core.MPResourceArray;
import com.mercadopago.core.annotations.idempotent.Idempotent;
import com.mercadopago.core.annotations.rest.GET;
import com.mercadopago.core.annotations.rest.POST;
import com.mercadopago.core.annotations.rest.PUT;
import com.mercadopago.core.annotations.validation.Numeric;
import com.mercadopago.core.annotations.validation.Size;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.datastructures.customer.card.PaymentMethod;
import com.mercadopago.resources.datastructures.payment.*;

import java.util.*;

/**
 * {
 *   "id": 20458724,
 *   "status": "approved",
 *   "payments": [
 *     {
 *       "id": 3870106238,
 *       "payment_type_id": "credit_card",
 *       "payment_method_id": "visa",
 *       "token": "f461ab1341a7e308c906aa767bce1a00",
 *       "transaction_amount": 500.12,
 *       "installments": 1,
 *       "processing_mode": "aggregator",
 *       "description": "Service charge",
 *       "capture": true,
 *       "external_reference": "externalRef123",
 *       "statement_descriptor": "WWW.MktAdvancedPaymentMLBTEST.COM.BR"
 *     }
 *   ],
 *   "disbursements": [
 *     {
 *       "id": 3870106325,
 *       "amount": 200.12,
 *       "external_reference": "externalDisb1Ref",
 *       "collector_id": 328310637,
 *       "application_fee": 20,
 *       "money_release_days": 3,
 *       "additional_info": {
 *         "items": [
 *           {
 *             "id": "item-ID-1234",
 *             "title": "Title of what you are paying for",
 *             "picture_url": "https://www.mercadopago.com/logomp3.gif",
 *             "description": "Item description",
 *             "category_id": "art",
 *             "quantity": 1,
 *             "unit_price": 100
 *           }
 *         ],
 *         "shipments": {
 *           "receiver_address": {
 *             "zip_code": "5700",
 *             "street_name": "Street",
 *             "street_number": 123,
 *             "floor": 4,
 *             "apartment": "C"
 *           }
 *         }
 *       }
 *     },
 *     {
 *       "id": 3870106343,
 *       "amount": 300,
 *       "external_reference": "externalDisb2Ref",
 *       "collector_id": 328310458,
 *       "application_fee": 30,
 *       "money_release_days": 3,
 *       "additional_info": {
 *         "items": [
 *           {
 *             "id": "item-ID-1234",
 *             "title": "Title of what you are paying for",
 *             "picture_url": "https://www.mercadopago.com/logomp3.gif",
 *             "description": "Item description",
 *             "category_id": "art",
 *             "quantity": 1,
 *             "unit_price": 100
 *           }
 *         ],
 *         "shipments": {
 *           "receiver_address": {
 *             "zip_code": "5700",
 *             "street_name": "Street",
 *             "street_number": 123,
 *             "floor": 4,
 *             "apartment": "C"
 *           }
 *         }
 *       }
 *     }
 *   ],
 *   "payer": {
 *     "id": 65476879,
 *     "email": "test_user_p@testuser.com",
 *     "first_name": "Amanda",
 *     "last_name": "Martins",
 *     "address": {
 *       "zip_code": "X5000",
 *       "street_name": "Calle",
 *       "street_numer": "120"
 *     },
 *     "identification": {
 *       "type": "DNI",
 *       "number": "33672209"
 *     }
 *   },
 *   "external_reference": "externalRootRef",
 *   "description": "",
 *   "binary_mode": false,
 *   "date_created": "2018-06-27T09:34:20.518-04:00",
 *   "date_last_updated": "2018-06-27T09:34:20.518-04:00",
 *   "metadata": {},
 *   "additional_info": {
 *     "items": [
 *       {
 *         "id": "item-ID-1234",
 *         "title": "Title of what you are paying for",
 *         "picture_url": "https://www.mercadopago.com/logomp3.gif",
 *         "description": "Item description",
 *         "category_id": "art",
 *         "quantity": 1,
 *         "unit_price": 100
 *       }
 *     ],
 *     "shipments": {
 *       "receiver_address": {
 *         "zip_code": "5700",
 *         "street_name": "Street",
 *         "street_number": 123,
 *         "floor": 4,
 *         "apartment": "C"
 *       }
 *     }
 *   },
 *   "application_id": 4422991580014613
 * }
 */
@Idempotent
public class AdvancedPayment extends Payment {



    private String applicationId=null;
    private ArrayList<Payment> payments=null;
    private ArrayList<Disbursement> disbursements=null;


    public String getApplicationId() {
        return applicationId;
    }

    public AdvancedPayment setApplicationId(String applicationId) {
        this.applicationId = applicationId;
        return this;
    }


    public ArrayList<Payment> getPayments() {
        return payments;
    }

    public AdvancedPayment setPayments(ArrayList<Payment> payments) {
        this.payments = payments;
        return this;
    }

    public ArrayList<Disbursement> getDisbursements() {
        return disbursements;
    }

    public AdvancedPayment setDisbursements(ArrayList<Disbursement> disbursements) {
        this.disbursements = disbursements;
        return this;
    }



    @GET(path = "/v1/advanced_payments/search")
    @Override
    public MPResourceArray search(HashMap<String, String> filters, Boolean useCache) throws MPException {
        for (Map.Entry<String, String> entry : filters.entrySet()) {
            this.addQueryParam(entry.getKey(), entry.getValue());
        }
        return this.processMethodBulk("search", filters, useCache);
    }

    @GET(path = "/v1/advanced_payments/:id")
    @Override
    public Payment findById(String id, Boolean useCache) throws MPException {
        return this.processMethod("findById", id, useCache);
    }

    @POST(path = "/v1/advanced_payments")
    @Override
    public Payment save() throws MPException {
        return super.processMethod("save", WITHOUT_CACHE);
    }

    @PUT(path = "/v1/advanced_payments/:id")
    public Payment update() throws MPException {
        return super.processMethod("update", WITHOUT_CACHE);
    }


    @Override
    public Payment refund() throws MPException {
        AdvancedPaymentRefund refund = new AdvancedPaymentRefund();
        refund.setPaymentId(this.getId());
        refund.setAccessToken(this.getAccessToken());
        refund.save();
        return this.findById(this.getId());
    }

    public Payment refundDisbursement(String disbursement) throws MPException {
        AdvancedDisbursementRefund refund = new AdvancedDisbursementRefund();
        refund.setPaymentId(this.getId());
        refund.setAccessToken(this.getAccessToken());
        refund.setDisbursementId(disbursement);
        refund.save();
        return this.findById(this.getId());
    }


}
