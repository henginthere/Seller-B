package backend.sellerB.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class SecurityUtil {

    private static final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);
    private SecurityUtil() {
    }

    // getCurrentUsername의 역할: Security Context의 Authentication 객체를 이용해 username을 return
    public static Optional<String> getCurrentUserid() {
        // SecurityContext의 Authentication객체가 저장되는 시점은 JwtFilter의 doFilter 메소드에서
        // Request가 들어올때 SecurityContext에 Authentication 객체를 저장해서 사용하게 됨
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            logger.debug("Security Context에 인증 정보가 없습니다.");
            return Optional.empty();
        }

        String userid = null;
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            userid = springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof String) {
            userid = (String) authentication.getPrincipal();
        }

        return Optional.ofNullable(userid);
    }


}
