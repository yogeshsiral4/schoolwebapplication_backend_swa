package com.swa.config;
import com.swa.entity.User;
import com.swa.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = repository.findById(email);
        try {
        	return user.map(UserInfoUserDetails::new)
                    .orElseThrow(() -> new UsernameNotFoundException("user not found " + email));
			
		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
			return null;
		}
    }
}
