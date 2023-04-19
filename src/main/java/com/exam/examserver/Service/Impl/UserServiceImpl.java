package com.exam.examserver.Service.Impl;

import com.exam.examserver.Entity.User;
import com.exam.examserver.Entity.UserRole;
import com.exam.examserver.Repository.RoleRepository;
import com.exam.examserver.Repository.UserRepository;
import com.exam.examserver.Service.UserSerive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class UserServiceImpl implements UserSerive {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
        User local=this.userRepository.findByUserName(user.getUserName());
        if(local!=null){
            System.out.println("User is already present");
            throw  new Exception("User already present !!");
        }else{

            for(UserRole u:userRoles){
                roleRepository.save(u.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            local=this.userRepository.save(user);
            return local;

        }
    }

    @Override
    public User getUser(String userName) {
        return this.userRepository.findByUserName(userName);
    }


}
