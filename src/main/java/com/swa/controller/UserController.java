package com.swa.controller;

import com.swa.dto.AuthRequest;
import com.swa.entity.User;
import com.swa.service.JwtService;
import com.swa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/home")
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user){
    	return userService.addNewUser(user);
    }

    @GetMapping("/{email}")
    public Optional<User> findById(@PathVariable String email){
    	return userService.findById(email);
    }
    
    @GetMapping("/teachers")
    public ResponseEntity<List<User>> findByRole(){
    	return ResponseEntity.ok(userService.findByRole("teacher"));
    }
    
    @DeleteMapping("/delete/{email}")
    public ResponseEntity<User> deleteUserById(@PathVariable String email){
    	return userService.deleteUserById(email);
    }
   
    @PutMapping("/update")
    public User updateUser(@RequestBody User user) {
    	return userService.updateUser(user);
    }

    @PostMapping("/login")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
    	boolean verified = userService.roleVerification(authRequest.getEmail(), authRequest.getRole());
    	if(verified) {
    		 Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
    	        if (authentication.isAuthenticated()) {
    	            return jwtService.generateToken(authRequest.getEmail());
    	        } 			
    	}
        else {
            throw new UsernameNotFoundException("invalid user request !");
        }
		return null;


    }
}