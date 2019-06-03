package com.mercadopago.resources;

import com.mercadopago.core.MPBase;
import com.mercadopago.core.annotations.rest.GET;
import com.mercadopago.exceptions.MPException;

/**
 * User: Diego Dakszewicz - diego@nubing.net
 * Date: 17/04/19 17:41
 */
public class UserInfo extends MPBase {

    private String id;

    private String nickname;

    private String lastName;

    private String firstName;

    private Double totalAmount;

    private Double availableBalance;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(Double availableBalance) {
        this.availableBalance = availableBalance;
    }

    @GET(path = "/users/me")
    public UserInfo get() throws MPException {
        return this.processMethod("get",false);
    }

    @GET(path = "/users/:id/mercadopago_account/balance")
    public UserInfo balance() throws MPException {
        return this.processMethod("balance",false);
    }

}
