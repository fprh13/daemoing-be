package com.daemoing.daemo.controller;

import com.daemoing.daemo.controller.response.ResponseDto;
import com.daemoing.daemo.dto.CategoryDto;
import com.daemoing.daemo.service.CategoryService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.daemoing.daemo.dto.CategoryDto.*;
import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * create
     */
    @PostMapping
    public ResponseEntity<?> save(SaveDto saveDto) {
        return ResponseEntity.status(CREATED).body(ResponseDto.success(CREATED,categoryService.save(saveDto)));
    }

    /**
     * update
     */
    @PatchMapping
    public ResponseEntity<?> update(@RequestBody UpdateDto updateDto) {
        return ResponseEntity.status(OK).body(ResponseDto.success(OK,categoryService.update(updateDto)));
    }

    /**
     * delete
     */
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody DeleteDto deleteDto) {
        categoryService.delete(deleteDto);
        return ResponseEntity.status(OK).body(ResponseDto.success(OK,null));
    }

    /**
     * reade category all
     */
    @GetMapping
    public ResponseEntity<?> readAll() {
        return ResponseEntity.status(OK).body(ResponseDto.success(OK,categoryService.readAll()));
    }






}
