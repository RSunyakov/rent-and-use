package ru.kpfu.itis.rentanduse;

import ru.kpfu.itis.rentanduse.models.User;
import ru.kpfu.itis.rentanduse.repository.UsersRepository;
import ru.kpfu.itis.rentanduse.repository.UsersRepositoryMongoDriverImpl;

import java.util.Arrays;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        UsersRepository usersRepository = new UsersRepositoryMongoDriverImpl();
        /*usersRepository.save(User.builder().
                firstName("Roman")
                .lastName("Syunyakov")
                .age(20)
                .city("Kazan")
                .registrationDate("29.11.2020")
                .role(Arrays.asList("holder", "renter")).build());*/
        Optional<User> userOptional = usersRepository.find("5fc3c7a992ab1b7bb2169d3f");
        User user = userOptional.get();
        user.setAge(21);
        usersRepository.update(user);
        System.out.println(userOptional.get());
    }
}
