package backend.sellerB.controller;

import backend.sellerB.service.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileController {
    private final AwsS3Service awsS3Service;

    @PostMapping("/{service-name}")
    @ResponseBody
    public String upload(@RequestParam("data") MultipartFile multipartFile, @PathVariable("service-name") String folderName) throws IOException {
        return awsS3Service.upload(multipartFile, folderName);
    }

}
