package com.example.dbclpm_be;

import com.example.dbclpm_be.controller.AuthController;
import com.example.dbclpm_be.controller.CustomerController;
import com.example.dbclpm_be.controller.ResidentialTypeController;
import com.example.dbclpm_be.entity.Customer;
import com.example.dbclpm_be.entity.ResidentialType;
import com.example.dbclpm_be.entity.User;
import com.example.dbclpm_be.respository.*;
import jakarta.transaction.Transactional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import payload.request.AddCustomerRequest;
import payload.request.CreateUserRequest;
import payload.request.RejectRequest;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DangKiSuDungNuocTest {

    @Autowired
    private CustomerController customerController;

    @Autowired
    private ResidentialTypeRepository residentialTypeRepository;

    @Autowired
    private AddressRespository addressRespository;
    @Autowired
    private InfoCommonRespository infoCommonRespository;
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ResidentialTypeController residentialTypeController;
    @Autowired
    private AuthController authController;

    @Autowired
    private UserRepository userRepository;
    @Test
    public void lay_tat_ca_cac_loai_ho()
    {
        ResponseEntity<?> responseEntity = residentialTypeController.getAllResidentialType();
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);
        List<ResidentialType> all_loai_ho= (List<ResidentialType>) responseEntity.getBody();
        assert all_loai_ho != null;
        Assert.assertEquals(all_loai_ho.size(),6);
    }
    @Test
    public void KH_loai_1dang_ky_su_dung_nuoc_thanh_cong()
    {

        AddCustomerRequest addCustomerRequest = new AddCustomerRequest("flslayder1312@gmail.com","0123976789","Nguyễn Văn Test",1L,1L,"Thành phố Hà Nội","Quận Long Biên","Phường Ngọc Thụy","342 Nguyễn Trãi","anh_truoc_test.png","anh_sau_test.png","test_1.png");


        ResponseEntity<?> responseEntity = customerController.addYeucauDKSDN(addCustomerRequest);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Long idCustomer = Long.valueOf(Objects.requireNonNull(responseEntity.getBody()).toString());
        System.out.println(idCustomer);
        ResponseEntity<?> responseEntity1 =  customerController.getCustomerByID(idCustomer);
        Customer customer = (Customer) responseEntity1.getBody();
        Assert.assertNotNull(customer);
        Assert.assertEquals(addCustomerRequest.baseId,customer.getBase().getId());
        Assert.assertEquals(addCustomerRequest.residentialTypeId,customer.getResidentialType().getId());
        Assert.assertEquals(addCustomerRequest.email,customer.getInfoCommon().getEmail());
        Assert.assertEquals(addCustomerRequest.phoneNumber,customer.getInfoCommon().getPhoneNumber());
        Assert.assertEquals(addCustomerRequest.name, customer.getInfoCommon().getName());
        Assert.assertEquals(addCustomerRequest.provinceOrCity,customer.getAddress().getProvinceOrCity());
        Assert.assertEquals(addCustomerRequest.district,customer.getAddress().getDistrict());
        Assert.assertEquals(addCustomerRequest.wards,customer.getAddress().getWards());
        Assert.assertEquals(addCustomerRequest.detailsAddress,customer.getAddress().getDetailsAddress());
        Assert.assertEquals(addCustomerRequest.front_image,customer.getFront_image());
        Assert.assertEquals(addCustomerRequest.back_image,customer.getBack_image());
        Assert.assertNull(customer.getCertificate_of_poverty());
        Assert.assertFalse(customer.isActived());
        customerRepository.deleteById(idCustomer);

    }
    @Test
    public void KH_loai2_dang_ky_su_dung_nuoc_thanh_cong()
    {
        AddCustomerRequest addCustomerRequest = new AddCustomerRequest("flslayder1312@gmail.com","0123458889","Nguyễn Văn Test",1L,2L,"Thành phố Hà Nội","Quận Long Biên","Phường Ngọc Thụy","752 Nguyễn Trãi","anh_truoc_test.png","anh_sau_test.png","anh_ho_ngheo.png");

        ResponseEntity<?> responseEntity = customerController.addYeucauDKSDN(addCustomerRequest);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Long idCustomer = Long.valueOf(Objects.requireNonNull(responseEntity.getBody()).toString());
        System.out.println(idCustomer);
        ResponseEntity<?> responseEntity1 =  customerController.getCustomerByID(idCustomer);
        Customer customer = (Customer) responseEntity1.getBody();
        Assert.assertNotNull(customer);
        Assert.assertEquals(addCustomerRequest.baseId,customer.getBase().getId());
        Assert.assertEquals(addCustomerRequest.residentialTypeId,customer.getResidentialType().getId());
        Assert.assertEquals(addCustomerRequest.email,customer.getInfoCommon().getEmail());
        Assert.assertEquals(addCustomerRequest.phoneNumber,customer.getInfoCommon().getPhoneNumber());
        Assert.assertEquals(addCustomerRequest.name, customer.getInfoCommon().getName());
        Assert.assertEquals(addCustomerRequest.provinceOrCity,customer.getAddress().getProvinceOrCity());
        Assert.assertEquals(addCustomerRequest.district,customer.getAddress().getDistrict());
        Assert.assertEquals(addCustomerRequest.wards,customer.getAddress().getWards());
        Assert.assertEquals(addCustomerRequest.detailsAddress,customer.getAddress().getDetailsAddress());
        Assert.assertEquals(addCustomerRequest.front_image,customer.getFront_image());
        Assert.assertEquals(addCustomerRequest.back_image,customer.getBack_image());
        Assert.assertNotNull(customer.getResidentialType());
        Assert.assertEquals(addCustomerRequest.certificate_of_poverty,customer.getCertificate_of_poverty());
        Assert.assertFalse(customer.isActived());
        customerRepository.deleteById(idCustomer);
    }

    @Test
    public void KH_loai3_dang_ky_su_dung_nuoc_thanh_cong()
    {
        AddCustomerRequest addCustomerRequest = new AddCustomerRequest("flslayder1312@gmail.com","0123451389","Nguyễn Văn Test",1L,3L,"Thành phố Hà Nội","Quận Long Biên","Phường Ngọc Thụy","12345 Nguyễn Trãi","anh_truoc_test.png","anh_sau_test.png",null);

        ResponseEntity<?> responseEntity = customerController.addYeucauDKSDN(addCustomerRequest);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Long idCustomer = Long.valueOf(Objects.requireNonNull(responseEntity.getBody()).toString());
        System.out.println(idCustomer);
        ResponseEntity<?> responseEntity1 =  customerController.getCustomerByID(idCustomer);
        Customer customer = (Customer) responseEntity1.getBody();
        Assert.assertNotNull(customer);
        Assert.assertEquals(addCustomerRequest.baseId,customer.getBase().getId());
        Assert.assertEquals(addCustomerRequest.residentialTypeId,3L);
        Assert.assertEquals(addCustomerRequest.email,customer.getInfoCommon().getEmail());
        Assert.assertEquals(addCustomerRequest.phoneNumber,customer.getInfoCommon().getPhoneNumber());
        Assert.assertEquals(addCustomerRequest.name, customer.getInfoCommon().getName());
        Assert.assertEquals(addCustomerRequest.provinceOrCity,customer.getAddress().getProvinceOrCity());
        Assert.assertEquals(addCustomerRequest.district,customer.getAddress().getDistrict());
        Assert.assertEquals(addCustomerRequest.wards,customer.getAddress().getWards());
        Assert.assertEquals(addCustomerRequest.detailsAddress,customer.getAddress().getDetailsAddress());
        Assert.assertEquals(addCustomerRequest.front_image,customer.getFront_image());
        Assert.assertEquals(addCustomerRequest.back_image,customer.getBack_image());
        Assert.assertEquals(addCustomerRequest.certificate_of_poverty,customer.getCertificate_of_poverty());
        Assert.assertFalse(customer.isActived());
        customerRepository.deleteById(idCustomer);
    }

    @Test
    public void KH_loai4_dang_ky_su_dung_nuoc_thanh_cong()
    {
        AddCustomerRequest addCustomerRequest = new AddCustomerRequest("flslayder1312@gmail.com","0123451389","Nguyễn Văn Test",1L,4L,"Thành phố Hà Nội","Quận Long Biên","Phường Ngọc Thụy","12345 Nguyễn Trãi","anh_truoc_test.png","anh_sau_test.png",null);

        ResponseEntity<?> responseEntity = customerController.addYeucauDKSDN(addCustomerRequest);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Long idCustomer = Long.valueOf(Objects.requireNonNull(responseEntity.getBody()).toString());
        System.out.println(idCustomer);
        ResponseEntity<?> responseEntity1 =  customerController.getCustomerByID(idCustomer);
        Customer customer = (Customer) responseEntity1.getBody();
        Assert.assertNotNull(customer);
        Assert.assertEquals(addCustomerRequest.baseId,customer.getBase().getId());
        Assert.assertEquals(addCustomerRequest.residentialTypeId,4L);
        Assert.assertEquals(addCustomerRequest.email,customer.getInfoCommon().getEmail());
        Assert.assertEquals(addCustomerRequest.phoneNumber,customer.getInfoCommon().getPhoneNumber());
        Assert.assertEquals(addCustomerRequest.name, customer.getInfoCommon().getName());
        Assert.assertEquals(addCustomerRequest.provinceOrCity,customer.getAddress().getProvinceOrCity());
        Assert.assertEquals(addCustomerRequest.district,customer.getAddress().getDistrict());
        Assert.assertEquals(addCustomerRequest.wards,customer.getAddress().getWards());
        Assert.assertEquals(addCustomerRequest.detailsAddress,customer.getAddress().getDetailsAddress());
        Assert.assertEquals(addCustomerRequest.front_image,customer.getFront_image());
        Assert.assertEquals(addCustomerRequest.back_image,customer.getBack_image());
        Assert.assertEquals(addCustomerRequest.certificate_of_poverty,customer.getCertificate_of_poverty());
        Assert.assertFalse(customer.isActived());
        customerRepository.deleteById(idCustomer);
    }
    @Test
    public void KH_loai5_dang_ky_su_dung_nuoc_thanh_cong()
    {
        AddCustomerRequest addCustomerRequest = new AddCustomerRequest("flslayder1312@gmail.com","0123451389","Nguyễn Văn Test",1L,5L,"Thành phố Hà Nội","Quận Long Biên","Phường Ngọc Thụy","12345 Nguyễn Trãi","anh_truoc_test.png","anh_sau_test.png",null);

        ResponseEntity<?> responseEntity = customerController.addYeucauDKSDN(addCustomerRequest);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Long idCustomer = Long.valueOf(Objects.requireNonNull(responseEntity.getBody()).toString());
        System.out.println(idCustomer);
        ResponseEntity<?> responseEntity1 =  customerController.getCustomerByID(idCustomer);
        Customer customer = (Customer) responseEntity1.getBody();
        Assert.assertNotNull(customer);
        Assert.assertEquals(addCustomerRequest.baseId,customer.getBase().getId());
        Assert.assertEquals(addCustomerRequest.residentialTypeId,5L);
        Assert.assertEquals(addCustomerRequest.email,customer.getInfoCommon().getEmail());
        Assert.assertEquals(addCustomerRequest.phoneNumber,customer.getInfoCommon().getPhoneNumber());
        Assert.assertEquals(addCustomerRequest.name, customer.getInfoCommon().getName());
        Assert.assertEquals(addCustomerRequest.provinceOrCity,customer.getAddress().getProvinceOrCity());
        Assert.assertEquals(addCustomerRequest.district,customer.getAddress().getDistrict());
        Assert.assertEquals(addCustomerRequest.wards,customer.getAddress().getWards());
        Assert.assertEquals(addCustomerRequest.detailsAddress,customer.getAddress().getDetailsAddress());
        Assert.assertEquals(addCustomerRequest.front_image,customer.getFront_image());
        Assert.assertEquals(addCustomerRequest.back_image,customer.getBack_image());
        Assert.assertEquals(addCustomerRequest.certificate_of_poverty,customer.getCertificate_of_poverty());
        Assert.assertFalse(customer.isActived());
        customerRepository.deleteById(idCustomer);
    }
    @Test
    public void KH_loai6_dang_ky_su_dung_nuoc_thanh_cong()
    {
        AddCustomerRequest addCustomerRequest = new AddCustomerRequest("flslayder1312@gmail.com","0123451389","Nguyễn Văn Test",1L,6L,"Thành phố Hà Nội","Quận Long Biên","Phường Ngọc Thụy","12345 Nguyễn Trãi","anh_truoc_test.png","anh_sau_test.png",null);

        ResponseEntity<?> responseEntity = customerController.addYeucauDKSDN(addCustomerRequest);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Long idCustomer = Long.valueOf(Objects.requireNonNull(responseEntity.getBody()).toString());
        System.out.println(idCustomer);
        ResponseEntity<?> responseEntity1 =  customerController.getCustomerByID(idCustomer);
        Customer customer = (Customer) responseEntity1.getBody();
        Assert.assertNotNull(customer);
        Assert.assertEquals(addCustomerRequest.baseId,customer.getBase().getId());
        Assert.assertEquals(addCustomerRequest.residentialTypeId,6L);
        Assert.assertEquals(addCustomerRequest.email,customer.getInfoCommon().getEmail());
        Assert.assertEquals(addCustomerRequest.phoneNumber,customer.getInfoCommon().getPhoneNumber());
        Assert.assertEquals(addCustomerRequest.name, customer.getInfoCommon().getName());
        Assert.assertEquals(addCustomerRequest.provinceOrCity,customer.getAddress().getProvinceOrCity());
        Assert.assertEquals(addCustomerRequest.district,customer.getAddress().getDistrict());
        Assert.assertEquals(addCustomerRequest.wards,customer.getAddress().getWards());
        Assert.assertEquals(addCustomerRequest.detailsAddress,customer.getAddress().getDetailsAddress());
        Assert.assertEquals(addCustomerRequest.front_image,customer.getFront_image());
        Assert.assertEquals(addCustomerRequest.back_image,customer.getBack_image());
        Assert.assertEquals(addCustomerRequest.certificate_of_poverty,customer.getCertificate_of_poverty());
        Assert.assertFalse(customer.isActived());
        customerRepository.deleteById(idCustomer);
    }
    @Test
    public void KH_loai2_dang_ky_su_dung_nuoc_thieu_chung_nhan_ho_ngheo()
    {
        AddCustomerRequest addCustomerRequest = new AddCustomerRequest("flslayder1312@gmail.com","0123458889","Nguyễn Văn Test",1L,2L,"Thành phố Hà Nội","Quận Long Biên","Phường Ngọc Thụy","456 Nguyễn Trãi","anh_truoc_test.png","anh_sau_test.png",null);

        ResponseEntity<?> responseEntity = customerController.addYeucauDKSDN(addCustomerRequest);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        Assert.assertEquals(responseEntity.getBody(),"Chưa up giấy chứng nhận hộ nghèo");
    }
    @Test
    public  void KH_dang_ky_trung_dia_chi()
    {
        AddCustomerRequest addCustomerRequest = new AddCustomerRequest("flslayder1312@gmail.com","0123458889","Nguyễn Văn Test",1L,2L,"Thành phố Hà Nội","Quận Long Biên","Phường Ngọc Thụy","123 Nguyễn Trãi","anh_truoc_test.png","anh_sau_test.png",null);

        ResponseEntity<?> responseEntity = customerController.addYeucauDKSDN(addCustomerRequest);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        Assert.assertEquals(responseEntity.getBody(),"Địa chỉ đã tồn tại");
    }
    @Test
    public void KH_dang_ky_trung_sdt()
    {

            AddCustomerRequest addCustomerRequest = new AddCustomerRequest("flslayder1312@gmail.com","0123456789","Nguyễn Văn Test",1L,2L,"Thành phố Hà Nội","Quận Long Biên","Phường Ngọc Thụy","1123 Nguyễn Trãi","anh_truoc_test.png","anh_sau_test.png","anh_chung_nhan.png");

            ResponseEntity<?> responseEntity = customerController.addYeucauDKSDN(addCustomerRequest);
            Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
            Assert.assertEquals(responseEntity.getBody(),"Số điện thoại đã tồn tại");

    }

    @Test
    public void KH_dang_ky_sai_baseID()
    {
        AddCustomerRequest addCustomerRequest = new AddCustomerRequest("flslayder1312@gmail.com","0123456789","Nguyễn Văn Test",10L,2L,"Thành phố Hà Nội","Quận Long Biên","Phường Ngọc Thụy","1123 Nguyễn Trãi","anh_truoc_test.png","anh_sau_test.png",null);

        ResponseEntity<?> responseEntity = customerController.addYeucauDKSDN(addCustomerRequest);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        Assert.assertEquals(responseEntity.getBody(),"Không tìm thấy base có id="+addCustomerRequest.baseId);
    }

    @Test
    public void KH_dang_ky_sai_residential_type_id()
    {
        AddCustomerRequest addCustomerRequest = new AddCustomerRequest("flslayder1312@gmail.com","0123456789","Nguyễn Văn Test",1L,10L,"Thành phố Hà Nội","Quận Long Biên","Phường Ngọc Thụy","1123 Nguyễn Trãi","anh_truoc_test.png","anh_sau_test.png",null);

        ResponseEntity<?> responseEntity = customerController.addYeucauDKSDN(addCustomerRequest);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        Assert.assertEquals(responseEntity.getBody(),"Không tìm thấy hộ dân cư có id="+addCustomerRequest.residentialTypeId);
    }

    @Test
    public void chap_thuan_yeu_cau_DKSD()
    {
        CreateUserRequest addCustomerRequest = new CreateUserRequest("Trần Văn Test","123", 48L, 10L,"flslayder1312@gmail.com");
        ResponseEntity<?> responseEntity = authController.acceptRequest(addCustomerRequest);
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);
        User  user =userRepository.findByCustomerId(addCustomerRequest.customer_id);
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getUsername(),"tvt12300000011");
        Assert.assertTrue(Optional.ofNullable(user.getRole().getId()).isPresent());
        Assert.assertEquals(user.getRole().getId().intValue(), 1);
    }
    @Test
    public void chap_thuan_yeu_cau_email_ko_hop_le()
    {
        CreateUserRequest addCustomerRequest = new CreateUserRequest("Trần Văn Test","123", 48L, 10L,"flslayder1312gmail.com");
        ResponseEntity<?> responseEntity = authController.acceptRequest(addCustomerRequest);
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.BAD_REQUEST);

    }
    @Test
    public void  tu_choi_yeu_cau_DKSDN_thanh_cong(){
        RejectRequest rejectRequest = new RejectRequest(48,"Không đúng địa chỉ");
        ResponseEntity<?> responseEntity = authController.rejectRequest(rejectRequest);
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);
        Assert.assertEquals(responseEntity.getBody(),"Từ chối yêu cầu thành công");
        Assert.assertEquals(customerRepository.findById(48L),Optional.empty());
    }

    @Test
    public void tu_choi_yeu_cau_DKSDN_thieu_ly_do()
    {
        RejectRequest rejectRequest = new RejectRequest(48,"");
        ResponseEntity<?> responseEntity = authController.rejectRequest(rejectRequest);
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.BAD_REQUEST);
        Assert.assertEquals(responseEntity.getBody(),"Chưa nhập lý do từ chối");
        Assert.assertTrue(customerRepository.findById(48L).isPresent());
    }

    @Test
    public void tu_choi_yeu_cau_DKSDN_sai_customer_id()
    {
        RejectRequest rejectRequest = new RejectRequest(499,"dấ");
        ResponseEntity<?> responseEntity = authController.rejectRequest(rejectRequest);
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.NOT_FOUND);
        Assert.assertEquals(responseEntity.getBody(),"Không tìm thấy invoice có id=499");
        Assert.assertTrue(customerRepository.findById(48L).isPresent());
    }

    @Test
    public void gui_email_thanh_cong()
    {
        String content= "Đăng ký sử dụng nước thành công!!!!\n"
                +"Vui lòng đăng nhập vào app với \n"+
                "Username: "+"user_name_test"+"\n"
                +"Password: "+ "password_test"+"\n";
        ResponseEntity<?> responseEntity = authController.sendMail("flslayder1312@gmail.com",content,"Đăng ký sử dụng nước thành công");
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);
        Assert.assertEquals(responseEntity.getBody(),"Gửi Thành Công");
    }

    @Test
    public void gui_email_sai_email_sai_dinh_dang()
    {
        String content= "Đăng ký sử dụng nước thành công!!!!\n"
                +"Vui lòng đăng nhập vào app với \n"+
                "Username: "+"user_name_test"+"\n"
                +"Password: "+ "password_test"+"\n";
        ResponseEntity<?> responseEntity = authController.sendMail("fhmdj24234nsgmail.com",content,"Đăng ký sử dụng nước thành công");
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.BAD_REQUEST);
        Assert.assertEquals(responseEntity.getBody(),"Email không tồn tại");
    }

    @Test
    public void tim_kiem_kh_nonactived_theo_ten_1()
    {
        String name = "Huy";
        ResponseEntity<?> responseEntity = customerController.getNonActiveCustomers(name,null,null);
        List<Customer> customerList = (List<Customer>) responseEntity.getBody();
        assert customerList != null;
        Assert.assertEquals(customerList.size(),2);
    }
    @Test
    public void tim_kiem_kh_nonactived_theo_ten_2()
    {
        String name = "Nguyễn Văn A";
        ResponseEntity<?> responseEntity = customerController.getNonActiveCustomers(name,null,null);
        List<Customer> customerList = (List<Customer>) responseEntity.getBody();
        assert customerList != null;
        Assert.assertEquals(customerList.size(),1);
    }
    @Test
    public void tim_kiem_kh_nonactived_theo_base_name_1()
    {
        String baseName = "Hà Nội";
        ResponseEntity<?> responseEntity = customerController.getNonActiveCustomers(null,baseName,null);
        List<Customer> customerList = (List<Customer>) responseEntity.getBody();
        assert customerList != null;
        Assert.assertEquals(customerList.size(),0);
    }
    @Test
    public void tim_kiem_kh_nonactived_theo_base_name_2()
    {
        String baseName = "Công ty TNHH Nước sạch Hà Nội";
        ResponseEntity<?> responseEntity = customerController.getNonActiveCustomers(null,baseName,null);
        List<Customer> customerList = (List<Customer>) responseEntity.getBody();
        assert customerList != null;
        Assert.assertEquals(customerList.size(),4);
    }

    @Test
    public void tim_kiem_kh_nonactived_theo_residentialName_1()
    {
        String residentialName = "Hộ dân cư";
        ResponseEntity<?> responseEntity = customerController.getNonActiveCustomers(null,null,residentialName);
        List<Customer> customerList = (List<Customer>) responseEntity.getBody();
        assert customerList!=null;
        Assert.assertEquals(customerList.size(),3);
    }

    @Test
    public void tim_kiem_kh_nonactived_theo_residentialName_2()
    {
        String residentialName = "cư";
        ResponseEntity<?> responseEntity = customerController.getNonActiveCustomers(null,null,residentialName);
        List<Customer> customerList = (List<Customer>) responseEntity.getBody();
        assert customerList!=null;
        Assert.assertEquals(customerList.size(),0);
    }

    @Test
    public void tim_kiem_kh_nonactived_base_name_null()
    {
        String residentialName= "Hộ dân cư";
        String name = "Huy";
        ResponseEntity<?> responseEntity = customerController.getNonActiveCustomers(name,null,residentialName);
        List<Customer> customerList = (List<Customer>) responseEntity.getBody();
        assert customerList!=null;
        Assert.assertEquals(customerList.size(),2);
    }

    @Test
    public void tim_kiem_kh_nonactived_residential_null()
    {
        String name = "Huy";
        ResponseEntity<?> responseEntity = customerController.getNonActiveCustomers(name,"Công ty TNHH Nước sạch Hà Nội" ,null);
        List<Customer> customerList = (List<Customer>) responseEntity.getBody();
        assert customerList!=null;
        Assert.assertEquals(customerList.size(),2);
    }

    @Test
    public void tim_kiem_kh_nonactived_name_null()
    {
        ResponseEntity<?> responseEntity = customerController.getNonActiveCustomers(null,"Công ty TNHH Nước sạch Hà Nội" ,"Hộ dân cư");
        List<Customer> customerList = (List<Customer>) responseEntity.getBody();
        assert customerList!=null;
        Assert.assertEquals(customerList.size(),3);
    }



}

