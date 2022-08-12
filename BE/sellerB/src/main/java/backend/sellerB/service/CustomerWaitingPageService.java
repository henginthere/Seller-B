package backend.sellerB.service;

import backend.sellerB.dto.CustomerWaitingPageDto;
import backend.sellerB.dto.RegisterCustomerWaitingPageDto;
import backend.sellerB.entity.CustomerWaitingPage;
import backend.sellerB.repository.CustomerWaitingPageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerWaitingPageService {
    private final CustomerWaitingPageRepository customerWaitingPageRepository;

    private final AwsS3Service awsS3Service;
    public CustomerWaitingPageDto create(RegisterCustomerWaitingPageDto registerCustomerWaitingPageDto) throws IOException {

//        String customerWaitingPageImage = awsS3Service.upload(registerCustomerWaitingPageDto.getCustomerWaitingPageImageFile(), "static");

        CustomerWaitingPage customerWaitingPage = CustomerWaitingPage.builder()
                .product(registerCustomerWaitingPageDto.getProduct())
                .customerWaitingPageMent(registerCustomerWaitingPageDto.getCustomerWaitingPageMent())
                .customerWaitingPageImage(registerCustomerWaitingPageDto.getCustomerWaitingPageImageUrl())
                .build();

        return CustomerWaitingPageDto.from(customerWaitingPageRepository.save(customerWaitingPage));
    }

    public List<CustomerWaitingPageDto> getCustomerWaitingPageList() { return CustomerWaitingPageDto.fromList(customerWaitingPageRepository.findAll());}

    public CustomerWaitingPageDto getCustomerWaitingPageDetail(Long seq) {
        Optional<CustomerWaitingPage> customerWaitingPageOptional = customerWaitingPageRepository.findById(seq);
        CustomerWaitingPage customerWaitingPage = customerWaitingPageOptional.get();
        return CustomerWaitingPageDto.from(customerWaitingPage);
    }

    public CustomerWaitingPageDto update(Long seq, RegisterCustomerWaitingPageDto registerCustomerWaitingPageDto) throws IOException {
        Optional<CustomerWaitingPage> customerWaitingPageOptional = customerWaitingPageRepository.findById(seq);
        CustomerWaitingPage customerWaitingPage = customerWaitingPageOptional.get();
//        String customerWaitingPageImage = awsS3Service.upload(registerCustomerWaitingPageDto.getCustomerWaitingPageImageFile(), "static");
        customerWaitingPage.setProduct(registerCustomerWaitingPageDto.getProduct());
        customerWaitingPage.setCustomerWaitingPageMent(registerCustomerWaitingPageDto.getCustomerWaitingPageMent());
        customerWaitingPage.setCustomerWaitingPageImage(registerCustomerWaitingPageDto.getCustomerWaitingPageImageUrl());
        return CustomerWaitingPageDto.from(customerWaitingPage);
    }

    //deleteYn이 불필요해서 repo에서 삭제하고 삭제한 데이터 반환
    public CustomerWaitingPageDto deleteCustomerWaitingPage(Long seq) {
        Optional<CustomerWaitingPage> customerWaitingPageOptional = customerWaitingPageRepository.findById(seq);
        CustomerWaitingPage customerWaitingPage = customerWaitingPageOptional.get();
        customerWaitingPage.setCustomerWaitingPageDelYn(true);
        customerWaitingPageRepository.deleteById(seq);
        return CustomerWaitingPageDto.from(customerWaitingPage);
    }
}
