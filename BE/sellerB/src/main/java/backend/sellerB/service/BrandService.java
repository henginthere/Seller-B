package backend.sellerB.service;

import backend.sellerB.dto.BrandDto;
import backend.sellerB.dto.ResponseBrandDto;
import backend.sellerB.entity.Brand;
import backend.sellerB.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;

    public BrandDto create(BrandDto brandDto) {
        Brand brand = Brand.builder()
                .brandNameKor(brandDto.getBrandNameKor())
                .brandNameEng(brandDto.getBrandNameEng())
                .brandLogo(brandDto.getBrandLogo())
                .build();
        return BrandDto.from(brandRepository.save(brand));
    }

    public List<ResponseBrandDto> getBrandList() {
        return ResponseBrandDto.fromList(brandRepository.findAll());
    }

    public ResponseBrandDto getBrandDetail(Long seq) {
        Optional<Brand> brandOptional = brandRepository.findById(seq);
        Brand brand = brandOptional.get();
        return ResponseBrandDto.from(brand);
    }

    public BrandDto update(Long seq, BrandDto brandDto) {
        Optional<Brand> brandOptional = brandRepository.findById(seq);
        Brand brand = brandOptional.get();
        brand.setBrandNameKor(brandDto.getBrandNameKor());
        brand.setBrandNameEng(brandDto.getBrandNameEng());
        brand.setBrandLogo(brandDto.getBrandLogo());
        return BrandDto.from(brand);
    }

    public BrandDto deleteBrand(Long seq) {
        Optional<Brand> brandOptional = brandRepository.findById(seq);
        Brand brand = brandOptional.get();
        brand.setBrandDelYn(true);
        return BrandDto.from(brand);
    }
}
