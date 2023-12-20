public class Person {
    public String name;
    public int age;
    public String email;
    public String status = "public";

    public Person(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
/*
Проверка, что в этом блоке слово не меняется:
все слова public в объявлении атрибутов и методов класса
заменить на слово private.
 */