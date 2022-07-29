package backend.sellerB.service;

import backend.sellerB.dto.ProductDto;
import backend.sellerB.entity.Product;
import backend.sellerB.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ProductDto create(ProductDto productDto) {
        Product product = Product.builder()
                .productGroup(productDto.getProductGroup())
                .productId(productDto.getProductId())
                .productName(productDto.getProductName())
                .productPrice(productDto.getProductPrice())
                .productManual(productDto.getProductManual())
                .productThumbnail(productDto.getProductId())
                .build();

        return ProductDto.from(productRepository.save(product));
    }

    public List<ProductDto> getProductList() { return ProductDto.fromList(productRepository.findAll());}

    public ProductDto getProductDetail(Integer seq) {
        Optional<Product> productOptional = productRepository.findById(seq);
        Product product = productOptional.get();
        return ProductDto.from(product);
    }

    public ProductDto update(Integer seq, ProductDto productDto) {
        Optional<Product> productOptional = productRepository.findById(seq);
        Product product = productOptional.get();
        product.setProductName(productDto.getProductName());
        product.setProductGroup(productDto.getProductGroup());
        product.setProductPrice(productDto.getProductPrice());
        product.setProductManual(productDto.getProductManual());
        product.setProductThumbnail(productDto.getProductThumbnail());
        return ProductDto.from(product);
    }

    public ProductDto deleteProduct(Integer seq) {
        Optional<Product> productOptional = productRepository.findById(seq);
        Product product = productOptional.get();
        product.setProductDelYn("Y");
        return ProductDto.from(product);
    }
}
