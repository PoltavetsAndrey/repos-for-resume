public class Comment {

    public int id;
    public String textComment;
    public int bookId;
    public int userId;

    public Comment(String textComment, int bookId, int userId) {
        this.textComment = textComment;
        this.bookId = bookId;
        this.userId = userId;
    }

    public Comment(String textComment, int id, int bookId, int userId) {
        this.id = id;
        this.textComment = textComment;
        this.bookId = bookId;
        this.userId = userId;
    }

    public Comment() {
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id = " + id +
                ", text_comment '" + textComment + '\'' +
                ", book_id = " + bookId +
                ", user_id = " + userId +
                '}';
    }
}
