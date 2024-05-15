package com.example.dbclpm_be;

import com.example.dbclpm_be.controller.BaseController;
import com.example.dbclpm_be.entity.Base;
import jakarta.transaction.Transactional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TestBaseController {
    @Autowired
    private BaseController baseController;

    @Test
    public void getAllBase()
    {
        ResponseEntity<?> listBases = baseController.getAllBase();
        List<Base> list = (List<Base>) listBases.getBody();
        assert list != null;
        Assert.assertEquals(list.size(),1);
    }
}
