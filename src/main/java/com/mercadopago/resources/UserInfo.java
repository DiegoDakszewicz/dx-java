package com.mercadopago.resources;

import com.mercadopago.core.MPBase;
import com.mercadopago.core.annotations.rest.GET;
import com.mercadopago.exceptions.MPException;

/**
 * User: Diego Dakszewicz - diego@nubing.net
 * Date: 17/04/19 17:41
 */
public class UserInfo extends MPBase {

    private String nickname;

    private String lastName;

    private String firstName;

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

    @GET(path = "/users/me")
    public UserInfo getMe() throws MPException {
        return this.processMethod("get",false);
    }
}
