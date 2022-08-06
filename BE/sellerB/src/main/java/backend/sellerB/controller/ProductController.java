package backend.sellerB.controller;

import backend.sellerB.dto.NoticeDto;
import backend.sellerB.dto.ProductDto;
import backend.sellerB.dto.ProductRes;
import backend.sellerB.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductDto> saveProduct(@Valid @RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.create(productDto));
    }

    @GetMapping("/{seq}")
    public ResponseEntity<ProductRes> getProductDetail(@PathVariable Long seq) {
        return ResponseEntity.ok(productService.getProductDetail(seq));
    }
    @GetMapping("/name/{product-name}")
    public ResponseEntity<List<ProductRes>> getProductListByName(HttpServletRequest request, @PathVariable("product-name") String productName) throws UnsupportedEncodingException {
        String koreanProductName = URLDecoder.decode(productName, "UTF-8");
        return ResponseEntity.ok(productService.getProductListByName(koreanProductName));
    }
    @GetMapping("/id/{product-id}")
    public ResponseEntity<ProductRes> getProductDetailById(@PathVariable String productId) {
        return ResponseEntity.ok(productService.getProductDetailById(productId));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductRes>> getProductList(HttpServletRequest request) {
        return ResponseEntity.ok(productService.getProductList());
    }

    @GetMapping("/list/{product-group-name}")
    public ResponseEntity<List<ProductRes>> getProductListByProductGroupName(HttpServletRequest request, @PathVariable("product-group-name") String productGroupName) throws UnsupportedEncodingException {
        String koreanProductGroupName = URLDecoder.decode(productGroupName, "UTF-8");
        return ResponseEntity.ok(productService.getProductListByGroupName(koreanProductGroupName));
    }

    @PutMapping("/{seq}")
    public ResponseEntity<ProductDto> updateProduct(@Valid @RequestBody ProductDto productDto, @PathVariable Long seq) {
        return ResponseEntity.ok(productService.update(seq, productDto));
    }

    @DeleteMapping("/{seq}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable Long seq) {
        return ResponseEntity.ok(productService.deleteProduct(seq));
    }
}
