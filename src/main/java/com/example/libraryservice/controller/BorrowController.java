package com.example.libraryservice.controller;

import com.example.libraryservice.common.Result;
import com.example.libraryservice.controller.request.BorrowPageRequest;
import com.example.libraryservice.entity.Borrow;
import com.example.libraryservice.service.IBorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrow")
public class BorrowController {
    @Autowired
    IBorrowService borrowService;



    @PostMapping("/save")
    public Result save(@RequestBody Borrow borrow){
        borrowService.save(borrow);
        return Result.success();
    }



    @GetMapping("/list")
    public Result list(){
        List<Borrow> users = borrowService.list();
        return Result.success(users);
    }

    @GetMapping("/page")
    public Result page(BorrowPageRequest borrowPageRequest){
        Object categorys = borrowService.page(borrowPageRequest);
        return Result.success(categorys);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Borrow borrow = borrowService.getById(id);
        return Result.success(borrow);
    }

    @PutMapping("/update")
    public Result update(@RequestBody Borrow borrow) {
        borrowService.update(borrow);
        return  Result.success();
    }

    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        borrowService.deleteById(id);
        return  Result.success();
    }
}
