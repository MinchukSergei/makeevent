package by.bsuir.ksis.bigdata.makeevent.controller;

import by.bsuir.ksis.bigdata.makeevent.dto.UserDetails;
import by.bsuir.ksis.bigdata.makeevent.repository.UserDetailsRepository;
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
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    protected RequestResponse<List<UserDetails>> getUsers() {
        RequestResponse<List<UserDetails>> requestResponse = RequestResponse.createSuccessResponse();
        List<UserDetails> users = userDetailsRepository.findAll();
        requestResponse.getRequestData().put("users", users);
        return requestResponse;
    }

    @RequestMapping(value = "/generateUsers/{count}", method = RequestMethod.GET)
    protected RequestResponse generateUsers(@PathVariable(value = "count") Integer count) {
        RequestResponse requestResponse = RequestResponse.createSuccessResponse();
        List<UserDetails> users = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            UserDetails userDetails = new UserDetails();
            userDetails.setEmail("hi" + i);
            userDetails.setFirstName(RandomStringUtils.randomAlphabetic(5));
            userDetails.setLastName(RandomStringUtils.randomAlphabetic(7));
            userDetails.setPassword(bCryptPasswordEncoder.encode("hi"));
            users.add(userDetails);
        }
        userDetailsRepository.saveAll(users);
        return requestResponse;
    }
}
