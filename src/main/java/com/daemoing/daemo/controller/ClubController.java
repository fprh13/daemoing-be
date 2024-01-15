package com.daemoing.daemo.controller;

import com.daemoing.daemo.controller.response.ResponseDto;
import com.daemoing.daemo.dto.CategoryDto;
import com.daemoing.daemo.service.ClubService;
import com.daemoing.daemo.dto.ClubDto.UpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import static com.daemoing.daemo.dto.ClubDto.SaveDto;
import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestController
@RequestMapping("/clubs")
@RequiredArgsConstructor
public class ClubController {

    private final ClubService clubService;

    /**
     * 모임 생성
     */
    @PostMapping
    public ResponseEntity<?> save(@RequestBody SaveDto saveDto, @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(CREATED).body(ResponseDto.success(CREATED,clubService.save(saveDto,userDetails)));
    }

    /**
     * read page order
     */
    @GetMapping
    public ResponseEntity<?> pageClub(CategoryDto.ReadReqDto readReqDto) {
        return ResponseEntity.status(OK).body(ResponseDto.success(OK,clubService.readClubListWithCategory(readReqDto)));
    }

    /**
     * 모임 조회
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> detail(@PathVariable Long id) {
        return ResponseEntity.status(OK).body(ResponseDto.success(OK,clubService.detail(id)));
    }

    /**
     * 모임 업데이트
     */
    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody UpdateDto updateDto,
                                    @AuthenticationPrincipal UserDetails userDetails) {
        clubService.update(updateDto,id,userDetails);
        return ResponseEntity.status(OK).body(ResponseDto.success(OK,null));
    }

    /**
     * 모임 삭제
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id,
                                    @AuthenticationPrincipal UserDetails userDetails) {
        clubService.delete(id, userDetails);
        return ResponseEntity.status(OK).body(ResponseDto.success(OK,null));
    }

    /**
     * 모임 신청
     */
    @PatchMapping("/{id}/application")
    public ResponseEntity<?> apply(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(OK).body(ResponseDto.success(OK,clubService.apply(id, userDetails)));
    }

    /**
     * 모임 신청 상태
     */
    @PatchMapping("/{id}/application/{loginId}/{management}" )
    public ResponseEntity<?> manage(@PathVariable Long id,
                                    @PathVariable String loginId,
                                    @PathVariable String management,
                                    @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(OK).body(ResponseDto.success(OK,clubService.manage(id,loginId,userDetails,management)));
    }

    /**
     * 모임 상태 리스트
     */
    @GetMapping("/{id}/application/{page}")
    public ResponseEntity<?> applicantList(@PathVariable Long id,
                                           @PathVariable int page,
                                           @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(OK).body(ResponseDto.success(OK,clubService.applicantList(page ,id, userDetails)));
    }

}
