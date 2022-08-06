package backend.sellerB.service;

import backend.sellerB.dto.ProductDto;
import backend.sellerB.dto.ProductRes;
import backend.sellerB.dto.RegisterConsultingDto;
import backend.sellerB.dto.ResponseConsultingDto;
import backend.sellerB.entity.*;
import backend.sellerB.repository.ConsultantRepository;
import backend.sellerB.repository.ConsultingRepository;
import backend.sellerB.repository.CustomerRepository;
import backend.sellerB.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public RegisterConsultingDto createConsulting(RegisterConsultingDto registerConsultingDto) {
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

        return RegisterConsultingDto.from(consultingRepository.save(consulting));
    }

    public List<ResponseConsultingDto> getConsultingListByCustomerId(String customerId) {
        return ResponseConsultingDto.fromList(consultingRepository.findAllByCustomer_CustomerId(customerId));}

    public List<ResponseConsultingDto> getConsultingListByConsultantId(String consultantId) {
        return ResponseConsultingDto.fromList(consultingRepository.findAllByConsultant_ConsultantId(consultantId));}


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
}
