package backend.sellerB.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GoogleLoginDto {
    private String googleIdToken;
}
