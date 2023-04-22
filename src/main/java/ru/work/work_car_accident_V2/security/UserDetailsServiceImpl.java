package ru.work.work_car_accident_V2.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.work.work_car_accident_V2.model.User;
import ru.work.work_car_accident_V2.repository.JpaUserRepository;

@Service("UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final JpaUserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(JpaUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User rsl = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User don't exist."));
        return SecurityUser.fromUser(rsl);
    }
}
