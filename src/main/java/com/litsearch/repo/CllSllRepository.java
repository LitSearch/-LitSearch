package com.litsearch.repo;

import com.litsearch.document.CllSllDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CllSllRepository extends ElasticsearchRepository<CllSllDocument, String> {

    /**
     * 제목으로 검색
     */
    List<CllSllDocument> findByTitleContaining(String title);

    /**
     * 여러 필드에서 검색
     */
    List<CllSllDocument> findByTitleContainingOrAbstractTextContainingOrBodyTextContainingOrBodyHtmlContaining(
            String title, String abstractText, String bodyText, String bodyHtml);
}
