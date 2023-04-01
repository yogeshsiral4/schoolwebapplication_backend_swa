package com.swa.repository;

import com.swa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<User, String> {
    Optional<User> findById(String email);
    User findByEmailAndRole(String email,String role);
    
//    @Query("SELECT u.id,u.email,u.firstName,u.lastName,u.subject FROM User u WHERE"+" u.role LIKE CONCAT('%',:query,'%')")
//    List<User> findByRole(String query);
    
    //
	  List<User> findByRole(String role);
}
