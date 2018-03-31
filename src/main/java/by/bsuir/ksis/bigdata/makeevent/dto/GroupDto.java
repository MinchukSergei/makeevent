package by.bsuir.ksis.bigdata.makeevent.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class GroupDto {
    @Id
    private String id;
    private String name;
    private String description;
    @DBRef(lazy = true)
    private List<UserDetailsDto> members;
    @DBRef
    @JsonIgnore
    private UserDetailsDto owner;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<UserDetailsDto> getMembers() {
        return members;
    }

    public void setMembers(List<UserDetailsDto> members) {
        this.members = members;
    }

    public UserDetailsDto getOwner() {
        return owner;
    }

    public void setOwner(UserDetailsDto owner) {
        this.owner = owner;
    }
}
