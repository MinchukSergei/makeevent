package by.bsuir.ksis.bigdata.makeevent.repository.extended;

import by.bsuir.ksis.bigdata.makeevent.dto.EventDto;

import java.util.List;

public interface ExtendedEventDtoRepository {
    List<EventDto> getEvents(String filter);
}
