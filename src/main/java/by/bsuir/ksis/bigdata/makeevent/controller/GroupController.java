package by.bsuir.ksis.bigdata.makeevent.controller;

import by.bsuir.ksis.bigdata.makeevent.dto.GroupDto;
import by.bsuir.ksis.bigdata.makeevent.dto.UserDetailsDto;
import by.bsuir.ksis.bigdata.makeevent.service.GroupDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private GroupDtoService groupDtoService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<GroupDto> createGroup(@RequestBody GroupDto groupDto,
                                                @AuthenticationPrincipal UserDetailsDto authUser) {
        return null;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PATCH)
    public ResponseEntity<GroupDto> editGroup(@RequestBody GroupDto groupDto,
                                              @AuthenticationPrincipal UserDetailsDto authUser) {
        return null;
    }

    @RequestMapping(value = "/{groupId}/{memberId}/add", method = RequestMethod.PATCH)
    public ResponseEntity<List<UserDetailsDto>> addMember(@PathVariable(value = "groupId") Long groupId,
                                                          @PathVariable(value = "memberId") Long memberId,
                                                          @AuthenticationPrincipal UserDetailsDto authUser) {
        return null;
    }

    @RequestMapping(value = "/{groupId}/{memberId}/delete", method = RequestMethod.PATCH)
    public ResponseEntity<List<UserDetailsDto>> deleteMember(@PathVariable(value = "groupId") Long groupId,
                                                             @PathVariable(value = "memberId") Long memberId,
                                                             @AuthenticationPrincipal UserDetailsDto authUser) {
        return null;
    }

    @RequestMapping(value = "/{groupId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteGroup(@PathVariable(value = "groupId") Long groupId,
                                            @AuthenticationPrincipal UserDetailsDto authUser) {
        return null;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public ResponseEntity<List<GroupDto>> getMyGroups(@RequestBody String filter,
                                                      @AuthenticationPrincipal UserDetailsDto authUser) {
        return null;
    }
}

