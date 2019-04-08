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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * {
 *         "application_id": "4422991580014613",
 *
 *         "disbursements": [
 *         {
 *             "amount": 200.12,
 *                 "external_reference": "",
 *                 "collector_id": 328310637,
 *                 "application_fee": 20.0,
 *                 "money_release_days": 3,
 *                 "additional_info": {
 *             "items": [
 *             {
 *                 "id": "item-ID-1234",
 *                     "title": "Title of what you are paying for",
 *                     "picture_url": "https://www.mercadopago.com/logomp3.gif",
 *                     "description": "Item description",
 *                     "category_id": "art",
 *                     "quantity": 1,
 *                     "unit_price": 100
 *             }
 *                     ],
 *             "shipments": {
 *                 "receiver_address": {
 *                     "zip_code": "5700",
 *                             "street_name": "Street",
 *                             "street_number": 123,
 *                             "floor": 4,
 *                             "apartment": "C"
 *                 }
 *             }
 *         }
 *         },
 *         {
 *             "amount": 300,
 *                 "external_reference": "",
 *                 "collector_id": 328310458,
 *                 "application_fee": 30.0,
 *                 "money_release_days": 3,
 *                 "additional_info": {
 *             "items": [
 *             {
 *                 "id": "item-ID-1234",
 *                     "title": "Title of what you are paying for",
 *                     "picture_url": "https://www.mercadopago.com/logomp3.gif",
 *                     "description": "Item description",
 *                     "category_id": "art",
 *                     "quantity": 1,
 *                     "unit_price": 100
 *             }
 *                     ],
 *             "shipments": {
 *                 "receiver_address": {
 *                     "zip_code": "5700",
 *                             "street_name": "Street",
 *                             "street_number": 123,
 *                             "floor": 4,
 *                             "apartment": "C"
 *                 }
 *             }
 *         }
 *         }
 *             ],
 *         "payer": {
 *         "id": 41234,
 *                 "email": "test_user_p@testuser.com",
 *                 "first_name": "Amanda",
 *                 "last_name": "Martins",
 *                 "address": {
 *             "zip_code": "X5000",
 *                     "street_name": "Calle",
 *                     "street_numer": "120"
 *         },
 *         "identification": {
 *             "type": "CPF",
 *                     "number": "33672209"
 *         }
 *     },
 *         "external_reference": "externalRootRef",
 *             "description": "",
 *             "binary_mode": false,
 *             "metadata": {},
 *         "additional_info": {
 *         "items": [
 *         {
 *             "id": "item-ID-1234",
 *                 "title": "Title of what you are paying for",
 *                 "picture_url": "https://www.mercadopago.com/logomp3.gif",
 *                 "description": "Item description",
 *                 "category_id": "art",
 *                 "quantity": 1,
 *                 "unit_price": 100
 *         }
 *                 ],
 *         "shipments": {
 *             "receiver_address": {
 *                 "zip_code": "5700",
 *                         "street_name": "Street",
 *                         "street_number": 123,
 *                         "floor": 4,
 *                         "apartment": "C"
 *             }
 *         }
 *     }
 *     }'
 */
@Idempotent
public class AdvancedPayment extends MPBase {



    private String id = null;
    private Date dateCreated = null;
    private Date dateApproved = null;
    private Date dateLastUpdated = null;
    private Date moneyReleaseDate = null;
    private Integer collectorId = null;
    private String authorizationCode = null;
    private OperationType operationType = null;

    public enum OperationType {
        regular_payment,
        money_transfer,
        recurring_payment,
        account_fund,
        payment_addition,
        cellphone_recharge,
        pos_payment
    }

    private Payer payer = null;
    private Boolean binaryMode = null;
    private Boolean liveMode = null;
    private Order order = null;
    private String externalReference = null;
    private String description = null;
    private JsonObject metadata = null;
    @Size(min = 3, max = 3)
    private CurrencyId currencyId = null;

    public enum CurrencyId {
        ARS,
        BRL,
        VEF,
        CLP,
        MXN,
        COP,
        PEN,
        UYU,
        USD
    }

