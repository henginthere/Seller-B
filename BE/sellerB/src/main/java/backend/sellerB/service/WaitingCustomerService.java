package backend.sellerB.service;

import backend.sellerB.dto.CreateWaitingCustomerDto;
import backend.sellerB.dto.WaitingCustomerDto;
import backend.sellerB.entity.*;
import backend.sellerB.repository.CustomerRepository;
import backend.sellerB.repository.ProductGroupRepository;
import backend.sellerB.repository.ProductRepository;
import backend.sellerB.repository.WaitingCustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class WaitingCustomerService {
    private final WaitingCustomerRepository waitingCustomerRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final ProductGroupRepository productGroupRepository;

    @Transactional
    public WaitingCustomerDto create(CreateWaitingCustomerDto createWaitingCustomerDto) {
        Optional<Customer> customerOptional = customerRepository.findById(createWaitingCustomerDto.getCustomerSeq());
        Customer customer = customerOptional.get();
        Optional<Product> productOptional = productRepository.findById(createWaitingCustomerDto.getProductSeq());
        Product product = productOptional.get();
        Optional<ProductGroup> productGroupOptional = productGroupRepository.findById(product.getProductGroup().getProductGroupSeq());
        ProductGroup productGroup = productGroupOptional.get();

        //dto를 엔티티로
        WaitingCustomer waitingCustomer = WaitingCustomer.builder()
                .customer(customer)
                .product(product)
                .waitingCustomerState(createWaitingCustomerDto.getWaitingCustomerState())
                .productGroup(productGroup)
                .build();

        return WaitingCustomerDto.from(waitingCustomerRepository.save(waitingCustomer));

    }

    public List<WaitingCustomerDto> getWaitingCustomersByProductGroup_ProductGroupName(String productGroupName) {
        return WaitingCustomerDto.fromList(waitingCustomerRepository.findWaitingCustomersByProductGroup_ProductGroupName(productGroupName));
    }

    public List<WaitingCustomerDto> getWaitingCustomersByProductGroup_ProductGroupSeq(Long productGroupSeq) {
        return WaitingCustomerDto.fromList(waitingCustomerRepository.findWaitingCustomersByProductGroup_ProductGroupSeq(productGroupSeq));
    }

    public WaitingCustomerDto getWaitingCustomerDetail(Long seq) {
        Optional<WaitingCustomer> waitingCustomerOptional = waitingCustomerRepository.findById(seq);
        WaitingCustomer waitingCustomer = waitingCustomerOptional.get();
        return WaitingCustomerDto.from(waitingCustomer);
    }


    public WaitingCustomerDto deleteWaitingCustomer(Long seq) {
        Optional<WaitingCustomer> waitingCustomerOptional = waitingCustomerRepository.findById(seq);
        WaitingCustomer waitingCustomer = waitingCustomerOptional.get();
//        waitingCustomer.setWatingCustomerGroupDelYn(true);
        waitingCustomerRepository.deleteById(seq);
        // 대기 고객 삭제 -> 상담 생성으로 이어지게?
        return WaitingCustomerDto.from(waitingCustomer);
    }

}
