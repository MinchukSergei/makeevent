package by.bsuir.ksis.bigdata.makeevent.service;

import by.bsuir.ksis.bigdata.makeevent.dto.GroupDto;
import by.bsuir.ksis.bigdata.makeevent.dto.UserDetailsDto;
import by.bsuir.ksis.bigdata.makeevent.exception.AccessDeniedException;

import java.util.List;

public interface GroupDtoService {
    GroupDto save(UserDetailsDto authUser, GroupDto groupDto);
    GroupDto edit(UserDetailsDto authUser, GroupDto groupDto) throws AccessDeniedException;
    List<GroupDto> get(UserDetailsDto authUser, String filter);
    GroupDto addMember(UserDetailsDto authUser, Long memberId, Long groupId);
    GroupDto deleteMember(UserDetailsDto authUser, Long memberId, Long groupId);
    void delete(UserDetailsDto authUser, Long groupId);
}
