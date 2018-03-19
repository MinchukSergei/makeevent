package by.bsuir.ksis.bigdata.makeevent.dto;

import org.springframework.data.annotation.Id;

import java.util.List;

public class GroupDto {
    @Id
    private String id;
    private String name;
    private String description;
    private List<UserDetails> users;
    private UserDetails owner;
}
