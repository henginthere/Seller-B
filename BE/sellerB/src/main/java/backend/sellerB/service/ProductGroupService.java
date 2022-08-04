package backend.sellerB.service;

import backend.sellerB.dto.ProductDto;
import backend.sellerB.dto.ProductGroupDto;
import backend.sellerB.entity.Product;
import backend.sellerB.entity.ProductGroup;
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

    public ProductGroupDto create(ProductGroupDto productGroupDto) {
        ProductGroup productGroup = ProductGroup.builder()
                .brand(productGroupDto.getBrand())
                .productGroupCode(productGroupDto.getProductGroupCode())
                .productGroupName(productGroupDto.getProductGroupName())
                .build();

        return ProductGroupDto.from(productGroupRepository.save(productGroup));
    }

    public List<ProductGroupDto> getProductGroupList() { return ProductGroupDto.fromList(productGroupRepository.findAll());}

    public ProductGroupDto getProductGroupDetail(Long seq) {
        Optional<ProductGroup> productGroupOptional = productGroupRepository.findById(seq);
        ProductGroup productGroup = productGroupOptional.get();
        return ProductGroupDto.from(productGroup);
    }

    public ProductGroupDto update(Long seq, ProductGroupDto productGroupDto) {
        Optional<ProductGroup> productGroupOptional = productGroupRepository.findById(seq);
        ProductGroup productGroup = productGroupOptional.get();
        productGroup.setBrand(productGroupDto.getBrand());
        productGroup.setProductGroupCode(productGroupDto.getProductGroupCode());
        productGroup.setProductGroupName(productGroupDto.getProductGroupName());
        return ProductGroupDto.from(productGroup);
    }

    public ProductGroupDto deleteProductGroup(Long seq) {
        Optional<ProductGroup> productGroupOptional = productGroupRepository.findById(seq);
        ProductGroup productGroup = productGroupOptional.get();
        productGroup.setProductGroupDelYn(true);
        return ProductGroupDto.from(productGroup);
    }
}
