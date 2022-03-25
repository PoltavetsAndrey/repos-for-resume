import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

public class SqlLibraryRepository implements ILibraryRepository {

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final CommentDao commentDao;
    private final UserDao userDao;

    public SqlLibraryRepository(BookDao bookDao, AuthorDao authorDao,
                                CommentDao commentDao, UserDao userDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.commentDao = commentDao;
        this.userDao = userDao;
    }

    @Override
    public Collection<Book> getAllBooks() {
        try {
            return bookDao.getAll();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch book", e);
        }
    }

    @Override
    public Collection<Author> getAllAuthors() {
        try {
            return authorDao.getAll();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch author", e);
        }
    }

    @Override
    public Optional<Author> findAuthorById(int id) {
        try {
            return authorDao.getById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch author", e);
        }
    }

    @Override
    public Collection<Comment> getAllComment() {
        try {
            return commentDao.getAll();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch comment", e);
        }
    }

    @Override
    public Collection<User> getAllUser() {
        try {
            return userDao.getAll();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch user", e);
        }
    }

    @Override
    public void saveAuthor(Author author) {
        try {
            if (author.id == 0) {
                authorDao.insert(author);
            } else {
                authorDao.update(author);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save author", e);
        }
    }

    @Override
    public void saveBook(Book book, Author author) {
        try {
            if (author.id == 0) {
                authorDao.insert(author);
            }
            book.authorId = author.id;
            bookDao.insert(book);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save book", e);
        }
    }

    @Override
    public void saveComment(Comment comment) {
        try {
            if (comment.bookId != 0 && comment.userId != 0) {
                commentDao.insert(comment);
            } else {
                System.out.println("You need to specify an id for the book and user");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save comment", e);
        }
    }

    @Override
    public void saveUser(User user) {
        try {
            if (user.id == 0) {
                userDao.insert(user);
            } else {
                userDao.update(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save user", e);
        }
    }

    @Override
    public void updateBook(Book book) {
        try {
            bookDao.update(book);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to replace book", e);
        }
    }

    @Override
    public void updateAuthor(Author author) {
        try {
            authorDao.update(author);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to replace author", e);
        }
    }

    @Override
    public void updateUser(User user) {
        try {
            userDao.update(user);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to replace user", e);
        }
    }

    @Override
    public void updateComment(Comment comment) {
        try {
            commentDao.update(comment);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to replace comment", e);
        }
    }

    @Override
    public void deleteAuthor(int id) {
        try {
            if (id != 0) {
                authorDao.delete(id);
            } else {
                System.out.println("Author not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to remove author", e);
        }
    }

    @Override
    public void deleteBook(int id) {
        try {
            if (id != 0) {
                bookDao.delete(id);
            } else {
                System.out.println("Book not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to remove author", e);
        }
    }

    @Override
    public void deleteComment(int id) {
        try {
            if (id != 0) {
                commentDao.delete(id);
            } else {
                System.out.println("Book not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to remove author", e);
        }
    }

    @Override
    public void deleteUser(int id) {
        try {
            if (id != 0) {
                userDao.delete(id);
            } else {
                System.out.println("Book not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to remove author", e);
        }
    }

    @Override
    public Collection<Book> findBookByAuthorName(String name) {
// Поиск по имени автора с использованием djoin
        try {
            return bookDao.findBooksByAuthorName(name);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find books", e);
        }
    }

    @Override
    public Collection<Author> findAuthorByBook(Book book) {
        try {
            return authorDao.findByBook(book);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find author", e);
        }
    }

    @Override
    public Collection<Comment> findCommentsByBook(Book book) {
        try {
            return commentDao.findByBook(book);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find comments");
        }
    }

    @Override
    public Book findBookByTitle(String title) {
        try {
            return bookDao.findBookByTitle(title);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find books", e);
        }
    }

    @Override
    public Collection<Author> findAuthorByName(String name) {
        try {
            return authorDao.findByName(name);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find author", e);
        }
    }

    @Override
    public Collection<Book> findBooksByAuthorId(int id) {
        try {
            return bookDao.getBooksByAuthorId(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find book", e);
        }
    }
}
