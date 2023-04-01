package com.swa;



import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import com.swa.dto.AuthRequest;
import com.swa.entity.User;

import io.restassured.http.ContentType;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class SwaApplicationTests {

	@Test
	void contextLoads() {
	}
	
// Test for Login 
	@Test
	@Order(1)
	void LoginTest() {
		AuthRequest authCheck = new AuthRequest();
		authCheck.setEmail("yogeshsiral@gmail.com");
		authCheck.setPassword("yogesh@123");
		authCheck.setRole("Admin");
		
		given().header("Content-type","application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
			   .body(authCheck)
			   .when()
			   .post("http://localhost:9192/home/login")
			   .then()
			   .assertThat().statusCode(200);		
	}
	
//Test for registration and check getting from database
	@Test
	@Order(2)
	void AddStudentTeacherTest() {
		User user = new User();
		user.setEmail("testadd@gmail.com");
		user.setPassword("test123");
		user.setFirstName("test");
		user.setRole("Student");
		
		User resultAdd = given()
			   .header("Content-type","application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
			   .body(user)
			   .when()
			   .post("http://localhost:9192/home/add")
			   .then()
			   .assertThat().statusCode(201)
			   .extract().response().getBody().as(User.class);
		
		assertEquals(user.getEmail(),resultAdd.getEmail());
		assertEquals(user.getFirstName(),resultAdd.getFirstName());
		assertEquals(user.getRole(),resultAdd.getRole());
	}

//Test for update and check with database
	@Test
	@Order(3)
	void UpdateUserTest()  {
		AuthRequest authCheck = new AuthRequest();
		authCheck.setEmail("yogeshsiral@gmail.com");
		authCheck.setPassword("yogesh@123");
		authCheck.setRole("Admin");
		
	String tokenn = given().header("Content-type","application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
		   .body(authCheck)
		   .when()
		   .post("http://localhost:9192/home/login")
		   .then()
		   .assertThat().statusCode(200)
		   .extract().response().asString();
	
	 System.out.println(tokenn);
     
     String token = "Bearer "+tokenn;
     
     User result = given()
             .header("Authorization",token).contentType(ContentType.JSON).accept(ContentType.JSON)
             .when()
             .get("http://localhost:9192/home/testadd@gmail.com")
             .then()
             .assertThat().statusCode(200)
             .extract().response().getBody().as(User.class);
     
     result.setLastName("Case");
     
     	given()
     	.header("Authorization",token).contentType(ContentType.JSON).accept(ContentType.JSON)
		   .body(result)
		   .when()
		   .put("http://localhost:9192/home/update")
		   .then()
		   .assertThat().statusCode(200)
		   .extract().response();
      
	}

// Test for delete user
	@Test
	@Order(4)
	void DeleteUserTest()  {
		AuthRequest authCheck = new AuthRequest();
		authCheck.setEmail("yogeshsiral@gmail.com");
		authCheck.setPassword("yogesh@123");
		authCheck.setRole("Admin");
		
	String tokenn = given().header("Content-type","application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
		   .body(authCheck)
		   .when()
		   .post("http://localhost:9192/home/login")
		   .then()
		   .assertThat().statusCode(200)
		   .extract().response().asString();
	
	 System.out.println(tokenn);
     
     String token = "Bearer "+tokenn;
     
     given()
             .header("Authorization",token).contentType(ContentType.JSON).accept(ContentType.JSON)
             .when()
             .delete("http://localhost:9192/home/delete/testadd@gmail.com")
             .then()
             .assertThat().statusCode(200)
             .extract().response();
	}
	
}
