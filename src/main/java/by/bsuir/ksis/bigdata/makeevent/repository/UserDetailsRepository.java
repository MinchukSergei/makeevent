package by.bsuir.ksis.bigdata.makeevent.repository;

import by.bsuir.ksis.bigdata.makeevent.dto.UserDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserDetailsRepository extends MongoRepository<UserDetails, String> {
    UserDetails findByEmail(String email);
}
