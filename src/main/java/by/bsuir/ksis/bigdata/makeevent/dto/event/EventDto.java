package by.bsuir.ksis.bigdata.makeevent.dto.event;

import by.bsuir.ksis.bigdata.makeevent.dto.UserDetails;
import by.bsuir.ksis.bigdata.makeevent.dto.event.scope.EventScopeItem;
import org.springframework.data.annotation.Id;

import java.util.Calendar;
import java.util.List;

public class EventDto {
    @Id
    private String id;
    private String name;
    private Double mapCoordinateX;
    private Double mapCoordinateY;
    private Calendar date;
    private String description;
    private UserDetails owner;
    private List<UserDetails> participants;
    private List<EventScopeItem> eventScopeItems;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMapCoordinateX() {
        return mapCoordinateX;
    }

    public void setMapCoordinateX(Double mapCoordinateX) {
        this.mapCoordinateX = mapCoordinateX;
    }

    public Double getMapCoordinateY() {
        return mapCoordinateY;
    }

    public void setMapCoordinateY(Double mapCoordinateY) {
        this.mapCoordinateY = mapCoordinateY;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserDetails getOwner() {
        return owner;
    }

    public void setOwner(UserDetails owner) {
        this.owner = owner;
    }

    public List<UserDetails> getParticipants() {
        return participants;
    }

    public void setParticipants(List<UserDetails> participants) {
        this.participants = participants;
    }

    public List<EventScopeItem> getEventScopeItems() {
        return eventScopeItems;
    }

    public void setEventScopeItems(List<EventScopeItem> eventScopeItems) {
        this.eventScopeItems = eventScopeItems;
    }
}
