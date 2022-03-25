import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Scanner;


/*Добавить классы пользователей и отзывов о книгах.
Определить связи между таблицами (один-к-одному или один-ко-многим)
Выделить DAO для каждой таблицы (книги, авторы, пользователи, отзывы) с реализацией CRUD операций.
Определить и реализовать ILibraryRepository как абстракцию, связывающую DAO книг, авторов,
 пользователей и отзывов.
*/
public class LibraryMain {

    private static final String SQLITE_CONNECTION_STRING = "jdbc:sqlite:sample.db";

    public static void main(String[] args) {
        new LibraryMain().run();
    }

    private void run() {
        try (Connection connection = DriverManager.getConnection(SQLITE_CONNECTION_STRING)) {
            BookDao bookDao = new BookDao(connection);
            AuthorDao authorDao = new AuthorDao(connection);
            CommentDao commentDao = new CommentDao(connection);
            UserDao userDao = new UserDao(connection);
            ILibraryRepository repository = initializeLibrary(connection);
            while (true) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("\t 0 - Создать библиотеку");

                System.out.println("\t 11 - Записать книгу");
                System.out.println("\t 12 - Прочитать всю таблицу Books");
                System.out.println("\t 13 - Заменить книгу");
                System.out.println("\t 14 - Удалить книгу");
                System.out.println("\t 15 - Найти книгу по названию");
                System.out.println("\t 16 - Найти книгу по id автора");

                System.out.println("\t 21 - Записать автора");
                System.out.println("\t 22 - Прочитать всю таблицу Authors");
                System.out.println("\t 23 - Заменить автора");
                System.out.println("\t 24 - Удалить автора");
                System.out.println("\t 25 - Найти автора по книге");
                System.out.println("\t 26 - Найти автора по id");
                System.out.println("\t 27 - Найти автора по имени");

                System.out.println("\t 31 - Записать комментарий");
                System.out.println("\t 32 - Прочитать всю таблицу Comments");
                System.out.println("\t 33 - Заменить комментарий");
                System.out.println("\t 34 - Удалить комментарий");
                System.out.println("\t 35 - Найти комментарии по книге");

                System.out.println("\t 41 - Записать пользователя");
                System.out.println("\t 42 - Прочитать всю таблицу Users");
                System.out.println("\t 43 - Заменить пользователя");
                System.out.println("\t 44 - Удалить пользователя");

                System.out.println("\t 9 - Выход");

                System.out.println("\t Сделайте свой выбор:");
                String userInput = scanner.next();
                switch (userInput) {
                    case "0":
                        doSqlTasks(connection, repository);
                        break;
                    case "11":
                        Book book = new Book("Мастер и Маргарита");
                        Author author = new Author("Булгаков", 1891);
                        repository.saveAuthor(author);
                        repository.saveBook(book, author);
                        break;
                    case "12":
                    for (Book book1 : repository.getAllBooks()) {
                        System.out.println(book1.toString());
                    }
                    break;
                    case "13":
                        Book book1 = new Book(1, "Властелин колец 3", 1);
                        repository.updateBook(book1);
                        break;
                    case "14":
                        repository.deleteBook(3);
                        break;
                    case "15":
                        Book book2 = repository.findBookByTitle("Властелин колец: 2");
                        System.out.println(book2.toString());
                        break;
                    case "16":
                        Collection<Book> books = new ArrayList<>();
                        books = repository.findBooksByAuthorId(1);
                        for (Book book3 : books) {
                            System.out.println(book3.toString());
                        }

                    case "21":
                        Author author1 = new Author("Достоевский", 1821);
                        repository.saveAuthor(author1);
                        break;
                    case "22":
                        for (Author author2 : repository.getAllAuthors()) {
                            System.out.println(author2.toString());
                        }
                        break;
                    case "23":
                        Author author3 = new Author(1, "Джон Толкин", 1980);
                        repository.updateAuthor(author3);
                        break;
                    case "24":
                        repository.deleteAuthor(3);
                        break;
                    case "25":
                        Collection<Author> authors = new ArrayList<>();
                        Book book3 = repository.findBookByTitle("Властелин колец: 1");
                        authors = repository.findAuthorByBook(book3);
                        for (Author author2 : authors) {
                            System.out.println(author2.toString());
                        }
                        break;
                    case "26" :
                        Optional<Author> author2 = Optional.of(new Author());
                        author2 = repository.findAuthorById(2);
                        System.out.println(author2.toString());
                        break;
                    case "27":
                        Collection<Author> author4 = new ArrayList<>();
                        author4 = repository.findAuthorByName("к");
                        for (Author author5 : author4) {
                            System.out.println(author5.toString());
                        }

                    case "31":
                        Comment comment = new Comment("Класс", 1, 1);
                        repository.saveComment(comment);
                        break;
                    case "32":
                        for (Comment comment1 : repository.getAllComment()) {
                            System.out.println(comment1.toString());
                        }
                        break;
                    case "33":
                        Comment comment2 = new Comment("Супер", 1, 1, 1);
                        repository.updateComment(comment2);
                        break;
                    case "34":
                        repository.deleteComment(1);
                        break;
                    case "35":
                        Collection<Comment> comments = new ArrayList<>();
                        Book book4 = repository.findBookByTitle("Властелин колец: 1");
                        System.out.println(book4.toString());
                        comments = repository.findCommentsByBook(book4);
                        for (Comment comment1 : comments) {
                            System.out.println(comment1.toString());
                        }
                        break;

                    case "41":
                        User user = new User("Петров");
                        repository.saveUser(user);
                        break;
                    case "42":
                        for (User user1 : repository.getAllUser()) {
                            System.out.println(user1.toString());
                        }
                        break;
                    case "43":
                        User user2 = new User("Сидоров", 1);
                        repository.updateUser(user2);
                        break;
                    case "44":
                        repository.deleteUser(1);
                        break;
                    case "9":
                        System.out.println("End");
                        return;
                    default:
                        System.out.println("Выбор не верный");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void doSqlTasks(Connection connection, ILibraryRepository repository) throws SQLException {
        Author author1 = new Author("Толкин", 1980);
        Author author2 = new Author("Страуструп", 1950);
        repository.saveBook(new Book("Властелин колец: 1"), author1);
        repository.saveBook(new Book("Властелин колец: 2"), author1);
        repository.saveBook(new Book("C++"), author2);

        repository.saveAuthor(author1);
        repository.saveAuthor(author2);

        Collection<Book> books = repository.findBookByAuthorName("л");
        System.out.println(books);

        Collection<Author> authors = repository.getAllAuthors();
        for (Author author : authors) {
            System.out.println("author: " + author);
        }
    }

    private ILibraryRepository initializeLibrary(Connection connection) throws SQLException {
        BookDao bookDao = new BookDao(connection);
        AuthorDao authorDao = new AuthorDao(connection);
        CommentDao commentDao = new CommentDao(connection);
        UserDao userDao = new UserDao(connection);

        bookDao.createTable();
        authorDao.createTable();
        commentDao.createTable();
        userDao.createTable();

        return new SqlLibraryRepository(bookDao, authorDao, commentDao, userDao);
    }
}
