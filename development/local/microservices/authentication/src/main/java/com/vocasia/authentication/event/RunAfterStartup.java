package com.vocasia.authentication.event;

import com.vocasia.authentication.config.DefaultAdminConfig;
import com.vocasia.authentication.entity.User;
import com.vocasia.authentication.request.RegisterRequest;
import com.vocasia.authentication.service.IKeyCloackService;
import com.vocasia.authentication.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@Component
public class RunAfterStartup {

    private final Logger logger = LoggerFactory.getLogger(RunAfterStartup.class);

    private final IUserService userService;
    private final IKeyCloackService keyCloackService;
    private final DefaultAdminConfig defaultAdminConfig;

    public RunAfterStartup(IUserService iUserService, IKeyCloackService iKeyCloackService, DefaultAdminConfig defaultAdminConfig) {
        this.userService = iUserService;
        this.keyCloackService = iKeyCloackService;
        this.defaultAdminConfig = defaultAdminConfig;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() throws NoSuchAlgorithmException, InvalidKeySpecException {
        logger.debug("Checking for existing admin user in the local database...");

        List<User> adminUsers = userService.getByRole("admin");

        if (adminUsers.isEmpty()) {
            logger.info("No admin user found in the local database. Creating default admin user...");

            createAndRegisterAdmin(defaultAdminConfig.getAdmin());
        } else {
            logger.info(adminUsers.size() + " admin user(s) found in the local database. Checking Keycloak...");
            User adminUser = adminUsers.get(0);

            if (adminUser.getUid() == null || !keyCloackService.isUserExists(adminUser.getUid())) {
                logger.warn("Admin user not found in Keycloak or UID is missing. Re-registering admin user to Keycloak...");

                String newKeycloakId = createAndRegisterAdmin(adminUser);
                if (!adminUser.getUid().equals(newKeycloakId)) {
                    User updatedUser = userService.updateUid(adminUser.getId(), newKeycloakId);
                    logger.info("Admin user updated in the local database with new Keycloak ID: " + updatedUser.getUid());
                } else {
                    logger.info("Admin user UID is consistent after re-registration.");
                }
            } else {
                logger.info("Admin user is present in both the local database and Keycloak.");
            }
        }
    }

    private void createAndRegisterAdmin(DefaultAdminConfig.AdminUser adminConfig) throws NoSuchAlgorithmException, InvalidKeySpecException {
        RegisterRequest registerRequest = buildRegisterRequest(adminConfig);

        String keycloakId = keyCloackService.registerNewUser(
                registerRequest.getEmail(),
                registerRequest.getUsername(),
                registerRequest.getPassword(),
                registerRequest.getName(),
                registerRequest.getRole()
        );

        logger.debug("Admin user successfully registered in Keycloak with ID: " + keycloakId);

        User registeredUser = userService.registerNewUser(keycloakId, registerRequest);

        logger.info("Admin user successfully created in the local database with ID: " + registeredUser.getId());
    }

    private String createAndRegisterAdmin(User existingAdminUser) throws NoSuchAlgorithmException, InvalidKeySpecException {
        RegisterRequest registerRequest = buildRegisterRequest(existingAdminUser);

        String keycloakId = keyCloackService.registerNewUser(
                registerRequest.getEmail(),
                registerRequest.getUsername(),
                registerRequest.getPassword(),
                registerRequest.getName(),
                registerRequest.getRole()
        );

        logger.debug("Admin user successfully re-registered in Keycloak with ID: " + keycloakId);

        return keycloakId;
    }

    private RegisterRequest buildRegisterRequest(DefaultAdminConfig.AdminUser adminConfig) {
        RegisterRequest registerRequest = new RegisterRequest();

        registerRequest.setName(adminConfig.getUsername());
        registerRequest.setEmail(adminConfig.getEmail());
        registerRequest.setUsername(adminConfig.getUsername());
        registerRequest.setPassword(adminConfig.getPassword());
        registerRequest.setRole("admin");

        return registerRequest;
    }

    private RegisterRequest buildRegisterRequest(User adminUser) {
        RegisterRequest registerRequest = new RegisterRequest();

        registerRequest.setName(adminUser.getName());
        registerRequest.setEmail(adminUser.getEmail());
        registerRequest.setUsername(adminUser.getUsername());
        registerRequest.setPassword(adminUser.getPassword());
        registerRequest.setRole("admin");

        return registerRequest;
    }

}

