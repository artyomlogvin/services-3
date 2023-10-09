package com.example.EdgarTask.repositories;

import com.example.EdgarTask.classes.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface bookRepo extends JpaRepository<Book, Integer> {
    List<Book> findByNameAndAuthor(String name, String author);
    List<Book> findByNameOrAuthor(String name, String author);
    List<Book> findByCategoryAndYear(String category, int year);
    boolean existsByIDOrNumber(Long ID, String number);

    List<Book> findByName(String name);
    List<Book> findByAuthor(String author);
    List<Book> findByYear(int year );
    List<Book> findByNumber(String number );
    List<Book> findByCategory(String category );
    List<Book> findByIsAgeRestricted(boolean isagerestricted );
    List<Book> findByID(Long ID );


}
