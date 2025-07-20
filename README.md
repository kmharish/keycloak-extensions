# keycloak-extensions

This is a multi-module project for Keycloak extensions.

## Modules
- `event-listeners`: Contains all Keycloak event listener SPIs.

## Password Update Session Revocation Event Listener

This Keycloak Event Listener SPI listens for password update events and revokes (invalidates) all sessions for the user whose password was updated.

### How it works
- Listens for `UPDATE_PASSWORD` events.
- When a user updates their password, all their sessions are revoked (invalidated).

### Build
```
./gradlew :event-listeners:clean :event-listeners:build
```

### Installation
1. Copy the built JAR from `event-listeners/build/libs/` to your Keycloak `providers/` directory.
2. Add the following to your `standalone.xml` or `keycloak.conf`:
   ```
   event-listeners: password-update-session-revocation
   ```
3. Restart Keycloak.

### Configuration
No additional configuration is required.