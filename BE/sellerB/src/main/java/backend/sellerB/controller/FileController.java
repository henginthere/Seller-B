package backend.sellerB.controller;

import backend.sellerB.service.AwsS3Service;
import backend.sellerB.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileController {
    private final AwsS3Service awsS3Service;
    private final BrandService brandService;

    @PostMapping
    @ResponseBody
    public String upload(@RequestParam("data") MultipartFile multipartFile) throws IOException {
        return awsS3Service.upload(multipartFile, "static");
    }

}
