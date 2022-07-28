package backend.sellerB.controller;

import backend.sellerB.dto.BrandDto;
import backend.sellerB.service.BrandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping
    public ResponseEntity<BrandDto> saveProduct(@Valid @RequestBody BrandDto brandDto) {
        return ResponseEntity.ok(brandService.create(brandDto));
    }

    @GetMapping("/{seq}")
    public ResponseEntity<BrandDto> getProductDetail(@PathVariable Integer seq) {
        return ResponseEntity.ok(brandService.getBrandDetail(seq));
    }

    @GetMapping("/list")
    public ResponseEntity<List<BrandDto>> getProductList(HttpServletRequest request) {
        return ResponseEntity.ok(brandService.getBrandList());
    }

    @PutMapping("/{seq}")
    public ResponseEntity<BrandDto> updateProduct(@Valid @RequestBody BrandDto brandDto, @PathVariable Integer seq) {
        return ResponseEntity.ok(brandService.update(seq, brandDto));
    }

    @DeleteMapping("/{seq}")
    public ResponseEntity<BrandDto> deleteProduct(@PathVariable Integer seq) {
        // Access the DB and delete the order
        return ResponseEntity.ok(brandService.deleteBrand(seq));
    }
}
