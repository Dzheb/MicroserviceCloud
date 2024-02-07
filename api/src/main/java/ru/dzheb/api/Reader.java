package ru.dzheb.api;

import lombok.Data;

import java.util.UUID;

@Data
public class Reader {
    private UUID id;
    private String firstname;
    private String lastname;

}
