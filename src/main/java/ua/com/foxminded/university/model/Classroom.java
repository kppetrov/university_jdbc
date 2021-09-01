package ua.com.foxminded.university.model;

public class Classroom {
    private int id;
    private int number;
    private String name;

    public Classroom() {
    }

    public Classroom(int id, int number, String name) {
        this.id = id;
        this.number = number;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
