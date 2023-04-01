package com.swa.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.swa.entity.User;
import com.swa.repository.UserInfoRepository;

@Service
public class UserService {
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	public Optional<User> findById(String email){
		return userInfoRepository.findById(email);
	}
	
	public List<User> findByRole(String role){
		return  userInfoRepository.findByRole(role);
	}
	
	public ResponseEntity<User> addNewUser(User user){
		if(userInfoRepository.existsById(user.getEmail())){
			return new ResponseEntity<User>(HttpStatus.ALREADY_REPORTED);
		}
		else {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			return new ResponseEntity<User>(userInfoRepository.save(user),HttpStatus.CREATED);
		}
	}
		
	public User updateUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userInfoRepository.save(user);
	}
	
	public ResponseEntity<User> deleteUserById(String email){
		if(userInfoRepository.existsById(email)) {
			userInfoRepository.deleteById(email);
			return new ResponseEntity<User>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}
	
	public Boolean roleVerification(String email,String role) {
		User user = userInfoRepository.findByEmailAndRole(email, role);
		if(user!=null) {
			return true;
		}
		else {
			return false;
		}
	}
}
