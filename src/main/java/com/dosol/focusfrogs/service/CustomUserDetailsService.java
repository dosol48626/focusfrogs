package com.dosol.focusfrogs.service;

import com.dosol.focusfrogs.domain.User;
import com.dosol.focusfrogs.dto.CustomUserDetails;
import com.dosol.focusfrogs.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User userData = userRepository.findByUsername(username);
        if (userData != null) {
            //널이면 데이터가 없는거고
            //널이 아니면 있기때문에 로그인 진행하면 된다.
            //로그인 할때 DB에 그 이름있는지 확인하는거임
            return new CustomUserDetails(userData);
        }

        return null;
    }
}
