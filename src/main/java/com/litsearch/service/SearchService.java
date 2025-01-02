package com.litsearch.service;

import com.litsearch.dto.BookDto;
import com.litsearch.entity.Book;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.massindexing.MassIndexer;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchService {

    private final EntityManager entityManager;
    private final PlatformTransactionManager transactionManager;

    @PostConstruct
    public void init() { // 초기 인덱싱, DB에 이미 존재하는 데이터를 인덱싱
        // 수동 트랜잭션, @PostConstruct와 @Transactional을 같이 사용할 수 없음
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
            SearchSession searchSession = Search.session(entityManager);
            MassIndexer indexer = searchSession.massIndexer(Book.class).threadsToLoadObjects(4);

            indexer.startAndWait();

            transactionManager.commit(status);
        } catch (Exception e) {
            log.error("Error occurred while indexing", e);
            transactionManager.rollback(status);
        }
    }

    // 트랜잭션이 필요한 검색 작업
    @Transactional
    public List<BookDto> searchAllQuery(String keyword) {
        SearchSession searchSession = Search.session(entityManager);

        SearchResult<Book> result = searchSession.search(Book.class)
                .where(f -> f.match()
                        .fields("title", "abstractText", "bodyText")
                        .matching(keyword))
                .fetch(20); // 모든 검색 결과 가져오기

        log.info("result count : {}", result.total().hitCount());

        return result.hits().stream().map(BookDto::from).toList();
    }
}
