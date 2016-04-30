package kona.web.authentication;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.stereotype.Component;

@Component
public class PreAuthTokenFilter extends AbstractPreAuthenticatedProcessingFilter {
    public static final String X_AUTH_TOKEN_HEADERNAME = "X-Auth-Token";

    @Inject
    public PreAuthTokenFilter(AuthenticationManager authenticationManager) {
        this.setAuthenticationManager(authenticationManager);
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        return request.getHeader(X_AUTH_TOKEN_HEADERNAME);
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return request.getHeader(X_AUTH_TOKEN_HEADERNAME);
    }
}

