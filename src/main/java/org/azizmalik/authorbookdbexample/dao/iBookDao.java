package org.azizmalik.authorbookdbexample.dao;

import org.azizmalik.authorbookdbexample.domain.Book;

import java.util.Optional;
import java.util.List;
public interface iBookDao {
    void create(Book book);


    Optional<Book> findOne(String s);

    List<Book> find();

    void update(String isbn, Book book);

    void delete(String isbn);
}
