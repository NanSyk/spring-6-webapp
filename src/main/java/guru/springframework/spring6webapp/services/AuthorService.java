package guru.springframework.spring6webapp.services;

import guru.springframework.spring6webapp.domain.Author;

import java.util.Iterator;

public interface AuthorService {
    Iterable<Author> findAll();
}
