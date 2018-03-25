package by.bsuir.ksis.bigdata.makeevent.repository;

import by.bsuir.ksis.bigdata.makeevent.dto.GroupDto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GroupDtoRepository extends MongoRepository<String, GroupDto> {
}
