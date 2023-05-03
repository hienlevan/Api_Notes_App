package com.notes_api.securities;
import com.notes_api.enntities.User;
import com.notes_api.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(()->
                        new UsernameNotFoundException(String.format("User có email %s không tồn tại", email)));
        return getUserDetails(user);
    }
    private JwtUserDetails getUserDetails(User user) {
        return new JwtUserDetails(
                user.getEmail(),
                user.getPassword()
        );
    }
}
