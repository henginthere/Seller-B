package backend.sellerB.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SaveReviewDto {

    private Long consultingSeq;
    private Float reviewGrade;
    private String reviewContent;

}