    private Float transactionAmount = null;
    private Float transactionAmountRefunded = null;
    private Float couponAmount = null;
    private Integer campaignId = null;
    private String couponCode = null;
    private TransactionDetails transactionDetails = null;
    private ArrayList<FeeDetail> feeDetails = null;
    private Integer differentialPricingId = null;
    private Float applicationFee = null;
    private Status status = null;

    public enum Status {
        pending,
        approved,
        authorized,
        in_process,
        in_mediation,
        rejected,
        cancelled,
        refunded,
        charged_back
    }

    private String statusDetail = null;
    private Boolean capture = null;
    private Boolean captured = null;
    private String callForAuthorizeId = null;
    private String paymentMethodId = null;
    private String issuerId = null;
    private PaymentTypeId paymentTypeId = null;

    public enum PaymentTypeId {
        account_money,
        ticket,
        bank_transfer,
        atm,
        credit_card,
        debit_card,
        prepaid_card
    }

    private String token = null;
    private Card card = null;
    private String statementDescriptor = null;
    @Numeric(min = 1, fractionDigits = 0)
    private Integer installments = null;
    private String notificationUrl = null;
    private ArrayList<Refund> refunds = null;
    private AdditionalInfo additionalInfo = null;
    private String callbackUrl = null;
    private Integer sponsorId=null;
    private String processingMode=null;
    private String merchantAccountId=null;


