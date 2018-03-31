package by.bsuir.ksis.bigdata.makeevent.repository.impl;

import by.bsuir.ksis.bigdata.makeevent.dto.EventDto;
import by.bsuir.ksis.bigdata.makeevent.dto.QEventDto;
import by.bsuir.ksis.bigdata.makeevent.repository.extended.ExtendedEventDtoRepository;
import com.querydsl.mongodb.morphia.MorphiaQuery;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExtendedEventDtoRepositoryImpl implements ExtendedEventDtoRepository {
    @Autowired
    private Morphia morphia;

    @Autowired
    private Datastore datastore;

    @Override
    public List<EventDto> getEvents(String filter) {
        QEventDto qEventDto = QEventDto.eventDto;
        MorphiaQuery<EventDto> morphiaQuery = new MorphiaQuery<>(morphia, datastore, qEventDto);

        return morphiaQuery.where(qEventDto.name.contains(filter)
                .and(qEventDto.description.contains(filter))).fetch();
    }
}
