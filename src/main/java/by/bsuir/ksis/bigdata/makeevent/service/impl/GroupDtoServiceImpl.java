package by.bsuir.ksis.bigdata.makeevent.service.impl;

import by.bsuir.ksis.bigdata.makeevent.dto.GroupDto;
import by.bsuir.ksis.bigdata.makeevent.dto.UserDetailsDto;
import by.bsuir.ksis.bigdata.makeevent.exception.AccessDeniedException;
import by.bsuir.ksis.bigdata.makeevent.repository.GroupDtoRepository;
import by.bsuir.ksis.bigdata.makeevent.service.GroupDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GroupDtoServiceImpl implements GroupDtoService {
    @Autowired
    private GroupDtoRepository groupDtoRepository;


    @Override
    public GroupDto save(UserDetailsDto authUser, GroupDto groupDto) {
        groupDto.setId(null);
        return groupDtoRepository.save(groupDto);
    }

    @Override
    public GroupDto edit(UserDetailsDto authUser, GroupDto groupDto) throws AccessDeniedException {
        if (groupDto.getId() == null) {
            return groupDtoRepository.save(groupDto);
        } else {
            Optional<GroupDto> oldGroup = authUser.getGroups()
                    .stream()
                    .filter(g -> g.getId().equals(groupDto.getId()))
                    .findFirst();
            if (oldGroup.isPresent()) {
                groupDto.setOwner(authUser);
                return groupDtoRepository.save(groupDto);
            } else {
                throw new AccessDeniedException();
            }
        }
    }

    @Override
    public List<GroupDto> get(UserDetailsDto authUser, String filter) {
        return authUser.getGroups()
                .stream()
                .filter(g -> g.getName().contains(filter) || g.getDescription().contains(filter))
                .collect(Collectors.toList());
    }

    @Override
    public GroupDto addMember(UserDetailsDto authUser, Long memberId, Long groupId) {
        return null;
    }

    @Override
    public GroupDto deleteMember(UserDetailsDto authUser, Long memberId, Long groupId) {
        return null;
    }

    @Override
    public void delete(UserDetailsDto authUser, Long groupId) {

    }
}
