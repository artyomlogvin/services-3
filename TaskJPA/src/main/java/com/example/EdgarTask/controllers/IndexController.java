package com.example.EdgarTask.controllers;
import com.example.EdgarTask.classes.MyRequestData;
import com.example.EdgarTask.classes.Book;
import com.example.EdgarTask.repositories.bookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class IndexController {

    private final bookRepo bkRepo;

    @Autowired
    public IndexController(bookRepo bkRepo) {
        this.bkRepo = bkRepo;
    }
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "Привет, мир!");
        return "greeting"; // Предполагается, что у вас есть файл "index.html" в папке "WEB-INF/views/"
    }
    @GetMapping("/intro")
    public String greeting(Model model) {
        model.addAttribute("message", "Привет, мир!");
        return "intro";
    }
    @GetMapping("save")
    public String gotosave(Model model) {

        return "save";
    }
    @GetMapping("error")
    public String gotoerror(Model model) {

        return "intro";
    }
    @GetMapping("search")
    public String gotosearch(Model model) {

        return "search";
    }
    @PostMapping("/save")
    public String saveData(@RequestParam("field1") Long field1,
                           @RequestParam("field2") String field2,
                           @RequestParam("field3") String field3,
                           @RequestParam("field4") String field4,
                           @RequestParam("field5") String field5,
                           @RequestParam("field6") int field6,
                           @RequestParam("field7") boolean field7) {
        if(field1==0||field2.isEmpty()||field3.isEmpty()||field4.isEmpty()||field5.isEmpty()||field6==0){
            return "intro";
        }
        // Проверка наличия записи с тем же номером и идентификатором в базе данных
        if (!bkRepo.existsByIDOrNumber(field1, field5)) {
            // Если запись не существует, добавляем в базу данных
            Book bk = new Book(field1, field2, field3, field4, field5, field6, field7);
            bkRepo.save(bk);
        } else {
            return "intro";
            // Если запись уже существует, выполните нужные действия
            // например, показать сообщение об ошибке или перенаправить пользователя на другую страницу
            // ...
        }

        // Верните страницу или перенаправьте пользователя на другую страницу после сохранения
        return "redirect:/"; // Перенаправление на главную страницу после сохранения
    }




    @GetMapping("/table")
    public String showTable(Model modell) {

        List<Book> bookList=bkRepo.findAll();
        modell.addAttribute("bookList", bookList);
        return "table";
    }
    @PostMapping("/search")
    public String searchData(Model modell, @RequestParam("field4") String field4,
                             @RequestParam("field6") int field6) {


        List<Book> bookList = bkRepo.findByCategoryAndYear(field4,field6);
        modell.addAttribute("bookList", bookList);


        return "search";
    }
    @PostMapping("/table")
    public  String handlePostRequest(Model modell,@RequestBody MyRequestData requestData) {


        String inputField = requestData.getInputField();
        String buttonId = requestData.getButtonId();
        List<Book> bookList = null;
        switch (buttonId) {
            case "brand":
                bookList= bkRepo.findByName(inputField);
                break;
            case "model":
                bookList=bkRepo.findByAuthor(inputField);
                break;
            case "category":
                bookList= bkRepo.findByCategory(inputField);
                break;
            case "number":
                bookList= bkRepo.findByNumber(inputField);
                break;
            case "year":
                bookList=bkRepo.findByYear(Integer.parseInt(inputField));
                break;
            case "istrailer":
                bookList=bkRepo.findByIsAgeRestricted(Boolean.parseBoolean(inputField));
                break;
            default:
                System.out.println("Неизвестный фрукт");
                break;
        }
        modell.addAttribute("bookList", bookList);

        return "table";
    }




}
