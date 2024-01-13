package com.daemoing.daemo.repository;

import com.daemoing.daemo.domain.UserClub;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserClubRepository extends JpaRepository<UserClub, Long> {

    /**
     *
     * @param applicant 모임에 등록한 신청자
     * @param id = clubId (현재 모임을 조회 용)
     */
    Optional<UserClub> findUserClubByApplicantAndClubId(String applicant, Long id);

    /**
     * 방장 검증
     * @param applicant 리더로 모임에 등록한 신청자
     * @param id = clubId (현재 모임을 조회 용)
     */
    Optional<UserClub> findUserClubLeaderByApplicantAndClubId(String applicant, Long id);

    /**
     * 모임 신청자 상태 목록 리스트 페이지
     * @param id = clubId (현재 모임을 조회 용)
     * @param pageable = 최신 순으로 10개
     */
    Page<UserClub> findApplicantPageByClubId(@Param("id") Long id, Pageable pageable);
}
