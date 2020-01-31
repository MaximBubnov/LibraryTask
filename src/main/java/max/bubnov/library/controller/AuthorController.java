package max.bubnov.library.controller;

import io.swagger.annotations.ApiOperation;
import max.bubnov.library.domain.Author;
import max.bubnov.library.service.AuthorService;
import max.bubnov.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "library/author")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
    }

    @GetMapping
    @ApiOperation(value = "Get all Authors", notes = "Finds all Authors", response = Author.class)
    public List<Author> getAll(){
        return authorService.getAll();
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Get one Author", notes = "Finds one Authors by ID", response = Author.class)
    public Author getOne(@PathVariable Long id) {
        return authorService.getAuthorById(id);
    }

    @PostMapping
    @ApiOperation(value = "Add Author", notes = "Add one Author", response = Author.class)
    public Author createAuthor(@RequestBody Author author) {
        return authorService.createAuthor(author);
    }

    @PutMapping(value = "{id}")
    @ApiOperation(value = "Update Author", notes = "Update Author information by ID", response = Author.class)
    public Author updateAuthor(@PathVariable Long id, @RequestBody Author author) {
        return authorService.updateAuthor(id, author);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Delete Author", notes = "Delete Author from Library", response = Author.class)
    public void deleteAuthor(@PathVariable(name = "id") Author author) {
        authorService.delete(author);
    }
}
