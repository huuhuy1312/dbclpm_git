package com.example.dbclpm_be;

import com.example.dbclpm_be.controller.InvoiceController;
import com.example.dbclpm_be.entity.Customer;
import com.example.dbclpm_be.entity.Invoice;
import com.example.dbclpm_be.respository.CustomerRepository;
import com.example.dbclpm_be.respository.InvoiceRepository;
import jakarta.transaction.Transactional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import payload.response.InvoiceDetailsResponse;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ThanhToanTest {

    @Autowired
    private InvoiceController invoiceController;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Test
    public void test_get_invoice_by_cus_id()
    {
        long cus_id =1;
        ResponseEntity<?> responseEntity= invoiceController.getAllInvoicesByCusId(1);
        List<Invoice> invoiceList = (List<Invoice>) responseEntity.getBody();
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(invoiceList.size(),12);
    }

    @Test
    public void test_get_invoice_by_cus_id_ko_hop_le()
    {
        long cus_id =1111;
        ResponseEntity<?> responseEntity= invoiceController.getAllInvoicesByCusId(cus_id);
        List<Invoice> invoiceList = (List<Invoice>) responseEntity.getBody();
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(invoiceList.size(),0);
    }

    @Test
    public void test_get_invoice_by_id() throws ParseException {
        long id = 1;
        Optional<Customer> customer = customerRepository.findById(1L);
        Invoice invoiceExpected = new Invoice(1,6,0,"03/2024",58650,7650,51000,"Đã Thanh Toán");
        ResponseEntity<?> responseEntity = invoiceController.getInvoiceById(id);
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);
        InvoiceDetailsResponse invoiceDetailsResponse = (InvoiceDetailsResponse) responseEntity.getBody();
        Invoice invoiceOut = invoiceDetailsResponse.getInvoice();
        assert invoiceOut != null;
        Assert.assertEquals(invoiceExpected.getId(),invoiceOut.getId());
        Assert.assertEquals(invoiceExpected.getOldNumber(),invoiceOut.getOldNumber());
        Assert.assertEquals(invoiceExpected.getNewNumber(),invoiceOut.getNewNumber());
        Assert.assertEquals(invoiceExpected.getTotalAll(),invoiceOut.getTotalAll());
        Assert.assertEquals(invoiceExpected.getTotalTax(),invoiceOut.getTotalTax());
        Assert.assertEquals(invoiceExpected.getTotalNoTax(),invoiceOut.getTotalNoTax());
        Assert.assertEquals(invoiceExpected.getStatus(),invoiceOut.getStatus());

    }
    @Test
    public void test_get_invoice_by_id_ko_hop_le()
    {
        long id = 111111;
        ResponseEntity<?> responseEntity = invoiceController.getInvoiceById(id);
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.NOT_FOUND);
        Assert.assertEquals(responseEntity.getBody(),"Không tìm thấy invoice có id="+id);
    }

    @Test
    public void update_status_sai_invoice_id()
    {
        long id = 1111;
        ResponseEntity<?> responseEntity =invoiceController.updateStatusInvoice(id);
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.NOT_FOUND);
        Assert.assertEquals(responseEntity.getBody(),"Không tìm thấy invoice có id=1111");
    }

    @Test
    public void update_status_invoice_da_thanh_toan()
    {
        long id = 2;
        ResponseEntity<?> responseEntity =invoiceController.updateStatusInvoice(id);
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.BAD_REQUEST);
        Assert.assertEquals(responseEntity.getBody(),"Invoice đã được thanh toán");
    }

    @Test
    public void update_status_invoice_thanhcong()
    {
        long id = 8;
        ResponseEntity<?> responseEntity =invoiceController.updateStatusInvoice(id);
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);
        Assert.assertEquals(responseEntity.getBody(),"Thanh Toán thành công");
        Invoice invoice = invoiceRepository.findById(id).get();
        Assert.assertEquals(invoice.getStatus(),"Đã Thanh Toán");
    }


}
