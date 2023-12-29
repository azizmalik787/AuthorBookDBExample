package org.azizmalik.authorbookdbexample.dao.impl;

import org.azizmalik.authorbookdbexample.TestDataUtil;
import org.azizmalik.authorbookdbexample.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookDaoTests {
    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BookDao underTest;

    @Test
    public void testThatCreateBookGeneratesCorrectSql(){
        Book book = TestDataUtil.createTestBookA();

        underTest.create(book);

        verify(jdbcTemplate).update(
                eq("INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)"),
                eq("987-123"),
                eq("The Shadow"),
                eq(1L)
        );
    }

    @Test
    public void testThatFindOneBookGeneratesTheCorrectSql(){
        underTest.findOne("987-123");
        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id FROM books WHERE isbn = ? LIMIT 1"),
                ArgumentMatchers.<BookDao.BookRowMapper> any(),
                eq("987-123")
        );
    }
    @Test
    public void testThatFindGeneratesCorrectSql(){
        underTest.find();
        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id from books"),
                ArgumentMatchers.<BookDao.BookRowMapper> any()
        );
    }

    @Test
    public void testThatUpdateCreatesTheCorrectSql(){

        Book book = TestDataUtil.createTestBookA();
        underTest.update("978-123", book);

        verify(jdbcTemplate).update(
                eq("UPDATE books SET isbn = ?, title = ?, author_id = ? WHERE isbn = ?"),
                eq("987-123"), eq("The Shadow"), eq(1L), eq("978-123")
        );
    }
    @Test
    public void testThatDeleteCreatesTheCorrectSql(){
        underTest.delete("978-123");

        verify(jdbcTemplate).update(
                eq("DELETE FROM books WHERE isbn = ?"),
                eq("978-123")
        );
    }



}
