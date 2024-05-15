package payload.request;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateUserRequest {
    public String full_name;
    public String base_id;
    public Long customer_id;
    public Long num_of_customer;
    public String email;
}
