package payload.request;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginRequest {
    public String username;
    public String password;
}
