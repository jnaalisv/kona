package kona.model.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class DomainConfiguration {

    /**
     * Use BCrypt password encoding with default strength of 10 log rounds.
     *
     * @return BCryptPasswordEncoder instance
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
