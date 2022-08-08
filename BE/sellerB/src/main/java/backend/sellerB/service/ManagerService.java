package backend.sellerB.service;


import backend.sellerB.dto.ConsultantDto;
import backend.sellerB.dto.EditManagerDto;
import backend.sellerB.dto.ManagerDto;
import backend.sellerB.dto.RegisterManagerDto;
import backend.sellerB.entity.Authority;
import backend.sellerB.entity.Consultant;
import backend.sellerB.entity.Manager;
import backend.sellerB.exception.DuplicateUserException;
import backend.sellerB.repository.*;
import backend.sellerB.util.SecurityUtil;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PreUpdate;
import java.util.Collections;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ManagerService {
    private static final Logger logger = LoggerFactory.getLogger(ManagerService.class);
    private final ManagerRepository managerRepository;
    private final ConsultantRepository consultantRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;
    private final BrandRepository brandRepository;

    @Transactional
    public ManagerDto signup(RegisterManagerDto registerManagerDto) {
        if (managerRepository.findBymanagerId(registerManagerDto.getManagerId()).orElse(null) != null ||
                consultantRepository.findByConsultantId(registerManagerDto.getManagerId()).orElse(null)!=null||
                customerRepository.findBycustomerId(registerManagerDto.getManagerId()).orElse(null)!=null) {
            throw new DuplicateUserException(registerManagerDto.getManagerId());
        }


        // 권한 정보를 만듦
        Authority authority = Authority.builder()
                .authorityName("ROLE_ADMIN")
                .build();

        authorityRepository.save(authority);

        System.out.println("프론트에서 받아온 dto의 브랜드 번호: "+registerManagerDto.getBrandSeq());
        System.out.println("프론트에서 받아온 id : "+registerManagerDto.getManagerId());



        //dto를 엔티티로
        Manager manager = Manager.builder()
                .managerSeq(registerManagerDto.getManagerSeq())
                .brand(brandRepository.findById(registerManagerDto.getBrandSeq()).get())
                .managerId(registerManagerDto.getManagerId())
                .managerName(registerManagerDto.getManagerName())
                .managerPass(passwordEncoder.encode(registerManagerDto.getManagerPass()))
                .managerTel(registerManagerDto.getManagerTel())
                .managerEmail(registerManagerDto.getManagerEmail())
                .authorities(Collections.singleton(authority))
                .build();

        System.out.println("manager빌더 : "+manager.getBrand());
        logger.info(manager.getManagerId());
        return ManagerDto.from(managerRepository.save(manager));

    }
    public ManagerDto getManagerDetail(Long managerSeq) {
        Optional<Manager> managerOptional = managerRepository.findByManagerSeq(managerSeq);
        Manager manager = managerOptional.get();
        return ManagerDto.from(manager);
    }

    @PreUpdate
    public ManagerDto update(EditManagerDto editManagerDto, Long managerSeq) {
        Optional<Manager> managerOptional = managerRepository.findById(managerSeq);
        Manager manager = managerOptional.get();

        String pass;
        if(editManagerDto.getManagerPass()==null || editManagerDto.getManagerPass()==""){
            pass = managerRepository.findByManagerSeq(managerSeq).get().getManagerPass();
            manager.setManagerPass(pass);
        }
        else{
            manager.setManagerPass(passwordEncoder.encode(editManagerDto.getManagerPass()));
        }
        manager.setManagerEmail(editManagerDto.getManagerEmail());
        manager.setManagerTel(editManagerDto.getManagerTel());
        manager.setManagerImageUrl(editManagerDto.getManagerImageUrl());

        manager = managerRepository.saveAndFlush(manager);

        return ManagerDto.from(manager);
    }


    public ManagerDto delete(Long managerSeq) {
        Optional<Manager> managerOptional = managerRepository.findById(managerSeq);
        Manager manager = managerOptional.get();
        manager.setManagerDelYn(true);
        managerRepository.delete(manager);
        return ManagerDto.from(manager);
    }

    // SecurityContext에 저장된 username의 정보만 가져옴옴
    @Transactional(readOnly = true)
    public ManagerDto getMyUserWithAuthorities() {
        return ManagerDto.from(SecurityUtil.getCurrentUserid().flatMap(managerRepository::findBymanagerId).orElse(null));
    }

}
