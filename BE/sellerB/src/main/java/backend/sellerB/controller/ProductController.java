package backend.sellerB.controller;

import backend.sellerB.dto.*;
import backend.sellerB.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
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
    public ResponseEntity<ProductDto> saveProduct(@Valid @ModelAttribute RegisterProductDto registerProductDto) throws IOException {
        return ResponseEntity.ok(productService.create(registerProductDto));
    }

    @GetMapping("/{seq}")
    public ResponseEntity<ProductDto> getProductDetail(@PathVariable Long seq) {
        return ResponseEntity.ok(productService.getProductDetail(seq));
    }
    @GetMapping("/name/{productName}")
    public ResponseEntity<List<ProductDto>> getProductListByName(HttpServletRequest request, @PathVariable String productName) throws UnsupportedEncodingException {
        String koreanProductName = URLDecoder.decode(productName, "UTF-8");
        return ResponseEntity.ok(productService.getProductListByName(koreanProductName));
    }
    @GetMapping("/id/{productId}")
    public ResponseEntity<ProductDto> getProductDetailById(@PathVariable String productId) {
        return ResponseEntity.ok(productService.getProductDetailById(productId));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductDto>> getProductList(HttpServletRequest request) {
        return ResponseEntity.ok(productService.getProductList());
    }
    @GetMapping("/list/brand/{seq}")
    public ResponseEntity<List<ProductDto>> getProductListByBrand(@PathVariable Long seq) {
        return ResponseEntity.ok(productService.getProductListByBrand(seq));
    }

    @GetMapping("/list/{productGroupSeq}")
    public ResponseEntity<List<ProductDto>> getProductListByProductGroupSeq(HttpServletRequest request, @PathVariable Long productGroupSeq) {
        return ResponseEntity.ok(productService.getProductListByGroupSeq(productGroupSeq));
    }

    @PutMapping("/{seq}")
    public ResponseEntity<ProductDto> updateProduct(@Valid @ModelAttribute EditProductDto editProductDto, @PathVariable Long seq) throws IOException {
        return ResponseEntity.ok(productService.update(seq, editProductDto));
    }

    @DeleteMapping("/{seq}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable Long seq) {
        return ResponseEntity.ok(productService.deleteProduct(seq));
    }
}
