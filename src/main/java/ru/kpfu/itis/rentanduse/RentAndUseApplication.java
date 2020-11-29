package ru.kpfu.itis.rentanduse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.kpfu.itis.rentanduse.models.User;
import ru.kpfu.itis.rentanduse.repository.UsersRepository;
import ru.kpfu.itis.rentanduse.repository.UsersRepositoryMongoTemplateImpl;

@SpringBootApplication
public class RentAndUseApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(RentAndUseApplication.class, args);
        UsersRepository repository = context.getBean(UsersRepositoryMongoTemplateImpl.class);
        User user = repository.find("5fc3c7a992ab1b7bb2169d3f").get();
        System.out.println(user);
        user.setAge(55);
        user.setLastName("21313");
        repository.update(user);
        System.out.println(repository.find("5fc3c7a992ab1b7bb2169d3f").get());
    }

}
