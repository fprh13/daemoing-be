package com.daemoing.daemo.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ClubAccessState {
    LEADER("LEADER", "방장"),
    WAITING("WAITING", "신청 대기 상태"),
    ACCEPTED("ACCEPTED", "신청 수락 상태"),
    REJECTED("REJECTED", "신청 거절 상태");

    private final String key;
    private final String title;


}
