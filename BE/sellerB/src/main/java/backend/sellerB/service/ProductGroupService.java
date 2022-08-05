package backend.sellerB.service;

import backend.sellerB.dto.ProductDto;
import backend.sellerB.dto.ProductGroupDto;
import backend.sellerB.dto.ProductGroupRes;
import backend.sellerB.entity.Brand;
import backend.sellerB.entity.Product;
import backend.sellerB.entity.ProductGroup;
import backend.sellerB.repository.BrandRepository;
import backend.sellerB.repository.ProductGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductGroupService {
    private final ProductGroupRepository productGroupRepository;
    private final BrandRepository brandRepository;

    public ProductGroupDto create(ProductGroupDto productGroupDto) {
        Optional<Brand> brandOptional = brandRepository.findByBrandNameKor(productGroupDto.getBrandName());
        Brand brand = brandOptional.get();
        ProductGroup productGroup = ProductGroup.builder()
                .brand(brand)
                .productGroupCode(productGroupDto.getProductGroupCode())
                .productGroupName(productGroupDto.getProductGroupName())
                .build();

        return ProductGroupDto.from(productGroupRepository.save(productGroup));
    }

    public List<ProductGroupRes> getProductGroupList() { return ProductGroupRes.fromList(productGroupRepository.findAll());}

    public ProductGroupRes getProductGroupDetail(Long seq) {
        Optional<ProductGroup> productGroupOptional = productGroupRepository.findById(seq);
        ProductGroup productGroup = productGroupOptional.get();
        return ProductGroupRes.from(productGroup);
    }

    public ProductGroupDto update(Long seq, ProductGroupDto productGroupDto) {
        Optional<Brand> brandOptional = brandRepository.findByBrandNameKor(productGroupDto.getBrandName());
        Brand brand = brandOptional.get();
        Optional<ProductGroup> productGroupOptional = productGroupRepository.findById(seq);
        ProductGroup productGroup = productGroupOptional.get();
        productGroup.setBrand(brand);
        productGroup.setProductGroupCode(productGroupDto.getProductGroupCode());
        productGroup.setProductGroupName(productGroupDto.getProductGroupName());
        return ProductGroupDto.from(productGroup);
    }

    public ProductGroupDto deleteProductGroup(Long seq) {
        Optional<ProductGroup> productGroupOptional = productGroupRepository.findById(seq);
        ProductGroup productGroup = productGroupOptional.get();
        productGroup.setProductGroupDelYn(true);
//        productGroupRepository.deleteById(seq);
        return ProductGroupDto.from(productGroup);
    }
}
