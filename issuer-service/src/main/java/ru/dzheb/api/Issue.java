package ru.dzheb.api;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class Issue {
    private UUID id;
    private LocalDate issuedAt;
    //    private UUID bookId;
    private Bookie book;
    private Reader reader;
}
