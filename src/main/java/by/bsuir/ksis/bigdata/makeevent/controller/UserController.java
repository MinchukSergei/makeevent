package by.bsuir.ksis.bigdata.makeevent.controller;

import by.bsuir.ksis.bigdata.makeevent.dto.UserDetailsDto;
import by.bsuir.ksis.bigdata.makeevent.util.RequestResponse;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserDetailsService userDetailsService;

    @RequestMapping(value = "/all", method = RequestMethod.POST)
    public RequestResponse<List<UserDetailsDto>> getUsers() {
        RequestResponse<List<UserDetailsDto>> requestResponse = RequestResponse.createSuccessResponse();
        userDetailsService.
        return requestResponse;
    }

}
