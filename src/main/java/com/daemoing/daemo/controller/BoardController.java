package com.daemoing.daemo.controller;


import com.daemoing.daemo.controller.response.ResponseDto;
import com.daemoing.daemo.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.daemoing.daemo.dto.BoardDto.UpdateDto;
import static com.daemoing.daemo.dto.BoardDto.WriteDto;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    /**
     * 게시물 작성
     */
    @PostMapping
    @Operation(summary = "게시물 작성 요청", description = "**제목 내용이 null이면 안됩니다.**", tags = {"Boards"})
    public ResponseEntity<?> write(@RequestBody WriteDto writeDto) {
        return ResponseEntity.status(OK).body(ResponseDto.success(OK,boardService.write(writeDto)));
    }

    /**
     * 게시물 수정
     */
    @PatchMapping("/{id}")
    @Operation(summary = "게시물 수정 요청", description = "**게시물 수정 요청입니다.**", tags = {"Boards"})
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UpdateDto updateDto) {
        return ResponseEntity.status(OK).body(ResponseDto.success(OK,boardService.update(id,updateDto)));
    }

    /**
     * 게시물 삭제
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "게시물 삭제 요청", description = "**게시물 삭제 요청입니다.**", tags = {"Boards"})
    public ResponseEntity<?> delete(@PathVariable Long id) {
        boardService.delete(id);
        return ResponseEntity.status(OK).body(ResponseDto.success(OK,null));
    }


    /**
     * 게시물 단건 조회
     */
    @GetMapping("/{id}")
    @Operation(summary = "게시물 단건 조회 요청", description = "**게시물 단건 조회 요청입니다.**", tags = {"Boards"})
    public ResponseEntity<?> detail(@PathVariable Long id) {
        return ResponseEntity.status(OK).body(ResponseDto.success(OK,boardService.detail(id)));
    }

    /**
     * 게시물 조회
     */
    @GetMapping("/page/{id}")
    @Operation(summary = "게시물 리스트 요청", description = "**게시물 리스트 조회 입니다..**", tags = {"Boards"})
    public ResponseEntity<?> page(@PathVariable int id) {
        return ResponseEntity.status(OK).body(ResponseDto.success(OK,boardService.page(id)));
    }

    /**
     * 게시물 검색
     */
    @GetMapping("page/{id}/search/{keyword}")
    @Operation(summary = "게시물 검색 요청", description = "**게시물 검색 요청 입니다..**", tags = {"Boards"})
    public ResponseEntity<?> search(@PathVariable int id,@PathVariable String keyword) {
        return ResponseEntity.status(OK).body(ResponseDto.success(OK,boardService.search(id, keyword)));
    }

}
