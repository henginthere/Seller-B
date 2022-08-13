package backend.sellerB.dto;

import backend.sellerB.entity.Consulting;
import backend.sellerB.entity.Product;
import backend.sellerB.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ReviewDto {

    private Long reviewSeq;
    private Consulting consulting;
    private Float reviewGrade;
    private String reviewContent;
    private String reviewRegUser;
    private LocalDateTime reviewRegDate;
    private String reviewModUser;
    private LocalDateTime reviewModDate;

    public static ReviewDto from(Review review) {
        if(review == null) return null;
        return ReviewDto.builder()
                .reviewSeq(review.getReviewSeq())
                .consulting(review.getConsulting())
                .reviewGrade(review.getReviewGrade())
                .reviewContent(review.getReviewContent())
                .reviewRegUser(review.getReviewRegUser())
                .reviewRegDate(review.getReviewRegDate())
                .reviewModUser(review.getReviewModUser())
                .reviewModDate(review.getReviewModDate())
                .build();
    }


    public static ArrayList<ReviewDto> fromList(List<Review> reviewList) {
        ArrayList<ReviewDto> listReviewDto = new ArrayList<>();
        int i = 0;
        while (i < reviewList.size()) {
            ReviewDto reviewDto = ReviewDto.builder()
                    .reviewSeq(reviewList.get(i).getReviewSeq())
                    .consulting(reviewList.get(i).getConsulting())
                    .reviewGrade(reviewList.get(i).getReviewGrade())
                    .reviewContent(reviewList.get(i).getReviewContent())
                    .reviewRegUser(reviewList.get(i).getReviewRegUser())
                    .reviewRegDate(reviewList.get(i).getReviewRegDate())
                    .reviewModUser(reviewList.get(i).getReviewModUser())
                    .reviewModDate(reviewList.get(i).getReviewModDate())
                    .build();
            listReviewDto.add(reviewDto);
            i++;
        }
        return listReviewDto;
    }
}
