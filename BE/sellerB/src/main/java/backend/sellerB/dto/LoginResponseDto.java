package backend.sellerB.dto;

import lombok.*;


@Data
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginResponseDto {

    private TokenDto tokenDto;
    private String authority;

    @Builder
    public LoginResponseDto(TokenDto tokenDto, String authority) {
        this.tokenDto = tokenDto;
        this.authority = authority;
    }
}
