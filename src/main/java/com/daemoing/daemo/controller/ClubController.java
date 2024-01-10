package com.daemoing.daemo.controller;

import com.daemoing.daemo.controller.response.ResponseDto;
import com.daemoing.daemo.service.ClubService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.daemoing.daemo.service.dto.ClubDto.SaveDto;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequestMapping("/api/clubs")
@RequiredArgsConstructor
public class ClubController {

    private final ClubService clubService;

    /**
     * 모임 생성
     */
    @PostMapping
    public ResponseEntity<?> save(@RequestBody SaveDto saveDto) {
        return ResponseEntity.status(OK).body(ResponseDto.success(OK,clubService.save(saveDto)));
    }
}
