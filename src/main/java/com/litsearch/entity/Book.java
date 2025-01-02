package com.litsearch.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

@Entity
@Indexed
@Table(name = "books")
@Getter
@ToString(of = {"id", "title", "url"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @FullTextField
    @Column(name = "title")
    private String title;

    @Column(name = "url")
    private String url;

    @FullTextField
    @Column(name = "abstract")
    private String abstractText;

    @FullTextField
    @Column(name = "body_text", columnDefinition = "TEXT")
    private String bodyText;

    @Column(name = "body_html", columnDefinition = "LONGTEXT")
    private String bodyHtml;

    @Builder
    public Book(String title, String url, String abstractText, String bodyText, String bodyHtml) {
        this.title = title;
        this.url = url;
        this.abstractText = abstractText;
        this.bodyText = bodyText;
        this.bodyHtml = bodyHtml;
    }
}
