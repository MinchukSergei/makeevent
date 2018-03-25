package by.bsuir.ksis.bigdata.makeevent.repository;

import by.bsuir.ksis.bigdata.makeevent.dto.UserDetailsDto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserDetailsDtoRepository extends MongoRepository<UserDetailsDto, String> {
    UserDetailsDto findByEmail(String email);
}
