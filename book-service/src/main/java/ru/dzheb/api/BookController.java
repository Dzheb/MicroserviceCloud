package ru.dzheb.api;

import com.github.javafaker.Faker;
import org.example.gb.timing.Timer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/book")
@Timer
public class BookController {
    private final Faker faker;
    private final List<Bookie> books;

    public BookController() {
        this.faker = new Faker();
        final List<Bookie> books = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Bookie book = new Bookie();
            book.setId(UUID.randomUUID());
            book.setName(faker.name().title());
            Author author = new Author();
            author.setId(UUID.randomUUID());
            author.setFirstName(faker.name().firstName());
            author.setLastName(faker.name().lastName());
            book.setAuthor(author);
            books.add(book);

        }
        this.books = List.copyOf(books);
    }

    @GetMapping
    public List<Bookie> getAll() {
        return books;
    }

    @GetMapping("/random")
    public Bookie getRandom() {
        final int randomIndex = faker.number()
                .numberBetween(0, books.size());
        return books.get(randomIndex);
    }

}
