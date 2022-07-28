package backend.sellerB.dto;

import backend.sellerB.entity.Brand;
import backend.sellerB.entity.Product;
import backend.sellerB.entity.ProductGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ProductGroupDto {
    private Brand brand;
    private String productGroupCode;
    private String productGroupName;

    public static ProductGroupDto from(ProductGroup productGroup) {
        if(productGroup == null) return null;
        return ProductGroupDto.builder()
                .brand(productGroup.getBrand())
                .productGroupCode(productGroup.getProductGroupCode())
                .productGroupName(productGroup.getProductGroupName())
                .build();
    }

    public static ArrayList<ProductGroupDto> fromList(List<ProductGroup> productGroupList) {
        ArrayList<ProductGroupDto> listProductGroupDto = new ArrayList<>();
        int i = 0;
        while (i < productGroupList.size()) {
            ProductGroupDto productGroupDto = ProductGroupDto.builder()
                    .brand(productGroupList.get(i).getBrand())
                    .productGroupCode(productGroupList.get(i).getProductGroupCode())
                    .productGroupName(productGroupList.get(i).getProductGroupName())
                    .build();
            listProductGroupDto.add(productGroupDto);
            i++;
        }
        return listProductGroupDto;
    }
}
