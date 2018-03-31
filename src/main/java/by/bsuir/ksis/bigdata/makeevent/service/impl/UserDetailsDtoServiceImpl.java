package by.bsuir.ksis.bigdata.makeevent.service.impl;

import by.bsuir.ksis.bigdata.makeevent.dto.UserDetailsDto;
import by.bsuir.ksis.bigdata.makeevent.repository.UserDetailsDtoRepository;
import by.bsuir.ksis.bigdata.makeevent.service.UserDetailsDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsDtoServiceImpl implements UserDetailsDtoService {
    @Autowired
    private UserDetailsDtoRepository userDetailsDtoRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetailsDto createUser(UserDetailsDto userDetailsDto) {
        userDetailsDto.setPassword(bCryptPasswordEncoder.encode(userDetailsDto.getPassword()));
        return userDetailsDtoRepository.save(userDetailsDto);
    }

    @Override
    public boolean isUserExists(UserDetailsDto userDetailsDto) {
        return userDetailsDtoRepository.findByEmail(userDetailsDto.getEmail()) != null;
    }

    @Override
    public UserDetailsDto editUser(UserDetailsDto userDetailsDto) {
        Optional<UserDetailsDto> optionalOldUser = userDetailsDtoRepository.findById(userDetailsDto.getId());
        if (optionalOldUser.isPresent()) {
            UserDetailsDto oldUser = optionalOldUser.get();
            userDetailsDto.setId(oldUser.getId());
            userDetailsDto.setEmail(oldUser.getEmail());
            userDetailsDto.setPassword(oldUser.getPassword());
            return userDetailsDtoRepository.save(userDetailsDto);
        }
        return null;
    }

    @Override
    public List<UserDetailsDto> getUsers(String filter) {
        return userDetailsDtoRepository.getUsers(filter);
    }
}
