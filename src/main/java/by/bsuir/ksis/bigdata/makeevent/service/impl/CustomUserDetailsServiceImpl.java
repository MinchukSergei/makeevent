package by.bsuir.ksis.bigdata.makeevent.service.impl;

import by.bsuir.ksis.bigdata.makeevent.dto.GroupDto;
import by.bsuir.ksis.bigdata.makeevent.dto.UserDetails;
import by.bsuir.ksis.bigdata.makeevent.dto.event.EventDto;
import by.bsuir.ksis.bigdata.makeevent.dto.event.scope.EventScopeItem;
import by.bsuir.ksis.bigdata.makeevent.dto.event.scope.GroupEventScopeItem;
import by.bsuir.ksis.bigdata.makeevent.dto.event.scope.UserEventScopeItem;
import by.bsuir.ksis.bigdata.makeevent.repository.UserDetailsRepository;
import by.bsuir.ksis.bigdata.makeevent.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component("customUserDetailsService")
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService, UserDetailsService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetails userDetails = userDetailsRepository.findByEmail(s);
        EventDto eventDto = new EventDto();
        List<EventScopeItem> eventScopeItems = new ArrayList<>();
        eventScopeItems.add(new GroupEventScopeItem(new GroupDto()));
        eventDto.setEventScopeItems(eventScopeItems);
        if (userDetails != null) {
            return new User(userDetails.getEmail(), userDetails.getPassword(), new HashSet<>());
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
    }
}
