package com.konstantinesoft.d3w2u5.services;

import com.konstantinesoft.d3w2u5.entities.Author;
import com.konstantinesoft.d3w2u5.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    public Author findAuthorById(int id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            return author.get();
        } else {
            throw new RuntimeException("Author not found for id :: " + id);
        }
    }

    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    public void deleteAuthor(int id) {
        authorRepository.deleteById(id);
    }

    public Author findByIdAndUpdate(int id, Author updatedAuthor) {
        Author found = this.findAuthorById(id);
        if (found != null) {
            found.setNome(updatedAuthor.getNome());
            found.setCognome(updatedAuthor.getCognome());
            found.setEmail(updatedAuthor.getEmail());
            found.setDataDiNascita(updatedAuthor.getDataDiNascita());
            found.setAvatar(updatedAuthor.getAvatar());
            this.saveAuthor(found);
            return updatedAuthor;
        } else {
            throw new RuntimeException("Author not found for id :: " + id);
        }
    }
}

