package com.selenium.test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDemo extends TestDriver {


    @Test
    public void login(){

        String csvFile = "D:\\dulieukhach\\Workspace\\Tren truong\\DBCLPM\\test_dang_nhap.csv";
        String csvOutputFile = "D:\\dulieukhach\\Workspace\\Tren truong\\DBCLPM\\test_dang_nhap_output.csv";

        try (CSVReader reader = new CSVReader(new BufferedReader(new FileReader(csvFile)));
             CSVWriter writer = new CSVWriter(new FileWriter(csvOutputFile))) {

            String[] nextRecord;
            boolean isFirstRow = true;
            while ((nextRecord = reader.readNext()) != null) {
                if (isFirstRow) { // Skip processing the first row with titles
                    writer.writeNext(nextRecord);
                    isFirstRow = false;
                    continue;
                }
                ChromeDriver driver = getDriver();
                try {
                    driver.get("http://localhost:3000");
                    WebElement username = driver.findElement(By.name("username"));
                    username.sendKeys(nextRecord[0]);

                    WebElement password = driver.findElement(By.name("password"));
                    password.sendKeys(nextRecord[1]);

                    driver.findElement(By.className("bt-dk")).click();

                    // Chờ cho trang được tải hoàn toàn và URL thay đổi
                    Duration duration = Duration.ofSeconds(1);
                    WebDriverWait wait = new WebDriverWait(driver, duration);
                    wait.until(ExpectedConditions.urlContains("http://localhost:3000/trang_chu"));

                    String title = driver.getTitle();

                    // Kiểm tra URL để xác nhận đăng nhập thành công
                    if (title.equals(nextRecord[2])) {
                        nextRecord[3] = "";
                        System.out.println("Login successful");
                    } else {
                        nextRecord[3] = "failed";
                        System.out.println("Login failed");
                    }
                }catch (Exception e)
                {
                    String title = driver.getTitle();
                    if(title.equals("Login Page"))
                    {
                        nextRecord[3] = "";
                    }else{
                        nextRecord[3] = "failed";
                    }

                }finally {
                    writer.writeNext(nextRecord);
                    driver.quit();
                }


            }
        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    public void dang_ky_su_dung_nuoc_check() throws SQLException {
        Connection connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclpm","root","huyhuyhuy1312");

        ChromeDriver driver = getDriver();
        driver.get("http://localhost:3000/dangKySuDungNuoc");

        // Đợi load xong data của các thành phố
        Duration durationCity = Duration.ofSeconds(10);
        WebDriverWait waitCity = new WebDriverWait(driver, durationCity);
        WebElement selectCityEle = driver.findElement(By.id("city"));
        Select selectCity = new Select(selectCityEle);
        waitCity.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("#city option"), 1));
        selectCity.selectByValue("Thành phố Hà Nội");

        // Đợi load xong data các quận huyện
        Duration durationDistrict = Duration.ofSeconds(10);
        WebDriverWait waitDistrict = new WebDriverWait(driver, durationDistrict);
        waitDistrict.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("#district option"), 1));
        WebElement selectDistrictEle = driver.findElement(By.id("district"));
        Select selectDistrict = new Select(selectDistrictEle);
        selectDistrict.selectByValue("Quận Ba Đình");

        // Nếu chọn giá trị ko trong select mà vấn được thì fails
        Duration durationWard = Duration.ofSeconds(10);
        WebDriverWait waitWard= new WebDriverWait(driver, durationWard);
        waitWard.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("#ward option"), 1));
        WebElement selectWardEle = driver.findElement(By.id("ward"));
        Select selectWard = new Select(selectWardEle);
        selectWard.selectByValue("Phường Vĩnh Phúc");

        WebElement addressInput = driver.findElement(By.className("addressInput"));
        addressInput.sendKeys("Test1");

        WebElement fullnameInput = driver.findElement(By.className("fullnameInput"));
        fullnameInput.sendKeys("Nguyễn Hữu Huy");

        WebElement emailInput= driver.findElement(By.className("emailInput"));
        emailInput.sendKeys("flslayder1312@gmail.com");

        WebElement phonenumInput= driver.findElement(By.className("phonenumInput"));
        phonenumInput.clear();
        phonenumInput.sendKeys("0123456789");

        WebElement cccdInput= driver.findElement(By.className("cccdInput"));
        cccdInput.sendKeys("012345678910");

        //UPload ảnh cccd mặt trước
        WebElement cccdFrontImageEle = driver.findElement(By.id("frontImage"));
        cccdFrontImageEle.sendKeys("D:\\dulieukhach\\Workspace\\Tren truong\\DBCLPM\\anh_cccd_mat_truoc.jpg");
        //UPload ảnh cccd mặt sau
        WebElement cccdBackImageEle = driver.findElement(By.id("backImage"));
        cccdBackImageEle.sendKeys("D:\\dulieukhach\\Workspace\\Tren truong\\DBCLPM\\anh_cccd_mat_sau.jpg");

        WebElement selectTypeResidentEle = driver.findElement(By.name("resident"));
        Select selectTypeResident = new Select(selectTypeResidentEle);
        List<WebElement> webElementList = selectTypeResident.getOptions();
        for (WebElement tmp: webElementList)
        {
            System.out.println(tmp.getText());
        }
        selectTypeResident.selectByIndex(1);

        Duration durationCertificate = Duration.ofSeconds(10);
        WebDriverWait waitCertificate = new WebDriverWait(driver, durationCertificate);
        waitCertificate.until(ExpectedConditions.numberOfElementsToBe(By.name("certificate"), 1));
        WebElement certificateImageEle = driver.findElement(By.name("certificate"));
        certificateImageEle.sendKeys("D:\\dulieukhach\\Workspace\\Tren truong\\DBCLPM\\giay_chung_nhan_ho_ngheo.jpg");

        driver.findElement(By.className("bt-dk")).click();

        Duration durationSuccess = Duration.ofSeconds(10);
        WebDriverWait waitSuccess = new WebDriverWait(driver, durationSuccess);
        waitSuccess.until(ExpectedConditions.numberOfElementsToBe(By.className("successContent"), 1));

            if(connection.isClosed())
            {
                System.out.println("We have not connected to the db");
            }else {
                System.out.println("We have successfully connected to the db");
                Statement statement = connection.createStatement();

                // Thực thi truy vấn và nhận kết quả
                ResultSet resultSet = statement.executeQuery("""
                                SELECT COUNT(*) AS total_customers\s
                                FROM dbclpm.customer AS c
                                INNER JOIN dbclpm.info_common AS ic ON c.info_common_id = ic.id
                                INNER JOIN dbclpm.address AS ads ON c.address_id = ads.id\
                                Where ic.name ="Nguyễn Hữu Huy"\s
                                \tand ic.email="flslayder1312@gmail.com"
                                    and ic.phone_number ="0123456789"
                                    and c.residential_type_id = 2
                                    and ads.details_address="Test1"
                                    and ads.province_or_city="Thành phố Hà Nội"
                                    and ads.district="Quận Ba Đình"
                                    and ads.wards ="Phường Vĩnh Phúc"
                                    and c.actived =0
                                    and c.certificate_of_poverty is not null;"""
                        );

                if (resultSet.next()) {
                    // Lấy giá trị từ cột 'total_customers'
                    int totalCustomers = resultSet.getInt("total_customers");
                    System.out.println("Total customers: " + totalCustomers);
                } else {
                    System.out.println("No result found.");
                }

                // Đóng tất cả các resource
                resultSet.close();
                statement.close();
            }




    }

    @Test
    public void thanh_toan_check_thanh_cong() throws SQLException
    {

        //Đăng nhập
        ChromeDriver driver = getDriver();
        driver.get("http://localhost:3000");
        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("nhh12300000001");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("Bzzhk7bO");
        driver.findElement(By.className("bt-dk")).click();

        // Chờ cho trang chủ được tải hoàn toàn và URL thay đổi
        Duration duration = Duration.ofSeconds(1);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(ExpectedConditions.urlContains("http://localhost:3000/trang_chu"));
        String title = driver.getTitle();
        driver.findElement(By.className("search_invoice")).click();

        //Đợi trang thống kế invoice được tải
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(1));
        WebElement invoiceTable = wait2.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".table-invoice table ")));
        List<WebElement> rows = invoiceTable.findElements(By.tagName("tr"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (!cells.isEmpty() && "Chưa thanh toán".equals(cells.get(7).getText())) {
                cells.get(8).findElement(By.tagName("a")).click();
                break;
            }
        }

        //Đợi trang viewDetails invoice được tải
        WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(1));
        WebElement button = wait3.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".btn-tt")));
        String currentUrl = driver.getCurrentUrl();
        String [] arrString  = currentUrl.split("/");
        long idInvoiceCheck = Long.parseLong(arrString[arrString.length-1]);
        button.click();

        // Điền thông tin thẻ
        wait.until(ExpectedConditions.urlContains("https://sandbox.vnpayment.vn/"));
        WebElement so_the = driver.findElement(By.name("card_number_mask"));
        so_the.sendKeys("9704198526191432198");
        WebElement ten_chu_the = driver.findElement(By.name("cardHolder"));
        ten_chu_the.sendKeys("NGUYEN VAN A");
        WebElement cartDate = driver.findElement(By.name("cardDate"));
        cartDate.sendKeys("07/15");
        WebElement button2 = wait3.until(ExpectedConditions.presenceOfElementLocated(By.id("btnContinue")));
        button2.click();
        WebElement button3 = wait3.until(ExpectedConditions.presenceOfElementLocated(By.id("btnAgree")));
        button3.click();
        // Nhập otp
        WebElement button4 = wait3.until(ExpectedConditions.presenceOfElementLocated(By.name("btnConfirm")));
        WebElement otpvalue = driver.findElement(By.name("otpvalue"));
        otpvalue.sendKeys("123456");
        button4.click();

        //Check trang Success
        Duration durationSuccess = Duration.ofSeconds(10);
        WebDriverWait waitSuccess = new WebDriverWait(driver, durationSuccess);
        waitSuccess.until(ExpectedConditions.urlContains("http://localhost:3000/thanhCong"));

        Connection connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/dbclpm","root","huyhuyhuy1312");
        if(connection.isClosed())
        {
            System.out.println("We have not connected to the db");
            return;

        }else {
            System.out.println("We have successfully connected to the db");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM dbclpm.invoice WHERE id="+idInvoiceCheck);
            String status;
            if(resultSet.next())
            {
                status = resultSet.getString("status");
                assertEquals("Đã Thanh Toán", status);
            }else {
                return;
            }
            System.out.println(status);
        }

        //Check Database
        driver.quit();
    }

    @Test
    public void thanh_toan_check_the_khong_du_so_du() throws SQLException
    {

        //Đăng nhập
        ChromeDriver driver = getDriver();
        driver.get("http://localhost:3000");
        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("nhh12300000001");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("Bzzhk7bO");
        driver.findElement(By.className("bt-dk")).click();

        // Chờ cho trang chủ được tải hoàn toàn và URL thay đổi
        Duration duration = Duration.ofSeconds(1);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(ExpectedConditions.urlContains("http://localhost:3000/trang_chu"));
        String title = driver.getTitle();
        driver.findElement(By.className("search_invoice")).click();

        //Đợi trang thống kế invoice được tải
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(1));
        WebElement invoiceTable = wait2.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".table-invoice table ")));
        List<WebElement> rows = invoiceTable.findElements(By.tagName("tr"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (!cells.isEmpty() && "Chưa thanh toán".equals(cells.get(7).getText())) {
                cells.get(8).findElement(By.tagName("a")).click();
                break;
            }
        }

        //Đợi trang viewDetails invoice được tải
        WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(1));
        WebElement button = wait3.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".btn-tt")));
        String currentUrl = driver.getCurrentUrl();
        String [] arrString  = currentUrl.split("/");
        long idInvoiceCheck = Long.parseLong(arrString[arrString.length-1]);
        button.click();

        // Điền thông tin thẻ
        wait.until(ExpectedConditions.urlContains("https://sandbox.vnpayment.vn/"));
        WebElement so_the = driver.findElement(By.name("card_number_mask"));
        so_the.sendKeys("9704195798459170488");
        WebElement ten_chu_the = driver.findElement(By.name("cardHolder"));
        ten_chu_the.sendKeys("NGUYEN VAN A");
        WebElement cartDate = driver.findElement(By.name("cardDate"));
        cartDate.sendKeys("07/15");
        WebElement button2 = wait3.until(ExpectedConditions.presenceOfElementLocated(By.id("btnContinue")));
        button2.click();
        WebElement button3 = wait3.until(ExpectedConditions.presenceOfElementLocated(By.id("btnAgree")));
        button3.click();

        WebDriverWait waitNotice = new WebDriverWait(driver, Duration.ofSeconds(1));
        Boolean eleNotice = waitNotice.until(ExpectedConditions.textToBePresentInElementLocated(By.id("lb_message_error"), "Tài khoản của khách hàng không đủ số dư để thực hiện giao dịch"));
