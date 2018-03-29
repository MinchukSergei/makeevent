package by.bsuir.ksis.bigdata.makeevent.repository.extended;

import by.bsuir.ksis.bigdata.makeevent.dto.UserDetailsDto;

import java.util.List;

public interface ExtendedUserDetailsDtoRepository {
    List<UserDetailsDto> getUsers(String filter);
}
