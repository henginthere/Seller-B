package backend.sellerB.service;

import backend.sellerB.dto.*;
import backend.sellerB.entity.Authority;
import backend.sellerB.entity.Customer;
import backend.sellerB.entity.Product;
import backend.sellerB.exception.DuplicateUserException;
import backend.sellerB.repository.AuthorityRepository;
import backend.sellerB.repository.ConsultantRepository;
import backend.sellerB.repository.CustomerRepository;
import backend.sellerB.repository.ManagerRepository;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.core.util.PasswordDecryptor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PreUpdate;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;

import static org.springframework.security.oauth2.core.OAuth2TokenIntrospectionClaimNames.CLIENT_ID;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ConsultantRepository consultantRepository;
    private final ManagerRepository managerRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;

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

    public LoginResponseDto googleLogin(CustomerDto customerDto)  {

        String id = customerDto.getCustomerId();
        String pw = customerDto.getCustomerPass();

            // Use or store profile information
            // ...
            Optional<Customer> optionalCustomer = customerRepository.findBycustomerId(id);
            if(!optionalCustomer.isPresent()){
                System.out.println("가입된 정보 없음");
                //회원가입
                // 권한 정보를 만듦
                signup(customerDto);
//                Authority authority = Authority.builder()
//                        .authorityName("ROLE_CUSTOMER")
//                        .build();
//
//                authorityRepository.save(authority);
//
//                Customer customer = Customer.builder()
//                        .customerId(id)
//                        .customerName(name)
//                        .customerPass(pw)
//                        .customerEmail(customerDto.getCustomerEmail())
//                        .customerBirth(customerDto.getCustomerBirth())
//                        .customerToken(customerDto.getCustomerToken())
//                        .authorities(Collections.singleton(authority))
//                        .build();
//                customerRepository.save(customer);
            }

            LoginResponseDto loginResponseDto = authService.authorize(id,pw);

            return loginResponseDto;

//        } else {
//            System.out.println("Invalid ID token.");
//            return null;
//        }
    }

    public LoginResponseDto naverLogin(CustomerDto customerDto)  {

        String id = customerDto.getCustomerId();
        String pw = customerDto.getCustomerPass();

        Optional<Customer> optionalCustomer = customerRepository.findBycustomerId(id);
        if(!optionalCustomer.isPresent()){
            System.out.println("가입된 정보 없음");
            //회원가입
            signup(customerDto);
        }

        LoginResponseDto loginResponseDto = authService.authorize(id,pw);

        return loginResponseDto;
    }

    public LoginResponseDto kakaoLogin(CustomerDto customerDto)  {

        String id = customerDto.getCustomerId();
        String pw = customerDto.getCustomerPass();

        Optional<Customer> optionalCustomer = customerRepository.findBycustomerId(id);
        if(!optionalCustomer.isPresent()){
            System.out.println("가입된 정보 없음");
            //회원가입
            signup(customerDto);
        }

        LoginResponseDto loginResponseDto = authService.authorize(id,pw);

        return loginResponseDto;
    }

    public List<CustomerDto> getCustomerList() { return CustomerDto.fromList(customerRepository.findAll());}

    public CustomerDto getCustomerDetail(Long seq) {
        Optional<Customer> customerOptional = customerRepository.findById(seq);
        Customer customer = customerOptional.get();
        return CustomerDto.from(customer);
    }

    @PreUpdate
    public CustomerDto updateCustomer(Long seq, EditCustomerDto editCustomerDto) {

        Optional<Customer> customerOptional = customerRepository.findById(seq);
        Customer customer = customerOptional.get();
        String pass;
        if(editCustomerDto.getCustomerPass()==null || editCustomerDto.getCustomerPass()==""){
            pass = customerRepository.findBycustomerSeq(seq).get().getCustomerPass();
            customer.setCustomerPass(pass);
            customer.setCustomerEmail(editCustomerDto.getCustomerEmail());
        }
        else{
            customer.setCustomerPass(passwordEncoder.encode(editCustomerDto.getCustomerPass()));
            customer.setCustomerEmail(editCustomerDto.getCustomerEmail());
        }

        customer = customerRepository.saveAndFlush(customer);
        return CustomerDto.from(customer);


    }

    @PreUpdate
    public CustomerDto updateCustomerToken(Long seq, EditCustomerTokenDto editCustomerTokenDto) {

        Optional<Customer> customerOptional = customerRepository.findById(seq);
        Customer customer = customerOptional.get();
        customer.setCustomerToken(editCustomerTokenDto.getCustomerToken());
//        customer = customerRepository.saveAndFlush(customer);
        System.out.println(customer.getCustomerId());
        return CustomerDto.from(customer);


    }


    public CustomerDto deleteCustomer(Long seq) {
        Optional<Customer> customerOptional = customerRepository.findById(seq);
        Customer customer = customerOptional.get();
//        customer.setCustomerDelYn(true);
        customerRepository.delete(customer);
        return CustomerDto.from(customer);
    }
}