    public String getId() {
        return id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getDateApproved() {
        return dateApproved;
    }

    public Date getDateLastUpdated() {
        return dateLastUpdated;
    }

    public Date getMoneyReleaseDate() {
        return moneyReleaseDate;
    }

    public Integer getCollectorId() {
        return collectorId;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public Payer getPayer() {
        return payer;
    }

    public AdvancedPayment setPayer(Payer payer) {
        this.payer = payer;
        return this;
    }

    public Boolean getBinaryMode() {
        return binaryMode;
    }

    public AdvancedPayment setBinaryMode(Boolean binaryMode) {
        this.binaryMode = binaryMode;
        return this;
    }

    public Boolean getLiveMode() {
        return liveMode;
    }

    public Order getOrder() {
        return order;
    }

    public AdvancedPayment setOrder(Order order) {
        this.order = order;
        return this;
    }

    public String getExternalReference() {
        return externalReference;
    }

    public AdvancedPayment setExternalReference(String externalReference) {
        this.externalReference = externalReference;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AdvancedPayment setDescription(String description) {
        this.description = description;
        return this;
    }

    public JsonObject getMetadata() {
        return metadata;
    }

    public AdvancedPayment setMetadata(JsonObject metadata) {
        this.metadata = metadata;
        return this;
    }

    public CurrencyId getCurrencyId() {
        return currencyId;
    }

    public Float getTransactionAmount() {
        return transactionAmount;
    }

    public AdvancedPayment setTransactionAmount(Float transactionAmount) {
        this.transactionAmount = transactionAmount;
        return this;
    }

    public Float getTransactionAmountRefunded() {
        return transactionAmountRefunded;
    }

    public Float getCouponAmount() {
        return couponAmount;
    }

    public AdvancedPayment setCouponAmount(Float couponAmount) {
        this.couponAmount = couponAmount;
        return this;
    }

    public AdvancedPayment setCampaignId(Integer campaignId) {
        this.campaignId = campaignId;
        return this;
    }

    public AdvancedPayment setCouponCode(String couponCode) {
        this.couponCode = couponCode;
        return this;
    }

    public TransactionDetails getTransactionDetails() {
        return transactionDetails;
    }

    public AdvancedPayment setTransactionDetails(TransactionDetails transactionDetails) {
        this.transactionDetails = transactionDetails;
        return this;
    }

    public ArrayList<FeeDetail> getFeeDetails() {
        return feeDetails;
    }

    public Integer getDifferentialPricingId() {
        return differentialPricingId;
    }

    public AdvancedPayment setDifferentialPricingId(Integer differentialPricingId) {
        this.differentialPricingId = differentialPricingId;
        return this;
    }

    public AdvancedPayment setApplicationFee(Float applicationFee) {
        this.applicationFee = applicationFee;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public AdvancedPayment setStatus(Status status) {
        this.status = status;
        return this;
    }

    public String getStatusDetail() {
        return statusDetail;
    }

    public AdvancedPayment setCapture(Boolean capture) {
        this.capture = capture;
        return this;
    }

    public Boolean getCaptured() {
        return captured;
    }

    public String getCallForAuthorizeId() {
        return callForAuthorizeId;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public AdvancedPayment setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
        return this;
    }

    public String getIssuerId() {
        return issuerId;
    }

    public AdvancedPayment setIssuerId(String issuerId) {
        this.issuerId = issuerId;
        return this;
    }

    public PaymentTypeId getPaymentTypeId() {
        return paymentTypeId;
    }

    public AdvancedPayment setToken(String token) {
        this.token = token;
        return this;
    }

    public Card getCard() {
        return card;
    }

    public String getStatementDescriptor() {
        return statementDescriptor;
    }

    public AdvancedPayment setStatementDescriptor(String statementDescriptor) {
        this.statementDescriptor = statementDescriptor;
        return this;
    }

    public Integer getInstallments() {
        return installments;
    }

    public AdvancedPayment setInstallments(Integer installments) {
        this.installments = installments;
        return this;
    }

    public String getNotificationUrl() {
        return notificationUrl;
    }

    public AdvancedPayment setNotificationUrl(String notificationUrl) {
        this.notificationUrl = notificationUrl;
        return this;
    }

    public Integer getSponsorId() {
        return sponsorId;
    }

    public AdvancedPayment setSponsorId(Integer sponsorId) {
        this.sponsorId = sponsorId;
        return this;
    }

    public String getProcessingMode() {
        return processingMode;
    }

    public AdvancedPayment setProcessingMode(String processingMode) {
        this.processingMode = processingMode;
        return this;
    }

    public ArrayList<Refund> getRefunds() {
        return refunds;
    }

    public AdvancedPayment setAdditionalInfo(AdditionalInfo additionalInfo) {
        this.additionalInfo = additionalInfo;
        return this;
    }

    public String getMerchantAccountId() {
        return merchantAccountId;
    }

    public void setMerchantAccountId(String merchantAccountId) {
        this.merchantAccountId = merchantAccountId;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public AdvancedPayment findById(String id) throws MPException {
        return findById(id, WITHOUT_CACHE);
    }

    @GET(path = "/v1/payments/search")
    public MPResourceArray search(HashMap<String, String> filters, Boolean useCache) throws MPException {
        for (Map.Entry<String, String> entry : filters.entrySet()) {
            this.addQueryParam(entry.getKey(), entry.getValue());
        }
        return this.processMethodBulk("search", filters, useCache);
    }

    @GET(path = "/v1/payments/:id")
    public AdvancedPayment findById(String id, Boolean useCache) throws MPException {
        return this.processMethod("findById", id, useCache);
    }

    @POST(path = "/v1/payments")
    public AdvancedPayment save() throws MPException {
        return super.processMethod("save", WITHOUT_CACHE);
    }

    @PUT(path = "/v1/payments/:id")
    public AdvancedPayment update() throws MPException {
        return super.processMethod("update", WITHOUT_CACHE);
    }

    public MPResourceArray getPaymentMethods(Boolean useCache) throws MPException {
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setAccessToken(this.getAccessToken());
        return paymentMethod.all(useCache);
    }


    public AdvancedPayment refund() throws MPException {
        // Create a refund
        Refund refund = new Refund();
        refund.setAccessToken(this.getAccessToken());
        refund.setPaymentId(this.getId());
        refund.save();
        // If refund has been successfully created then update the instance values

        if (refund.getId() != null) {
            AdvancedPayment payment = this.findById(this.getId()); // Get updated payment instance
            this.status = payment.getStatus();
            this.refunds = payment.getRefunds();
            this.transactionAmountRefunded = payment.getTransactionAmountRefunded();
        }
        return this;
    }

}
