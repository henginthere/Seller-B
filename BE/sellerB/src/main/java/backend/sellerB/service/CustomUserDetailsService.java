package backend.sellerB.service;

import backend.sellerB.entity.Consultant;
import backend.sellerB.entity.Customer;
import backend.sellerB.entity.Manager;
import backend.sellerB.repository.ConsultantRepository;
import backend.sellerB.repository.CustomerRepository;
import backend.sellerB.repository.ManagerRepository;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;

    private final ManagerRepository managerRepository;
    private final ConsultantRepository consultantRepository;

    private final CustomerRepository customerRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String id)  {
        // login 시에 db에서 유저정보와, 권한정보를 같이 가져옴

        // 1. 매니저 테이블에서 찾고
        if(managerRepository.findBymanagerId(id).isPresent()){
            return managerRepository.findBymanagerId(id)
                    .map(user -> createUser(id,user))
                    .get();
        }
        else if(consultantRepository.findByConsultantId(id).isPresent()){
            return consultantRepository.findByConsultantId(id)
                    .map(user->createUser(id,user))
                    .get();
        }
        else if(customerRepository.findBycustomerId(id).isPresent()){
            return customerRepository.findBycustomerId(id)
                    .map(user->createUser(id,user))
                    .get();
        }
        else{
            throw new UsernameNotFoundException(id + " -> 데이터베이스에서 찾을 수 없습니다.");
        }
    }

    private org.springframework.security.core.userdetails.User createUser(String id, Manager manager) {
        List<GrantedAuthority> grantedAuthorities = manager.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toList());
        // 1의 정보를 기반으로 userdetails.User객체를 생성해서 return
        return new org.springframework.security.core.userdetails.User(manager.getManagerId(),
                manager.getManagerPass(),
                grantedAuthorities);
    }

    private org.springframework.security.core.userdetails.User createUser(String id, Consultant consultant) {
        List<GrantedAuthority> grantedAuthorities = consultant.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toList());
        // 1의 정보를 기반으로 userdetails.User객체를 생성해서 return
        return new org.springframework.security.core.userdetails.User(consultant.getConsultantId(),
                consultant.getConsultantPass(),
                grantedAuthorities);
    }
    private org.springframework.security.core.userdetails.User createUser(String id, Customer customer) {
        List<GrantedAuthority> grantedAuthorities = customer.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toList());
        // 1의 정보를 기반으로 userdetails.User객체를 생성해서 return
        return new org.springframework.security.core.userdetails.User(customer.getCustomerId(),
                customer.getCustomerPass(),
                grantedAuthorities);
    }

}
