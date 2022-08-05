package backend.sellerB.dto;

import lombok.*;


@Data
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginResponseDto {

    private TokenDto tokenDto;
    private String authority;
    private Long seq;
    private Long brandSeq;

    @Builder
    public LoginResponseDto(TokenDto tokenDto, String authority, Long seq, Long brandSeq) {
        this.tokenDto = tokenDto;
        this.authority = authority;
        this.seq = seq;
        this.brandSeq = brandSeq;
    }
}