//        System.out.println(eleNotice);
//        System.out.println(driver.findElement(By.id("lb_message_error")).getText());
        driver.quit();
    }


    @Test
    public void thanh_toan_check_the_chua_duoc_kich_hoat() throws SQLException
    {

        //Đăng nhập
        ChromeDriver driver = getDriver();
        driver.get("http://localhost:3000");
        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("nhh12300000001");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("Bzzhk7bO");
        driver.findElement(By.className("bt-dk")).click();

        // Chờ cho trang chủ được tải hoàn toàn và URL thay đổi
        Duration duration = Duration.ofSeconds(1);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(ExpectedConditions.urlContains("http://localhost:3000/trang_chu"));
        String title = driver.getTitle();
        driver.findElement(By.className("search_invoice")).click();

        //Đợi trang thống kế invoice được tải
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(1));
        WebElement invoiceTable = wait2.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".table-invoice table ")));
        List<WebElement> rows = invoiceTable.findElements(By.tagName("tr"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (!cells.isEmpty() && "Chưa thanh toán".equals(cells.get(7).getText())) {
                cells.get(8).findElement(By.tagName("a")).click();
                break;
            }
        }

        //Đợi trang viewDetails invoice được tải
        WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(1));
        WebElement button = wait3.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".btn-tt")));
        String currentUrl = driver.getCurrentUrl();
        String [] arrString  = currentUrl.split("/");
        long idInvoiceCheck = Long.parseLong(arrString[arrString.length-1]);
        button.click();

        // Điền thông tin thẻ
        wait.until(ExpectedConditions.urlContains("https://sandbox.vnpayment.vn/"));
        WebElement so_the = driver.findElement(By.name("card_number_mask"));
        so_the.sendKeys("9704192181368742");
        WebElement ten_chu_the = driver.findElement(By.name("cardHolder"));
        ten_chu_the.sendKeys("NGUYEN VAN A");
        WebElement cartDate = driver.findElement(By.name("cardDate"));
        cartDate.sendKeys("07/15");
        WebElement button2 = wait3.until(ExpectedConditions.presenceOfElementLocated(By.id("btnContinue")));
        button2.click();
        WebElement button3 = wait3.until(ExpectedConditions.presenceOfElementLocated(By.id("btnAgree")));
        button3.click();

        WebDriverWait waitNotice = new WebDriverWait(driver, Duration.ofSeconds(1));
        Boolean eleNotice = waitNotice.until(ExpectedConditions.textToBePresentInElementLocated(By.id("lb_message_error"), "Thẻ chưa được kích hoạt"));
