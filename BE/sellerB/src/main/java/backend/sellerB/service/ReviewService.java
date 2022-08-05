package backend.sellerB.service;

import backend.sellerB.dto.ProductDto;
import backend.sellerB.dto.ReviewDto;
import backend.sellerB.entity.Product;
import backend.sellerB.entity.Review;
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

    public ReviewDto createReview(ReviewDto reviewDto) {
        Review review = Review.builder()
                .reviewSeq(reviewDto.getReviewSeq())
                .consulting(reviewDto.getConsulting())
                .reviewGrade(reviewDto.getReviewGrade())
                .reviewContent(reviewDto.getReviewContent())
                .reviewRegUserSeq(reviewDto.getReviewRegUserSeq())
                .reviewRegDate(reviewDto.getReviewRegDate())
                .reviewModUserSeq(reviewDto.getReviewModUserSeq())
                .reviewModDate(reviewDto.getReviewModDate())
                .build();

        return ReviewDto.from(reviewRepository.save(review));
    }

    public List<ReviewDto> getReviewListByConsultantId(String consultantId) {

        return ReviewDto.fromList(reviewRepository.findAll());}

    public List<ReviewDto> getReviewListByCustomerSeq(Long customerSeq) {
        return ReviewDto.fromList(reviewRepository.findAll());}

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
        review.setReviewRegUserSeq(reviewDto.getReviewRegUserSeq());
        review.setReviewRegDate(reviewDto.getReviewRegDate());
        review.setReviewModUserSeq(reviewDto.getReviewModUserSeq());
        review.setReviewModDate(reviewDto.getReviewModDate());
        return ReviewDto.from(review);
    }

    public ReviewDto deleteReview(Long seq) {
        Optional<Review> reviewOptional = reviewRepository.findById(seq);
        Review review = reviewOptional.get();
        review.setReviewDelYn(true);
        return ReviewDto.from(review);
    }
}
