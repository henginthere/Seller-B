package backend.sellerB.service;

import backend.sellerB.controller.AuthController;
import backend.sellerB.dto.ManagerDto;
import backend.sellerB.dto.TokenDto;
import backend.sellerB.exception.UnauthorizedException;
import backend.sellerB.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
    // 로그인 관련 메서드
    public TokenDto authorize(String id, String password) {

        logger.info(id+"############");
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(id, password);
        logger.info(authenticationToken.getName()+"여기까지############");
        //실제 검증이 일어나는 부분
        //authenticate 메서드가 실행될 때
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        logger.info(authentication.getName()+"왜 여기는,,,############");
        String authorities = getAuthorities(authentication);

        TokenDto tokenDto = tokenProvider.createToken(authentication.getName(), authorities);

        logger.info("토큰 발급 :"+tokenDto.getAccessToken());
        return tokenProvider.createToken(authentication.getName(), authorities);
    }

    // 토큰 재발급 관련 메서드
    public TokenDto reissue(String requestAccessToken, String requestRefreshToken) {
        if (!tokenProvider.validateToken(requestRefreshToken)) {
//            throw new UnauthorizedException("유효하지 않은 RefreshToken 입니다");

        }
        Authentication authentication = tokenProvider.getAuthentication(requestAccessToken);

        ManagerDto principal = (ManagerDto) authentication.getPrincipal();

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String authorities = getAuthorities(authentication);

        return tokenProvider.createToken(principal.getManagerId(), authorities);
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
