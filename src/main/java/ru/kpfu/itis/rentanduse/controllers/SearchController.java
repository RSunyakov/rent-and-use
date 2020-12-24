package ru.kpfu.itis.rentanduse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.rentanduse.dto.UserDto;
import ru.kpfu.itis.rentanduse.dto.UserRequest;
import ru.kpfu.itis.rentanduse.repository.UsersByRequestRepository;

import java.util.List;

@RestController
public class SearchController {

    @Autowired
    private UsersByRequestRepository usersByRequestRepository;

    @GetMapping("/users/search")
    public ResponseEntity<List<UserDto>> searchByRequest(UserRequest userRequest) {
        return ResponseEntity.ok(usersByRequestRepository.findByRequest(userRequest));
    }
}
