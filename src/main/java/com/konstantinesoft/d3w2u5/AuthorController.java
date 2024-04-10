package com.konstantinesoft.d3w2u5;
import com.konstantinesoft.d3w2u5.entities.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.konstantinesoft.d3w2u5.services.*;
import java.util.List;

@RestController// Specializzazione di @Component, ci serve per definire una "collezione" di endpoints
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @GetMapping
    public List<Author> getAuthorList(){
        System.out.println("getAuthorList");
        return this.authorService.findAllAuthors();
    }
    @GetMapping("/{id}")
    public Author findById(@PathVariable int id) {
        return this.authorService.findAuthorById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Status Code 201
    public Author saveAuthor(@RequestBody Author body) {
        return this.authorService.saveAuthor(body);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable int id) {
        this.authorService.deleteAuthor(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Author updateAuthor(@PathVariable int id,@RequestBody Author updatedAuthor) {
        return this.authorService.findByIdAndUpdate(id, updatedAuthor);
    }
}
