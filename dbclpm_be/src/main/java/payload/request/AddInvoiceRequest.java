package payload.request;

import lombok.AllArgsConstructor;

import java.util.Date;
@AllArgsConstructor
public class AddInvoiceRequest {
    public long customer_id;
    public  long new_number;
    public String period;


}
