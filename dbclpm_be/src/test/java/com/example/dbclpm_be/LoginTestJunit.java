package com.example.dbclpm_be;

import com.example.dbclpm_be.controller.AuthController;
import com.example.dbclpm_be.entity.User;
import com.example.dbclpm_be.respository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import payload.request.LoginRequest;
import payload.response.CustomerLoginResponse;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class LoginTestJunit {
    @Autowired
    private AuthController authController;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void manager_login_thanh_cong()
    {
        String username ="manager";
        String password ="123456";
        LoginRequest loginRequest = new LoginRequest(username,password);
        ResponseEntity<?> responseEntity = authController.login(loginRequest);
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);
        User user = (User) responseEntity.getBody();
        assert user != null;
        Assert.assertEquals(user.getRole().getName(),"manager");
    }

    @Test
    public void employee_login_thanh_cong()
    {
        String username ="employee";
        String password ="123456";
        LoginRequest loginRequest = new LoginRequest(username,password);
        ResponseEntity<?> responseEntity = authController.login(loginRequest);
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);
        User user = (User) responseEntity.getBody();
        assert user != null;
        Assert.assertEquals(user.getRole().getName(),"employee");
    }

    @Test
    public void customer_login_thanh_cong()
    {
        String username ="nhh12300000001";
        String password ="Bzzhk7bO";
        LoginRequest loginRequest = new LoginRequest(username,password);
        ResponseEntity<?> responseEntity = authController.login(loginRequest);
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);
        CustomerLoginResponse user = (CustomerLoginResponse) responseEntity.getBody();
        assert user != null;
        Assert.assertEquals(user.role.getName(),"customer");
    }

    @Test
    public void manager_login_that_bai()
    {
        String username ="manager";
        String password ="1234567";
        LoginRequest loginRequest = new LoginRequest(username,password);
        ResponseEntity<?> responseEntity = authController.login(loginRequest);
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.NOT_FOUND);
    }
    @Test
    public void employee_login_that_bai()
    {
        String username ="employee";
        String password ="1234567";
        LoginRequest loginRequest = new LoginRequest(username,password);
        ResponseEntity<?> responseEntity = authController.login(loginRequest);
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.NOT_FOUND);
    }
    @Test
    public void customer_login_that_bai()
    {
        String username ="abc";
        String password ="1234567";
        LoginRequest loginRequest = new LoginRequest(username,password);
        ResponseEntity<?> responseEntity = authController.login(loginRequest);
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.NOT_FOUND);
    }
}
