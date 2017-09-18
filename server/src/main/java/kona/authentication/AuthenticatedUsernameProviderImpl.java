package kona.authentication;

import kona.persistence.AuthenticatedUsernameProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthenticatedUsernameProviderImpl implements AuthenticatedUsernameProvider {

    @Override
    public String getUsername() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final Object principal = authentication.getDetails();

        if (principal instanceof UserDetails) {
            final UserDetails userDetails = (UserDetails) principal;
            return userDetails.getUsername();
        }
        throw new RuntimeException("unauthorized");
    }
}
