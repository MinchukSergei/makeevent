package by.bsuir.ksis.bigdata.makeevent.service.impl;

import by.bsuir.ksis.bigdata.makeevent.dto.UserDetailsDto;
import by.bsuir.ksis.bigdata.makeevent.repository.UserDetailsDtoRepository;
import by.bsuir.ksis.bigdata.makeevent.service.AuthUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component("authUserDetailsServiceImpl")
public class AuthUserDetailsServiceImpl implements AuthUserDetailsService, UserDetailsService {

    @Autowired
    private UserDetailsDtoRepository userDetailsDtoRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetailsDto userDetailsDto = userDetailsDtoRepository.findByEmail(s);
        if (userDetailsDto != null) {
            return new User(userDetailsDto.getEmail(), userDetailsDto.getPassword(), new HashSet<>());
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
    }
}
