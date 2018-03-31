package by.bsuir.ksis.bigdata.makeevent.repository;

import by.bsuir.ksis.bigdata.makeevent.dto.EventDto;
import by.bsuir.ksis.bigdata.makeevent.repository.extended.ExtendedEventDtoRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventDtoRepository extends MongoRepository<EventDto, String>,
        ExtendedEventDtoRepository {
}
