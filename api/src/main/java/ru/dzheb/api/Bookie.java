package ru.dzheb.api;

import lombok.Data;

import java.util.UUID;
@Data
public class Bookie {
    private UUID id;
    private String name;
    private Author author;
}
