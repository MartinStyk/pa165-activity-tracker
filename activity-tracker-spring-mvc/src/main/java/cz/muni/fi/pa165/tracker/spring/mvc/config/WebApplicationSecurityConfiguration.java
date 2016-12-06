package cz.muni.fi.pa165.tracker.spring.mvc.config;

import cz.muni.fi.pa165.tracker.dto.UserDTO;
import cz.muni.fi.pa165.tracker.enums.UserRole;
import cz.muni.fi.pa165.tracker.facade.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.inject.Inject;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

/**
 * Configure security context of application.
 *
 * @author Martin Styk
 * @version 5.12.2016
 */
@Configuration
@EnableWebSecurity
public class WebApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final Logger log = LoggerFactory.getLogger(WebApplicationSecurityConfiguration.class);

    @Inject
    private UserFacade userFacade;

    /**
     * Initialize authentication in our application.
     * Initialize user roles
     */
    @Inject
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        log.info("Configuring security - global users");
        List<UserDTO> userDTOs = userFacade.findAll();

        for (UserDTO userDTO : userDTOs) {
            if (UserRole.ADMIN.equals(userDTO.getRole())) {
                log.debug("Admin : " + userDTO.getEmail());
                auth.inMemoryAuthentication().passwordEncoder(passwordEncoder())
                        .withUser(userDTO.getEmail()).password(userDTO.getPasswordHash()).roles("ADMIN");
            } else {
                log.debug("User : " + userDTO.getEmail());
                auth.inMemoryAuthentication().passwordEncoder(passwordEncoder())
                        .withUser(userDTO.getEmail()).password(userDTO.getPasswordHash()).roles("USER");
            }
        }
    }

    /**
     * Configure secured URLs inside out application
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/sports/create/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/sports/update/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/sports/remove/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/sports/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .and()
                .formLogin()
                .loginPage("/login").loginProcessingUrl("/j_spring_security_check")
                .failureUrl("/login?error=invalidLoginAttempt")
                .usernameParameter("user").passwordParameter("pass")
                .and()
                .logout().logoutSuccessUrl("/")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .and()
                .csrf().disable();

    }

    /**
     * We need to provide password encoding corresponding to way how we encode passwords during user creation.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence password) {
                final int saltByteSize = 24;
                final int hashByteSize = 24;
                final int numberIterations = 1000;
                // Generate a random salt
                SecureRandom random = new SecureRandom();
                byte[] salt = new byte[saltByteSize];
                random.nextBytes(salt);
                // Hash the password
                byte[] hash = pbkdf2(password.toString().toCharArray(), salt, numberIterations, hashByteSize);
                // format iterations:salt:hash
                return numberIterations + ":" + toHex(salt) + ":" + toHex(hash);
            }

            @Override
            public boolean matches(CharSequence password, String correctHash) {
                if (password == null) {
                    return false;
                }
                if (correctHash == null) {
                    throw new IllegalArgumentException("password hash is null");
                }
                String[] params = correctHash.split(":");
                int iterations = Integer.parseInt(params[0]);
                byte[] salt = fromHex(params[1]);
                byte[] hash = fromHex(params[2]);
                byte[] testHash = pbkdf2(password.toString().toCharArray(), salt, iterations, hash.length);
                return slowEquals(hash, testHash);
            }

            private byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes) {
                try {
                    PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
                    return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            /**
             * Compares two byte arrays in length-constant time. This comparison method
             * is used so that password hashes cannot be extracted from an on-line
             * system using a timing attack and then attacked off-line.
             *
             * @param a the first byte array
             * @param b the second byte array
             * @return true if both byte arrays are the same, false if not
             */
            private boolean slowEquals(byte[] a, byte[] b) {
                int diff = a.length ^ b.length;
                for (int i = 0; i < a.length && i < b.length; i++) {
                    diff |= a[i] ^ b[i];
                }
                return diff == 0;
            }

            private byte[] fromHex(String hex) {
                byte[] binary = new byte[hex.length() / 2];
                for (int i = 0; i < binary.length; i++) {
                    binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
                }
                return binary;
            }

            private String toHex(byte[] array) {
                BigInteger bigInt = new BigInteger(1, array);
                String hex = bigInt.toString(16);
                int paddingLength = (array.length * 2) - hex.length();
                return paddingLength > 0 ? String.format("%0" + paddingLength + "d", 0) + hex : hex;
            }
        };
    }


}