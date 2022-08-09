package backend.sellerB.dto;

import backend.sellerB.entity.Notice;
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
public class ProductDto {
    private Long productSeq;
    private String productGroupName;
    private Long productGroupSeq;
    private String productId;
    private String productName;
    private Integer productPrice;
    private String productManual;
    private String productThumbnail;


    public static ProductDto from(Product product) {
        if(product == null) return null;
        return ProductDto.builder()
                .productSeq(product.getProductSeq())
                .productGroupName(product.getProductGroup().getProductGroupName())
                .productGroupSeq(product.getProductGroup().getProductGroupSeq())
                .productId(product.getProductId())
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .productManual(product.getProductManual())
                .productThumbnail(product.getProductThumbnail())
                .build();
    }


    public static ArrayList<ProductDto> fromList(List<Product> productList) {
        ArrayList<ProductDto> listProductDto = new ArrayList<>();
        int i = 0;
        while (i < productList.size()) {
            ProductDto productDto = ProductDto.builder()
                    .productSeq(productList.get(i).getProductSeq())
                    .productGroupName(productList.get(i).getProductGroup().getProductGroupName())
                    .productGroupSeq(productList.get(i).getProductGroup().getProductGroupSeq())
                    .productId(productList.get(i).getProductId())
                    .productName(productList.get(i).getProductName())
                    .productPrice(productList.get(i).getProductPrice())
                    .productManual(productList.get(i).getProductManual())
                    .productThumbnail(productList.get(i).getProductThumbnail())
                    .build();
            listProductDto.add(productDto);
            i++;
        }
        return listProductDto;
    }
}
