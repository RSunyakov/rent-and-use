package ru.kpfu.itis.rentanduse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequest {
    private Integer age;
    private String city;
    private String firstName;
    private String lastName;
    private String registrationDate;
}
