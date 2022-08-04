package backend.sellerB.dto;

import backend.sellerB.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ProductRes {
    private String productId;
    private String productName;
    private Integer productPrice;
    private String productManual;

    public static ProductRes from(Product product) {
        if(product == null) return null;
        return ProductRes.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .productManual(product.getProductManual())
                .build();
    }


    public static ArrayList<ProductRes> fromList(List<Product> productList) {
        ArrayList<ProductRes> listProductRes = new ArrayList<>();
        int i = 0;
        while (i < productList.size()) {
            ProductRes productRes = ProductRes.builder()
                    .productId(productList.get(i).getProductId())
                    .productName(productList.get(i).getProductName())
                    .productPrice(productList.get(i).getProductPrice())
                    .productManual(productList.get(i).getProductManual())
                    .build();
            listProductRes.add(productRes);
            i++;
        }
        return listProductRes;
    }
}
