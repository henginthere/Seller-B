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
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.core.util.PasswordDecryptor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PreUpdate;
import java.io.IOException;
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

    public LoginResponseDto googleLogin(GoogleLoginDto googleLoginDto) throws GeneralSecurityException, IOException {
        HttpTransport transport = new NetHttpTransport();
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                // Specify the CLIENT_ID of the app that accesses the backend:
                .setAudience(Collections.singletonList(CLIENT_ID))
                // Or, if multiple clients access the backend:
                //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
                .build();

// (Receive idTokenString by HTTPS POST)

        GoogleIdToken idToken = verifier.verify(googleLoginDto.getGoogleIdToken());
        if (idToken != null) {
            Payload payload = idToken.getPayload();

            // Print user identifier
            String userId = payload.getSubject();
            System.out.println("User ID: " + userId);

            // Get profile information from payload
            String email = payload.getEmail();
            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
            String name = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");
            String locale = (String) payload.get("locale");
            String familyName = (String) payload.get("family_name");
            String givenName = (String) payload.get("given_name");


            // Use or store profile information
            // ...
            Optional<Customer> optionalCustomer = customerRepository.findBycustomerId(userId);
            if(!optionalCustomer.isPresent()){
                //회원가입
                // 권한 정보를 만듦
                Authority authority = Authority.builder()
                        .authorityName("ROLE_CUSTOMER")
                        .build();

                authorityRepository.save(authority);

                Customer customer = Customer.builder()
                        .customerId(userId)
                        .customerPass(email)
                        .customerName(name)
                        .authorities(Collections.singleton(authority))
                        .build();

                customerRepository.save(customer);
            }
            LoginResponseDto loginResponseDto = authService.authorize(userId,email);

            return loginResponseDto;

        } else {
            System.out.println("Invalid ID token.");
            return null;
        }
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
