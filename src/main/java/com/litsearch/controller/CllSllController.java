package com.litsearch.controller;

import com.litsearch.document.CllSllDocument;
import com.litsearch.service.CllSllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cll-sll")
public class CllSllController {

    private final CllSllService cllSllService;

    @Autowired
    public CllSllController(CllSllService cllSllService) {
        this.cllSllService = cllSllService;
    }

    /**
     * 문서 저장
     */
    @PostMapping("/documents")
    public CllSllDocument saveDocument(@RequestBody CllSllDocument doc) {
        return cllSllService.saveDocument(doc);
    }

    /**
     * 전체 문서 조회
     */
    @GetMapping("/documents")
    public Iterable<CllSllDocument> findAll() {
        return cllSllService.findAll();
    }

    /**
     * 제목으로 검색
     */
    @GetMapping("/search/title")
    public List<CllSllDocument> searchByTitle(@RequestParam("q") String title) {
        return cllSllService.searchByTitle(title);
    }

    /**
     * 여러 필드에서 검색
     */
    @GetMapping("/search")
    public List<CllSllDocument> search(@RequestParam("q") String keyword) {
        return cllSllService.search(keyword);
    }
}
