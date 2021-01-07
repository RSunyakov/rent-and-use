package ru.kpfu.itis.rentanduse.controllers;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.rentanduse.dto.UserDto;
import ru.kpfu.itis.rentanduse.dto.UserRequest;
import ru.kpfu.itis.rentanduse.models.User;
import ru.kpfu.itis.rentanduse.repository.UserSearchRepository;
import ru.kpfu.itis.rentanduse.repository.UsersByRequestRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class SearchController {

    @Autowired
    private UsersByRequestRepository usersByRequestRepository;

    @Autowired
    UserSearchRepository userSearchRepository;

    @GetMapping("/users/search")
    public ResponseEntity<List<UserDto>> searchByRequest(UserRequest userRequest) {
        return ResponseEntity.ok(usersByRequestRepository.findByRequest(userRequest));
    }

    @GetMapping("/users/search/predicate")
    public ResponseEntity<List<UserDto>> searchByPredicate(@QuerydslPredicate(root = User.class, bindings = UserSearchRepository.class) Predicate predicate) {
        return ResponseEntity.ok(
                StreamSupport.stream(userSearchRepository.findAll(predicate).spliterator(), true)
                .map(user ->
                        UserDto.builder()
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .age(user.getAge())
                        .registrationDate(user.getRegistrationDate())
                        .city(user.getCity())
                        .build()).collect(Collectors.toList()));
    }
}
