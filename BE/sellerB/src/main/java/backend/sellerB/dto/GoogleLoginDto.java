package backend.sellerB.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoogleLoginDto {
    private String googleId;
    private String googlePw;
    private String googleName;
}
