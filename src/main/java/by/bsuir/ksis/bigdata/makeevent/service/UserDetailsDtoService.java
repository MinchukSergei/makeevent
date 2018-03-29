package by.bsuir.ksis.bigdata.makeevent.service;

import by.bsuir.ksis.bigdata.makeevent.dto.UserDetailsDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserDetailsDtoService {
    UserDetailsDto createUser(UserDetailsDto userDetailsDto);
    boolean isUserExists(UserDetailsDto userDetailsDto);
    UserDetailsDto editUser(UserDetailsDto userDetailsDto);
    List<UserDetailsDto> getUsers(String filter);
}
