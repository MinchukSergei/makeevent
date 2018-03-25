package by.bsuir.ksis.bigdata.makeevent.dto.event.scope;

import by.bsuir.ksis.bigdata.makeevent.dto.UserDetailsDto;

public class UserEventScopeItem implements EventScopeItem {
    private UserDetailsDto userDetailsDto;

    public UserEventScopeItem(UserDetailsDto userDetailsDto) {
        this.userDetailsDto = userDetailsDto;
    }

    @Override
    public EventScope getEventScope() {
        return EventScope.USER;
    }

    public UserDetailsDto getUserDetailsDto() {
        return userDetailsDto;
    }

    public void setUserDetailsDto(UserDetailsDto userDetailsDto) {
        this.userDetailsDto = userDetailsDto;
    }
}
