package backend.sellerB.service;

import backend.sellerB.dto.CustomerDto;
import backend.sellerB.dto.ProductDto;
import backend.sellerB.entity.Authority;
import backend.sellerB.entity.Customer;
import backend.sellerB.entity.Product;
import backend.sellerB.exception.DuplicateUserException;
import backend.sellerB.repository.AuthorityRepository;
import backend.sellerB.repository.ConsultantRepository;
import backend.sellerB.repository.CustomerRepository;
import backend.sellerB.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ConsultantRepository consultantRepository;
    private final ManagerRepository managerRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public CustomerDto signup(CustomerDto customerDto) {
        if (customerRepository.findBycustomerId(customerDto.getCustomerId()).orElse(null) != null ||
        consultantRepository.findByConsultantId(customerDto.getCustomerId()).orElse(null)!=null ||
        managerRepository.findBymanagerId(customerDto.getCustomerId()).orElse(null)!=null) {
            throw new DuplicateUserException(customerDto.getCustomerId());
        }

        // 권한 정보를 만듦
        Authority authority = Authority.builder()
                .authorityName("ROLE_CUSTOMER")
                .build();

        authorityRepository.save(authority);

        Customer customer = Customer.builder()
                .customerId(customerDto.getCustomerId())
                .customerName(customerDto.getCustomerName())
                .customerPass(passwordEncoder.encode(customerDto.getCustomerPass()))
                .customerEmail(customerDto.getCustomerEmail())
                .customerBirth(customerDto.getCustomerBirth())
                .customerToken(customerDto.getCustomerToken())
                .authorities(Collections.singleton(authority))
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
