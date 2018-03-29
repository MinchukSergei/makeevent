package by.bsuir.ksis.bigdata.makeevent.repository;

import by.bsuir.ksis.bigdata.makeevent.dto.UserDetailsDto;
import by.bsuir.ksis.bigdata.makeevent.repository.extended.ExtendedUserDetailsDtoRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserDetailsDtoRepository extends MongoRepository<UserDetailsDto, String>,
        ExtendedUserDetailsDtoRepository {
    UserDetailsDto findByEmail(String email);
}
