package com.example.dbclpm_be.controller;

import com.example.dbclpm_be.entity.*;
import com.example.dbclpm_be.respository.*;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import payload.request.AddCustomerRequest;
import payload.request.FilterCustomerRequest;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/customer")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BaseRespository baseRespository;

    @Autowired
    private ResidentialTypeRepository residentialTypeRepository;
    @Autowired
    private AddressRespository addressRespository;
    @Autowired
    private InfoCommonRespository infoCommonRespository;

    ///Đã Test
    @PostMapping("/add")
    public ResponseEntity<?> addYeucauDKSDN(@RequestBody AddCustomerRequest addCustomerRequest)
    {
        try {
            Optional<Base> optionalBase = baseRespository.findById(addCustomerRequest.baseId);
            Base base = optionalBase.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Không tìm thấy base có id=" + addCustomerRequest.baseId));

            Optional<ResidentialType> residentialTypeOptional = residentialTypeRepository.findById(addCustomerRequest.residentialTypeId);
            ResidentialType residentialType = residentialTypeOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Không tìm thấy hộ dân cư có id=" + addCustomerRequest.residentialTypeId));

            InfoCommon infoCommon = new InfoCommon(addCustomerRequest.email, addCustomerRequest.phoneNumber, addCustomerRequest.name);

            Address address = new Address(addCustomerRequest.detailsAddress, addCustomerRequest.provinceOrCity, addCustomerRequest.district, addCustomerRequest.wards);
            List<Address> addresses = addressRespository.findByAllFields(address.getDetailsAddress().replace(" ", "").toLowerCase(), address.getProvinceOrCity(), address.getDistrict(), address.getWards());

            if (!addresses.isEmpty()) {
                return ResponseEntity.badRequest().body("Địa chỉ đã tồn tại");
            }
            List<Customer> customerList = customerRepository.findByPhoneNumber(addCustomerRequest.phoneNumber);
            if (!customerList.isEmpty()) {
                return ResponseEntity.badRequest().body("Số điện thoại đã tồn tại");
            }
            infoCommonRespository.save(infoCommon);
            addressRespository.save(address);
            Customer customer = new Customer(base, infoCommon, residentialType, address, false, addCustomerRequest.front_image, addCustomerRequest.back_image, addCustomerRequest.certificate_of_poverty);
            customer.setWaterIndex(0L);
            if (addCustomerRequest.residentialTypeId != 2L) {
                customer.setCertificate_of_poverty(null);
            } else {
                if (addCustomerRequest.certificate_of_poverty == null) {
                    return ResponseEntity.badRequest().body("Chưa up giấy chứng nhận hộ nghèo");
                }
            }
            customerRepository.save(customer);
            return ResponseEntity.ok(customer.getId());
        }catch (ResponseStatusException ex)
        {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
        }
    }

    //
    @GetMapping("/{cus_id}")
    public ResponseEntity<?> getCustomerByID(@PathVariable long cus_id)
    {
        try {
            Optional<Customer> customer = customerRepository.findById(cus_id);
            Customer customer1 = customer.orElseThrow(() ->new ResponseStatusException(HttpStatus.BAD_REQUEST,"Không tìm thấy customer có id=" + cus_id));
            return ResponseEntity.ok(customer1);
        }catch (ResponseStatusException ex)
        {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
        }
    }

    @GetMapping("/getNonActivedCustomers")
    public ResponseEntity<?> getNonActiveCustomers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String baseName,
            @RequestParam(required = false) String residentialName) {
        // Logic here
        List<Customer> customerList = customerRepository.findNonActiveCustomers(name,baseName,residentialName);
        return ResponseEntity.ok(customerList);
    }
    @GetMapping("/getActivedCustomers")
    public ResponseEntity<?> getAllActiveCustomers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String baseName,
            @RequestParam(required = false) String residentialName,
            @RequestParam(required = false) String ordinalNumbers) {
        // Logic here
        List<Customer> customerList = customerRepository.findCustomerByOrdinalNumbersAndNameAndBaseNameAndResidentialType(name,baseName,residentialName,ordinalNumbers);
        return ResponseEntity.ok(customerList);
    }
    @PostMapping("/updateIndexWater")
    public ResponseEntity<?> updateIndexWater(@RequestParam long cus_id ,@RequestParam(required = true) long newIndexWater){
        try {
            if(newIndexWater<0)
            {
                return ResponseEntity.badRequest().body("Số nước mới không hợp lệ");
            }
            Optional<Customer> customer = customerRepository.findById(cus_id);
            Customer customer1 = customer.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Khong tim thay customer co id=" + cus_id));
            if(newIndexWater == customer1.getWaterIndex())
            {
                return ResponseEntity.badRequest().body("Số nước mới trùng với số nước cũ");
            }
            customer1.setWaterIndex(newIndexWater);
            customerRepository.save(customer1);
            return ResponseEntity.ok("Cập nhật số nước thành công");
        }catch (ResponseStatusException ex)
        {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
        }

    }
}
