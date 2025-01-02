package com.litsearch.controller;

import com.litsearch.dto.BookDto;
import com.litsearch.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping
    public ResponseEntity<List<BookDto>> search(@RequestParam(value = "query") String query) {
        List<BookDto> result = searchService.searchAllQuery(query);

        return ResponseEntity.ok(result);
    }

}
