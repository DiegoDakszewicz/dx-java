package com.mercadopago.core.credentials;

import com.google.gson.JsonObject;
import com.mercadopago.MercadoPago;
import com.mercadopago.core.MPApiResponse;
import com.mercadopago.core.MPCoreUtils;
import com.mercadopago.core.annotations.rest.PayloadType;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.exceptions.MPRestException;
import com.mercadopago.net.HttpMethod;
import com.mercadopago.net.MPRestClient;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Mercado Pago MercadoPago
 * For Marketplace and uncoupled from Mercadopago.SDK constants
 */
public class Credential {


    public Credential(String clientId, String secretKey, String accessToken, String refreshToken) {
        this.setClientId(clientId);
        this.setSecretKey(secretKey);
        this.setAccessToken(accessToken);
        this.setRefreshToken(refreshToken);
    }

    public Credential(String clientId, String secretKey){
        this.setClientId(clientId);
        this.setSecretKey(secretKey);
    }

    public Credential(String accessToken) {
        this.setAccessToken(accessToken);
    }

    public interface RefreshAccessTokenCallback {
        void onRefresh(Credential credentials);
    }

    public enum GRANT_TYPE {
        authorization_code, refresh_token, client_credentials
    }


    /**
     * This field could be clientId for individual credentials or ApplicationId  for marketplace app
     */
    private String clientId = null;
    private String secretKey = null;
    private String grantType = null;

    /**
     * Code provided from Mercadopago Connect callback to obtain our first accessToken and refreshToken
     */
    private String code = null;
    private String redirectUri = null;

    private String accessToken = null;
    private String refreshToken = null;
    private String publicKey = null;
    private String liveMode = null;
    private String userId = null;
    private String tokenType = null;
    private Integer expiresIn = null;
    private String scope = null;
    /**
     * Set it when accessToken fails and refreshToken mechanism is called
     */
    private String oldAccessToken = null;

    private MPApiResponse lastResponse = null;

    private RefreshAccessTokenCallback refreshAccessTokenCallback = null;

    public String getClientId() {
        return clientId;
    }

    public Credential setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public Credential setSecretKey(String secretKey) {
        this.secretKey = secretKey;
        return this;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public Credential setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public Credential setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    public String getLiveMode() {
        return liveMode;
    }

    public Credential setLiveMode(String liveMode) {
        this.liveMode = liveMode;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public Credential setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getTokenType() {
        return tokenType;
    }

    public Credential setTokenType(String tokenType) {
        this.tokenType = tokenType;
        return this;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public Credential setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

    public String getScope() {
        return scope;
    }

    public Credential setScope(String scope) {
        this.scope = scope;
        return this;
    }


    public String getGrantType() {
        return grantType;
    }

    public Credential setGrantType(String grantType) {
        this.grantType = grantType;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Credential setCode(String code) {
        this.code = code;
        return this;
    }

    public MPApiResponse getLastResponse() {
        return lastResponse;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public Credential setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
        return this;
    }

    public String getOldAccessToken() {
        return oldAccessToken;
    }

    public Credential setOldAccessToken(String oldAccessToken) {
        this.oldAccessToken = oldAccessToken;
        return this;
    }

    public RefreshAccessTokenCallback getRefreshAccessTokenCallback() {
        return refreshAccessTokenCallback;
    }

    public void setRefreshAccessTokenCallback(RefreshAccessTokenCallback refreshAccessTokenCallback) {
        this.refreshAccessTokenCallback = refreshAccessTokenCallback;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public Credential setPublicKey(String publicKey) {
        this.publicKey = publicKey;
        return this;
    }

    public boolean isHasRefreshToken() {
        return StringUtils.isNotEmpty(this.getRefreshToken());
    }

    public String getMercadopagoConnectUri(String callback) {
        try {
            return "https://auth.mercadopago.com.ar/authorization?response_type=code&platform_id=mp&client_id=" + this.clientId + "&redirect_uri=" + URLEncoder.encode(callback, "UTF-8");
        } catch (UnsupportedEncodingException var3) {
            return "https://auth.mercadopago.com.ar/authorization?response_type=code&platform_id=mp&client_id=" + this.clientId + "&redirect_uri=" + callback;
        }
    }

    public Credential authenticate() throws MPRestException {
        JsonObject jsonPayload = new JsonObject();
        jsonPayload.addProperty("client_id", getClientId());
        jsonPayload.addProperty("client_secret", getSecretKey());
        if (StringUtils.isNotEmpty(this.getCode())) {
            jsonPayload.addProperty("grant_type", GRANT_TYPE.authorization_code.name());
            jsonPayload.addProperty("code", this.getCode());
            jsonPayload.addProperty("redirect_uri", this.getRedirectUri());
        } else {
            jsonPayload.addProperty("grant_type", GRANT_TYPE.client_credentials.name());
        }
        String baseUri = MercadoPago.SDK.getBaseUrl();
        lastResponse = new MPRestClient().executeRequest(
                HttpMethod.POST,
                baseUri + "/oauth/token",
                PayloadType.JSON,
                jsonPayload,
                null);
        Credential result = this;
        if (lastResponse.getJsonElementResponse() != null &&
                lastResponse.getJsonElementResponse().isJsonObject()) {
            result = MPCoreUtils.getResourceFromJson(this.getClass(), lastResponse.getJsonElementResponse().getAsJsonObject());
            result.lastResponse = lastResponse;
        }
        return result;
    }

    public Credential refreshToken() throws MPException {
        JsonObject jsonPayload = new JsonObject();
        if (StringUtils.isNotEmpty(this.getRefreshToken())) {
            jsonPayload.addProperty("client_id", getClientId());
            jsonPayload.addProperty("client_secret", getSecretKey());
            jsonPayload.addProperty("grant_type", GRANT_TYPE.refresh_token.name());
            jsonPayload.addProperty("refresh_token", this.getRefreshToken());
        } else {
            throw new MPException("Refresh Token not set");
        }
        String baseUri = MercadoPago.SDK.getBaseUrl();
        lastResponse = new MPRestClient().executeRequest(
                HttpMethod.POST,
                baseUri + "/oauth/token",
                PayloadType.JSON,
                jsonPayload,
                null);
        Credential result = this;
        if (lastResponse.getJsonElementResponse() != null &&
                lastResponse.getJsonElementResponse().isJsonObject()) {
            result = MPCoreUtils.getResourceFromJson(this.getClass(), lastResponse.getJsonElementResponse().getAsJsonObject());
            result.setOldAccessToken(this.getAccessToken());
            this.getRefreshAccessTokenCallback().onRefresh(result);
            result.lastResponse = lastResponse;
        } else {
            throw new MPException("Refresh token is invalid! Please validate your account again!");
        }
        return result;
    }

    public String replaceOldToken(String path) {
        return path.replace(this.getOldAccessToken(), this.getAccessToken());
    }

}
