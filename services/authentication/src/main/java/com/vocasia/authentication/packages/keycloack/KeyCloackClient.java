package com.vocasia.authentication.packages.keycloack;

import com.vocasia.authentication.config.KeycloackConfig;
import com.vocasia.authentication.mapper.AccessTokenMapper;
import com.vocasia.authentication.util.NameParserUtil;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RoleMappingResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.keycloak.admin.client.Keycloak;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;

@Component
@AllArgsConstructor
public class KeyCloackClient {

    private final KeycloackConfig keycloackConfig;
    private final NameParserUtil nameParserUtil;
    private final Logger logger = LoggerFactory.getLogger(KeyCloackClient.class);

    protected Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl(keycloackConfig.getServerUrl())
                .realm(keycloackConfig.getRealm())
                .clientId(keycloackConfig.getClientId())
                .clientSecret(keycloackConfig.getClientSecret())
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .build();
    }

    public String registerNewUser(String email, String username, String password, String name, String roleName) {
        Keycloak keycloak = keycloak();

        // Check if user exists
        List<UserRepresentation> users = keycloak.realm(keycloackConfig.getRealm()).users().list();
        Optional<UserRepresentation> existingUser = users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();

        if (existingUser.isPresent()) {
            logger.debug("User with username {} already exists with id {}", username, existingUser.get().getId());
            return existingUser.get().getId();
        }

        String firstName = nameParserUtil.getFirstName(name);
        String lastName = nameParserUtil.getLastName(name, firstName);

        // Create user representation
        UserRepresentation user = new UserRepresentation();
        user.setEmail(email);
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEnabled(true);
        user.setCredentials(Collections.singletonList(new CredentialRepresentation() {{
            setType(CredentialRepresentation.PASSWORD);
            setValue(password);
            setTemporary(false);
        }}));

        var response = keycloak.realm(keycloackConfig.getRealm()).users().create(user);

        if (response.getStatus() != 201) {
            logger.debug("Failed to create user: {}", response.getStatus());
            return null;
        }

        String userId = keycloak.realm(keycloackConfig.getRealm()).users().search(username).get(0).getId();
        logger.debug("User with username {} successfully created with id {}", username, userId);

        // Assign role to user
        assignRoleToUser(keycloak, userId, roleName);

        return userId;
    }

    private void assignRoleToUser(Keycloak keycloak, String userId, String roleName) {
        List<RoleRepresentation> roles = keycloak.realm(keycloackConfig.getRealm()).roles().list();
        RoleRepresentation role = roles.stream().filter(r -> r.getName().equals(roleName)).findFirst().orElse(null);

        if (role == null) {
            logger.debug("Role with name {} not found", roleName);
            return;
        }

        RoleMappingResource roleMappingResource = keycloak.realm(keycloackConfig.getRealm()).users().get(userId).roles();
        roleMappingResource.realmLevel().add(Collections.singletonList(role));
    }

    public AccessTokenMapper getAccessToken(String username, String password) throws IOException {
        return fetchAccessToken(username, password, "password");
    }

    public AccessTokenMapper refreshAccessToken(String refreshToken) throws IOException {
        return fetchAccessToken(null, refreshToken, "refresh_token");
    }

    private AccessTokenMapper fetchAccessToken(String username, String passwordOrRefreshToken, String grantType) throws IOException {
        String tokenEndpoint = keycloackConfig.getServerUrl() + "/realms/" + keycloackConfig.getRealm() + "/protocol/openid-connect/token";

        // Prepare the HTTP client
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(tokenEndpoint);

        // Prepare the form data
        String form = "client_id=" + keycloackConfig.getAuthClientId()
                + "&grant_type=" + grantType;

        if ("password".equals(grantType)) {
            form += "&username=" + username + "&password=" + passwordOrRefreshToken;
        } else {
            form += "&refresh_token=" + passwordOrRefreshToken;
        }

        httpPost.setEntity(new StringEntity(form));
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");

        // Execute the request and get the response
        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity);

            // Parse the response as JSON and map to AccessToken
            JSONObject jsonResponse = new JSONObject(responseString);
            return new AccessTokenMapper(jsonResponse.toMap());
        }
    }
}
