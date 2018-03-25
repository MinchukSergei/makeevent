package by.bsuir.ksis.bigdata.makeevent.service.impl;

import by.bsuir.ksis.bigdata.makeevent.dto.UserDetailsDto;
import by.bsuir.ksis.bigdata.makeevent.repository.UserDetailsDtoRepository;
import by.bsuir.ksis.bigdata.makeevent.service.UserDetailsDtoService;
import by.bsuir.ksis.bigdata.makeevent.util.MongoDbUtil;
import com.mongodb.client.MongoCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserDetailsDtoServiceImpl implements UserDetailsDtoService {
    @Resource
    private UserDetailsDtoRepository userDetailsDtoRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<UserDetailsDto> getUserDetailsDto() {
        MongoCollection userDetailsDtoCollection = MongoDbUtil.getCollectionByClass(mongoTemplate, UserDetailsDto.class);
        return null;
    }
}
