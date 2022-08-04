//package backend.sellerB.service;
//
//import backend.sellerB.dto.ProductDto;
//import backend.sellerB.dto.ReviewDto;
//import backend.sellerB.entity.Product;
//import backend.sellerB.entity.Review;
//import backend.sellerB.repository.ReviewRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//public class ReviewService {
//    private final ReviewRepository reviewRepository;
//
//    public ReviewDto create(ReviewDto reviewDto) {
//        Review review = Review.builder()
//                .reviewSeq(reviewDto.getReviewSeq())
//                .consulting(reviewDto.getConsulting())
//                .reviewGrade(reviewDto.getReviewGrade())
//                .reviewContent(reviewDto.getReviewContent())
//                .reviewRegUserSeq(reviewDto.getReviewRegUserSeq())
//                .reviewRegDate(reviewDto.getReviewRegDate())
//                .reviewModUserSeq(reviewDto.getReviewModUserSeq())
//                .reviewModDate(reviewDto.getReviewModDate())
//                .build();
//
//        return ReviewDto.from(reviewRepository.save(review));
//    }
//
//    public List<ProductDto> getProductList() { return ProductDto.fromList(productRepository.findAll());}
//
//    public ProductDto getProductDetail(Integer seq) {
//        Optional<Product> productOptional = productRepository.findById(seq);
//        Product product = productOptional.get();
//        return ProductDto.from(product);
//    }
//
//    public ProductDto update(Integer seq, ProductDto productDto) {
//        Optional<Product> productOptional = productRepository.findById(seq);
//        Product product = productOptional.get();
//        product.setProductName(productDto.getProductName());
//        product.setProductGroup(productDto.getProductGroup());
//        product.setProductPrice(productDto.getProductPrice());
//        product.setProductManual(productDto.getProductManual());
//        product.setProductThumbnail(productDto.getProductThumbnail());
//        return ProductDto.from(product);
//    }
//
//    public ProductDto deleteProduct(Integer seq) {
//        Optional<Product> productOptional = productRepository.findById(seq);
//        Product product = productOptional.get();
//        product.setProductDelYn("Y");
//        return ProductDto.from(product);
//    }
//}
