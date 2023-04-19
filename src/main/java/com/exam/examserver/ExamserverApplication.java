package com.exam.examserver;

import com.exam.examserver.Entity.Role;
import com.exam.examserver.Entity.User;
import com.exam.examserver.Entity.UserRole;
import com.exam.examserver.Service.Impl.UserServiceImpl;
import com.exam.examserver.Service.UserSerive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ExamserverApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ExamserverApplication.class, args);

	}

	@Autowired
	private UserServiceImpl userService;



	public  void run(String... args) throws Exception{
		User user=new User();
		user.setFirstName("Durgesh");
		user.setPassword("durgesh123");
		user.setEmail("durgesh@gmail.com");
		user.setLastName("chaubey");
		user.setPhone("7355104033");
		user.setEnabled(true);
		Role role=new Role();
		role.setRoleId(1);
		role.setRoleName("Admin");
		Set<UserRole> userRoleSet=new HashSet<>();
		UserRole userRole=new UserRole();
		userRole.setRole(role);
		userRoleSet.add(userRole);

		User user1=userService.createUser(user,userRoleSet);
		System.out.println(user1.getFirstName());
	}

}
