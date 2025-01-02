package com.litsearch.dto;

import com.litsearch.entity.Book;

import java.io.Serializable;

/**
 * DTO for {@link com.litsearch.entity.Book}
 */
public record BookDto(
        Long id,
        String title,
        String url,
        String abstractText
) implements Serializable {
    public static BookDto from(Book book) {
        return new BookDto(
            book.getId(),
            book.getTitle(),
            book.getUrl(),
            book.getAbstractText()
        );
    }
}