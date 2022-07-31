package backend.sellerB.controller;

import backend.sellerB.dto.NoticeDto;
import backend.sellerB.service.AuthService;
import backend.sellerB.service.NoticeService;
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


    @PostMapping
    public ResponseEntity<NoticeDto> saveNotice(@Valid @RequestBody NoticeDto noticeDto) {
        return ResponseEntity.ok(noticeService.create(noticeDto));
    }

    @GetMapping("/list")
    public ResponseEntity<List<NoticeDto>> getNoticeList(HttpServletRequest request) {
        return ResponseEntity.ok(noticeService.getNoticeList());
    }

    @GetMapping("/{seq}")
    public ResponseEntity<NoticeDto> getNoticeDetail(@PathVariable Integer seq) {
        return ResponseEntity.ok(noticeService.getNoticeDetail(seq));
    }

    @GetMapping("/search/{noticeTitle}")
    public ResponseEntity<List<NoticeDto>> findByNoticeTitle(@PathVariable String noticeTitle) {
        return ResponseEntity.ok(noticeService.search(noticeTitle));
    }


    @PutMapping("/{seq}")
    public ResponseEntity<NoticeDto> putNoticeDetail(@Valid @RequestBody NoticeDto noticeDto, @PathVariable Integer seq) {
        return ResponseEntity.ok(noticeService.update(seq, noticeDto));
    }

//    @DeleteMapping ("/{seq}")
//    public Integer deleteNotice(@PathVariable Integer seq) {
//        Integer res = noticeService.delete(seq);
//        if(res)
//        return ResponseEntity.ok(noticeService.delete(seq));
//    }

    //soft delete
    @DeleteMapping("/{seq}")
    public ResponseEntity<NoticeDto> deleteNotice(@PathVariable Integer seq) {
        // Access the DB and delete the order
        return ResponseEntity.ok(noticeService.deleteNotice(seq));
    }
}
