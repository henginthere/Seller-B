package backend.sellerB.service;

import backend.sellerB.dto.TokenDto;
import backend.sellerB.dto.LoginResponseDto;
import backend.sellerB.entity.Consultant;
import backend.sellerB.entity.Manager;
import backend.sellerB.exception.ManagerNotFoundException;
import backend.sellerB.jwt.TokenProvider;
import backend.sellerB.repository.ConsultantRepository;
import backend.sellerB.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    ConsultantRepository consultantRepository;

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
    // 로그인 관련 메서드
    public LoginResponseDto  authorize(String id, String password) {


        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(id, password);
        //실제 검증이 일어나는 부분
        //authenticate 메서드가 실행될 때

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String authorities = getAuthorities(authentication);

        logger.info("권한 : "+authorities);

        TokenDto tokenDto;
        Long seq;
        Long brandSeq;
        if(authorities.equals("ROLE_ADMIN")){
            Manager manager = managerRepository.findBymanagerId(id).orElseThrow(()->new ManagerNotFoundException("가입되지 않은 정보입니다."));
            seq = manager.getManagerSeq();
            brandSeq = manager.getBrandSeq().getBrandSeq();
            tokenDto = tokenProvider.createManagerToken(authentication.getName(), authorities);
        }
        else{
            Consultant consultant = consultantRepository.findByConsultantId(id).orElseThrow(()->new ManagerNotFoundException("가입되지 않은 정보입니다."));
            seq = consultant.getConsultantSeq();
            brandSeq = -1L;
            tokenDto = tokenProvider.createConsultantToken(authentication.getName(),authorities);
        }

        return new LoginResponseDto(tokenDto,authorities,seq,brandSeq);

    }

    // 토큰 재발급 관련 메서드
    public TokenDto reissue(String requestAccessToken, String requestRefreshToken) {
        if (!tokenProvider.validateToken(requestRefreshToken)) {
//            throw new UnauthorizedException("유효하지 않은 RefreshToken 입니다");
            //재로그인 요청반환

        }

        Authentication authentication = tokenProvider.getAuthentication(requestRefreshToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String authorities = getAuthorities(authentication);
        String memberId;

        if(authorities.equals("ROLE_ADMIN")){
            memberId = managerRepository.findByManagerSeq(Long.parseLong(authentication.getName())).get().getManagerId();
            return tokenProvider.createManagerToken(memberId, authorities);
        }
        else{
            memberId = consultantRepository.findByConsultantSeq(Long.parseLong(authentication.getName())).getConsultantId();
            return tokenProvider.createConsultantToken(memberId, authorities);
        }


    }

    // 권한 가져오기
    public String getAuthorities(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }
//
//    public void logout(String accessToken, String refreshToken) {
//        redisUtil.setBlackList(accessToken, "accessToken", 1800);
//        redisUtil.setBlackList(refreshToken, "refreshToken", 60400);
//    }
}
