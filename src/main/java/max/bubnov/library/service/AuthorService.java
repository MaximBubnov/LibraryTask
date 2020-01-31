package max.bubnov.library.service;

import max.bubnov.library.domain.Author;
import max.bubnov.library.repo.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepo authorRepo;

    @Autowired
    public AuthorService(AuthorRepo authorRepo, BookService bookService) {
        this.authorRepo = authorRepo;
    }


    public List<Author> getAll() {
        return authorRepo.findAll();
    }

    public Author getAuthorById(Long id) {
        return authorRepo.findById(id).stream().findFirst().orElse(null);
    }

    public Author createAuthor(Author author) {

        return authorRepo.save(author);
    }

    public Author updateAuthor(Long id, Author author) {
        Author oldAuthor = getAuthorById(id);

            oldAuthor.setFirstName(author.getFirstName());
            oldAuthor.setLastName(author.getLastName());
            oldAuthor.setPatronymic(author.getPatronymic());
            oldAuthor.setDateBirth(author.getDateBirth());
            oldAuthor.setBooks(author.getBooks());

        return authorRepo.save(oldAuthor);
    }

    public void delete(Author author) {
        authorRepo.delete(author);
    }

}
