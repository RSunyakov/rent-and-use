package ru.kpfu.itis.rentanduse.repository;

import ru.kpfu.itis.rentanduse.dto.UserDto;
import ru.kpfu.itis.rentanduse.dto.UserRequest;

import java.util.List;

public interface UsersByRequestRepository {
    List<UserDto> findByRequest(UserRequest userRequest);
}
