package backend.sellerB.service;

import backend.sellerB.dto.ManagerDto;
import backend.sellerB.entity.Authority;
import backend.sellerB.entity.Manager;
import backend.sellerB.exception.DuplicateUserException;
import backend.sellerB.repository.AuthorityRepository;
import backend.sellerB.repository.ManagerRepository;import backend.sellerB.repository.NoticeRepository;
import backend.sellerB.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@Transactional
@RequiredArgsConstructor
public class ManagerService {
    private static final Logger logger = LoggerFactory.getLogger(ManagerService.class);
    private final ManagerRepository managerRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

    @Transactional
    public ManagerDto signup(ManagerDto managerDto) {
        if (managerRepository.findBymanagerId(managerDto.getManagerId()).orElse(null) != null) {
            throw new DuplicateUserException(managerDto.getManagerId());
        }


        // 권한 정보를 만듦
        Authority authority = Authority.builder()
                .authorityName("ROLE_ADMIN")
                .build();

        authorityRepository.save(authority);

        //dto를 엔티티로
        Manager manager = Manager.builder()
                .managerId(managerDto.getManagerId())
                .managerName(managerDto.getManagerName())
                .managerPass(passwordEncoder.encode(managerDto.getManagerPass()))
                .managerTel(managerDto.getManagerTel())
                .managerEmail(managerDto.getManagerEmail())
                .authorities(Collections.singleton(authority))
                .build();

        logger.info(manager.getManagerId());
        return ManagerDto.from(managerRepository.save(manager));

    }

    // SecurityContext에 저장된 username의 정보만 가져옴옴
    @Transactional(readOnly = true)
    public ManagerDto getMyUserWithAuthorities() {
        return ManagerDto.from(SecurityUtil.getCurrentUserid().flatMap(managerRepository::findBymanagerId).orElse(null));
    }

}
