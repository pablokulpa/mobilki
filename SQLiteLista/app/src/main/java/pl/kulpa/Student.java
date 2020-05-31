package pl.kulpa;

public class Student {

    private Long id;
    private String name;
    private String Surname;

    public Student() {
    }

    public Student(Long id, String name, String surname) {
        this.id = id;
        this.name = name;
        Surname = surname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", Surname='" + Surname + '\'' +
                '}';
    }
}
