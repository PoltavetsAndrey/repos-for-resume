public class Author {

    public int id;
    public String name;
    public int birthYear;

    public Author() {
    }

    public Author(String name, int birthYear) {
        this.name = name;
        this.birthYear = birthYear;
    }

    public Author(int id, String name, int birthYear) {
        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id = " + id +
                ", name '" + name + '\'' +
                ", birth_year = " + birthYear +
                '}';
    }
}
