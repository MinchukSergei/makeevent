package by.bsuir.ksis.bigdata.makeevent.controller;

import by.bsuir.ksis.bigdata.makeevent.dto.UserDetailsDto;
import by.bsuir.ksis.bigdata.makeevent.repository.UserDetailsDtoRepository;
import by.bsuir.ksis.bigdata.makeevent.util.RequestResponse;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dev/")
public class MainController {
    @Resource
    private UserDetailsDtoRepository userDetailsDtoRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    protected RequestResponse<List<UserDetailsDto>> getUsers() {
        RequestResponse<List<UserDetailsDto>> requestResponse = RequestResponse.createSuccessResponse();
        List<UserDetailsDto> users = userDetailsDtoRepository.findAll();
        requestResponse.getRequestData().put("users", users);
        return requestResponse;
    }

    @RequestMapping(value = "/generateUsers/{count}", method = RequestMethod.GET)
    protected RequestResponse generateUsers(@PathVariable(value = "count") Integer count) {
        RequestResponse requestResponse = RequestResponse.createSuccessResponse();
        List<UserDetailsDto> users = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            UserDetailsDto userDetailsDto = new UserDetailsDto();
            userDetailsDto.setEmail("hi" + i);
            userDetailsDto.setFirstName(RandomStringUtils.randomAlphabetic(5));
            userDetailsDto.setLastName(RandomStringUtils.randomAlphabetic(7));
            userDetailsDto.setPassword(bCryptPasswordEncoder.encode("hi"));
            users.add(userDetailsDto);
        }
        userDetailsDtoRepository.saveAll(users);
        return requestResponse;
    }
}
