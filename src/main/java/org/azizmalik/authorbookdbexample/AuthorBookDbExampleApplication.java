package org.azizmalik.authorbookdbexample;

import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log
public class AuthorBookDbExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorBookDbExampleApplication.class, args);
    }

}
