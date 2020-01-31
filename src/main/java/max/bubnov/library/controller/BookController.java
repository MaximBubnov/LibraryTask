package max.bubnov.library.controller;


import io.swagger.annotations.ApiOperation;
import max.bubnov.library.domain.Book;
import max.bubnov.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("library/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @ApiOperation(value = "Get all Books", notes = "Finds all Books in library", response = Book.class)
    public List<Book> getAll() {
        return bookService.getAll();
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Get one Book", notes = "Finds Books in library by ID", response = Book.class)
    public Book getOne(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @PostMapping
    @ApiOperation(value = "Add Book", notes = "Add Book in library", response = Book.class)
    public Book addBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @PutMapping("{id}")
    @ApiOperation(value = "Update Book", notes = "Update Book by ID", response = Book.class)
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Delete Book", notes = "Delete books from library by ID", response = Book.class)
    public void delete(@PathVariable(name = "id") Book book) {
        bookService.delete(book);
    }

}
