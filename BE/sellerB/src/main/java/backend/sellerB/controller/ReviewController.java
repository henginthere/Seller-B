package backend.sellerB.controller;

import backend.sellerB.dto.ProductDto;
import backend.sellerB.dto.ReviewDto;
import backend.sellerB.dto.SaveReviewDto;
import backend.sellerB.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<ReviewDto> saveReview(@Valid @RequestBody SaveReviewDto saveReviewDto) {
        return ResponseEntity.ok(reviewService.createReview(saveReviewDto));
    }

    @GetMapping("/{seq}")
    public ResponseEntity<ReviewDto> getReviewDetail(@PathVariable Long seq) {
        return ResponseEntity.ok(reviewService.getReviewDetail(seq));
    }

    //상담사별 후기 내역 리스트 조회
    @GetMapping("/list/consultant/{consultantSeq}")
    public ResponseEntity<List<ReviewDto>> getReviewListByConsultantSeq(HttpServletRequest request, @PathVariable Long consultantSeq) {
        return ResponseEntity.ok(reviewService.getReviewListByConsultantSeq(consultantSeq));
    }

    // 고객 seq로 후기 내역 찾기
    @GetMapping("/list/customer/{customerSeq}")
    public ResponseEntity<List<ReviewDto>> getReviewListByCustomerSeq(HttpServletRequest request, @PathVariable Long customerSeq) {
        return ResponseEntity.ok(reviewService.getReviewListByCustomerSeq(customerSeq));
    }

    @PutMapping("/{seq}")
    public ResponseEntity<ReviewDto> updateReview(@Valid @RequestBody ReviewDto reviewDto, @PathVariable Long seq) {
        return ResponseEntity.ok(reviewService.updateReview(seq, reviewDto));
    }

    @DeleteMapping("/{seq}")
    public ResponseEntity<ReviewDto> deleteReview(@PathVariable Long seq) {
        // Access the DB and delete the order
        return ResponseEntity.ok(reviewService.deleteReview(seq));
    }
}
