package backend.sellerB.service;

import backend.sellerB.dto.ProductDto;
import backend.sellerB.dto.ProductRes;
import backend.sellerB.entity.Product;
import backend.sellerB.entity.ProductGroup;
import backend.sellerB.repository.ProductGroupRepository;
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
    private final ProductGroupRepository productGroupRepository;

    public ProductDto create(ProductDto productDto) {
        Optional<ProductGroup> productGroupOptional = productGroupRepository.findByProductGroupName(productDto.getProductGroupName());
        ProductGroup productGroup = productGroupOptional.get();
        Product product = Product.builder()
                .productSeq(productDto.getProductSeq())
                .productGroup(productGroup)
                .productId(productDto.getProductId())
                .productName(productDto.getProductName())
                .productPrice(productDto.getProductPrice())
                .productManual(productDto.getProductManual())
                .productThumbnail(productDto.getProductId())
                .build();

        return ProductDto.from(productRepository.save(product));
    }

    public List<ProductRes> getProductList() { return ProductRes.fromList(productRepository.findAll());}
    public List<ProductRes> getProductListByGroupName(String groupName) {
        Optional<List<Product>> optionalProductResList = productRepository.findProductsByProductGroup_ProductGroupName(groupName);
        List<Product> productList = optionalProductResList.get();
        return ProductRes.fromList(productList);}

    public ProductRes getProductDetail(Long seq) {
        Optional<Product> productOptional = productRepository.findById(seq);
        Product product = productOptional.get();
        return ProductRes.from(product);
    }

    public List<ProductRes> getProductListByName(String name) {
        Optional<List<Product>> optionalProductResList = productRepository.findByProductNameContaining(name);
        List<Product> productList = optionalProductResList.get();
        return ProductRes.fromList(productList);
    }

    public ProductRes getProductDetailById(String productId) {
        Optional<Product> productOptional = productRepository.findByProductId(productId);
        Product product = productOptional.get();
        return ProductRes.from(product);
    }
    public ProductDto update(Long seq, ProductDto productDto) {
        Optional<Product> productOptional = productRepository.findById(seq);
        Product product = productOptional.get();
        Optional<ProductGroup> productGroupOptional = productGroupRepository.findByProductGroupName(productDto.getProductGroupName());
        ProductGroup productGroup = productGroupOptional.get();
        product.setProductSeq(productDto.getProductSeq());
        product.setProductName(productDto.getProductName());
        product.setProductGroup(productGroup);
        product.setProductPrice(productDto.getProductPrice());
        product.setProductManual(productDto.getProductManual());
        product.setProductThumbnail(productDto.getProductThumbnail());
        return ProductDto.from(product);
    }

    public ProductDto deleteProduct(Long seq) {
        Optional<Product> productOptional = productRepository.findById(seq);
        Product product = productOptional.get();
//        product.setProductDelYn(true);
        productRepository.deleteById(seq);
        return ProductDto.from(product);
    }
}
