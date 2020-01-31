package max.bubnov.library.service;

import max.bubnov.library.domain.Book;
import max.bubnov.library.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepo bookRepo;

    @Autowired
    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getAll() {
        return bookRepo.findAll();
    }

    public Book getById(Long id) {
        return bookRepo.findById(id).stream().findFirst().orElse(null);
    }

    public Book createBook(Book book) {
        return bookRepo.save(book);
    }

    public Book updateBook(Long id, Book book) {

        Book oldBook = getById(id);

        oldBook.setName(book.getName());
        oldBook.setDate(book.getDate());
        oldBook.setAnnotation(book.getAnnotation());
        oldBook.setAuthors(book.getAuthors());

        return bookRepo.save(oldBook);

    }

    public void delete(Book book) {
        bookRepo.delete(book);
    }


}
