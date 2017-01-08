package kona.model.application;

import kona.infrastructure.persistence.DummyUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class UserService implements UserDetailsService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final DummyUserRepository dummyUserRepository;

    @Inject
    public UserService(BCryptPasswordEncoder passwordEncoder, DummyUserRepository dummyUserRepository) {
        this.passwordEncoder = passwordEncoder;
        this.dummyUserRepository = dummyUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return dummyUserRepository.loadUserByUsername(username);
        // TODO: check if found
    }
}
