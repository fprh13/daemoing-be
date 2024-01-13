package com.daemoing.daemo.service.validator;

import com.daemoing.daemo.domain.Club;
import com.daemoing.daemo.domain.User;
import com.daemoing.daemo.domain.UserClub;
import com.daemoing.daemo.domain.type.ClubAccessState;
import com.daemoing.daemo.global.common.exception.CustomException;

import static com.daemoing.daemo.global.common.ErrorCode.ACCESS_DENIED;
import static com.daemoing.daemo.global.common.ErrorCode.DUPLICATE_RESOURCE;

public class ClubValidator {

    /**
     * 모임의 리더인지 검증
     */
    public static void checkLeader(Club club) {
        if (!isLeader(club)) {
            throw new CustomException(ACCESS_DENIED);
        }
    }

    /**
     * 모임의 리더인지 검증
     */
    public static void checkLeader(UserClub userClub) {
        if (!isLeader(userClub)) {
            throw new CustomException(ACCESS_DENIED);
        }
    }

    /**
     * 모임에 신청한 적 있는 유저 인지 검증
     */
    public static void checkDuplicateApplicant(User user, Club club) {
        if (isAlreadyApplied(user, club)) {
            throw new CustomException(DUPLICATE_RESOURCE);
        }
    }

    // 모임 리더 인지 검증 true = 리더 , false = 리더가 아님
    private static boolean isLeader(Club club) {
        return club.getUserClubs().stream()
                .anyMatch(userClub -> userClub.getClubAccessState().equals(ClubAccessState.LEADER));
    }

    // 모임 리더 인지 검증 true = 리더 , false = 리더가 아님
    private static boolean isLeader(UserClub userClub) {
        return userClub.getClubAccessState().equals(ClubAccessState.LEADER);
    }

    // 신청한 적 있는 유저 인지 검증 true = 신청한 유저 , false = 신청 안한 유저
    private static boolean isAlreadyApplied(User user, Club club) {
        return club.getUserClubs().stream()
                .anyMatch(userClub -> userClub.getUser().getId().equals(user.getId()));
    }

}

