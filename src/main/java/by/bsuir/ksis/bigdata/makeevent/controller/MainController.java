package by.bsuir.ksis.bigdata.makeevent.controller;

import by.bsuir.ksis.bigdata.makeevent.dto.UserData;
import by.bsuir.ksis.bigdata.makeevent.repository.UserDataRepository;
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
    private UserDataRepository userDataRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    protected RequestResponse<List<UserData>> getUsers() {
        RequestResponse<List<UserData>> requestResponse = RequestResponse.createSuccessResponse();
        List<UserData> users = userDataRepository.findAll();
        requestResponse.getRequestData().put("users", users);
        return requestResponse;
    }

    @RequestMapping(value = "/generateUsers/{count}", method = RequestMethod.GET)
    protected RequestResponse generateUsers(@PathVariable(value = "count") Integer count) {
        RequestResponse requestResponse = RequestResponse.createSuccessResponse();
        List<UserData> users = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            UserData userData = new UserData();
            userData.setEmail("hi" + i);
            userData.setFirstName(RandomStringUtils.randomAlphabetic(5));
            userData.setLastName(RandomStringUtils.randomAlphabetic(7));
            userData.setPassword(bCryptPasswordEncoder.encode("hi"));
            users.add(userData);
        }
        userDataRepository.saveAll(users);
        return requestResponse;
    }
}
