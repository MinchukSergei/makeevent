package by.bsuir.ksis.bigdata.makeevent.controller;

import by.bsuir.ksis.bigdata.makeevent.dto.UserDetailsDto;
import by.bsuir.ksis.bigdata.makeevent.service.UserDetailsDtoService;
import by.bsuir.ksis.bigdata.makeevent.util.RequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserDetailsDtoService userDetailsDtoService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public RequestResponse createUser(@RequestBody UserDetailsDto userDetailsDto) {
        RequestResponse<UserDetailsDto> requestResponse = RequestResponse.createSuccessResponse();
        if (userDetailsDtoService.isUserExists(userDetailsDto)) {
            requestResponse.setSuccess(false);
            requestResponse.getErrors().put("user.unique", "Email is already in use.");
        } else {
            userDetailsDto = userDetailsDtoService.createUser(userDetailsDto);
            requestResponse.getRequestData().put("user", userDetailsDto);
        }
        return requestResponse;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PATCH)
    public RequestResponse editUser(@RequestBody UserDetailsDto userDetailsDto, @AuthenticationPrincipal UserDetailsDto authUser) {
        RequestResponse<UserDetailsDto> requestResponse = RequestResponse.createSuccessResponse();
//        String getUserFromSession = "someId";//TODO need to retrieve old user and edit it
        userDetailsDto.setId(authUser.getId());
        userDetailsDto = userDetailsDtoService.editUser(userDetailsDto);
        requestResponse.getRequestData().put("user", userDetailsDto);
        return requestResponse;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public RequestResponse<List<UserDetailsDto>> getUsers(@RequestBody String filter) {
        RequestResponse<List<UserDetailsDto>> requestResponse = RequestResponse.createSuccessResponse();
        List<UserDetailsDto> users = userDetailsDtoService.getUsers(filter);
        requestResponse.getRequestData().put("users", users);
        return requestResponse;
    }
}
