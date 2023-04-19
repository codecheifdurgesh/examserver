package com.exam.examserver.Service;

import com.exam.examserver.Entity.User;
import com.exam.examserver.Entity.UserRole;
import org.springframework.stereotype.Service;

import java.util.Set;


public interface UserSerive {
    public User createUser(User user, Set<UserRole> userRoles) throws Exception;
    public User getUser(String userName);

}
