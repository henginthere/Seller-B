package backend.sellerB.service;

import backend.sellerB.dto.CustomerWaitingPageDto;
import backend.sellerB.dto.RegisterCustomerWaitingPageDto;
import backend.sellerB.entity.CustomerWaitingPage;
import backend.sellerB.entity.Product;
import backend.sellerB.exception.DuplicateWaitingPageException;
import backend.sellerB.repository.CustomerWaitingPageRepository;
import backend.sellerB.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PreUpdate;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerWaitingPageService {
    private final CustomerWaitingPageRepository customerWaitingPageRepository;
    private final ProductRepository productRepository;

//    private final AwsS3Service awsS3Service;
    @PreUpdate
    public CustomerWaitingPageDto create(RegisterCustomerWaitingPageDto registerCustomerWaitingPageDto) throws IOException {

        Optional<CustomerWaitingPage> customerWaitingPageOptional = customerWaitingPageRepository.findByProduct_ProductSeq(registerCustomerWaitingPageDto.getProductSeq());
        if(!(customerWaitingPageOptional.isPresent())) {
//        String customerWaitingPageImage = awsS3Service.upload(registerCustomerWaitingPageDto.getCustomerWaitingPageImageFile(), "static");
            Optional<Product> productOptional = productRepository.findById(registerCustomerWaitingPageDto.getProductSeq());
            Product product = productOptional.get();

            CustomerWaitingPage customerWaitingPage = CustomerWaitingPage.builder()
                    .product(product)
                    .customerWaitingPageMent(registerCustomerWaitingPageDto.getCustomerWaitingPageMent())
                    .customerWaitingPageImage(registerCustomerWaitingPageDto.getCustomerWaitingPageImage())
                    .build();

            return CustomerWaitingPageDto.from(customerWaitingPageRepository.save(customerWaitingPage));
        }
        else{
            return null;
        }

    }

    public List<CustomerWaitingPageDto> getCustomerWaitingPageList() { return CustomerWaitingPageDto.fromList(customerWaitingPageRepository.findAll());}

//    public CustomerWaitingPageDto getCustomerWaitingPageDetail(Long seq) {
//        Optional<CustomerWaitingPage> customerWaitingPageOptional = customerWaitingPageRepository.findById(seq);
//        CustomerWaitingPage customerWaitingPage = customerWaitingPageOptional.get();
//        return CustomerWaitingPageDto.from(customerWaitingPage);
//    }

    public CustomerWaitingPageDto getCustomerWaitingPageDetailByProductSeq(Long productSeq) {
        Optional<CustomerWaitingPage> customerWaitingPageOptional = customerWaitingPageRepository.findByProduct_ProductSeq(productSeq);
        CustomerWaitingPage customerWaitingPage = customerWaitingPageOptional.get();
        return CustomerWaitingPageDto.from(customerWaitingPage);
    }

    public CustomerWaitingPageDto update(Long productSeq, RegisterCustomerWaitingPageDto registerCustomerWaitingPageDto) throws IOException {
//        Optional<CustomerWaitingPage> customerWaitingPageOptional = customerWaitingPageRepository.findById(seq);
//        CustomerWaitingPage customerWaitingPage = customerWaitingPageOptional.get();
        Optional<Product> productOptional = productRepository.findById(registerCustomerWaitingPageDto.getProductSeq());
        Product product = productOptional.get();
//        String customerWaitingPageImage = awsS3Service.upload(registerCustomerWaitingPageDto.getCustomerWaitingPageImageFile(), "static");
        Optional<CustomerWaitingPage> customerWaitingPageOptional = customerWaitingPageRepository.findByProduct_ProductSeq(productSeq);
        CustomerWaitingPage customerWaitingPage = customerWaitingPageOptional.get();
        customerWaitingPage.setProduct(product);
        customerWaitingPage.setCustomerWaitingPageMent(registerCustomerWaitingPageDto.getCustomerWaitingPageMent());
        customerWaitingPage.setCustomerWaitingPageImage(registerCustomerWaitingPageDto.getCustomerWaitingPageImage());
        return CustomerWaitingPageDto.from(customerWaitingPage);
    }

    //deleteYn이 불필요해서 repo에서 삭제하고 삭제한 데이터 반환
    public CustomerWaitingPageDto deleteCustomerWaitingPage(Long productSeq) {
//        Optional<Product> productOptional = productRepository.findById(productSeq);
//        Product product = productOptional.get();
        Optional<CustomerWaitingPage> customerWaitingPageOptional = customerWaitingPageRepository.findByProduct_ProductSeq(productSeq);
        CustomerWaitingPage customerWaitingPage = customerWaitingPageOptional.get();
        customerWaitingPage.setCustomerWaitingPageDelYn(true);
        customerWaitingPageRepository.deleteById(customerWaitingPage.getCustomerWaitingPageSeq());
        return CustomerWaitingPageDto.from(customerWaitingPage);
    }
}
