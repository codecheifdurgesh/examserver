package com.exam.examserver.Repository;

import com.exam.examserver.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUserName(String userName);
}
