package backend.sellerB.service;

import backend.sellerB.dto.CustomerDto;
import backend.sellerB.dto.ProductDto;
import backend.sellerB.entity.Customer;
import backend.sellerB.entity.Product;
import backend.sellerB.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = Customer.builder()
                .customerId(customerDto.getCustomerId())
                .customerName(customerDto.getCustomerName())
                .customerPass(passwordEncoder.encode(customerDto.getCustomerPass()))
                .customerEmail(customerDto.getCustomerEmail())
                .customerGender(customerDto.getCustomerGender())
                .customerTel(customerDto.getCustomerTel())
                .customerAddr(customerDto.getCustomerAddr())
                .customerBirth(customerDto.getCustomerBirth())
                .customerToken(customerDto.getCustomerToken())
                .build();

        return CustomerDto.from(customerRepository.save(customer));
    }

    public List<CustomerDto> getCustomerList() { return CustomerDto.fromList(customerRepository.findAll());}

    public CustomerDto getCustomerDetail(Long seq) {
        Optional<Customer> customerOptional = customerRepository.findById(seq);
        Customer customer = customerOptional.get();
        return CustomerDto.from(customer);
    }

    public CustomerDto updateCustomer(Long seq, CustomerDto customerDto) {
        Optional<Customer> customerOptional = customerRepository.findById(seq);
        Customer customer = customerOptional.get();
        customer.setCustomerPass(passwordEncoder.encode(customerDto.getCustomerPass()));
        customer.setCustomerEmail(customerDto.getCustomerEmail());
        customer.setCustomerTel(customerDto.getCustomerTel());
        customer.setCustomerAddr(customerDto.getCustomerAddr());
        customer.setCustomerToken(customerDto.getCustomerToken());
        return CustomerDto.from(customer);
    }

    public CustomerDto deleteCustomer(Long seq) {
        Optional<Customer> customerOptional = customerRepository.findById(seq);
        Customer customer = customerOptional.get();
        customer.setCustomerDelYn(true);
        return CustomerDto.from(customer);
    }
}
