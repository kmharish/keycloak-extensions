package com.example.keycloak.eventlistener;

import org.keycloak.Config;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

public class PasswordUpdateSessionRevocationProviderFactory implements EventListenerProviderFactory {
    public static final String ID = "password-update-session-revocation";

    @Override
    public EventListenerProvider create(KeycloakSession session) {
        return new PasswordUpdateSessionRevocationProvider(session);
    }

    @Override
    public void init(Config.Scope config) {
        // No initialization needed
    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {
        // No post-init needed
    }

    @Override
    public void close() {
        // No resources to close
    }

    @Override
    public String getId() {
        return ID;
    }
} 