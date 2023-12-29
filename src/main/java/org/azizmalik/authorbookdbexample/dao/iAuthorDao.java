package org.azizmalik.authorbookdbexample.dao;

import org.azizmalik.authorbookdbexample.domain.Author;


import java.util.Optional;
import java.util.List;

public interface iAuthorDao {
    void create(Author author);

    Optional<Author> findOne(long l);

    List<Author> find();

    void update(long id, Author author);

    void delete(long id);
}
