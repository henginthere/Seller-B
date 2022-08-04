package backend.sellerB.dto;

import backend.sellerB.entity.ProductGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ProductGroupRes {
    private Long productGroupSeq;
    private String brandName;
    private String productGroupCode;
    private String productGroupName;

    public static ProductGroupRes from(ProductGroup productGroup) {
        if(productGroup == null) return null;
        return ProductGroupRes.builder()
                .productGroupSeq(productGroup.getProductGroupSeq())
                .brandName(productGroup.getBrand().getBrandNameKor())
                .productGroupCode(productGroup.getProductGroupCode())
                .productGroupName(productGroup.getProductGroupName())
                .build();
    }

    public static ArrayList<ProductGroupRes> fromList(List<ProductGroup> productGroupList) {
        ArrayList<ProductGroupRes> listProductGroupRes = new ArrayList<>();
        int i = 0;
        while (i < productGroupList.size()) {
            ProductGroupRes productGroupRes = ProductGroupRes.builder()
                    .productGroupSeq(productGroupList.get(i).getProductGroupSeq())
                    .brandName(productGroupList.get(i).getBrand().getBrandNameKor())
                    .productGroupCode(productGroupList.get(i).getProductGroupCode())
                    .productGroupName(productGroupList.get(i).getProductGroupName())
                    .build();
            listProductGroupRes.add(productGroupRes);
            i++;
        }
        return listProductGroupRes;
    }
}
