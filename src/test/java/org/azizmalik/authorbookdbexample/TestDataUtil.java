package org.azizmalik.authorbookdbexample;

import org.azizmalik.authorbookdbexample.domain.Author;
import org.azizmalik.authorbookdbexample.domain.Book;

public class TestDataUtil {
    private TestDataUtil(){}


    public static Author createTestAuthorA() {
        Author author = Author.builder()
                .id(1L)
                .name("Abigail Rose")
                .age(80)
                .build();
        return author;
    }
    public static Author createTestAuthorB() {
        Author author = Author.builder()
                .id(2L)
                .name("Thomas Cronin")
                .age(44)
                .build();
        return author;
    }

    public static Author createTestAuthorC() {
        Author author = Author.builder()
                .id(3L)
                .name("Jess A Casey")
                .age(24)
                .build();
        return author;
    }

    public static Book createTestBookA() {
        return Book.builder()
                .isbn("987-123")
                .title("The Shadow")
                .authorId(1L)
                .build();
    }
    public static Book createTestBookB() {
        return Book.builder()
                .isbn("987-124")
                .title("TFIOS")
                .authorId(1L)
                .build();
    }
    public static Book createTestBookC() {
        return Book.builder()
                .isbn("987-125")
                .title("Beyond the Horizon")
                .authorId(1L)
                .build();
    }
}
