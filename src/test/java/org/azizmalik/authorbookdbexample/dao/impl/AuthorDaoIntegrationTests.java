package org.azizmalik.authorbookdbexample.dao.impl;

import org.azizmalik.authorbookdbexample.TestDataUtil;
import org.azizmalik.authorbookdbexample.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorDaoIntegrationTests {

    private AuthorDao underTest;

    @Autowired //Dependency injection
    public AuthorDaoIntegrationTests(AuthorDao underTest){
        this.underTest = underTest;
    }

    @Test
    public void testAuthorCanBeCreatedAndRecalled(){
        Author author = TestDataUtil.createTestAuthorA();
        underTest.create(author);
        Optional<Author> result = underTest.findOne(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRecalled(){
        Author authorA = TestDataUtil.createTestAuthorA();
        underTest.create(authorA);
        Author authorB = TestDataUtil.createTestAuthorB();
        underTest.create(authorB);
        Author authorC = TestDataUtil.createTestAuthorC();
        underTest.create(authorC);

        List<Author> result = underTest.find();
        assertThat(result)
                .hasSize(3)
                .containsExactly(authorA, authorB, authorC);

    }

    @Test
    public void authorCanBeUpdated(){
        Author authorA = TestDataUtil.createTestAuthorA();
        underTest.create(authorA);
        authorA.setName("UPDATED");

        // Update the author so the name will be changed to "UPDATED".
        underTest.update(authorA.getId(), authorA);

        // will give the record that was recently updated in the statement above.
        Optional<Author> result = underTest.findOne(authorA.getId());

        // will check across the present authorA and everything should be same, test will pass.
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorA);
    }

    @Test
    public void testThatAuthorCanBeDeleted(){
        Author author = TestDataUtil.createTestAuthorA();
        underTest.create(author);
        underTest.delete(author.getId());

        Optional<Author> result = underTest.findOne(author.getId());
        assertThat(result).isEmpty();
    }
}
