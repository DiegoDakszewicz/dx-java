package com.mercadopago.resources;

import com.mercadopago.core.MPBase;
import com.mercadopago.core.MPResourceArray;
import com.mercadopago.core.annotations.rest.DELETE;
import com.mercadopago.core.annotations.rest.GET;
import com.mercadopago.core.annotations.rest.POST;
import com.mercadopago.core.annotations.rest.PUT;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.datastructures.customer.card.Cardholder;
import com.mercadopago.resources.datastructures.customer.card.Issuer;
import com.mercadopago.resources.datastructures.customer.card.PaymentMethod;
import com.mercadopago.resources.datastructures.customer.card.SecurityCode;

import java.util.Date;

/**
 * Mercado Pago MercadoPago
 * Retrieves information about a customer's cards.
 *
 * Created by Eduardo Paoletta on 12/15/16.
 */
public class Card extends MPBase {

    private String token = null;
    private String id = null;
    private String customerId = null;
    private Integer expirationMonth = null;
    private Integer expirationYear = null;
    private String firstSixDigits = null;
    private String lastFourDigits = null;
    private PaymentMethod paymentMethod = null;
    private SecurityCode securityCode = null;
    private Issuer issuer = null;
    private Cardholder cardholder = null;
    private Date dateCreated = null;
    private Date dateLastUpdated = null;
    private String paymentMethodId = null;


    public Card setToken(String token) {
        this.token = token;
        return this;
    }

    public String getId() {
        return id;
    }

    public Card setId(String id) {
        this.id = id;
        return this;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Card setCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public Integer getExpirationMonth() {
        return expirationMonth;
    }

    public Card setExpirationMonth(Integer expirationMonth) {
        this.expirationMonth = expirationMonth;
        return this;
    }

    public Integer getExpirationYear() {
        return expirationYear;
    }

    public Card setExpirationYear(Integer expirationYear) {
        this.expirationYear = expirationYear;
        return this;
    }

    public String getFirstSixDigits() {
        return firstSixDigits;
    }

    public Card setFirstSixDigits(String firstSixDigits) {
        this.firstSixDigits = firstSixDigits;
        return this;
    }

    public String getLastFourDigits() {
        return lastFourDigits;
    }

    public Card setLastFourDigits(String lastFourDigits) {
        this.lastFourDigits = lastFourDigits;
        return this;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public Card setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public SecurityCode getSecurityCode() {
        return securityCode;
    }

    public Card setSecurityCode(SecurityCode securityCode) {
        this.securityCode = securityCode;
        return this;
    }

    public Issuer getIssuer() {
        return issuer;
    }

    public Card setIssuer(Issuer issuer) {
        this.issuer = issuer;
        return this;
    }

    public Cardholder getCardholder() {
        return cardholder;
    }

    public Card setCardholder(Cardholder cardholder) {
        this.cardholder = cardholder;
        return this;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Card setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public Date getDateLastUpdated() {
        return dateLastUpdated;
    }

    public Card setDateLastUpdated(Date dateLastUpdated) {
        this.dateLastUpdated = dateLastUpdated;
        return this;
    }


    public MPResourceArray all(String customerId) throws MPException {
        return all(customerId, WITHOUT_CACHE);
    }

    @GET(path="/v1/customers/:customer_id/cards")
    public  MPResourceArray all(String customerId, Boolean useCache) throws MPException {
        return Card.processMethodBulk(Card.class, "all", customerId, useCache);
    }

    public  Card findById(String customerId, String id) throws MPException {
        return findById(customerId, id, WITHOUT_CACHE);
    }

    @GET(path="/v1/customers/:customer_id/cards/:id")
    public Card findById(String customerId, String id, Boolean useCache) throws MPException {
        return processMethod(Card.class, "findById", customerId, id, useCache);
    }

    @POST(path="/v1/customers/:customer_id/cards/")
    public Card save() throws MPException {
        return super.processMethod("save", WITHOUT_CACHE);
    }

    @PUT(path="/v1/customers/:customer_id/cards/:id")
    public Card update() throws MPException {
        return super.processMethod("update", WITHOUT_CACHE);
    }

    @DELETE(path="/v1/customers/:customer_id/cards/:id")
    public Card delete() throws MPException {
        return super.processMethod("delete", WITHOUT_CACHE);
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }
}
