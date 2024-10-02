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
    private final IKeyCloackService iKeyCloackService;
    private final DefaultAdminConfig defaultAdminConfig;

    public RunAfterStartup(IUserService userService, IKeyCloackService iKeyCloackService, DefaultAdminConfig defaultAdminConfig) {
        this.userService = userService;
        this.iKeyCloackService = iKeyCloackService;
        this.defaultAdminConfig = defaultAdminConfig;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() throws NoSuchAlgorithmException, InvalidKeySpecException {
        System.out.println("Checking admin user...");

        List<User> users = userService.getByRole("admin");

        if (users.isEmpty()) {
            logger.debug("Admin user not found, creating default admin user...");

            RegisterRequest registerRequest = new RegisterRequest();

            registerRequest.setName(defaultAdminConfig.getAdmin().getUsername());
            registerRequest.setEmail(defaultAdminConfig.getAdmin().getEmail());
            registerRequest.setUsername(defaultAdminConfig.getAdmin().getUsername());
            registerRequest.setPassword(defaultAdminConfig.getAdmin().getPassword());
            registerRequest.setRole("admin");

            String registeredKeycloackId = iKeyCloackService.registerNewUser(
                    registerRequest.getEmail(),
                    registerRequest.getUsername(),
                    registerRequest.getPassword(),
                    registerRequest.getName(),
                    registerRequest.getRole()
            );

            logger.debug("Admin user created at Keycloack Authorization Server with ID: " + registeredKeycloackId);

            User registeredUser = userService.registerNewUser(registeredKeycloackId, registerRequest);

            logger.debug("Admin user created at local database with ID: " + registeredUser.getId());
        } else {
            logger.debug("Found " + users.size() + " admin user(s) in local database.");
        }
    }

}
