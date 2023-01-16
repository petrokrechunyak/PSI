package com.tests.task7_8;

import com.tests.task7_8.entity.User;
import com.tests.task7_8.repository.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {
    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    InitializingBean initialize() {
        return () -> {
            userRepository.save(new User("Petro", "Petro", "123"));
            userRepository.save(new User("Ivan", "Ivanlogin", "password"));
            userRepository.save(new User("ThirdName", "login", "simlepassword"));
        };
    }

}
