package backend.sellerB.controller;

import backend.sellerB.dto.NoticeDto;
import backend.sellerB.dto.ProductDto;
import backend.sellerB.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
    public ResponseEntity<ProductDto> getProductDetail(@PathVariable Long seq) {
        return ResponseEntity.ok(productService.getProductDetail(seq));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductDto>> getProductList(HttpServletRequest request) {
        return ResponseEntity.ok(productService.getProductList());
    }

    @PutMapping("/{seq}")
    public ResponseEntity<ProductDto> updateProduct(@Valid @RequestBody ProductDto productDto, @PathVariable Long seq) {
        return ResponseEntity.ok(productService.update(seq, productDto));
    }

    @DeleteMapping("/{seq}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable Long seq) {
        // Access the DB and delete the order
        return ResponseEntity.ok(productService.deleteProduct(seq));
    }
}
