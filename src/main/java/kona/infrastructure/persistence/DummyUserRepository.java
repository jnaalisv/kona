package kona.infrastructure.persistence;

import kona.domain.user.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

@Repository
public class DummyUserRepository implements UserDetailsService {

    private final BCryptPasswordEncoder passwordEncoder;

    @Inject
    public DummyUserRepository(final BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User(username, passwordEncoder.encode(username));
    }
}
