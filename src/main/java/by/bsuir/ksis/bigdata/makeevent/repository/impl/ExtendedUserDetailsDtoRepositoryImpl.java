package by.bsuir.ksis.bigdata.makeevent.repository.impl;

import by.bsuir.ksis.bigdata.makeevent.dto.QUserDetailsDto;
import by.bsuir.ksis.bigdata.makeevent.dto.UserDetailsDto;
import by.bsuir.ksis.bigdata.makeevent.repository.extended.ExtendedUserDetailsDtoRepository;
import com.querydsl.mongodb.morphia.MorphiaQuery;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExtendedUserDetailsDtoRepositoryImpl implements ExtendedUserDetailsDtoRepository {
    @Autowired
    private Morphia morphia;

    @Autowired
    private Datastore datastore;

    @Override
    public List<UserDetailsDto> getUsers(String filter) {
        QUserDetailsDto qUserDetailsDto = QUserDetailsDto.userDetailsDto;
        MorphiaQuery<UserDetailsDto> morphiaQuery = new MorphiaQuery<>(morphia, datastore, qUserDetailsDto);

        return morphiaQuery.where(qUserDetailsDto.email.contains(filter)
                .and(qUserDetailsDto.firstName.contains(filter))
                .and(qUserDetailsDto.lastName.contains(filter))).fetch();
    }
}
