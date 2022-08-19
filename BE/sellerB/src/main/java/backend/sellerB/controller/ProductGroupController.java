package backend.sellerB.controller;

import backend.sellerB.dto.ProductGroupDto;
import backend.sellerB.dto.ProductGroupRes;
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
    public ResponseEntity<ProductGroupRes> getProductGroupDetail(@PathVariable Long seq) {
        return ResponseEntity.ok(productGroupService.getProductGroupDetail(seq));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductGroupRes>> getProductGroupList(HttpServletRequest request) {
        return ResponseEntity.ok(productGroupService.getProductGroupList());
    }

    @PutMapping("/{seq}")
    public ResponseEntity<ProductGroupDto> updateProductGroup(@Valid @RequestBody ProductGroupDto productGroupDto, @PathVariable Long seq) {
        return ResponseEntity.ok(productGroupService.update(seq, productGroupDto));
    }

    @DeleteMapping("/{seq}")
    public ResponseEntity<ProductGroupDto> deleteProductGroup(@PathVariable Long seq) {
        return ResponseEntity.ok(productGroupService.deleteProductGroup(seq));
    }
}
