package org.azizmalik.authorbookdbexample.dao.impl;

import org.azizmalik.authorbookdbexample.TestDataUtil;
import org.azizmalik.authorbookdbexample.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuthorDaoTests {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private AuthorDao underTest;

    @Test
    public void testThatCreateAuthorGeneratesCorrectSql(){
        Author author = TestDataUtil.createTestAuthorA();
        underTest.create(author);
        verify(jdbcTemplate).update(
                eq("INSERT INTO authors (id, name, age) VALUES (?, ?, ?)"),
                eq(1L), eq("Abigail Rose"), eq(80)

        );
    }

    @Test
    public void testForFindOneGeneratesCorrectSql(){
        underTest.findOne(1L);
        verify(jdbcTemplate).query(
                eq("SELECT id, name, age FROM authors WHERE id = ? LIMIT 1"),
                ArgumentMatchers.<AuthorDao.AuthorRowMapper> any(),
                eq(1L)
        );
    }

    @Test
    public void testThatFindManyGeneratesTheCorrectSql(){
        underTest.find();
        verify(jdbcTemplate).query(
                eq("SELECT id, name, age FROM authors"),
                ArgumentMatchers.<AuthorDao.AuthorRowMapper> any()
        );
    }

    @Test
    public void testThatUpdateGeneratesCorrectSql(){
        Author author = TestDataUtil.createTestAuthorA();

        underTest.update(author.getId(), author);


        verify(jdbcTemplate).update(
                eq("UPDATE authors set id = ?, name = ?, age = ? WHERE id = ?"),
                eq(1L), eq("Abigail Rose"), eq(80), eq(1L)
//                having a different id here is a better test.....

        );
    }

    @Test
    public void testThatDeleteGeneratesTheCorrectSql(){
        underTest.delete(1L);

        verify(jdbcTemplate).update(
                eq("DELETE from authors WHERE id = ?"),
                eq(1L)
        );

    }

}
