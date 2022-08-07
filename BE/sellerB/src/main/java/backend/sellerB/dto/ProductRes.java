package backend.sellerB.dto;

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
public class ProductRes {
    private Long productSeq;
    private String productId;
    private String productName;
    private Integer productPrice;
    private String productManual;
    private ProductGroup productGroup;
    private String productThumbnail;

    public static ProductRes from(Product product) {
        if(product == null) return null;
        return ProductRes.builder()
                .productSeq(product.getProductSeq())
                .productId(product.getProductId())
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .productManual(product.getProductManual())
                .productGroup(product.getProductGroup())
                .productThumbnail(product.getProductThumbnail())
                .build();
    }


    public static ArrayList<ProductRes> fromList(List<Product> productList) {
        ArrayList<ProductRes> listProductRes = new ArrayList<>();
        int i = 0;
        while (i < productList.size()) {
            ProductRes productRes = ProductRes.builder()
                    .productSeq(productList.get(i).getProductSeq())
                    .productId(productList.get(i).getProductId())
                    .productName(productList.get(i).getProductName())
                    .productPrice(productList.get(i).getProductPrice())
                    .productManual(productList.get(i).getProductManual())
                    .productGroup(productList.get(i).getProductGroup())
                    .productThumbnail(productList.get(i).getProductThumbnail())
                    .build();
            listProductRes.add(productRes);
            i++;
        }
        return listProductRes;
    }
}
