package com.tests.task7_8.repository;

import com.tests.task7_8.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getByLoginAndPassword(String login, String password);


}
