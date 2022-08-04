package backend.sellerB.service;

import backend.sellerB.dto.CustomerWaitingPageDto;
import backend.sellerB.entity.CustomerWaitingPage;
import backend.sellerB.repository.CustomerWaitingPageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerWaitingPageService {
    private final CustomerWaitingPageRepository customerWaitingPageRepository;

    public CustomerWaitingPageDto create(CustomerWaitingPageDto customerWaitingPageDto) {
        CustomerWaitingPage customerWaitingPage = CustomerWaitingPage.builder()
                .product(customerWaitingPageDto.getProduct())
                .customerWaitingPageMent(customerWaitingPageDto.getCustomerWaitingPageMent())
                .customerWaitingPageImage(customerWaitingPageDto.getCustomerWaitingPageImage())
                .build();

        return CustomerWaitingPageDto.from(customerWaitingPageRepository.save(customerWaitingPage));
    }

    public List<CustomerWaitingPageDto> getCustomerWaitingPageList() { return CustomerWaitingPageDto.fromList(customerWaitingPageRepository.findAll());}

    public CustomerWaitingPageDto getCustomerWaitingPageDetail(Long seq) {
        Optional<CustomerWaitingPage> customerWaitingPageOptional = customerWaitingPageRepository.findById(seq);
        CustomerWaitingPage customerWaitingPage = customerWaitingPageOptional.get();
        return CustomerWaitingPageDto.from(customerWaitingPage);
    }

    public CustomerWaitingPageDto update(Long seq, CustomerWaitingPageDto customerWaitingPageDto) {
        Optional<CustomerWaitingPage> customerWaitingPageOptional = customerWaitingPageRepository.findById(seq);
        CustomerWaitingPage customerWaitingPage = customerWaitingPageOptional.get();
        customerWaitingPage.setProduct(customerWaitingPageDto.getProduct());
        customerWaitingPage.setCustomerWaitingPageMent(customerWaitingPageDto.getCustomerWaitingPageMent());
        customerWaitingPage.setCustomerWaitingPageImage(customerWaitingPageDto.getCustomerWaitingPageImage());
        return CustomerWaitingPageDto.from(customerWaitingPage);
    }

    //deleteYn이 불필요해서 repo에서 삭제하고 삭제한 데이터 반환
    public CustomerWaitingPageDto deleteCustomerWaitingPage(Long seq) {
        Optional<CustomerWaitingPage> customerWaitingPageOptional = customerWaitingPageRepository.findById(seq);
        CustomerWaitingPage customerWaitingPage = customerWaitingPageOptional.get();
        customerWaitingPage.setCustomerWaitingPageDelYn(true);
        return CustomerWaitingPageDto.from(customerWaitingPage);
    }
}