//        System.out.println(eleNotice);
//        System.out.println(driver.findElement(By.id("lb_message_error")).getText());
        driver.quit();
    }

    @Test
    public void thanh_toan_check_the_bi_khoa() throws SQLException
    {

        //Đăng nhập
        ChromeDriver driver = getDriver();
        driver.get("http://localhost:3000");
        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("nhh12300000001");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("Bzzhk7bO");
        driver.findElement(By.className("bt-dk")).click();

        // Chờ cho trang chủ được tải hoàn toàn và URL thay đổi
        Duration duration = Duration.ofSeconds(1);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(ExpectedConditions.urlContains("http://localhost:3000/trang_chu"));
        String title = driver.getTitle();
        driver.findElement(By.className("search_invoice")).click();

        //Đợi trang thống kế invoice được tải
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(1));
        WebElement invoiceTable = wait2.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".table-invoice table ")));
        List<WebElement> rows = invoiceTable.findElements(By.tagName("tr"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (!cells.isEmpty() && "Chưa thanh toán".equals(cells.get(7).getText())) {
                cells.get(8).findElement(By.tagName("a")).click();
                break;
            }
        }

        //Đợi trang viewDetails invoice được tải
        WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(1));
        WebElement button = wait3.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".btn-tt")));
        String currentUrl = driver.getCurrentUrl();
        String [] arrString  = currentUrl.split("/");
        long idInvoiceCheck = Long.parseLong(arrString[arrString.length-1]);
        button.click();

        // Điền thông tin thẻ
        wait.until(ExpectedConditions.urlContains("https://sandbox.vnpayment.vn/"));
        WebElement so_the = driver.findElement(By.name("card_number_mask"));
        so_the.sendKeys("9704193370791314");
        WebElement ten_chu_the = driver.findElement(By.name("cardHolder"));
        ten_chu_the.sendKeys("NGUYEN VAN A");
        WebElement cartDate = driver.findElement(By.name("cardDate"));
        cartDate.sendKeys("07/15");
        WebElement button2 = wait3.until(ExpectedConditions.presenceOfElementLocated(By.id("btnContinue")));
        button2.click();
        WebElement button3 = wait3.until(ExpectedConditions.presenceOfElementLocated(By.id("btnAgree")));
        button3.click();

        WebDriverWait waitNotice = new WebDriverWait(driver, Duration.ofSeconds(1));
        Boolean eleNotice = waitNotice.until(ExpectedConditions.textToBePresentInElementLocated(By.id("lb_message_error"), "Thẻ bị khóa"));
