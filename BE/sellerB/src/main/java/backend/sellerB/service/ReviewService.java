package backend.sellerB.service;

import backend.sellerB.dto.ProductDto;
import backend.sellerB.dto.ReviewDto;
import backend.sellerB.dto.SaveReviewDto;
import backend.sellerB.entity.Product;
import backend.sellerB.entity.Review;
import backend.sellerB.repository.ConsultingRepository;
import backend.sellerB.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ConsultingRepository consultingRepository;

    public ReviewDto createReview(SaveReviewDto saveReviewDto) {
        Review review = Review.builder()
                .consulting(consultingRepository.findById(saveReviewDto.getConsultingSeq()).get())
                .reviewGrade(saveReviewDto.getReviewGrade())
                .reviewContent(saveReviewDto.getReviewContent())
                .build();

        return ReviewDto.from(reviewRepository.save(review));
    }

    public List<ReviewDto> getReviewListByConsultantSeq(Long consultantSeq) {
        Optional<List<Review>> optionalReviewList = reviewRepository.findByConsulting_Consultant_ConsultantSeq(consultantSeq);
        List<Review> reviewList = optionalReviewList.get();
        return ReviewDto.fromList(reviewList);
    }

    public List<ReviewDto> getReviewListByCustomerSeq(Long customerSeq) {
        Optional<List<Review>> optionalReviewList = reviewRepository.findByConsulting_Customer_CustomerSeq(customerSeq);
        List<Review> reviewList = optionalReviewList.get();
        return ReviewDto.fromList(reviewList);
    }

    public ReviewDto getReviewDetail(Long seq) {
        Optional<Review> reviewOptional = reviewRepository.findById(seq);
        Review review = reviewOptional.get();
        return ReviewDto.from(review);
    }

    public ReviewDto updateReview(Long seq, ReviewDto reviewDto) {
        Optional<Review> reviewOptional = reviewRepository.findById(seq);
        Review review = reviewOptional.get();
        review.setReviewSeq(reviewDto.getReviewSeq());
        review.setConsulting(reviewDto.getConsulting());
        review.setReviewGrade(reviewDto.getReviewGrade());
        review.setReviewContent(reviewDto.getReviewContent());
        review.setReviewRegUser(reviewDto.getReviewRegUser());
        review.setReviewRegDate(reviewDto.getReviewRegDate());
        review.setReviewModUser(reviewDto.getReviewModUser());
        review.setReviewModDate(reviewDto.getReviewModDate());
        return ReviewDto.from(review);
    }

    public ReviewDto deleteReview(Long seq) {
        Optional<Review> reviewOptional = reviewRepository.findById(seq);
        Review review = reviewOptional.get();
        reviewRepository.deleteById(seq);
        return ReviewDto.from(review);
    }
}
