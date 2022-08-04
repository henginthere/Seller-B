package backend.sellerB.controller;

import backend.sellerB.dto.AddressDto;
import backend.sellerB.dto.NoticeDto;
import backend.sellerB.dto.NoticeReq;
import backend.sellerB.service.AuthService;
import backend.sellerB.service.NoticeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
//@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {
    private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    private final NoticeService noticeService;

    @Operation(summary = "create notice", description = "공지 가져오기")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = NoticeDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping
    public ResponseEntity<NoticeReq> saveNotice(@Valid @RequestBody NoticeReq noticeReq) {
        return ResponseEntity.ok(noticeService.create(noticeReq));
    }

    @GetMapping("/list")
    public ResponseEntity<List<NoticeDto>> getNoticeList(HttpServletRequest request) {
        return ResponseEntity.ok(noticeService.getNoticeList());
    }

    @GetMapping("/{seq}")
    public ResponseEntity<NoticeDto> getNoticeDetail(@PathVariable Long seq) {
        return ResponseEntity.ok(noticeService.getNoticeDetail(seq));
    }

    @GetMapping("/search/{noticeTitle}")
    public ResponseEntity<List<NoticeDto>> findByNoticeTitle(@PathVariable String noticeTitle) {
        return ResponseEntity.ok(noticeService.search(noticeTitle));
    }


    @PutMapping("/{seq}")
    public ResponseEntity<NoticeReq> putNoticeDetail(@Valid @RequestBody NoticeReq noticeReq, @PathVariable Long seq) {
        return ResponseEntity.ok(noticeService.update(seq, noticeReq));
    }

//    @DeleteMapping ("/{seq}")
//    public Integer deleteNotice(@PathVariable Integer seq) {
//        Integer res = noticeService.delete(seq);
//        if(res)
//        return ResponseEntity.ok(noticeService.delete(seq));
//    }

    //soft delete
    @DeleteMapping("/{seq}")
    public ResponseEntity<NoticeDto> deleteNotice(@PathVariable Long seq) {
        // Access the DB and delete the order
        return ResponseEntity.ok(noticeService.deleteNotice(seq));
    }
}
