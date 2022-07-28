package backend.sellerB.service;

import backend.sellerB.dto.BrandDto;
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

    public List<BrandDto> getBrandList() {
        return BrandDto.fromList(brandRepository.findAll());
    }

    public BrandDto getBrandDetail(Integer seq) {
        Optional<Brand> brandOptional = brandRepository.findById(seq);
        Brand brand = brandOptional.get();
        return BrandDto.from(brand);
    }

    public BrandDto update(Integer seq, BrandDto brandDto) {
        Optional<Brand> brandOptional = brandRepository.findById(seq);
        Brand brand = brandOptional.get();
        brand.setBrandNameKor(brandDto.getBrandNameKor());
        brand.setBrandNameEng(brandDto.getBrandNameEng());
        brand.setBrandLogo(brandDto.getBrandLogo());
        return BrandDto.from(brand);
    }

    public BrandDto deleteBrand(Integer seq) {
        Optional<Brand> brandOptional = brandRepository.findById(seq);
        Brand brand = brandOptional.get();
        brand.setBrandDelYn("Y");
        return BrandDto.from(brand);
    }
}
