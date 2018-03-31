package by.bsuir.ksis.bigdata.makeevent.controller;

import by.bsuir.ksis.bigdata.makeevent.dto.GroupDto;
import by.bsuir.ksis.bigdata.makeevent.dto.UserDetailsDto;
import by.bsuir.ksis.bigdata.makeevent.exception.AccessDeniedException;
import by.bsuir.ksis.bigdata.makeevent.exception.UserNotFoundException;
import by.bsuir.ksis.bigdata.makeevent.service.GroupDtoService;
import by.bsuir.ksis.bigdata.makeevent.util.RequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private GroupDtoService groupDtoService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public RequestResponse<GroupDto> createGroup(@RequestBody GroupDto groupDto,
                                                 @AuthenticationPrincipal UserDetailsDto authUser) {
        RequestResponse<GroupDto> responseEntity = RequestResponse.createSuccessResponse();
        groupDto = groupDtoService.save(authUser, groupDto);
        responseEntity.getRequestData().put("group", groupDto);
        return responseEntity;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PATCH)
    public RequestResponse<GroupDto> editGroup(@RequestBody GroupDto groupDto,
                                              @AuthenticationPrincipal UserDetailsDto authUser) {
        RequestResponse<GroupDto> responseEntity = RequestResponse.createSuccessResponse();
        try {
            groupDto = groupDtoService.edit(authUser, groupDto);
        } catch (AccessDeniedException e) {
            responseEntity.setSuccess(false);
            responseEntity.getErrors().put("group.access.denied", String.format("Access to the group '%s' is denied", groupDto.getId()));
            return responseEntity;
        }
        responseEntity.getRequestData().put("group", groupDto);
        return responseEntity;
    }

    @RequestMapping(value = "/{groupId}/{memberId}/add", method = RequestMethod.PATCH)
    public RequestResponse<List<UserDetailsDto>> addMember(@PathVariable(value = "groupId") String groupId,
                                                          @PathVariable(value = "memberId") String memberId,
                                                          @AuthenticationPrincipal UserDetailsDto authUser) {
        RequestResponse<List<UserDetailsDto>> responseEntity = RequestResponse.createSuccessResponse();
        GroupDto groupDto;
        try {
            groupDto = groupDtoService.addMember(authUser, memberId, groupId);
        } catch (AccessDeniedException e) {
            responseEntity.setSuccess(false);
            responseEntity.getErrors().put("group.access.denied", String.format("Access to the group '%s' is denied", groupId));
            return responseEntity;
        } catch (UserNotFoundException e) {
            responseEntity.setSuccess(false);
            responseEntity.getErrors().put("user.not.found", String.format("User '%s' is not found", memberId));
            return responseEntity;
        }
        responseEntity.getRequestData().put("group.members", groupDto.getMembers());
        return responseEntity;
    }

    @RequestMapping(value = "/{groupId}/{memberId}/delete", method = RequestMethod.PATCH)
    public RequestResponse<List<UserDetailsDto>> deleteMember(@PathVariable(value = "groupId") String groupId,
                                                             @PathVariable(value = "memberId") String memberId,
                                                             @AuthenticationPrincipal UserDetailsDto authUser) {
        RequestResponse<List<UserDetailsDto>> responseEntity = RequestResponse.createSuccessResponse();
        GroupDto groupDto;
        try {
            groupDto = groupDtoService.deleteMember(authUser, memberId, groupId);
        } catch (AccessDeniedException e) {
            responseEntity.setSuccess(false);
            responseEntity.getErrors().put("group.access.denied", String.format("Access to the group '%s' is denied", groupId));
            return responseEntity;
        } catch (UserNotFoundException e) {
            responseEntity.setSuccess(false);
            responseEntity.getErrors().put("user.not.found", String.format("User '%s' is not found", memberId));
            return responseEntity;
        }
        responseEntity.getRequestData().put("group.members", groupDto.getMembers());
        return responseEntity;
    }

    @RequestMapping(value = "/{groupId}", method = RequestMethod.DELETE)
    public RequestResponse<Void> deleteGroup(@PathVariable(value = "groupId") String groupId,
                                            @AuthenticationPrincipal UserDetailsDto authUser) {
        RequestResponse<Void> responseEntity = RequestResponse.createSuccessResponse();
        try {
            groupDtoService.delete(authUser, groupId);
        } catch (AccessDeniedException e) {
            responseEntity.setSuccess(false);
            responseEntity.getErrors().put("group.access.denied", String.format("Access to the group '%s' is denied", groupId));
            return responseEntity;
        }
        return responseEntity;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public RequestResponse<List<GroupDto>> getMyGroups(@RequestBody String filter,
                                                      @AuthenticationPrincipal UserDetailsDto authUser) {
        RequestResponse<List<GroupDto>> responseEntity = RequestResponse.createSuccessResponse();
        List<GroupDto> groups = groupDtoService.get(authUser, filter);
        responseEntity.getRequestData().put("groups", groups);
        return responseEntity;
    }
}

