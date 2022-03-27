import java.io.Serializable;

public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;
    public String name;
    public String telephone;
   //public transient int password;
    public int password;


    public Contact(String name, String telephone, int password) {
        this.name = name;
        this.telephone = telephone;
        this.password = password;
    }

    public Contact() {
    }
}
