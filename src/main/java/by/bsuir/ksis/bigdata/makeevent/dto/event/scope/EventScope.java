package by.bsuir.ksis.bigdata.makeevent.dto.event.scope;

public enum EventScope {
    ALL_USERS(0),
    USER(1),
    GROUP(2),
    PRIVATE(3);

    private Integer id;

    EventScope(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
