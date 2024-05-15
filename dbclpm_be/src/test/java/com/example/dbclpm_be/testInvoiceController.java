package com.example.dbclpm_be;

import com.example.dbclpm_be.controller.InvoiceController;
import com.example.dbclpm_be.entity.Customer;
import com.example.dbclpm_be.entity.Invoice;
import com.example.dbclpm_be.respository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import payload.request.AddInvoiceRequest;
import payload.response.InvoiceDetailsResponse;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class testInvoiceController {
    @Autowired
    private InvoiceController invoiceController;
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void test_add_invoice_residential_1_7() throws ParseException {
        AddInvoiceRequest addInvoiceRequest = new AddInvoiceRequest(1,110,"04/2024");
        ResponseEntity<?> responseEntity =invoiceController.addInvoice(addInvoiceRequest);
        Invoice invoice = (Invoice) responseEntity.getBody();
        assert invoice != null;
        Assert.assertEquals(59500,invoice.getTotalNoTax());
        Assert.assertEquals(68425,invoice.getTotalAll());
        Assert.assertEquals(invoice.getTotalTax(),8925);
        Assert.assertEquals(invoice.getNewNumber(),110);
        Assert.assertEquals(invoice.getOldNumber(),103);
        ResponseEntity<?> responseEntity1 =invoiceController.getInvoiceById(invoice.getId());
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);
        Assert.assertEquals(((Invoice) responseEntity.getBody()).getId(),invoice.getId());
    }
    @Test
    public void test_add_invoice_residential_1_10() throws ParseException {
        AddInvoiceRequest addInvoiceRequest = new AddInvoiceRequest(1,113,"04/2024");
        ResponseEntity<?> responseEntity =invoiceController.addInvoice(addInvoiceRequest);
        Invoice invoice = (Invoice) responseEntity.getBody();
        assert invoice != null;
        Assert.assertEquals(85000,invoice.getTotalNoTax());
        Assert.assertEquals(97750,invoice.getTotalAll());
        Assert.assertEquals(invoice.getTotalTax(),12750);
        Assert.assertEquals(invoice.getNewNumber(),113);
        Assert.assertEquals(invoice.getOldNumber(),103);
        ResponseEntity<?> responseEntity1 =invoiceController.getInvoiceById(invoice.getId());
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);
        Assert.assertEquals(((Invoice) responseEntity.getBody()).getId(),invoice.getId());
    }
    @Test
    public void test_add_invoice_residential_1_17() throws ParseException {
        AddInvoiceRequest addInvoiceRequest = new AddInvoiceRequest(1,120,"04/2024");
        ResponseEntity<?> responseEntity =invoiceController.addInvoice(addInvoiceRequest);
        Invoice invoice = (Invoice) responseEntity.getBody();
        assert invoice != null;
        Assert.assertEquals(154300,invoice.getTotalNoTax());
        Assert.assertEquals(invoice.getTotalTax(),23145);
        Assert.assertEquals(177445,invoice.getTotalAll());

        Assert.assertEquals(invoice.getNewNumber(),120);
        Assert.assertEquals(invoice.getOldNumber(),103);
        ResponseEntity<?> responseEntity1 =invoiceController.getInvoiceById(invoice.getId());
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);
        Assert.assertEquals(((Invoice) responseEntity.getBody()).getId(),invoice.getId());
    }

    @Test
    public void test_add_invoice_residential_1_20() throws ParseException {
        long newNumber = 123;
        AddInvoiceRequest addInvoiceRequest = new AddInvoiceRequest(1,newNumber,"04/2024");
        ResponseEntity<?> responseEntity =invoiceController.addInvoice(addInvoiceRequest);
        Invoice invoice = (Invoice) responseEntity.getBody();
        assert invoice != null;
        Assert.assertEquals(184000,invoice.getTotalNoTax());
        Assert.assertEquals(invoice.getTotalTax(),27600);
        Assert.assertEquals(211600,invoice.getTotalAll());

        Assert.assertEquals(invoice.getNewNumber(),newNumber);
        Assert.assertEquals(invoice.getOldNumber(),103);
        ResponseEntity<?> responseEntity1 =invoiceController.getInvoiceById(invoice.getId());
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);
        Assert.assertEquals(((Invoice) responseEntity.getBody()).getId(),invoice.getId());
    }

    @Test
    public void test_add_invoice_residential_1_27() throws ParseException {
        long newNumber = 130;
        AddInvoiceRequest addInvoiceRequest = new AddInvoiceRequest(1,newNumber,"04/2024");
        ResponseEntity<?> responseEntity =invoiceController.addInvoice(addInvoiceRequest);
        Invoice invoice = (Invoice) responseEntity.getBody();
        assert invoice != null;
        Assert.assertEquals(296000,invoice.getTotalNoTax());
        Assert.assertEquals(invoice.getTotalTax(),44400);
        Assert.assertEquals(340400,invoice.getTotalAll());

        Assert.assertEquals(invoice.getNewNumber(),newNumber);
        Assert.assertEquals(invoice.getOldNumber(),103);
        ResponseEntity<?> responseEntity1 =invoiceController.getInvoiceById(invoice.getId());
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);
        Assert.assertEquals(((Invoice) responseEntity.getBody()).getId(),invoice.getId());
    }

    @Test
    public void test_add_invoice_residential_1_30() throws ParseException {
        long newNumber = 133;
        AddInvoiceRequest addInvoiceRequest = new AddInvoiceRequest(1,newNumber,"04/2024");
        ResponseEntity<?> responseEntity =invoiceController.addInvoice(addInvoiceRequest);
        Invoice invoice = (Invoice) responseEntity.getBody();
        assert invoice != null;
        Assert.assertEquals(344000,invoice.getTotalNoTax());
        Assert.assertEquals(invoice.getTotalTax(),51600);
        Assert.assertEquals(395600,invoice.getTotalAll());

        Assert.assertEquals(invoice.getNewNumber(),newNumber);
        Assert.assertEquals(invoice.getOldNumber(),103);
        ResponseEntity<?> responseEntity1 =invoiceController.getInvoiceById(invoice.getId());
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);
        Assert.assertEquals(((Invoice) responseEntity.getBody()).getId(),invoice.getId());
    }

    @Test
    public void test_add_invoice_residential_1_37() throws ParseException {
        long newNumber = 140;
        AddInvoiceRequest addInvoiceRequest = new AddInvoiceRequest(1,newNumber,"04/2024");
        ResponseEntity<?> responseEntity =invoiceController.addInvoice(addInvoiceRequest);
        Invoice invoice = (Invoice) responseEntity.getBody();
        assert invoice != null;
        Assert.assertEquals(533000,invoice.getTotalNoTax());
        Assert.assertEquals(invoice.getTotalTax(),79950);
        Assert.assertEquals(612950,invoice.getTotalAll());

        Assert.assertEquals(invoice.getNewNumber(),newNumber);
        Assert.assertEquals(invoice.getOldNumber(),103);
        ResponseEntity<?> responseEntity1 =invoiceController.getInvoiceById(invoice.getId());
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);
        Assert.assertEquals(((Invoice) responseEntity.getBody()).getId(),invoice.getId());
    }

    @Test
    public void test_add_invoice_residential_2_7() throws ParseException {
        long newIndex = 107;
        AddInvoiceRequest addInvoiceRequest = new AddInvoiceRequest(2,newIndex,"04/2024");
        ResponseEntity<?> responseEntity =invoiceController.addInvoice(addInvoiceRequest);
        Invoice invoice = (Invoice) responseEntity.getBody();
        assert invoice != null;
        Assert.assertEquals(41811,invoice.getTotalNoTax());
        Assert.assertEquals(48083,invoice.getTotalAll());
        Assert.assertEquals(invoice.getTotalTax(),6272);
        Assert.assertEquals(invoice.getNewNumber(),newIndex);
        Assert.assertEquals(invoice.getOldNumber(),100);
        ResponseEntity<?> responseEntity1 =invoiceController.getInvoiceById(invoice.getId());
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);
        Assert.assertEquals(((Invoice) responseEntity.getBody()).getId(),invoice.getId());
    }
    @Test
    public void test_add_invoice_residential_2_10() throws ParseException {
        long newIndex = 110;
        AddInvoiceRequest addInvoiceRequest = new AddInvoiceRequest(2,newIndex,"04/2024");
        ResponseEntity<?> responseEntity =invoiceController.addInvoice(addInvoiceRequest);
        Invoice invoice = (Invoice) responseEntity.getBody();
        assert invoice != null;
        Assert.assertEquals(59730,invoice.getTotalNoTax());
        Assert.assertEquals(68690,invoice.getTotalAll());
        Assert.assertEquals(invoice.getTotalTax(),8960);
        Assert.assertEquals(invoice.getNewNumber(),newIndex);
        Assert.assertEquals(invoice.getOldNumber(),100);
        ResponseEntity<?> responseEntity1 =invoiceController.getInvoiceById(invoice.getId());
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);
        Assert.assertEquals(((Invoice) responseEntity.getBody()).getId(),invoice.getId());
    }
    @Test
    public void test_add_invoice_residential_2_17() throws ParseException {
        long newIndex = 117;
        AddInvoiceRequest addInvoiceRequest = new AddInvoiceRequest(2,newIndex,"04/2024");
        ResponseEntity<?> responseEntity =invoiceController.addInvoice(addInvoiceRequest);
        Invoice invoice = (Invoice) responseEntity.getBody();
        assert invoice != null;
        Assert.assertEquals(129030,invoice.getTotalNoTax());
        Assert.assertEquals(invoice.getTotalTax(),19355);
        Assert.assertEquals(148385,invoice.getTotalAll());

        Assert.assertEquals(invoice.getNewNumber(),newIndex);
        Assert.assertEquals(invoice.getOldNumber(),100);
        ResponseEntity<?> responseEntity1 =invoiceController.getInvoiceById(invoice.getId());
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);
        Assert.assertEquals(((Invoice) responseEntity.getBody()).getId(),invoice.getId());
    }

    @Test
    public void test_add_invoice_residential_2_20() throws ParseException {
        long newNumber = 120;
        AddInvoiceRequest addInvoiceRequest = new AddInvoiceRequest(2,newNumber,"04/2024");
        ResponseEntity<?> responseEntity =invoiceController.addInvoice(addInvoiceRequest);
        Invoice invoice = (Invoice) responseEntity.getBody();
        assert invoice != null;
        Assert.assertEquals(158730,invoice.getTotalNoTax());
        Assert.assertEquals(invoice.getTotalTax(),23810);
        Assert.assertEquals(182540,invoice.getTotalAll());

        Assert.assertEquals(invoice.getNewNumber(),newNumber);
        Assert.assertEquals(invoice.getOldNumber(),100);
        ResponseEntity<?> responseEntity1 =invoiceController.getInvoiceById(invoice.getId());
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);
        Assert.assertEquals(((Invoice) responseEntity.getBody()).getId(),invoice.getId());
    }

    @Test
    public void test_add_invoice_residential_2_27() throws ParseException {
        long newNumber = 127;
        AddInvoiceRequest addInvoiceRequest = new AddInvoiceRequest(2,newNumber,"04/2024");
        ResponseEntity<?> responseEntity =invoiceController.addInvoice(addInvoiceRequest);
        Invoice invoice = (Invoice) responseEntity.getBody();
        assert invoice != null;
        Assert.assertEquals(270730,invoice.getTotalNoTax());
        Assert.assertEquals(invoice.getTotalTax(),40610);
        Assert.assertEquals(311340,invoice.getTotalAll());

        Assert.assertEquals(invoice.getNewNumber(),newNumber);
        Assert.assertEquals(invoice.getOldNumber(),100);
        ResponseEntity<?> responseEntity1 =invoiceController.getInvoiceById(invoice.getId());
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);
        Assert.assertEquals(((Invoice) responseEntity.getBody()).getId(),invoice.getId());
    }

    @Test
    public void test_add_invoice_residential_2_30() throws ParseException {
        long newNumber = 130;
        AddInvoiceRequest addInvoiceRequest = new AddInvoiceRequest(2,newNumber,"04/2024");
        ResponseEntity<?> responseEntity =invoiceController.addInvoice(addInvoiceRequest);
        Invoice invoice = (Invoice) responseEntity.getBody();
        assert invoice != null;
        Assert.assertEquals(318730,invoice.getTotalNoTax());
        Assert.assertEquals(invoice.getTotalTax(),47810);
        Assert.assertEquals(366540,invoice.getTotalAll());

        Assert.assertEquals(invoice.getNewNumber(),newNumber);
        Assert.assertEquals(invoice.getOldNumber(),100);
        ResponseEntity<?> responseEntity1 =invoiceController.getInvoiceById(invoice.getId());
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);
        Assert.assertEquals(((Invoice) responseEntity.getBody()).getId(),invoice.getId());
    }

    @Test
    public void test_add_invoice_residential_2_37() throws ParseException {
        long newNumber = 137;
        AddInvoiceRequest addInvoiceRequest = new AddInvoiceRequest(2,newNumber,"04/2024");
        ResponseEntity<?> responseEntity =invoiceController.addInvoice(addInvoiceRequest);
        Invoice invoice = (Invoice) responseEntity.getBody();
        assert invoice != null;
        Assert.assertEquals(507730,invoice.getTotalNoTax());
        Assert.assertEquals(invoice.getTotalTax(),76160);
        Assert.assertEquals(583890,invoice.getTotalAll());

        Assert.assertEquals(invoice.getNewNumber(),newNumber);
        Assert.assertEquals(invoice.getOldNumber(),100);
        ResponseEntity<?> responseEntity1 =invoiceController.getInvoiceById(invoice.getId());
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);
        Assert.assertEquals(((Invoice) responseEntity.getBody()).getId(),invoice.getId());
    }


    @Test
    public void test_add_invoice_residential_3_7() throws ParseException {
        long newIndex = 7;
        AddInvoiceRequest addInvoiceRequest = new AddInvoiceRequest(35,newIndex,"04/2024");
        ResponseEntity<?> responseEntity =invoiceController.addInvoice(addInvoiceRequest);
        Invoice invoice = (Invoice) responseEntity.getBody();
        assert invoice != null;
        Assert.assertEquals(94500,invoice.getTotalNoTax());
        Assert.assertEquals(108675,invoice.getTotalAll());
        Assert.assertEquals(invoice.getTotalTax(),14175);
        Assert.assertEquals(invoice.getNewNumber(),newIndex);
        Assert.assertEquals(invoice.getOldNumber(),0);
        ResponseEntity<?> responseEntity1 =invoiceController.getInvoiceById(invoice.getId());
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);
        Assert.assertEquals(((Invoice) responseEntity.getBody()).getId(),invoice.getId());
    }
    @Test
    public void test_add_invoice_residential_3_10() throws ParseException {
        long newIndex = 10;
        AddInvoiceRequest addInvoiceRequest = new AddInvoiceRequest(35,newIndex,"04/2024");
        ResponseEntity<?> responseEntity =invoiceController.addInvoice(addInvoiceRequest);
        Invoice invoice = (Invoice) responseEntity.getBody();
        assert invoice != null;
        Assert.assertEquals(135000,invoice.getTotalNoTax());
        Assert.assertEquals(155250,invoice.getTotalAll());
        Assert.assertEquals(invoice.getTotalTax(),20250);
        Assert.assertEquals(invoice.getNewNumber(),newIndex);
        Assert.assertEquals(invoice.getOldNumber(),0);
        ResponseEntity<?> responseEntity1 =invoiceController.getInvoiceById(invoice.getId());
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);
        Assert.assertEquals(((Invoice) responseEntity.getBody()).getId(),invoice.getId());
    }
    @Test
    public void test_add_invoice_residential_3_17() throws ParseException {
        long newIndex = 17;
        AddInvoiceRequest addInvoiceRequest = new AddInvoiceRequest(35,newIndex,"04/2024");
        ResponseEntity<?> responseEntity =invoiceController.addInvoice(addInvoiceRequest);
        Invoice invoice = (Invoice) responseEntity.getBody();
        assert invoice != null;
        Assert.assertEquals(229500,invoice.getTotalNoTax());
        Assert.assertEquals(invoice.getTotalTax(),34425);
        Assert.assertEquals(263925,invoice.getTotalAll());

        Assert.assertEquals(invoice.getNewNumber(),newIndex);
        Assert.assertEquals(invoice.getOldNumber(),0);
        ResponseEntity<?> responseEntity1 =invoiceController.getInvoiceById(invoice.getId());
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);
        Assert.assertEquals(((Invoice) responseEntity.getBody()).getId(),invoice.getId());
    }

    @Test
    public void test_add_invoice_residential_3_20() throws ParseException {
        long newNumber = 20;
        AddInvoiceRequest addInvoiceRequest = new AddInvoiceRequest(35,newNumber,"04/2024");
        ResponseEntity<?> responseEntity =invoiceController.addInvoice(addInvoiceRequest);
        Invoice invoice = (Invoice) responseEntity.getBody();
        assert invoice != null;
        Assert.assertEquals(270000,invoice.getTotalNoTax());
        Assert.assertEquals(invoice.getTotalTax(),40500);
        Assert.assertEquals(310500,invoice.getTotalAll());

        Assert.assertEquals(invoice.getNewNumber(),newNumber);
        Assert.assertEquals(invoice.getOldNumber(),0);
        ResponseEntity<?> responseEntity1 =invoiceController.getInvoiceById(invoice.getId());
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);
        Assert.assertEquals(((Invoice) responseEntity.getBody()).getId(),invoice.getId());
    }

    @Test
    public void test_add_invoice_residential_3_27() throws ParseException {
        long newNumber = 27;
        AddInvoiceRequest addInvoiceRequest = new AddInvoiceRequest(35,newNumber,"04/2024");
        ResponseEntity<?> responseEntity =invoiceController.addInvoice(addInvoiceRequest);
        Invoice invoice = (Invoice) responseEntity.getBody();
        assert invoice != null;
        Assert.assertEquals(364500,invoice.getTotalNoTax());
        Assert.assertEquals(invoice.getTotalTax(),54675);
        Assert.assertEquals(419175,invoice.getTotalAll());

        Assert.assertEquals(invoice.getNewNumber(),newNumber);
        Assert.assertEquals(invoice.getOldNumber(),0);
        ResponseEntity<?> responseEntity1 =invoiceController.getInvoiceById(invoice.getId());
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);
        Assert.assertEquals(((Invoice) responseEntity.getBody()).getId(),invoice.getId());
    }

    @Test
    public void test_add_invoice_residential_3_30() throws ParseException {
        long newNumber = 30;
        AddInvoiceRequest addInvoiceRequest = new AddInvoiceRequest(35,newNumber,"04/2024");
        ResponseEntity<?> responseEntity =invoiceController.addInvoice(addInvoiceRequest);
        Invoice invoice = (Invoice) responseEntity.getBody();
        assert invoice != null;
        Assert.assertEquals(405000,invoice.getTotalNoTax());
        Assert.assertEquals(invoice.getTotalTax(),60750);
        Assert.assertEquals(465750,invoice.getTotalAll());

        Assert.assertEquals(invoice.getNewNumber(),newNumber);
        Assert.assertEquals(invoice.getOldNumber(),0);
        ResponseEntity<?> responseEntity1 =invoiceController.getInvoiceById(invoice.getId());
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);
        Assert.assertEquals(((Invoice) responseEntity.getBody()).getId(),invoice.getId());
    }

    @Test
    public void test_add_invoice_residential_3_37() throws ParseException {
        long newNumber = 37;
        AddInvoiceRequest addInvoiceRequest = new AddInvoiceRequest(35,newNumber,"04/2024");
        ResponseEntity<?> responseEntity =invoiceController.addInvoice(addInvoiceRequest);
        Invoice invoice = (Invoice) responseEntity.getBody();
        assert invoice != null;
        Assert.assertEquals(499500,invoice.getTotalNoTax());
        Assert.assertEquals(invoice.getTotalTax(),74925);
        Assert.assertEquals(574425,invoice.getTotalAll());

        Assert.assertEquals(invoice.getNewNumber(),newNumber);
        Assert.assertEquals(invoice.getOldNumber(),0);
        ResponseEntity<?> responseEntity1 =invoiceController.getInvoiceById(invoice.getId());
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);
        Assert.assertEquals(((Invoice) responseEntity.getBody()).getId(),invoice.getId());
    }



    @Test
    public void test_add_invoice_residential_4_7() throws ParseException {
        long newIndex = 7;
        AddInvoiceRequest addInvoiceRequest = new AddInvoiceRequest(36,newIndex,"04/2024");
        ResponseEntity<?> responseEntity =invoiceController.addInvoice(addInvoiceRequest);
        Invoice invoice = (Invoice) responseEntity.getBody();
        assert invoice != null;
        Assert.assertEquals(94500,invoice.getTotalNoTax());
        Assert.assertEquals(108675,invoice.getTotalAll());
        Assert.assertEquals(invoice.getTotalTax(),14175);
        Assert.assertEquals(invoice.getNewNumber(),newIndex);
        Assert.assertEquals(invoice.getOldNumber(),0);
        ResponseEntity<?> responseEntity1 =invoiceController.getInvoiceById(invoice.getId());
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);
        Assert.assertEquals(((Invoice) responseEntity.getBody()).getId(),invoice.getId());
    }
    @Test
    public void test_add_invoice_residential_4_10() throws ParseException {
        long newIndex = 10;
        AddInvoiceRequest addInvoiceRequest = new AddInvoiceRequest(36,newIndex,"04/2024");
        ResponseEntity<?> responseEntity =invoiceController.addInvoice(addInvoiceRequest);
        Invoice invoice = (Invoice) responseEntity.getBody();
        assert invoice != null;
        Assert.assertEquals(135000,invoice.getTotalNoTax());
        Assert.assertEquals(155250,invoice.getTotalAll());
        Assert.assertEquals(invoice.getTotalTax(),20250);
        Assert.assertEquals(invoice.getNewNumber(),newIndex);
        Assert.assertEquals(invoice.getOldNumber(),0);
        ResponseEntity<?> responseEntity1 =invoiceController.getInvoiceById(invoice.getId());
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);
        Assert.assertEquals(((Invoice) responseEntity.getBody()).getId(),invoice.getId());
    }
    @Test
    public void test_add_invoice_residential_4_17() throws ParseException {
        long newIndex = 17;
        AddInvoiceRequest addInvoiceRequest = new AddInvoiceRequest(36,newIndex,"04/2024");
        ResponseEntity<?> responseEntity =invoiceController.addInvoice(addInvoiceRequest);
        Invoice invoice = (Invoice) responseEntity.getBody();
        assert invoice != null;
        Assert.assertEquals(229500,invoice.getTotalNoTax());
        Assert.assertEquals(invoice.getTotalTax(),34425);
        Assert.assertEquals(263925,invoice.getTotalAll());

        Assert.assertEquals(invoice.getNewNumber(),newIndex);
        Assert.assertEquals(invoice.getOldNumber(),0);
        ResponseEntity<?> responseEntity1 =invoiceController.getInvoiceById(invoice.getId());
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);
        Assert.assertEquals(((Invoice) responseEntity.getBody()).getId(),invoice.getId());
    }

    @Test
    public void test_add_invoice_residential_4_20() throws ParseException {
        long newNumber = 20;
        AddInvoiceRequest addInvoiceRequest = new AddInvoiceRequest(36,newNumber,"04/2024");
        ResponseEntity<?> responseEntity =invoiceController.addInvoice(addInvoiceRequest);
        Invoice invoice = (Invoice) responseEntity.getBody();
        assert invoice != null;
        Assert.assertEquals(270000,invoice.getTotalNoTax());
        Assert.assertEquals(invoice.getTotalTax(),40500);
        Assert.assertEquals(310500,invoice.getTotalAll());

        Assert.assertEquals(invoice.getNewNumber(),newNumber);
        Assert.assertEquals(invoice.getOldNumber(),0);
        ResponseEntity<?> responseEntity1 =invoiceController.getInvoiceById(invoice.getId());
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);
        Assert.assertEquals(((Invoice) responseEntity.getBody()).getId(),invoice.getId());
    }

    @Test
    public void test_add_invoice_residential_4_27() throws ParseException {
        long newNumber = 27;
        AddInvoiceRequest addInvoiceRequest = new AddInvoiceRequest(36,newNumber,"04/2024");
        ResponseEntity<?> responseEntity =invoiceController.addInvoice(addInvoiceRequest);
        Invoice invoice = (Invoice) responseEntity.getBody();
        assert invoice != null;
        Assert.assertEquals(364500,invoice.getTotalNoTax());
        Assert.assertEquals(invoice.getTotalTax(),54675);
        Assert.assertEquals(419175,invoice.getTotalAll());

        Assert.assertEquals(invoice.getNewNumber(),newNumber);
        Assert.assertEquals(invoice.getOldNumber(),0);
        ResponseEntity<?> responseEntity1 =invoiceController.getInvoiceById(invoice.getId());
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);
        Assert.assertEquals(((Invoice) responseEntity.getBody()).getId(),invoice.getId());
    }

    @Test
    public void test_add_invoice_residential_4_30() throws ParseException {
        long newNumber = 30;
        AddInvoiceRequest addInvoiceRequest = new AddInvoiceRequest(36,newNumber,"04/2024");
        ResponseEntity<?> responseEntity =invoiceController.addInvoice(addInvoiceRequest);
        Invoice invoice = (Invoice) responseEntity.getBody();
        assert invoice != null;
        Assert.assertEquals(405000,invoice.getTotalNoTax());
        Assert.assertEquals(invoice.getTotalTax(),60750);
        Assert.assertEquals(465750,invoice.getTotalAll());

        Assert.assertEquals(invoice.getNewNumber(),newNumber);
        Assert.assertEquals(invoice.getOldNumber(),0);
        ResponseEntity<?> responseEntity1 =invoiceController.getInvoiceById(invoice.getId());
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);
        Assert.assertEquals(((Invoice) responseEntity.getBody()).getId(),invoice.getId());
    }

    @Test
    public void test_add_invoice_residential_4_37() throws ParseException {
        long newNumber = 37;
        AddInvoiceRequest addInvoiceRequest = new AddInvoiceRequest(36,newNumber,"04/2024");
        ResponseEntity<?> responseEntity =invoiceController.addInvoice(addInvoiceRequest);
        Invoice invoice = (Invoice) responseEntity.getBody();
        assert invoice != null;
        Assert.assertEquals(499500,invoice.getTotalNoTax());
        Assert.assertEquals(invoice.getTotalTax(),74925);
        Assert.assertEquals(574425,invoice.getTotalAll());

        Assert.assertEquals(invoice.getNewNumber(),newNumber);
        Assert.assertEquals(invoice.getOldNumber(),0);
        ResponseEntity<?> responseEntity1 =invoiceController.getInvoiceById(invoice.getId());
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);
        Assert.assertEquals(((Invoice) responseEntity.getBody()).getId(),invoice.getId());
    }



    @Test
    public void test_add_invoice_residential_5_7() throws ParseException {
        long newIndex = 7;
        AddInvoiceRequest addInvoiceRequest = new AddInvoiceRequest(37,newIndex,"04/2024");
        ResponseEntity<?> responseEntity =invoiceController.addInvoice(addInvoiceRequest);
        Invoice invoice = (Invoice) responseEntity.getBody();
        assert invoice != null;
        Assert.assertEquals(112000,invoice.getTotalNoTax());
        Assert.assertEquals(128800,invoice.getTotalAll());
        Assert.assertEquals(invoice.getTotalTax(),16800);
        Assert.assertEquals(invoice.getNewNumber(),newIndex);
        Assert.assertEquals(invoice.getOldNumber(),0);
        ResponseEntity<?> responseEntity1 =invoiceController.getInvoiceById(invoice.getId());
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);
        Assert.assertEquals(((Invoice) responseEntity.getBody()).getId(),invoice.getId());
    }
    @Test
    public void test_add_invoice_residential_5_10() throws ParseException {
        long newIndex = 10;
        AddInvoiceRequest addInvoiceRequest = new AddInvoiceRequest(37,newIndex,"04/2024");
        ResponseEntity<?> responseEntity =invoiceController.addInvoice(addInvoiceRequest);
        Invoice invoice = (Invoice) responseEntity.getBody();
        assert invoice != null;
        Assert.assertEquals(160000,invoice.getTotalNoTax());
        Assert.assertEquals(184000,invoice.getTotalAll());
        Assert.assertEquals(invoice.getTotalTax(),24000);
        Assert.assertEquals(invoice.getNewNumber(),newIndex);
        Assert.assertEquals(invoice.getOldNumber(),0);
        ResponseEntity<?> responseEntity1 =invoiceController.getInvoiceById(invoice.getId());
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);
        Assert.assertEquals(((Invoice) responseEntity.getBody()).getId(),invoice.getId());
    }
    @Test
    public void test_add_invoice_residential_5_17() throws ParseException {
        long newIndex = 17;
        AddInvoiceRequest addInvoiceRequest = new AddInvoiceRequest(37,newIndex,"04/2024");
        ResponseEntity<?> responseEntity =invoiceController.addInvoice(addInvoiceRequest);
        Invoice invoice = (Invoice) responseEntity.getBody();
        assert invoice != null;
        Assert.assertEquals(272000,invoice.getTotalNoTax());
        Assert.assertEquals(invoice.getTotalTax(),40800);
        Assert.assertEquals(312800,invoice.getTotalAll());

        Assert.assertEquals(invoice.getNewNumber(),newIndex);
        Assert.assertEquals(invoice.getOldNumber(),0);
        ResponseEntity<?> responseEntity1 =invoiceController.getInvoiceById(invoice.getId());
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);
        Assert.assertEquals(((Invoice) responseEntity.getBody()).getId(),invoice.getId());
    }

    @Test
    public void test_add_invoice_residential_5_20() throws ParseException {
        long newNumber = 20;
        AddInvoiceRequest addInvoiceRequest = new AddInvoiceRequest(37,newNumber,"04/2024");
        ResponseEntity<?> responseEntity =invoiceController.addInvoice(addInvoiceRequest);
        Invoice invoice = (Invoice) responseEntity.getBody();
        assert invoice != null;
        Assert.assertEquals(320000,invoice.getTotalNoTax());
        Assert.assertEquals(invoice.getTotalTax(),48000);
        Assert.assertEquals(368000,invoice.getTotalAll());

        Assert.assertEquals(invoice.getNewNumber(),newNumber);
        Assert.assertEquals(invoice.getOldNumber(),0);
        ResponseEntity<?> responseEntity1 =invoiceController.getInvoiceById(invoice.getId());
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);
        Assert.assertEquals(((Invoice) responseEntity.getBody()).getId(),invoice.getId());
    }

    @Test
    public void test_add_invoice_residential_5_27() throws ParseException {
        long newNumber = 27;
        AddInvoiceRequest addInvoiceRequest = new AddInvoiceRequest(37,newNumber,"04/2024");
        ResponseEntity<?> responseEntity =invoiceController.addInvoice(addInvoiceRequest);
        Invoice invoice = (Invoice) responseEntity.getBody();
        assert invoice != null;
        Assert.assertEquals(432000,invoice.getTotalNoTax());
        Assert.assertEquals(invoice.getTotalTax(),64800);
        Assert.assertEquals(496800,invoice.getTotalAll());

        Assert.assertEquals(invoice.getNewNumber(),newNumber);
        Assert.assertEquals(invoice.getOldNumber(),0);
        ResponseEntity<?> responseEntity1 =invoiceController.getInvoiceById(invoice.getId());
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);
        Assert.assertEquals(((Invoice) responseEntity.getBody()).getId(),invoice.getId());
    }

    @Test
    public void test_add_invoice_residential_5_30() throws ParseException {
        long newNumber = 30;
        AddInvoiceRequest addInvoiceRequest = new AddInvoiceRequest(37,newNumber,"04/2024");
        ResponseEntity<?> responseEntity =invoiceController.addInvoice(addInvoiceRequest);
        Invoice invoice = (Invoice) responseEntity.getBody();
        assert invoice != null;
        Assert.assertEquals(480000,invoice.getTotalNoTax());
        Assert.assertEquals(invoice.getTotalTax(),72000);
        Assert.assertEquals(552000,invoice.getTotalAll());

        Assert.assertEquals(invoice.getNewNumber(),newNumber);
        Assert.assertEquals(invoice.getOldNumber(),0);
        ResponseEntity<?> responseEntity1 =invoiceController.getInvoiceById(invoice.getId());
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);
        Assert.assertEquals(((Invoice) responseEntity.getBody()).getId(),invoice.getId());
    }

    @Test
    public void test_add_invoice_residential_5_37() throws ParseException {
        long newNumber = 37;
        AddInvoiceRequest addInvoiceRequest = new AddInvoiceRequest(37,newNumber,"04/2024");
        ResponseEntity<?> responseEntity =invoiceController.addInvoice(addInvoiceRequest);
        Invoice invoice = (Invoice) responseEntity.getBody();
        assert invoice != null;
        Assert.assertEquals(592000,invoice.getTotalNoTax());
        Assert.assertEquals(invoice.getTotalTax(),88800);
        Assert.assertEquals(680800,invoice.getTotalAll());

        Assert.assertEquals(invoice.getNewNumber(),newNumber);
        Assert.assertEquals(invoice.getOldNumber(),0);
        ResponseEntity<?> responseEntity1 =invoiceController.getInvoiceById(invoice.getId());
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);
        Assert.assertEquals(((Invoice) responseEntity.getBody()).getId(),invoice.getId());
    }


    @Test
    public void test_add_invoice_residential_6_7() throws ParseException {
        long newIndex = 7;
        AddInvoiceRequest addInvoiceRequest = new AddInvoiceRequest(38,newIndex,"04/2024");
        ResponseEntity<?> responseEntity =invoiceController.addInvoice(addInvoiceRequest);
        Invoice invoice = (Invoice) responseEntity.getBody();
        assert invoice != null;
        Assert.assertEquals(203000,invoice.getTotalNoTax());
        Assert.assertEquals(233450,invoice.getTotalAll());
        Assert.assertEquals(invoice.getTotalTax(),30450);
        Assert.assertEquals(invoice.getNewNumber(),newIndex);
        Assert.assertEquals(invoice.getOldNumber(),0);
        ResponseEntity<?> responseEntity1 =invoiceController.getInvoiceById(invoice.getId());
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);
        Assert.assertEquals(((Invoice) responseEntity.getBody()).getId(),invoice.getId());
    }
    @Test
    public void test_add_invoice_residential_6_10() throws ParseException {
        long newIndex = 10;
        AddInvoiceRequest addInvoiceRequest = new AddInvoiceRequest(38,newIndex,"04/2024");
        ResponseEntity<?> responseEntity =invoiceController.addInvoice(addInvoiceRequest);
        Invoice invoice = (Invoice) responseEntity.getBody();
        assert invoice != null;
        Assert.assertEquals(290000,invoice.getTotalNoTax());
        Assert.assertEquals(333500,invoice.getTotalAll());
        Assert.assertEquals(invoice.getTotalTax(),43500);
        Assert.assertEquals(invoice.getNewNumber(),newIndex);
        Assert.assertEquals(invoice.getOldNumber(),0);
        ResponseEntity<?> responseEntity1 =invoiceController.getInvoiceById(invoice.getId());
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);
        Assert.assertEquals(((Invoice) responseEntity.getBody()).getId(),invoice.getId());
    }
    @Test
    public void test_add_invoice_residential_6_17() throws ParseException {
        long newIndex = 17;
        AddInvoiceRequest addInvoiceRequest = new AddInvoiceRequest(38,newIndex,"04/2024");
        ResponseEntity<?> responseEntity =invoiceController.addInvoice(addInvoiceRequest);
        Invoice invoice = (Invoice) responseEntity.getBody();
        assert invoice != null;
        Assert.assertEquals(493000,invoice.getTotalNoTax());
        Assert.assertEquals(invoice.getTotalTax(),73950);
        Assert.assertEquals(566950,invoice.getTotalAll());

        Assert.assertEquals(invoice.getNewNumber(),newIndex);
        Assert.assertEquals(invoice.getOldNumber(),0);
        ResponseEntity<?> responseEntity1 =invoiceController.getInvoiceById(invoice.getId());
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);
        Assert.assertEquals(((Invoice) responseEntity.getBody()).getId(),invoice.getId());
    }

    @Test
    public void test_add_invoice_residential_6_20() throws ParseException {
        long newNumber = 20;
        AddInvoiceRequest addInvoiceRequest = new AddInvoiceRequest(38,newNumber,"04/2024");
        ResponseEntity<?> responseEntity =invoiceController.addInvoice(addInvoiceRequest);
        Invoice invoice = (Invoice) responseEntity.getBody();
        assert invoice != null;
        Assert.assertEquals(580000,invoice.getTotalNoTax());
        Assert.assertEquals(invoice.getTotalTax(),87000);
        Assert.assertEquals(667000,invoice.getTotalAll());

        Assert.assertEquals(invoice.getNewNumber(),newNumber);
        Assert.assertEquals(invoice.getOldNumber(),0);
        ResponseEntity<?> responseEntity1 =invoiceController.getInvoiceById(invoice.getId());
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);
        Assert.assertEquals(((Invoice) responseEntity.getBody()).getId(),invoice.getId());
    }

    @Test
    public void test_add_invoice_residential_6_27() throws ParseException {
        long newNumber = 27;
        AddInvoiceRequest addInvoiceRequest = new AddInvoiceRequest(38,newNumber,"04/2024");
        ResponseEntity<?> responseEntity =invoiceController.addInvoice(addInvoiceRequest);
        Invoice invoice = (Invoice) responseEntity.getBody();
        assert invoice != null;
        Assert.assertEquals(783000,invoice.getTotalNoTax());
        Assert.assertEquals(invoice.getTotalTax(),117450);
        Assert.assertEquals(900450,invoice.getTotalAll());

        Assert.assertEquals(invoice.getNewNumber(),newNumber);
        Assert.assertEquals(invoice.getOldNumber(),0);
        ResponseEntity<?> responseEntity1 =invoiceController.getInvoiceById(invoice.getId());
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);
        Assert.assertEquals(((Invoice) responseEntity.getBody()).getId(),invoice.getId());
    }

    @Test
    public void test_add_invoice_residential_6_30() throws ParseException {
        long newNumber = 30;
        AddInvoiceRequest addInvoiceRequest = new AddInvoiceRequest(38,newNumber,"04/2024");
        ResponseEntity<?> responseEntity =invoiceController.addInvoice(addInvoiceRequest);
        Invoice invoice = (Invoice) responseEntity.getBody();
        assert invoice != null;
        Assert.assertEquals(870000,invoice.getTotalNoTax());
        Assert.assertEquals(invoice.getTotalTax(),130500);
        Assert.assertEquals(1000500,invoice.getTotalAll());

        Assert.assertEquals(invoice.getNewNumber(),newNumber);
        Assert.assertEquals(invoice.getOldNumber(),0);
        ResponseEntity<?> responseEntity1 =invoiceController.getInvoiceById(invoice.getId());
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);
        Assert.assertEquals(((Invoice) responseEntity.getBody()).getId(),invoice.getId());
    }

    @Test
    public void test_add_invoice_residential_6_37() throws ParseException {
        long newNumber = 37;
        AddInvoiceRequest addInvoiceRequest = new AddInvoiceRequest(38,newNumber,"04/2024");
        ResponseEntity<?> responseEntity =invoiceController.addInvoice(addInvoiceRequest);
        Invoice invoice = (Invoice) responseEntity.getBody();
        assert invoice != null;
        Assert.assertEquals(1073000,invoice.getTotalNoTax());
        Assert.assertEquals(invoice.getTotalTax(),160950);
        Assert.assertEquals(1233950,invoice.getTotalAll());

        Assert.assertEquals(invoice.getNewNumber(),newNumber);
        Assert.assertEquals(invoice.getOldNumber(),0);
        ResponseEntity<?> responseEntity1 =invoiceController.getInvoiceById(invoice.getId());
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);
        Assert.assertEquals(((Invoice) responseEntity.getBody()).getId(),invoice.getId());
    }

    @Test
    public void test_add_invoice_cus_id_ko_hople() throws ParseException {
        long newNumber = 37;
        AddInvoiceRequest addInvoiceRequest = new AddInvoiceRequest(3888,newNumber,"04/2024");
        ResponseEntity<?> responseEntity =invoiceController.addInvoice(addInvoiceRequest);
        Assert.assertEquals(responseEntity.getBody(),"khong tim thay customer");

    }
    @Test
    public void test_add_invoice_so_nuoc_moi_ko_hople() throws ParseException {
        long newNumber = 37;
        AddInvoiceRequest addInvoiceRequest = new AddInvoiceRequest(1,newNumber,"04/2024");
        ResponseEntity<?> responseEntity =invoiceController.addInvoice(addInvoiceRequest);
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.BAD_REQUEST);
        Assert.assertEquals(responseEntity.getBody(),"Số nước mới nhỏ hơn số nước cũ");
    }
}
