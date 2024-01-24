package com.daemoing.daemo.controller;

import com.daemoing.daemo.controller.response.ResponseDto;
import com.daemoing.daemo.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "카테고리 생성 요청", description = "**카테고리 생성 요청 입니다.**", tags = {"Categories"})
    public ResponseEntity<?> save(SaveDto saveDto) {
        return ResponseEntity.status(CREATED).body(ResponseDto.success(CREATED,categoryService.save(saveDto)));
    }

    /**
     * update
     */
    @PatchMapping
    @Operation(summary = "카테고리 수정 요청", description = "**카테고리 수정 요청 입니다.**", tags = {"Categories"})
    public ResponseEntity<?> update(@RequestBody UpdateDto updateDto) {
        return ResponseEntity.status(OK).body(ResponseDto.success(OK,categoryService.update(updateDto)));
    }

    /**
     * delete
     */
    @DeleteMapping
    @Operation(summary = "카테고리 삭제 요청", description = "**카테고리 삭제 요청 입니다.**", tags = {"Categories"})
    public ResponseEntity<?> delete(@RequestBody DeleteDto deleteDto) {
        categoryService.delete(deleteDto);
        return ResponseEntity.status(OK).body(ResponseDto.success(OK,null));
    }

    /**
     * reade category all
     */
    @GetMapping
    @Operation(summary = "모든 카테고리 조회 요청", description = "**모든 카테고리 조회 요청 입니다.**", tags = {"Categories"})
    public ResponseEntity<?> readAll() {
        return ResponseEntity.status(OK).body(ResponseDto.success(OK,categoryService.readAll()));
    }
}
