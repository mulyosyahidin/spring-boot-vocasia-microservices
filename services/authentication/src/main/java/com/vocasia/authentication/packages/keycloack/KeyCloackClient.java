package com.vocasia.authentication.packages.keycloack;

import com.vocasia.authentication.config.KeycloackConfig;
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

    private KeycloackConfig keycloackConfig;
    private NameParserUtil nameParserUtil;
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

        boolean isUserExists = false;

        // get all users
        List<UserRepresentation> users = keycloak.realm(keycloackConfig.getRealm()).users().list();

        for (UserRepresentation user : users) {
            if (user.getUsername().equals(username)) {
                isUserExists = true;

                break;
            }
        }

        if (isUserExists) {
            UserRepresentation userByUsername = keycloak.realm(keycloackConfig.getRealm()).users().search(username).get(0);

            logger.debug("User with username {} already exists with id {}", username, userByUsername.getId());
            return userByUsername.getId();
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

        // get all roles
        List<RoleRepresentation> roles = keycloak.realm(keycloackConfig.getRealm()).roles().list();

        // get role by name
        RoleRepresentation role = roles.stream().filter(r -> r.getName().equals(roleName)).findFirst().orElse(null);

        if (role == null) {
            logger.debug("Role with name {} not found", roleName);

            return null;
        }

        // get role mapping resource
        RoleMappingResource roleMappingResource = keycloak.realm(keycloackConfig.getRealm()).users().get(userId).roles();

        // add role to user
        roleMappingResource.realmLevel().add(Collections.singletonList(role));

        return userId;
    }

    public Map<String, Object> getAccessToken(String username, String password) throws IOException {
        String tokenEndpoint = keycloackConfig.getServerUrl() + "/realms/" + keycloackConfig.getRealm() + "/protocol/openid-connect/token";

        // Prepare the HTTP client
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(tokenEndpoint);

        // Prepare the form data (encoded as URL)
        String form = "client_id="+ keycloackConfig.getAuthClientId()
                + "&grant_type=password"
                + "&username=" + username
                + "&password=" + password;

        httpPost.setEntity(new StringEntity(form));
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");

        // Execute the request and get the response
        CloseableHttpResponse response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        String responseString = EntityUtils.toString(entity);
        response.close();

        // Parse the response as JSON
        JSONObject jsonResponse = new JSONObject(responseString);

        // Map the required token details
        Map<String, Object> tokenData = new HashMap<>();
        tokenData.put("access_token", jsonResponse.getString("access_token"));
        tokenData.put("refresh_token", jsonResponse.getString("refresh_token"));
        tokenData.put("expires_in", jsonResponse.getInt("expires_in"));
        tokenData.put("refresh_expires_in", jsonResponse.getInt("refresh_expires_in"));
        tokenData.put("token_type", jsonResponse.getString("token_type"));
        tokenData.put("scope", jsonResponse.optString("scope", ""));
        tokenData.put("not-before-policy", jsonResponse.getInt("not-before-policy"));
        tokenData.put("session_state", jsonResponse.getString("session_state"));

        return tokenData;
    }
}
