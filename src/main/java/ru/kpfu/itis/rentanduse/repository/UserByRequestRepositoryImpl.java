package ru.kpfu.itis.rentanduse.repository;

import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.rentanduse.dto.UserDto;
import ru.kpfu.itis.rentanduse.dto.UserRequest;
import ru.kpfu.itis.rentanduse.models.User;

import java.util.List;
import java.util.stream.Collectors;

import static ru.kpfu.itis.rentanduse.models.QUser.user;

@Repository
public class UserByRequestRepositoryImpl implements UsersByRequestRepository {

    @Autowired
    private UsersMongoRepository usersRepository;

    @Override
    public List<UserDto> findByRequest(UserRequest userRequest) {

      BooleanBuilder predicate = new BooleanBuilder();
        if (userRequest.getAge() != null) {

            predicate.or(user.age.eq(userRequest.getAge()));
        }

        if (userRequest.getCity() != null) {
            predicate.or(user.city.eq(userRequest.getCity()));
        }

        if (userRequest.getFirstName() != null) {
            predicate.or(user.firstName.eq(userRequest.getFirstName()));
        }

        if (userRequest.getLastName() != null) {
            predicate.or(user.lastName.eq(userRequest.getLastName()));
        }

        if (userRequest.getRegistrationDate() != null) {
            predicate.or(user.registrationDate.eq(userRequest.getRegistrationDate()));
        }

        List<User> users = (List<User>) usersRepository.findAll(predicate);

        return users.stream().map(row -> UserDto.builder()
        .age(row.getAge())
                .city(row.getCity())
                .firstName(row.getFirstName())
                .lastName(row.getLastName())
                .registrationDate(row.getRegistrationDate())
                .build()).collect(Collectors.toList());

    }
}
