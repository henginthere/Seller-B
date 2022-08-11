package backend.sellerB.service;

import backend.sellerB.dto.EditProductDto;
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
        Optional<ProductGroup> productGroupOptional = productGroupRepository.findById(productDto.getProductGroupSeq());
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

    public List<ProductDto> getProductList() { return ProductDto.fromList(productRepository.findAll());}
    public List<ProductDto> getProductListByBrand(Long seq) {
        Optional<List<Product>> optionalProductList = productRepository.findByProductGroup_Brand_BrandSeq(seq);
        List<Product> productList = optionalProductList.get();
        return ProductDto.fromList(productList);}
    public List<ProductDto> getProductListByGroupSeq(Long productGroupSeq) {
        Optional<List<Product>> optionalProductResList = productRepository.findProductsByProductGroup_ProductGroupSeq(productGroupSeq);
        List<Product> productList = optionalProductResList.get();
        return ProductDto.fromList(productList);}

    public ProductDto getProductDetail(Long seq) {
        Optional<Product> productOptional = productRepository.findById(seq);
        Product product = productOptional.get();
        return ProductDto.from(product);
    }

    public List<ProductDto> getProductListByName(String name) {
        Optional<List<Product>> optionalProductResList = productRepository.findByProductNameContaining(name);
        List<Product> productList = optionalProductResList.get();
        return ProductDto.fromList(productList);
    }

    public ProductDto getProductDetailById(String productId) {
        Optional<Product> productOptional = productRepository.findByProductId(productId);
        Product product = productOptional.get();
        return ProductDto.from(product);
    }
    public ProductDto update(Long seq, EditProductDto editProductDto) {
        Optional<Product> productOptional = productRepository.findById(seq);
        Product product = productOptional.get();
        ProductGroup productGroup = product.getProductGroup();
        product.setProductSeq(editProductDto.getProductSeq());
        product.setProductName(editProductDto.getProductName());
        product.setProductGroup(productGroup);
        product.setProductPrice(editProductDto.getProductPrice());
        product.setProductManual(editProductDto.getProductManual());
        product.setProductThumbnail(editProductDto.getProductThumbnail());
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
