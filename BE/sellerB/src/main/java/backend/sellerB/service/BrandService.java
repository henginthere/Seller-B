package backend.sellerB.service;

import backend.sellerB.dto.BrandDto;
import backend.sellerB.dto.ResponseBrandDto;
import backend.sellerB.entity.Brand;
import backend.sellerB.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;
    private final AwsS3Service awsS3Service;

    public ResponseBrandDto create(BrandDto brandDto) throws IOException {
        Brand brand = Brand.builder()
                .brandNameKor(brandDto.getBrandNameKor())
                .brandNameEng(brandDto.getBrandNameEng())
                .brandLogo(brandDto.getBrandLogoUrl())
                .build();
        return ResponseBrandDto.from(brandRepository.save(brand));
    }

    public List<ResponseBrandDto> getBrandList() {
        return ResponseBrandDto.fromList(brandRepository.findAll());
    }

    public ResponseBrandDto getBrandDetail(Long seq) {
        Optional<Brand> brandOptional = brandRepository.findById(seq);
        Brand brand = brandOptional.get();
        return ResponseBrandDto.from(brand);
    }

    public ResponseBrandDto update(Long seq, BrandDto brandDto) throws IOException {
        Optional<Brand> brandOptional = brandRepository.findById(seq);
        Brand brand = brandOptional.get();
        brand.setBrandNameKor(brandDto.getBrandNameKor());
        brand.setBrandNameEng(brandDto.getBrandNameEng());
        brand.setBrandLogo(brand.getBrandLogo());
        return ResponseBrandDto.from(brand);
    }

    public BrandDto deleteBrand(Long seq) {
        Optional<Brand> brandOptional = brandRepository.findById(seq);
        Brand brand = brandOptional.get();
        brandRepository.deleteById(seq);
        return BrandDto.from(brand);
    }
}
