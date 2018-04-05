package kona.model.application;

import kona.persistence.impl.HibernateUserRepository;
import kona.domain.user.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final HibernateUserRepository hibernateUserRepository;

    public UserService(BCryptPasswordEncoder passwordEncoder, HibernateUserRepository hibernateUserRepository) {
        this.passwordEncoder = passwordEncoder;
        this.hibernateUserRepository = hibernateUserRepository;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> maybeUser = hibernateUserRepository.loadUserByUsername(username);

        return maybeUser
                .map(UserDetailsImpl::new)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
