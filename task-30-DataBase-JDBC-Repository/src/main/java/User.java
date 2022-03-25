public class User {

    public String name;
    public int id;

    public User(String name) {
        this.name = name;
    }

    public User(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                " name '" + name + '\'' +
                ", id = " + id +
                '}';
    }
}
