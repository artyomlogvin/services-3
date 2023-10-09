package com.example.EdgarTask.classes;
import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long ID;
    @Column(name="name")
    private String name;
    @Column(name="author")
    private String author;
    @Column(name="category")
    private String category;
    @Column(name="number")
    private String number;
    @Column(name="year")
    private int year;
    @Column(name="isagerestricted")
    private boolean isAgeRestricted;
    protected Book(){}
    public Book(long ID, String name, String author, String category, String number, int year, boolean isAgeRestricted) {
        this.ID = ID;
        this.name = name;
        this.author = author;
        this.category = category;
        this.number = number;
        this.year = year;
        this.isAgeRestricted = isAgeRestricted;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isAgeRestricted() {
        return isAgeRestricted;
    }

    public void setAgeRestriction(boolean restricted) {
        isAgeRestricted = restricted;
    }
}
