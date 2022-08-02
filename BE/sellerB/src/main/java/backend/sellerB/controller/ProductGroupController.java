package backend.sellerB.controller;

import backend.sellerB.dto.ProductGroupDto;
import backend.sellerB.service.ProductGroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product-group")
public class ProductGroupController {
    private final ProductGroupService productGroupService;

    public ProductGroupController(ProductGroupService productGroupService) {
        this.productGroupService = productGroupService;
    }

    @PostMapping
    public ResponseEntity<ProductGroupDto> saveProductGroup(@Valid @RequestBody ProductGroupDto productGroupDto) {
        return ResponseEntity.ok(productGroupService.create(productGroupDto));
    }

    @GetMapping("/{seq}")
    public ResponseEntity<ProductGroupDto> getProductGroupDetail(@PathVariable Integer seq) {
        return ResponseEntity.ok(productGroupService.getProductGroupDetail(seq));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductGroupDto>> getProductGroupList(HttpServletRequest request) {
        return ResponseEntity.ok(productGroupService.getProductGroupList());
    }

    @PutMapping("/{seq}")
    public ResponseEntity<ProductGroupDto> updateProductGroup(@Valid @RequestBody ProductGroupDto productGroupDto, @PathVariable Integer seq) {
        return ResponseEntity.ok(productGroupService.update(seq, productGroupDto));
    }

    @DeleteMapping("/{seq}")
    public ResponseEntity<ProductGroupDto> deleteProductGroup(@PathVariable Integer seq) {
        // Access the DB and delete the order
        return ResponseEntity.ok(productGroupService.deleteProductGroup(seq));
    }
}
