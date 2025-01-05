package com.litsearch.service;

import com.litsearch.document.CllSllDocument;
import com.litsearch.repo.CllSllRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CllSllService {

    private final CllSllRepository cllSllRepository;

    @Autowired
    public CllSllService(CllSllRepository cllSllRepository) {
        this.cllSllRepository = cllSllRepository;
    }

    /**
     * 문서 저장
     */
    public CllSllDocument saveDocument(CllSllDocument doc) {
        return cllSllRepository.save(doc);
    }

    /**
     * 전체 문서 조회
     */
    public Iterable<CllSllDocument> findAll() {
        return cllSllRepository.findAll();
    }

    /**
     * 제목으로 검색
     */
    public List<CllSllDocument> searchByTitle(String title) {
        return cllSllRepository.findByTitleContaining(title);
    }

    /**
     * 여러 필드에서 키워드 검색
     */
    public List<CllSllDocument> search(String keyword) {
        return cllSllRepository.findByTitleContainingOrAbstractTextContainingOrBodyTextContainingOrBodyHtmlContaining(
                keyword, keyword, keyword, keyword);
    }
}
