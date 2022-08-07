package backend.sellerB.config;

import backend.sellerB.repository.ConsultantRepository;
import backend.sellerB.repository.CustomerRepository;
import backend.sellerB.repository.ManagerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.stream.Collectors;

@EnableJpaAuditing
@Configuration
public class AuditorAwareConfig {

    @Resource
    private ManagerRepository managerRepository;
    @Resource
    private ConsultantRepository consultantRepository;
    @Resource
    private CustomerRepository customerRepository;
    @Bean
    public AuditorAware<Long> auditorAware() {

        return new AuditorAware<>() {
            @Override
            public Optional<Long> getCurrentAuditor() {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

                String authorities = getAuthorities(authentication);
                System.out.println("권한정보 : "+authentication);
                if (authentication.getPrincipal().equals("anonymousUser") || authorities.equals("ROLE_ANONYMOUS"))
                {
                    return Optional.of(-1L);
                }
                else if(authorities.equals("ROLE_ADMIN")){
                    return Optional.of(managerRepository.findByManagerSeq(Long.parseLong(authentication.getName())).get().getManagerSeq());
                }
                else if(authorities.equals("ROEL_USER")){
                    return Optional.of(consultantRepository.findByConsultantSeq(Long.parseLong(authentication.getName())).getConsultantSeq());
                }
                else{
                    return Optional.of(customerRepository.findBycustomerSeq(Long.parseLong(authentication.getName())).get().getCustomerSeq());
                }
            }

        };
    }

    public String getAuthorities(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }
}
