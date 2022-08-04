package backend.sellerB.service;

import backend.sellerB.dto.AuthorityDto;
import backend.sellerB.dto.ConsultantDto;
import backend.sellerB.dto.EditConsultantDto;
import backend.sellerB.dto.NoticeDto;
import backend.sellerB.entity.Authority;
import backend.sellerB.entity.Consultant;
import backend.sellerB.entity.MemberAuth;
import backend.sellerB.entity.Notice;
import backend.sellerB.exception.DuplicateUserException;
import backend.sellerB.repository.AuthorityRepository;
import backend.sellerB.repository.ConsultantRepository;
import backend.sellerB.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.*;


@Service
@Transactional
@RequiredArgsConstructor
public class ConsultantService {

    private static final Logger logger = LoggerFactory.getLogger(ConsultantService.class);
    private final ConsultantRepository consultantRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

    @Transactional
    public ConsultantDto signup(ConsultantDto consultantDto) {
        if (consultantRepository.findByConsultantId(consultantDto.getConsultantId()).orElse(null) != null) {
            throw new DuplicateUserException(consultantDto.getConsultantId());
        }

        // 권한 정보를 만듦
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();
        authorityRepository.save(authority);

        //dto를 엔티티로
        Consultant consultant = Consultant.builder()
                .consultantId(consultantDto.getConsultantId())
                .consultantName(consultantDto.getConsultantName())
                .consultantEmail(consultantDto.getConsultantEmail())
                .consultantPass(passwordEncoder.encode(consultantDto.getConsultantPass()))
                .consultantTel(consultantDto.getConsultantTel())
                .productGroup(consultantDto.getProductGroup())
                .authorities(Collections.singleton(authority))
                .build();

        return ConsultantDto.from(consultantRepository.save(consultant));


    }

    public List<ConsultantDto> getConsultantList() {
        return ConsultantDto.fromList(consultantRepository.findAll());
    }


    public ConsultantDto getConsultantDetail(Long consultantSeq) {
        Optional<Consultant> consultantOptional = consultantRepository.findById(consultantSeq);
        Consultant consultant = consultantOptional.get();
        return ConsultantDto.from(consultant);
    }

    public List<ConsultantDto> searchByConsultantNameContaining(String consultantName) {

        return ConsultantDto.fromList(consultantRepository.findByConsultantNameContaining(consultantName));
    }

//    public List<ConsultantDto> searchByConsultantId(String consultantId) {
//
//        return ConsultantDto.fromList(consultantRepository.findByConsultantIdContaining(consultantId));
//    }

    public List<ConsultantDto> searchByProductGroupSeq(Long productGroupSeq) {

        return ConsultantDto.fromList(consultantRepository.findByProductGroup(productGroupSeq));
    }

    public ConsultantDto update(EditConsultantDto editConsultantDto, Long consultantSeq) {
        Optional<Consultant> consultantOptional = consultantRepository.findById(consultantSeq);
        Consultant consultant = consultantOptional.get();
        consultant.setConsultantPass(passwordEncoder.encode(editConsultantDto.getConsultantPass()));
        consultant.setConsultantEmail(editConsultantDto.getConsultantEmail());
        consultant.setConsultantImageUrl(editConsultantDto.getConsultantImageUrl());
        consultant.setConsultantTel(editConsultantDto.getConsultantTel());
        consultant.setProductGroup(editConsultantDto.getProductGroup());

        return ConsultantDto.from(consultant);
    }

    public ConsultantDto delete(Long consultantSeq) {
        Optional<Consultant> consultantOptional = consultantRepository.findById(consultantSeq);
        Consultant consultant = consultantOptional.get();
        consultant.setConsultantDelYn(true);
        return ConsultantDto.from(consultant);
    }


    // SecurityContext에 저장된 username의 정보만 가져옴옴
    @Transactional(readOnly = true)
    public ConsultantDto getMyUserWithAuthorities() {
        return ConsultantDto.from(SecurityUtil.getCurrentUserid().flatMap(consultantRepository::findByConsultantId).orElse(null));
    }


}
