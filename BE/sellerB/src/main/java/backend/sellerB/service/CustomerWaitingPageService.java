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

    @PreUpdate
    public CustomerWaitingPageDto create(RegisterCustomerWaitingPageDto registerCustomerWaitingPageDto) throws IOException {

        Optional<CustomerWaitingPage> customerWaitingPageOptional = customerWaitingPageRepository.findByProduct_ProductSeq(registerCustomerWaitingPageDto.getProductSeq());
        if(!(customerWaitingPageOptional.isPresent())) {
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


    public CustomerWaitingPageDto getCustomerWaitingPageDetailByProductSeq(Long productSeq) {
        Optional<CustomerWaitingPage> customerWaitingPageOptional = customerWaitingPageRepository.findByProduct_ProductSeq(productSeq);
        CustomerWaitingPage customerWaitingPage = customerWaitingPageOptional.get();
        return CustomerWaitingPageDto.from(customerWaitingPage);
    }

    public CustomerWaitingPageDto update(Long productSeq, RegisterCustomerWaitingPageDto registerCustomerWaitingPageDto) throws IOException {
        Optional<Product> productOptional = productRepository.findById(registerCustomerWaitingPageDto.getProductSeq());
        Product product = productOptional.get();
        Optional<CustomerWaitingPage> customerWaitingPageOptional = customerWaitingPageRepository.findByProduct_ProductSeq(productSeq);
        CustomerWaitingPage customerWaitingPage = customerWaitingPageOptional.get();
        customerWaitingPage.setProduct(product);
        customerWaitingPage.setCustomerWaitingPageMent(registerCustomerWaitingPageDto.getCustomerWaitingPageMent());
        customerWaitingPage.setCustomerWaitingPageImage(registerCustomerWaitingPageDto.getCustomerWaitingPageImage());
        return CustomerWaitingPageDto.from(customerWaitingPage);
    }


    public CustomerWaitingPageDto deleteCustomerWaitingPage(Long productSeq) {
        Optional<CustomerWaitingPage> customerWaitingPageOptional = customerWaitingPageRepository.findByProduct_ProductSeq(productSeq);
        CustomerWaitingPage customerWaitingPage = customerWaitingPageOptional.get();
        customerWaitingPage.setCustomerWaitingPageDelYn(true);
        customerWaitingPageRepository.deleteById(customerWaitingPage.getCustomerWaitingPageSeq());
        return CustomerWaitingPageDto.from(customerWaitingPage);
    }
}
