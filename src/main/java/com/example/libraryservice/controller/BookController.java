package com.example.libraryservice.controller;

import com.example.libraryservice.common.Result;
import com.example.libraryservice.controller.request.BookPageRequest;
import com.example.libraryservice.controller.request.CategoryPageRequest;
import com.example.libraryservice.entity.Book;
import com.example.libraryservice.service.IBookService;
import com.example.libraryservice.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    IBookService bookService;



    @PostMapping("/save")
    public Result save(@RequestBody Book book){
        bookService.save(book);
        return Result.success();
    }



    @GetMapping("/list")
    public Result list(){
        List<Book> users = bookService.list();
        return Result.success(users);
    }

    @GetMapping("/page")
    public Result page(BookPageRequest bookPageRequest){
        Object categorys = bookService.page(bookPageRequest);
        return Result.success(categorys);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Book book = bookService.getById(id);
        return Result.success(book);
    }

    @PutMapping("/update")
    public Result update(@RequestBody Book book) {
        bookService.update(book);
        return  Result.success();
    }

    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        bookService.deleteById(id);
        return  Result.success();
    }
}
