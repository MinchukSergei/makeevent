package by.bsuir.ksis.bigdata.makeevent.service;

import by.bsuir.ksis.bigdata.makeevent.dto.GroupDto;
import by.bsuir.ksis.bigdata.makeevent.dto.UserDetailsDto;
import by.bsuir.ksis.bigdata.makeevent.exception.AccessDeniedException;
import by.bsuir.ksis.bigdata.makeevent.exception.UserNotFoundException;

import java.util.List;

public interface GroupDtoService {
    GroupDto save(UserDetailsDto authUser, GroupDto groupDto);
    GroupDto edit(UserDetailsDto authUser, GroupDto groupDto) throws AccessDeniedException;
    List<GroupDto> get(UserDetailsDto authUser, String filter);
    GroupDto addMember(UserDetailsDto authUser, String memberId, String groupId) throws AccessDeniedException, UserNotFoundException;
    GroupDto deleteMember(UserDetailsDto authUser, String memberId, String groupId) throws AccessDeniedException, UserNotFoundException;
    void delete(UserDetailsDto authUser, String groupId) throws AccessDeniedException;
}
