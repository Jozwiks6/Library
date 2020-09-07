package pl.camp.it.library.dao.impl;

import pl.camp.it.library.model.Author;

import java.util.List;

public interface IAuthorDAO {
    List<Author> findAuthors(String pattern);
}
