package backend.sellerB.dto;

import backend.sellerB.entity.Brand;
import backend.sellerB.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class BrandDto {

    private String brandNameKor;
    private String brandNameEng;
    private String brandLogo;

    public static BrandDto from(Brand brand) {
        if(brand == null) return null;
        return BrandDto.builder()
                .brandNameKor(brand.getBrandNameKor())
                .brandNameEng(brand.getBrandNameEng())
                .brandLogo(brand.getBrandLogo())
                .build();
    }

    public static ArrayList<BrandDto> fromList(List<Brand> brandList) {
        ArrayList<BrandDto> listBrandDto = new ArrayList<>();
        int i = 0;
        while (i < brandList.size()) {
            BrandDto brandDto = BrandDto.builder()
                    .brandNameKor(brandList.get(i).getBrandNameKor())
                    .brandNameEng(brandList.get(i).getBrandNameEng())
                    .brandLogo(brandList.get(i).getBrandLogo())
                    .build();
            listBrandDto.add(brandDto);
            i++;
        }
        return listBrandDto;
    }
}
