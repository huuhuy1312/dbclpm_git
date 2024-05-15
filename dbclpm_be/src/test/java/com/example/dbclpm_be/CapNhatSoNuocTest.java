package com.example.dbclpm_be;

import com.example.dbclpm_be.controller.CustomerController;
import com.example.dbclpm_be.entity.Customer;
import jakarta.transaction.Transactional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CapNhatSoNuocTest {
    @Autowired
    private CustomerController customerController;
    @Test
    public void cap_nhat_so_nuoc_cus_id_hople()
    {
        long cus_id = 1;
        long newIndexWater = 199;
        ResponseEntity<?> responseEntity = customerController.updateIndexWater(cus_id,newIndexWater);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(responseEntity.getBody(),"Cập nhật số nước thành công");
        ResponseEntity<?> customer = customerController.getCustomerByID(cus_id);
        Customer customer1 = (Customer) customer.getBody();
        assert customer1 != null;
        Assert.assertEquals(customer1.getWaterIndex(),newIndexWater);
    }

    @Test
    public void cap_nhat_so_nuoc_cus_id_kohople()
    {
        long cus_id = 11111;
        long newIndexWater = 199;
        ResponseEntity<?> responseEntity = customerController.updateIndexWater(cus_id,newIndexWater);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        Assert.assertEquals(responseEntity.getBody(),"Khong tim thay customer co id="+cus_id);

    }
    @Test
    public void cap_nhat_so_nuoc_chi_so_nuoc_moi_am()
    {
        long cus_id = 1;
        ResponseEntity<?> responseEntity = customerController.updateIndexWater(cus_id,-10);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        Assert.assertEquals(responseEntity.getBody(),"Số nước mới không hợp lệ");

    }
    @Test
    public void cap_nhat_so_nuoc_chi_so_nuoc_moi_trung()
    {
        long cus_id = 1;
        ResponseEntity<?> responseEntity = customerController.updateIndexWater(cus_id,103);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        Assert.assertEquals(responseEntity.getBody(),"Số nước mới trùng với số nước cũ");

    }

    @Test
    public void tim_kiem_kh_actived_theo_name_1()
    {
        String name = "Nguyễn Hữu Huy";
        ResponseEntity<?> responseEntity = customerController.getAllActiveCustomers(name,null,null,null);
        List<Customer> customerList = (List<Customer>) responseEntity.getBody();
        assert customerList!=null;
        Assert.assertEquals(customerList.size(),2);
    }

    @Test
    public void tim_kiem_kh_actived_theo_name_2()
    {
        String name = "Nguyễn Văn A";
        ResponseEntity<?> responseEntity = customerController.getAllActiveCustomers(name,null,null,null);
        List<Customer> customerList = (List<Customer>) responseEntity.getBody();
        assert customerList!=null;
        Assert.assertEquals(customerList.size(),0);
    }
    @Test
    public void tim_kiem_kh_actived_theo_name_4()
    {
        String name = "phạm thanh phúc";
        ResponseEntity<?> responseEntity = customerController.getAllActiveCustomers(name,null,null,null);
        List<Customer> customerList = (List<Customer>) responseEntity.getBody();
        assert customerList!=null;
        Assert.assertEquals(customerList.size(),1);
    }
    @Test
    public void tim_kiem_kh_actived_theo_name_3()
    {
        String name = "a";
        ResponseEntity<?> responseEntity = customerController.getAllActiveCustomers(name,null,null,null);
        List<Customer> customerList = (List<Customer>) responseEntity.getBody();
        assert customerList!=null;
        Assert.assertEquals(customerList.size(),6);
    }

    @Test
    public void tim_kiem_kh_actived_theo_base_name_1()
    {
        String baseName = "Công ty TNHH Nước sạch Hà Nội";
        ResponseEntity<?> responseEntity = customerController.getAllActiveCustomers(null,baseName,null,null);
        List<Customer> customerList = (List<Customer>) responseEntity.getBody();
        assert customerList!=null;
        Assert.assertEquals(customerList.size(),10);
    }
    @Test
    public void tim_kiem_kh_actived_theo_base_name_2()
    {
        String baseName = "Hà Nội";
        ResponseEntity<?> responseEntity = customerController.getAllActiveCustomers(null,baseName,null,null);
        List<Customer> customerList = (List<Customer>) responseEntity.getBody();
        assert customerList!=null;
        Assert.assertEquals(customerList.size(),0);
    }
    @Test
    public void tim_kiem_kh_actived_theo_residentialName_1()
    {
        String residentialName = "Hộ dân cư";
        ResponseEntity<?> responseEntity = customerController.getAllActiveCustomers(null,null,residentialName,null);
        List<Customer> customerList = (List<Customer>) responseEntity.getBody();
        assert customerList!=null;
        Assert.assertEquals(customerList.size(),2);
    }

    @Test
    public void tim_kiem_kh_actived_theo_residentialName_2()
    {
        String residentialName = "cư";
        ResponseEntity<?> responseEntity = customerController.getAllActiveCustomers(null,null,residentialName,null);
        List<Customer> customerList = (List<Customer>) responseEntity.getBody();
        assert customerList!=null;
        Assert.assertEquals(customerList.size(),0);
    }
    @Test
    public void tim_kiem_kh_actived_theo_ordinalNumber_2()
    {
        String ordinal_number = "12300000001";
        ResponseEntity<?> responseEntity = customerController.getAllActiveCustomers(null,null,null,ordinal_number);
        List<Customer> customerList = (List<Customer>) responseEntity.getBody();
        assert customerList!=null;
        Assert.assertEquals(customerList.size(),1);
    }

    @Test
    public void tim_kiem_kh_actived_theo_ordinalNumber_3()
    {
        String ordinal_number = "12300000";
        ResponseEntity<?> responseEntity = customerController.getAllActiveCustomers(null,null,null,ordinal_number);
        List<Customer> customerList = (List<Customer>) responseEntity.getBody();
        assert customerList!=null;
        Assert.assertEquals(customerList.size(),10);
    }
    @Test
    public void tim_kiem_kh_actived_theo_ordinalNumber_1()
    {
        String ordinal_number = "1231111111";
        ResponseEntity<?> responseEntity = customerController.getAllActiveCustomers(null,null,null,ordinal_number);
        List<Customer> customerList = (List<Customer>) responseEntity.getBody();
        assert customerList!=null;
        Assert.assertEquals(customerList.size(),0);
    }
}
