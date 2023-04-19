package com.exam.examserver.Controller;

import com.exam.examserver.Config.JwtUtil;
import com.exam.examserver.Entity.JwtRequest;
import com.exam.examserver.Entity.JwtResponse;
import com.exam.examserver.Entity.User;
import com.exam.examserver.Service.Impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthenticateController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {

            authenticate(jwtRequest.getUserName(),jwtRequest.getPassword());
        }
        catch (UsernameNotFoundException e){
            e.printStackTrace();
            throw new Exception("User not found");
        }
        UserDetails userDetails=this.userDetailsService.loadUserByUsername(jwtRequest.getUserName());
        String s=this.jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(s));
    }

    private void authenticate(String userName,String password) throws Exception {

        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,password));
        }
        catch (DisabledException e){
            throw new Exception("User disabled");
        }
        catch (BadCredentialsException e){
            throw new Exception("invalide credentials"+e.getMessage());
        }
    }

    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal){
        return (User)this.userDetailsService.loadUserByUsername(principal.getName());
    }
}
