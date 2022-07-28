package backend.sellerB.service;

import backend.sellerB.entity.Manager;
import backend.sellerB.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String managerId)  {
        // 1. login 시에 db에서 유저정보와, 권한정보를 같이 가져옴
        return userRepository.findOneWithAuthoritiesBymanagerId(managerId)
                .map(user -> createUser(managerId, user))
                .orElseThrow(() -> new UsernameNotFoundException(managerId + " -> 데이터베이스에서 찾을 수 없습니다."));
    }

    private org.springframework.security.core.userdetails.User createUser(String managerId, Manager manager) {
        List<GrantedAuthority> grantedAuthorities = manager.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toList());
        // 1의 정보를 기반으로 userdetails.User객체를 생성해서 return
        return new org.springframework.security.core.userdetails.User(manager.getManagerId(),
                manager.getManagerPass(),
                grantedAuthorities);
    }

}
