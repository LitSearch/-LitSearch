package com.litsearch.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * Elasticsearch에 매핑될 엔티티 클래스.
 * indexName은 실제 Elasticsearch 인덱스 이름을 지정해줍니다.
 */
@Setter
@Getter
@Document(indexName = "cll_sll_index")
public class CllSllDocument {

    // Getter/Setter
    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Text)
    private String url;

    @Field(type = FieldType.Text)
    private String abstractText;  // 'abstract'는 예약어 비슷하므로 필드명 변경

    @Field(type = FieldType.Text)
    private String bodyText;

    @Field(type = FieldType.Text)
    private String bodyHtml;

    // 기본 생성자
    public CllSllDocument() {}

    // 전체 필드 생성자
    public CllSllDocument(String id, String title, String url, String abstractText, String bodyText, String bodyHtml) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.abstractText = abstractText;
        this.bodyText = bodyText;
        this.bodyHtml = bodyHtml;
    }

}
