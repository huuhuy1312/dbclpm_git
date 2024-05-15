package payload.request;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RejectRequest {
    public long customer_id;
    public String reason;
}
