package com.vocasia.authentication.mapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class AccessTokenMapper implements Serializable {

    private String accessToken;
    private String refreshToken;
    private int expiresIn;
    private int refreshExpiresIn;
    private String tokenType;
    private String scope;
    private int notBeforePolicy;
    private String sessionState;

    public AccessTokenMapper(Map<String, Object> tokenData) {
        this.accessToken = (String) tokenData.get("access_token");
        this.refreshToken = (String) tokenData.get("refresh_token");
        this.expiresIn = (int) tokenData.get("expires_in");
        this.refreshExpiresIn = (int) tokenData.get("refresh_expires_in");
        this.tokenType = (String) tokenData.get("token_type");
        this.scope = (String) tokenData.get("scope");
        this.notBeforePolicy = (int) tokenData.get("not-before-policy");
        this.sessionState = (String) tokenData.get("session_state");
    }

    @JsonProperty("access_token")
    public String getAccessToken() {
        return accessToken;
    }

    @JsonProperty("refresh_token")
    public String getRefreshToken() {
        return refreshToken;
    }

    @JsonProperty("expires_in")
    public int getExpiresIn() {
        return expiresIn;
    }

    @JsonProperty("refresh_expires_in")
    public int getRefreshExpiresIn() {
        return refreshExpiresIn;
    }

    @JsonProperty("token_type")
    public String getTokenType() {
        return tokenType;
    }

    @JsonProperty("scope")
    public String getScope() {
        return scope;
    }

    @JsonProperty("not-before-policy")
    public int getNotBeforePolicy() {
        return notBeforePolicy;
    }

    @JsonProperty("session_state")
    public String getSessionState() {
        return sessionState;
    }
}