//        System.out.println(eleNotice);
        System.out.println(driver.findElement(By.id("lb_message_error")).getText());
        driver.quit();
    }

    @Test
    public void thanh_toan_check_the_bi_het_han() throws SQLException
    {

        //Đăng nhập
        ChromeDriver driver = getDriver();
        driver.get("http://localhost:3000");
        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("nhh12300000001");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("Bzzhk7bO");
        driver.findElement(By.className("bt-dk")).click();

        // Chờ cho trang chủ được tải hoàn toàn và URL thay đổi
        Duration duration = Duration.ofSeconds(1);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(ExpectedConditions.urlContains("http://localhost:3000/trang_chu"));
        String title = driver.getTitle();
        driver.findElement(By.className("search_invoice")).click();

        //Đợi trang thống kế invoice được tải
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(1));
        WebElement invoiceTable = wait2.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".table-invoice table ")));
        List<WebElement> rows = invoiceTable.findElements(By.tagName("tr"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (!cells.isEmpty() && "Chưa thanh toán".equals(cells.get(7).getText())) {
                cells.get(8).findElement(By.tagName("a")).click();
                break;
            }
        }

        //Đợi trang viewDetails invoice được tải
        WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(1));
        WebElement button = wait3.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".btn-tt")));
        String currentUrl = driver.getCurrentUrl();
        String [] arrString  = currentUrl.split("/");
        long idInvoiceCheck = Long.parseLong(arrString[arrString.length-1]);
        button.click();

        // Điền thông tin thẻ
        wait.until(ExpectedConditions.urlContains("https://sandbox.vnpayment.vn/"));
        WebElement so_the = driver.findElement(By.name("card_number_mask"));
        so_the.sendKeys("9704194841945513");
        WebElement ten_chu_the = driver.findElement(By.name("cardHolder"));
        ten_chu_the.sendKeys("NGUYEN VAN A");
        WebElement cartDate = driver.findElement(By.name("cardDate"));
        cartDate.sendKeys("07/15");
        WebElement button2 = wait3.until(ExpectedConditions.presenceOfElementLocated(By.id("btnContinue")));
        button2.click();
        WebElement button3 = wait3.until(ExpectedConditions.presenceOfElementLocated(By.id("btnAgree")));
        button3.click();

        WebDriverWait waitNotice = new WebDriverWait(driver, Duration.ofSeconds(1));
        Boolean eleNotice = waitNotice.until(ExpectedConditions.textToBePresentInElementLocated(By.id("lb_message_error"), "Thẻ bị hết hạn"));
//        System.out.println(eleNotice);
        System.out.println(driver.findElement(By.id("lb_message_error")).getText());
        driver.quit();
    }
}