package by.bsuir.ksis.bigdata.makeevent.service;

import by.bsuir.ksis.bigdata.makeevent.dto.EventDto;
import by.bsuir.ksis.bigdata.makeevent.dto.UserDetailsDto;
import by.bsuir.ksis.bigdata.makeevent.exception.AccessDeniedException;
import by.bsuir.ksis.bigdata.makeevent.exception.UserNotFoundException;

import java.util.List;

public interface EventDtoService {
    EventDto save(UserDetailsDto authUser, EventDto eventDto);
    EventDto edit(UserDetailsDto authUser, EventDto eventDto) throws AccessDeniedException;
    EventDto addParticipant(UserDetailsDto authUser, String memberId, String eventId) throws AccessDeniedException, UserNotFoundException;
    EventDto addParticipantsFromGroup(UserDetailsDto authUser, String groupId, String eventId) throws AccessDeniedException;
    EventDto deleteParticipant(UserDetailsDto authUser, String memberId, String eventId) throws AccessDeniedException, UserNotFoundException;
    void delete(UserDetailsDto authUser, String eventId) throws AccessDeniedException;
    List<EventDto> get(String filter);
    List<EventDto> getMy(UserDetailsDto authUser, String filter);
}
