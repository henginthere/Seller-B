package backend.sellerB.service;

import backend.sellerB.dto.*;
import backend.sellerB.entity.*;
import backend.sellerB.repository.ConsultantRepository;
import backend.sellerB.repository.ConsultingRepository;
import backend.sellerB.repository.CustomerRepository;
import backend.sellerB.repository.ProductRepository;
import backend.sellerB.util.FcmUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ConsultingService {
    private final ConsultingRepository consultingRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final ConsultantRepository consultantRepository;


    // openvidu session create rest api 요청
    public OpenviduSessionDto requestToOpenviduCreate(String customerId) throws JsonProcessingException {
        String url = "https://i7d105.p.ssafy.io:8443/openvidu/api/sessions";
        RestTemplate restTemplate = new RestTemplate();

        // Header set
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        // auth 값 숨김 필요!!!!!!!!!!!!!!!!!!!
        httpHeaders.setBasicAuth("OPENVIDUAPP","SELLERB");

        // Body set
        RequestOpenviduBody requestOpenviduBody = RequestOpenviduBody.builder()
                .customSessionId(customerId)
                .build();

        // Message
        HttpEntity<?> requestMessage = new HttpEntity<>(requestOpenviduBody, httpHeaders);

        // Request
        HttpEntity<String> response = restTemplate.postForEntity(url, requestMessage, String.class);

        // Response 파싱
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        OpenviduSessionDto dto = objectMapper.readValue(response.getBody(), OpenviduSessionDto.class);

        return dto;
    }

    public HttpEntity<String> requestToOpenviduDelete(String customerId) throws JsonProcessingException {
        String url = String.format("https://i7d105.p.ssafy.io:8443/openvidu/api/sessions/%s", customerId);
        RestTemplate restTemplate = new RestTemplate();

        // Header set
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        // auth 값 숨김 필요!!!!!!!!!!!!!!!!!!!
        httpHeaders.setBasicAuth("OPENVIDUAPP","SELLERB");


        // Message
        HttpEntity<?> requestMessage = new HttpEntity<>(httpHeaders);

        // Request
        HttpEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, requestMessage, String.class);
        return response;
    }

    public RegisterConsultingDto createConsulting(RegisterConsultingDto registerConsultingDto) throws JsonProcessingException, FirebaseMessagingException {
        Optional<Customer> customerOptional = customerRepository.findBycustomerId(registerConsultingDto.getCustomerId());
        Customer customer = customerOptional.get();
        Optional<Product> productOptional = productRepository.findById(registerConsultingDto.getProductSeq());
        Product product = productOptional.get();
        Optional<Consultant> consultantOptional = consultantRepository.findById(registerConsultingDto.getConsultantSeq());
        Consultant consultant = consultantOptional.get();
        Consulting consulting = Consulting.builder()
                .customer(customer)
                .consultant(consultant)
                .product(product)
                .consultingState(registerConsultingDto.getConsultingState())
                .build();
        // openvidu session 만들기
        // OpenviduSessionDto openviduSessionDto = requestToOpenviduCreate(customer.getCustomerId());

        // fcm 메세지 보내기
        FcmUtil.send_FCM(customer.getCustomerToken(), "상담 시작 알림", "신청하신 상담이 시작되었습니다");

        return RegisterConsultingDto.from(consultingRepository.save(consulting));
    }

    public List<ResponseConsultingDto> getConsultingListByCustomerId(String customerId) {
        return ResponseConsultingDto.fromList(consultingRepository.findAllByCustomer_CustomerId(customerId));}

    public List<ResponseConsultingDto> getConsultingListByCustomerIdPeriod(String customerId, Integer period) {
        LocalDateTime now = LocalDateTime.now();
        if (period.equals(1)) {
            now = now.minusDays(1);
        } else if (period.equals(7)) {
            now = now.minusWeeks(1);
        } else if (period.equals(30)) {
            now = now.minusMonths(1);
        }
        return ResponseConsultingDto.fromList(consultingRepository.findConsultingsByCustomer_CustomerIdAndAndConsultingStartDateIsAfter(customerId, now));}

    public List<ResponseConsultingDto> getConsultingListByConsultantId(String consultantId) {
        return ResponseConsultingDto.fromList(consultingRepository.findAllByConsultant_ConsultantId(consultantId));}

    public List<ResponseConsultingDto> getConsultingListByConsultantIdPeriod(String consultantId, Integer period) {
        LocalDateTime now = LocalDateTime.now();
        if (period.equals(1)) {
            now = now.minusDays(1);
        } else if (period.equals(7)) {
            now = now.minusWeeks(1);
        } else if (period.equals(30)) {
            now = now.minusMonths(1);
        }
        return ResponseConsultingDto.fromList(consultingRepository.findConsultingsByConsultant_ConsultantIdAndConsultingStartDateIsAfter(consultantId, now));}


    public RegisterConsultingDto updateConsulting(Long seq, RegisterConsultingDto registerConsultingDto) {
        Optional<Consulting> consultingOptional = consultingRepository.findById(seq);
        Consulting consulting = consultingOptional.get();
        Optional<Customer> customerOptional = customerRepository.findBycustomerId(registerConsultingDto.getCustomerId());
        Customer customer = customerOptional.get();
        Optional<Product> productOptional = productRepository.findById(registerConsultingDto.getProductSeq());
        Product product = productOptional.get();
        Optional<Consultant> consultantOptional = consultantRepository.findById(registerConsultingDto.getConsultantSeq());
        Consultant consultant = consultantOptional.get();
        consulting.setCustomer(customer);
        consulting.setConsultant(consultant);
        consulting.setProduct(product);
        consulting.setConsultingState(registerConsultingDto.getConsultingState());


        return RegisterConsultingDto.from(consulting);
    }
    @PreUpdate
    public RegisterConsultingDto updateConsultingState(Long seq, EditConsultingStateDto editConsultingStateDto) throws JsonProcessingException {
        Optional<Consulting> consultingOptional = consultingRepository.findById(seq);
        Consulting consulting = consultingOptional.get();
        String consultingState = consulting.getConsultingState();

        if (editConsultingStateDto.getConsultingState().equals("start")) {
            consulting.setConsultingState(editConsultingStateDto.getConsultingState());
            return RegisterConsultingDto.from(consulting);
        }
        if (consultingState.equals("waiting")) {
            consulting.setConsultingState("noShow");
        } else if (consultingState.equals("start")) {
            consulting.setConsultingState("end");
        }

        // openvidu session delete
        // HttpEntity<String> response = requestToOpenviduDelete(consulting.getCustomer().getCustomerId());

        return RegisterConsultingDto.from(consulting);
    }
}
