import java.util.Collection;
import java.util.Optional;

public interface ILibraryRepository {

    Collection<Book> getAllBooks();

    Collection<Author> getAllAuthors();

    Optional<Author> findAuthorById(int id);

    Collection<Comment> getAllComment();

    Collection<User> getAllUser();

    void saveAuthor(Author author);

    void saveBook(Book book, Author author);

    void saveComment(Comment comment);

    void saveUser(User user);

    void updateBook(Book book);

    void updateAuthor(Author author);

    void updateUser(User user);

    void updateComment(Comment comment);

    void deleteAuthor(int id);

    void deleteBook(int id);

    void deleteComment(int id);

    void deleteUser(int id);

    /**
     * ищет все книги, имя автора включает заданный текст имени
     *
     * @param name Текст имени для частичного поиска
     * @return коллекция заданных книг
     */

    Collection<Book> findBookByAuthorName(String name);

    Collection<Author> findAuthorByBook(Book book);

    Collection<Comment> findCommentsByBook(Book book);

    Book findBookByTitle(String title);

    Collection<Author> findAuthorByName(String name);

    Collection<Book> findBooksByAuthorId(int id);
}
