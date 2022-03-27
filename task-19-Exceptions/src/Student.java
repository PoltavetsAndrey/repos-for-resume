public class Student {

    private String name;
    private int age;

    public void setName(String name) {
        if (!name.isEmpty() || name == null) {
            throw new IllegalArgumentException("Введите имя правильно");
        } else {
            this.name = name;
        }
    }

    public void setAge(int age) {
        if (age < 0 || age > 130) {
            throw new IllegalArgumentException("Возраст неверный");
        } else {
            this.age = age;
        }
    }
}
