package com.mercadopago.resources;

import com.mercadopago.core.MPBase;
import com.mercadopago.core.MPResourceArray;
import com.mercadopago.core.annotations.rest.GET;
import com.mercadopago.exceptions.MPException;

/**
 * Mercado Pago SDK
 * Identification Types Array class
 * <p>
 * Created by Eduardo Paoletta on 12/15/16.
 */
public class IdentificationType extends MPBase {

    private String id = null;
    private String name = null;
    private String type = null;
    private Integer minLength = null;
    private Integer maxLength = null;


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Integer getMinLength() {
        return minLength;
    }

    public Integer getMaxLength() {
        return maxLength;
    }


    public MPResourceArray all() throws MPException {
        return all(WITHOUT_CACHE);
    }

    @GET(path = "/v1/identification_types")
    public MPResourceArray all(Boolean useCache) throws MPException {
        return this.processMethodBulk(IdentificationType.class, "all", useCache);
    }

}
