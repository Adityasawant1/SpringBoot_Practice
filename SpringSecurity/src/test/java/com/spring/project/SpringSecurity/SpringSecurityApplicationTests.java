package com.spring.project.SpringSecurity;

import com.spring.project.SpringSecurity.entities.UserEntity;
import com.spring.project.SpringSecurity.services.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringSecurityApplicationTests {

    @Autowired
    private JwtService jwtService;
	@Test
	void contextLoads() {

        UserEntity userEntity = new UserEntity(4L,"sawantaditya3636@gmail.com","1234","aditya");

        String token = jwtService.generateToken(userEntity);

        System.out.println(token);
        System.out.println();
        System.out.println();


        System.out.println(jwtService.getUserById(token));
        System.out.println();
	}

}
