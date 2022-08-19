package backend.sellerB.dto;

import backend.sellerB.entity.Brand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ResponseBrandDto {
    private Long brandSeq;
    private String brandNameKor;
    private String brandNameEng;
    private String brandLogo;

    public static ResponseBrandDto from(Brand brand) {
        if(brand == null) return null;
        return ResponseBrandDto.builder()
                .brandSeq(brand.getBrandSeq())
                .brandNameKor(brand.getBrandNameKor())
                .brandNameEng(brand.getBrandNameEng())
                .brandLogo(brand.getBrandLogo())
                .build();
    }

    public static ArrayList<ResponseBrandDto> fromList(List<Brand> brandList) {
        ArrayList<ResponseBrandDto> listResponseBrandDto = new ArrayList<>();
        int i = 0;
        while (i < brandList.size()) {
            ResponseBrandDto responseBrandDto = ResponseBrandDto.builder()
                    .brandSeq(brandList.get(i).getBrandSeq())
                    .brandNameKor(brandList.get(i).getBrandNameKor())
                    .brandNameEng(brandList.get(i).getBrandNameEng())
                    .brandLogo(brandList.get(i).getBrandLogo())
                    .build();
            listResponseBrandDto.add(responseBrandDto);
            i++;
        }
        return listResponseBrandDto;
    }
}
