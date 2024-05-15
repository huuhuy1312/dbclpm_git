package com.example.dbclpm_be;

import com.example.dbclpm_be.controller.CustomerController;
import com.example.dbclpm_be.entity.Customer;
import com.example.dbclpm_be.entity.User;
import com.example.dbclpm_be.respository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import jakarta.transaction.Transactional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.junit.Assert;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CustomerControllerTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private CustomerController customerController;

    @Test
    public void LayDuocChiTietThongTinKhachHang()
    {
        try{
            ResponseEntity<?> customer = customerController.getCustomerByID(100);
            assertNotNull(customer);
            System.out.println("Passed");
        }catch (Exception e)
        {
            assertEquals(e.getMessage(), "Không tìm thấy customer có id="+100);
        }
    }

}
