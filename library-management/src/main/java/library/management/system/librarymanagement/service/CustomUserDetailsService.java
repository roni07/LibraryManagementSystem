package library.management.system.librarymanagement.service;

import library.management.system.librarymanagement.model.User;
import library.management.system.librarymanagement.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) {

        Optional<User> optionalUser = userRepository.findUserByUserId(userId);

        optionalUser.orElseThrow(() -> new UsernameNotFoundException("User Id Not found"));

        return optionalUser.get();
    }

}
