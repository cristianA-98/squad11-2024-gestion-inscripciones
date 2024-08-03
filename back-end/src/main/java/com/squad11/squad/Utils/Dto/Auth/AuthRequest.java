package com.squad11.squad.Utils.Dto.Auth;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor()
@NoArgsConstructor()
@Data()
@Builder()
public class AuthRequest {
    long id;
    @NotEmpty(message = "Email is required")
    @Pattern(regexp = ".*(^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$)", message = "Email not valid")
    String email;

    @NotEmpty(message = "Email is required")
    @Length(min = 7, max = 20, message = "Password min 8 and max 20")
    String password;
}
