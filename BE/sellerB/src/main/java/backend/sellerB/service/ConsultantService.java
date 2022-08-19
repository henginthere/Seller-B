package backend.sellerB.service;

import backend.sellerB.dto.*;
import backend.sellerB.entity.*;
import backend.sellerB.exception.DuplicateUserException;
import backend.sellerB.repository.*;
import backend.sellerB.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PreUpdate;
import java.io.IOException;
import java.util.*;


@Service
@Transactional
@RequiredArgsConstructor
public class ConsultantService {

    private static final Logger logger = LoggerFactory.getLogger(ConsultantService.class);
    private final ConsultantRepository consultantRepository;
    private final CustomerRepository customerRepository;
    private final ManagerRepository managerRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;
    private final ProductGroupRepository productGroupRepository;

    @Transactional
    public ConsultantDto signup(RegisterConsultantDto registerConsultantDto) throws IOException {
        if (consultantRepository.findByConsultantId(registerConsultantDto.getConsultantId()).orElse(null) != null ||
            managerRepository.findBymanagerId(registerConsultantDto.getConsultantId()).orElse(null)!=null ||
        customerRepository.findBycustomerId(registerConsultantDto.getConsultantId()).orElse(null)!=null ) {
            throw new DuplicateUserException(registerConsultantDto.getConsultantId());
        }

        // 권한 정보를 만듦
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();
        authorityRepository.save(authority);


        Consultant consultant = Consultant.builder()
                .consultantId(registerConsultantDto.getConsultantId())
                .consultantName(registerConsultantDto.getConsultantName())
                .consultantEmail(registerConsultantDto.getConsultantEmail())
                .consultantPass(passwordEncoder.encode(registerConsultantDto.getConsultantPass()))
                .consultantTel(registerConsultantDto.getConsultantTel())
                .productGroup(productGroupRepository.findById(registerConsultantDto.getProductGroupSeq()).get())
                .consultantImageUrl(registerConsultantDto.getConsultantImageUrl())
                .authorities(Collections.singleton(authority))
                .build();

        return ConsultantDto.from(consultantRepository.save(consultant));


    }

    public List<ResponseConsultantDto> getConsultantList() {
        return ResponseConsultantDto.fromList(consultantRepository.findAll());
    }


    public ResponseConsultantDto getConsultantDetail(Long consultantSeq) {
        Optional<Consultant> consultantOptional = consultantRepository.findById(consultantSeq);
        Consultant consultant = consultantOptional.get();
        return ResponseConsultantDto.from(consultant);
    }

    public List<ResponseConsultantDto> searchByConsultantNameContaining(String consultantName) {

        return ResponseConsultantDto.fromList(consultantRepository.findByConsultantNameContaining(consultantName));
    }


    public List<ResponseConsultantDto> searchByProductGroupSeq(Long productGroupSeq) {

//        return ConsultantDto.fromList(consultantRepository.findByProductGroup(productGroupSeq));
        return ResponseConsultantDto.fromList(consultantRepository.findConsultantsByProductGroup_ProductGroupSeq(productGroupSeq));
    }

    public List<ResponseConsultantDto> searchByBrandName(String brandName) {
        return ResponseConsultantDto.fromList(consultantRepository.findConsultantsByProductGroup_Brand_BrandNameKor(brandName));
    }

    @PreUpdate
    public ConsultantDto update(EditConsultantDto editConsultantDto, Long consultantSeq) throws IOException {
        Optional<Consultant> consultantOptional = consultantRepository.findById(consultantSeq);
        Consultant consultant = consultantOptional.get();
        Optional<ProductGroup> productGroupOptional = productGroupRepository.findById(editConsultantDto.getProductGroupSeq());
        ProductGroup productGroup = productGroupOptional.get();

        String pass;
        if(editConsultantDto.getConsultantPass()==null || editConsultantDto.getConsultantPass()==""){
            pass = consultantRepository.findByConsultantSeq(consultantSeq).getConsultantPass();
            consultant.setConsultantPass(pass);
        }
        else{
            consultant.setConsultantPass(passwordEncoder.encode(editConsultantDto.getConsultantPass()));
        }
        consultant.setConsultantEmail(editConsultantDto.getConsultantEmail());
        consultant.setConsultantImageUrl(editConsultantDto.getConsultantImageUrl());
        consultant.setConsultantTel(editConsultantDto.getConsultantTel());
        consultant.setProductGroup(productGroup);

        consultant = consultantRepository.saveAndFlush(consultant);

        return ConsultantDto.from(consultant);
    }

    public ConsultantDto delete(Long consultantSeq) {
        Optional<Consultant> consultantOptional = consultantRepository.findById(consultantSeq);
        Consultant consultant = consultantOptional.get();
        consultantRepository.deleteById(consultantSeq);
        return ConsultantDto.from(consultant);
    }


    // SecurityContext에 저장된 username의 정보만 가져옴옴
    @Transactional(readOnly = true)
    public ConsultantDto getMyUserWithAuthorities() {
        return ConsultantDto.from(SecurityUtil.getCurrentUserid().flatMap(consultantRepository::findByConsultantId).orElse(null));
    }


}
