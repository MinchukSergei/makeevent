package by.bsuir.ksis.bigdata.makeevent.service.impl;

import by.bsuir.ksis.bigdata.makeevent.dto.EventDto;
import by.bsuir.ksis.bigdata.makeevent.dto.GroupDto;
import by.bsuir.ksis.bigdata.makeevent.dto.UserDetailsDto;
import by.bsuir.ksis.bigdata.makeevent.exception.AccessDeniedException;
import by.bsuir.ksis.bigdata.makeevent.exception.UserNotFoundException;
import by.bsuir.ksis.bigdata.makeevent.repository.EventDtoRepository;
import by.bsuir.ksis.bigdata.makeevent.repository.UserDetailsDtoRepository;
import by.bsuir.ksis.bigdata.makeevent.service.EventDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventDtoServiceImpl implements EventDtoService {
    @Autowired
    private EventDtoRepository eventDtoRepository;

    @Autowired
    private UserDetailsDtoRepository userDetailsDtoRepository;

    @Override
    public EventDto save(UserDetailsDto authUser, EventDto eventDto) {
        eventDto.setId(null);
        eventDto.setOwner(authUser);
        return eventDtoRepository.save(eventDto);
    }

    @Override
    public EventDto edit(UserDetailsDto authUser, EventDto eventDto) throws AccessDeniedException {
        if (eventDto.getId() == null) {
            eventDto.setOwner(authUser);
            return eventDtoRepository.save(eventDto);
        } else {
            Optional<EventDto> oldEvent = authUser.getEvents()
                    .stream()
                    .filter(e -> e.getId().equals(eventDto.getId()))
                    .findFirst();
            if (oldEvent.isPresent()) {
                eventDto.setOwner(authUser);
                return eventDtoRepository.save(eventDto);
            } else {
                throw new AccessDeniedException();
            }
        }
    }

    @Override
    public EventDto addParticipant(UserDetailsDto authUser, String participantId, String eventId) throws AccessDeniedException, UserNotFoundException {
        List<EventDto> events = authUser.getEvents();
        Optional<UserDetailsDto> optionalUser = userDetailsDtoRepository.findById(participantId);
        Optional<EventDto> optionalEvent = events.stream().filter(e -> e.getId().equals(eventId)).findFirst();

        if (!optionalEvent.isPresent()) {
            throw new AccessDeniedException();
        }
        if (!optionalUser.isPresent()) {
            throw new UserNotFoundException();
        }

        EventDto event = optionalEvent.get();
        event.getParticipants().add(optionalUser.get());
        return eventDtoRepository.save(event);
    }

    @Override
    public EventDto addParticipantsFromGroup(UserDetailsDto authUser, String groupId, String eventId) throws AccessDeniedException {
        List<EventDto> events = authUser.getEvents();
        List<GroupDto> groups = authUser.getGroups();
        Optional<GroupDto> optionalGroup = groups.stream().filter(g -> g.getId().equals(groupId)).findFirst();
        Optional<EventDto> optionalEvent = events.stream().filter(e -> e.getId().equals(eventId)).findFirst();

        if (!optionalEvent.isPresent()) {
            throw new AccessDeniedException("event.access.denied");
        }
        if (!optionalGroup.isPresent()) {
            throw new AccessDeniedException("group.access.denied");
        }

        EventDto event = optionalEvent.get();
        GroupDto group = optionalGroup.get();
        event.getParticipants().addAll(group.getMembers());
        return eventDtoRepository.save(event);
    }

    @Override
    public EventDto deleteParticipant(UserDetailsDto authUser, String participantId, String eventId) throws AccessDeniedException, UserNotFoundException {
        List<EventDto> events = authUser.getEvents();
        Optional<UserDetailsDto> optionalUser = userDetailsDtoRepository.findById(participantId);
        Optional<EventDto> optionalEvent = events.stream().filter(e -> e.getId().equals(eventId)).findFirst();

        if (!optionalEvent.isPresent()) {
            throw new AccessDeniedException();
        }
        if (!optionalUser.isPresent()) {
            throw new UserNotFoundException();
        }

        EventDto event = optionalEvent.get();
        event.getParticipants().removeIf(e -> e.getId().equals(participantId));
        return eventDtoRepository.save(event);
    }

    @Override
    public void delete(UserDetailsDto authUser, String eventId) throws AccessDeniedException {
        List<EventDto> events = authUser.getEvents();
        Optional<EventDto> optionalEvent = events.stream().filter(e -> e.getId().equals(eventId)).findFirst();

        if (!optionalEvent.isPresent()) {
            throw new AccessDeniedException();
        }

        eventDtoRepository.delete(optionalEvent.get());
    }

    @Override
    public List<EventDto> get(String filter) {
        return eventDtoRepository.getEvents(filter);
    }

    @Override
    public List<EventDto> getMy(UserDetailsDto authUser, String filter) {
        List<EventDto> events = authUser.getEvents();
        return events
                .stream()
                .filter(e -> e.getName().contains(filter) || e.getDescription().contains(filter))
                .collect(Collectors.toList());
    }
}
