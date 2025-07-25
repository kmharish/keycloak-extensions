package com.example.keycloak.eventlistener;

import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventType;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.UserModel;
import org.keycloak.models.UserSessionModel;
import org.keycloak.models.UserSessionProvider;
import org.keycloak.models.RealmModel;
import java.util.List;


public class PasswordUpdateSessionRevocationProvider implements EventListenerProvider {
    private final KeycloakSession session;

    public PasswordUpdateSessionRevocationProvider(KeycloakSession session) {
        this.session = session;
    }

    @Override
    public void onEvent(Event event) {
        if (event.getType() == EventType.UPDATE_PASSWORD) {
            String userId = event.getUserId();
            String realmId = event.getRealmId();
            if (userId != null && realmId != null) {
                RealmModel realm = session.realms().getRealm(realmId);
                UserModel user = session.users().getUserById(realm, userId);
                if (user != null) {
                    UserSessionProvider userSessionProvider = session.sessions();
                    List<UserSessionModel> sessions = userSessionProvider.getUserSessionsStream(realm, user).toList();
                    for (UserSessionModel userSession : sessions) {
                        userSessionProvider.removeUserSession(realm, userSession);
                    }
                }
            }
        }
    }

    @Override
    public void onEvent(AdminEvent adminEvent, boolean b) {

    }

    @Override
    public void close() {
        // No resources to close
    }
} 