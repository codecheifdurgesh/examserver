package com.exam.examserver.Controller;

import com.exam.examserver.Entity.Role;
import com.exam.examserver.Entity.User;
import com.exam.examserver.Entity.UserRole;
import com.exam.examserver.Service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {

        Set<UserRole> userRoleSet=new HashSet<>();
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        Role role=new Role();
        role.setRoleName("Normal");
        role.setRoleId(1);
        UserRole userRole=new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        userRoleSet.add(userRole);
        return this.userService.createUser(user,userRoleSet);

    }

    @GetMapping("/{userName}")
    public User getUser(@PathVariable("userName") String userName){
        return userService.getUser(userName);
    }

}
