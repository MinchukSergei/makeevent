package by.bsuir.ksis.bigdata.makeevent.dto.event.scope;

import by.bsuir.ksis.bigdata.makeevent.dto.GroupDto;

public class GroupEventScopeItem implements EventScopeItem {
    private GroupDto groupDto;

    public GroupEventScopeItem(GroupDto groupDto) {
        this.groupDto = groupDto;
    }

    @Override
    public EventScope getEventScope() {
        return EventScope.GROUP;
    }

    public GroupDto getGroupDto() {
        return groupDto;
    }

    public void setGroupDto(GroupDto groupDto) {
        this.groupDto = groupDto;
    }
}
