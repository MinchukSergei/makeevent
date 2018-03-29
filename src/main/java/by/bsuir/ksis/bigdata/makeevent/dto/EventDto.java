package by.bsuir.ksis.bigdata.makeevent.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Calendar;
import java.util.List;

@Document
public class EventDto {
    @Id
    private String id;
    private String name;
    private Double mapCoordinateX;
    private Double mapCoordinateY;
    private Calendar date;
    private String description;
    @DBRef
    private UserDetailsDto owner;
    @DBRef(lazy = true)
    private List<UserDetailsDto> participants;

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

    public UserDetailsDto getOwner() {
        return owner;
    }

    public void setOwner(UserDetailsDto owner) {
        this.owner = owner;
    }

    public List<UserDetailsDto> getParticipants() {
        return participants;
    }

    public void setParticipants(List<UserDetailsDto> participants) {
        this.participants = participants;
    }
}
