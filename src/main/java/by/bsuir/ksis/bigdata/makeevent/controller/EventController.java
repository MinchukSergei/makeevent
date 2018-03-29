package by.bsuir.ksis.bigdata.makeevent.controller;

import by.bsuir.ksis.bigdata.makeevent.dto.EventDto;
import by.bsuir.ksis.bigdata.makeevent.dto.UserDetailsDto;
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
        return null;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PATCH)
    public RequestResponse<EventDto> editEvent(@RequestBody EventDto eventDto,
                                               @AuthenticationPrincipal UserDetailsDto authUser) {
        return null;
    }

    @RequestMapping(value = "/{eventId}/{memberId}/add", method = RequestMethod.PATCH)
    public RequestResponse<List<UserDetailsDto>> addMember(@PathVariable("eventId") Long eventId,
                                                           @PathVariable("memberId") Long memberId,
                                                           @AuthenticationPrincipal UserDetailsDto authUser) {
        return null;
    }

    @RequestMapping(value = "/{eventId}/{memberId}/delete", method = RequestMethod.PATCH)
    public RequestResponse<List<UserDetailsDto>> deleteMember(@PathVariable("eventId") Long eventId,
                                                              @PathVariable("memberId") Long memberId,
                                                              @AuthenticationPrincipal UserDetailsDto authUser) {
        return null;
    }

    @RequestMapping(value = "/{eventId}/{groupId}/add", method = RequestMethod.PATCH)
    public RequestResponse<List<UserDetailsDto>> addMembersFromGroup(@PathVariable("eventId") Long eventId,
                                                                     @PathVariable("groupId") Long groupId,
                                                                     @AuthenticationPrincipal UserDetailsDto authUser) {
        return null;
    }

    @RequestMapping(value = "/{eventId}", method = RequestMethod.DELETE)
    public RequestResponse<Void> deleteEvent(@PathVariable("eventId") Long eventId,
                                             @AuthenticationPrincipal UserDetailsDto authUser) {
        return null;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public RequestResponse<List<EventDto>> getEvents(@RequestBody String filter) {
        return null;
    }

    @RequestMapping(value = "/my/get", method = RequestMethod.POST)
    public RequestResponse<List<EventDto>> getMyEvents(@RequestBody String filter,
                                                       @AuthenticationPrincipal UserDetailsDto authUser) {
        return null;
    }
}
