package backend.sellerB.service;

import backend.sellerB.dto.NoticeDto;
import backend.sellerB.dto.NoticeReq;
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
    public WaitingCustomerDto create(WaitingCustomerDto waitingCustomerDto) {
        Optional<Customer> customerOptional = customerRepository.findById(waitingCustomerDto.getCustomerSeq());
        Customer customer = customerOptional.get();
        Optional<Product> productOptional = productRepository.findById(waitingCustomerDto.getProductSeq());
        Product product = productOptional.get();
        Optional<ProductGroup> productGroupOptional = productGroupRepository.findByProductGroupName(waitingCustomerDto.getProductGroupName());
        ProductGroup productGroup = productGroupOptional.get();

        //dto를 엔티티로
        WaitingCustomer waitingCustomer = WaitingCustomer.builder()
                .customer(customer)
                .product(product)
                .waitingCustomerState(waitingCustomerDto.getWaitingCustomerState())
                .productGroup(productGroup)
                .build();

        return WaitingCustomerDto.from(waitingCustomerRepository.save(waitingCustomer));

    }

    public List<WaitingCustomerDto> getWaitingCustomersByProductGroup_ProductGroupName(String productGroupName) {
        return WaitingCustomerDto.fromList(waitingCustomerRepository.findWaitingCustomersByProductGroup_ProductGroupName(productGroupName));
    }

    public WaitingCustomerDto getWaitingCustomerDetail(Long seq) {
        Optional<WaitingCustomer> waitingCustomerOptional = waitingCustomerRepository.findById(seq);
        WaitingCustomer waitingCustomer = waitingCustomerOptional.get();
        return WaitingCustomerDto.from(waitingCustomer);
    }


//      상담이 잡히면 상태 변경이 아니라 그냥 삭제하면 될듯
//    public NoticeReq updateWaitingCustomer(Long seq, NoticeReq noticeReq) {
//        Optional<Notice> noticeOptional = noticeRepository.findById(seq);
//        Notice notice = noticeOptional.get();
//        notice.setNoticeTitle(noticeReq.getNoticeTitle());
//        notice.setNoticeContent(noticeReq.getNoticeContent());
//        return NoticeReq.from(notice);
//    }

    public WaitingCustomerDto deleteWaitingCustomer(Long seq) {
        Optional<WaitingCustomer> waitingCustomerOptional = waitingCustomerRepository.findById(seq);
        WaitingCustomer waitingCustomer = waitingCustomerOptional.get();
        waitingCustomer.setWatingCustomerGroupDelYn(true);
        return WaitingCustomerDto.from(waitingCustomer);
    }

}
