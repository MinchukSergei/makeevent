package by.bsuir.ksis.bigdata.makeevent.controller;

import by.bsuir.ksis.bigdata.makeevent.dto.EventDto;
import by.bsuir.ksis.bigdata.makeevent.dto.UserDetailsDto;
import by.bsuir.ksis.bigdata.makeevent.exception.AccessDeniedException;
import by.bsuir.ksis.bigdata.makeevent.exception.UserNotFoundException;
import by.bsuir.ksis.bigdata.makeevent.service.EventDtoService;
import by.bsuir.ksis.bigdata.makeevent.util.RequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    private EventDtoService eventDtoService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public RequestResponse<EventDto> createEvent(@RequestBody EventDto eventDto,
                                                 @AuthenticationPrincipal UserDetailsDto authUser) {
        RequestResponse<EventDto> responseEntity = RequestResponse.createSuccessResponse();
        eventDto = eventDtoService.save(authUser, eventDto);
        responseEntity.getRequestData().put("event", eventDto);
        return responseEntity;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PATCH)
    public RequestResponse<EventDto> editEvent(@RequestBody EventDto eventDto,
                                               @AuthenticationPrincipal UserDetailsDto authUser) {
        RequestResponse<EventDto> responseEntity = RequestResponse.createSuccessResponse();
        try {
            eventDto = eventDtoService.edit(authUser, eventDto);
        } catch (AccessDeniedException e) {
            responseEntity.setSuccess(false);
            responseEntity.getErrors().put("event.access.denied", String.format("Access to the event '%s' is denied", eventDto.getId()));
            return responseEntity;
        }
        responseEntity.getRequestData().put("event", eventDto);
        return responseEntity;
    }

    @RequestMapping(value = "/{eventId}/{participantId}/add", method = RequestMethod.PATCH)
    public RequestResponse<List<UserDetailsDto>> addParticipant(@PathVariable("eventId") String eventId,
                                                           @PathVariable("participantId") String participantId,
                                                           @AuthenticationPrincipal UserDetailsDto authUser) {
        RequestResponse<List<UserDetailsDto>> responseEntity = RequestResponse.createSuccessResponse();
        EventDto eventDto;
        try {
            eventDto = eventDtoService.addParticipant(authUser, participantId, eventId);
        } catch (AccessDeniedException e) {
            responseEntity.setSuccess(false);
            responseEntity.getErrors().put("event.access.denied", String.format("Access to the event '%s' is denied", eventId));
            return responseEntity;
        } catch (UserNotFoundException e) {
            responseEntity.setSuccess(false);
            responseEntity.getErrors().put("user.not.found", String.format("User '%s' is not found", participantId));
            return responseEntity;
        }
        responseEntity.getRequestData().put("event.participants", eventDto.getParticipants());
        return responseEntity;
    }

    @RequestMapping(value = "/{eventId}/{memberId}/delete", method = RequestMethod.PATCH)
    public RequestResponse<List<UserDetailsDto>> deleteMember(@PathVariable("eventId") String eventId,
                                                              @PathVariable("memberId") String memberId,
                                                              @AuthenticationPrincipal UserDetailsDto authUser) {
        RequestResponse<List<UserDetailsDto>> responseEntity = RequestResponse.createSuccessResponse();
        EventDto eventDto;
        try {
            eventDto = eventDtoService.deleteParticipant(authUser, memberId, eventId);
        } catch (AccessDeniedException e) {
            responseEntity.setSuccess(false);
            responseEntity.getErrors().put("event.access.denied", String.format("Access to the event '%s' is denied", eventId));
            return responseEntity;
        } catch (UserNotFoundException e) {
            responseEntity.setSuccess(false);
            responseEntity.getErrors().put("user.not.found", String.format("User '%s' is not found", memberId));
            return responseEntity;
        }
        responseEntity.getRequestData().put("event.participants", eventDto.getParticipants());
        return responseEntity;
    }

    @RequestMapping(value = "/{eventId}/{groupId}/add", method = RequestMethod.PATCH)
    public RequestResponse<List<UserDetailsDto>> addParticipantsFromGroup(@PathVariable("eventId") String eventId,
                                                                          @PathVariable("groupId") String groupId,
                                                                          @AuthenticationPrincipal UserDetailsDto authUser) {
        RequestResponse<List<UserDetailsDto>> responseEntity = RequestResponse.createSuccessResponse();
        EventDto eventDto;
        try {
            eventDto = eventDtoService.addParticipantsFromGroup(authUser, groupId, eventId);
        } catch (AccessDeniedException e) {
            responseEntity.setSuccess(false);
            String errorMessage = "";
            switch (e.getMessage()) {
                case "event.access.denied":
                    errorMessage = String.format("Access to the event '%s' is denied", eventId);
                    break;
                case "group.access.denied":
                    errorMessage = String.format("Access to the group '%s' is denied", groupId);
                    break;
            }
            responseEntity.getErrors().put(e.getMessage(), errorMessage);
            return responseEntity;
        }
        responseEntity.getRequestData().put("event.participants", eventDto.getParticipants());
        return responseEntity;
    }

    @RequestMapping(value = "/{eventId}", method = RequestMethod.DELETE)
    public RequestResponse<Void> deleteEvent(@PathVariable("eventId") String eventId,
                                             @AuthenticationPrincipal UserDetailsDto authUser) {
        RequestResponse<Void> responseEntity = RequestResponse.createSuccessResponse();
        try {
            eventDtoService.delete(authUser, eventId);
        } catch (AccessDeniedException e) {
            responseEntity.setSuccess(false);
            responseEntity.getErrors().put("event.access.denied", String.format("Access to the event '%s' is denied", eventId));
            return responseEntity;
        }
        return responseEntity;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public RequestResponse<List<EventDto>> getEvents(@RequestBody String filter) {
        RequestResponse<List<EventDto>> responseEntity = RequestResponse.createSuccessResponse();
        List<EventDto> events = eventDtoService.get(filter);
        responseEntity.getRequestData().put("events", events);
        return responseEntity;
    }

    @RequestMapping(value = "/my/get", method = RequestMethod.POST)
    public RequestResponse<List<EventDto>> getMyEvents(@RequestBody String filter,
                                                       @AuthenticationPrincipal UserDetailsDto authUser) {
        RequestResponse<List<EventDto>> responseEntity = RequestResponse.createSuccessResponse();
        List<EventDto> events = eventDtoService.getMy(authUser, filter);
        responseEntity.getRequestData().put("events", events);
        return responseEntity;
    }
}
