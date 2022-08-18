package backend.sellerB.controller;

import backend.sellerB.dto.BrandDto;
import backend.sellerB.dto.ResponseBrandDto;
import backend.sellerB.service.BrandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping
    public ResponseEntity<ResponseBrandDto> saveBrand(@Valid @RequestBody BrandDto brandDto) throws IOException {
        return ResponseEntity.ok(brandService.create(brandDto));
    }

    @GetMapping("/{seq}")
    public ResponseEntity<ResponseBrandDto> getBrandDetail(@PathVariable Long seq) {
        return ResponseEntity.ok(brandService.getBrandDetail(seq));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ResponseBrandDto>> getBrandList(HttpServletRequest request) {
        return ResponseEntity.ok(brandService.getBrandList());
    }

    @PutMapping("/{seq}")
    public ResponseEntity<ResponseBrandDto> updateBrand(@Valid @RequestBody BrandDto brandDto, @PathVariable Long seq) throws IOException {
        return ResponseEntity.ok(brandService.update(seq, brandDto));
    }

    @DeleteMapping("/{seq}")
    public ResponseEntity<BrandDto> deleteBrand(@PathVariable Long seq) {
        return ResponseEntity.ok(brandService.deleteBrand(seq));
    }
}
