package by.bsuir.ksis.bigdata.makeevent.dto.event.scope;

import by.bsuir.ksis.bigdata.makeevent.dto.UserDetails;

public class UserEventScopeItem implements EventScopeItem {
    private UserDetails userDetails;

    public UserEventScopeItem(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    @Override
    public EventScope getEventScope() {
        return EventScope.USER;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }
}
