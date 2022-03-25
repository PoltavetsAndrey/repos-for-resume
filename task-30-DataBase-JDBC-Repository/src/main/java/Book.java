public class Book {

    public int authorId;
    public String title;
    public int id;

    public Book() {
    }

    public Book(String title) {
        this.title = title;
    }

    public Book(int authorId, String title, int id) {
        this.authorId = authorId;
        this.title = title;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id = " + id +
                ", title '" + title + '\'' +
                ", author_id = " + authorId +
                '}';
    }
}
