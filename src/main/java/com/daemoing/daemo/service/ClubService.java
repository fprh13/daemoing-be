package com.daemoing.daemo.service;


import com.daemoing.daemo.service.dto.ClubDto;
import com.daemoing.daemo.domain.Club;
import com.daemoing.daemo.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClubService {

    private final ClubRepository clubRepository;

    /**
     * create
     */
    // TODO: 연관관계에 따른 연관관계 메서드 필요
    @Transactional
    public Long save(ClubDto.SaveDto saveDto) {
        return clubRepository.save(Club.builder()
                .name(saveDto.getName())
                .description(saveDto.getDescription())
                .isOnline(saveDto.isOnline())
                .openChatAddress(saveDto.getOpenChatAddress())
                .participantMax(saveDto.getParticipantMax())
                .activeDate(saveDto.getActiveDate())
                .univ(saveDto.getUniv())
                .build()).getId();
    }

    /**
     * update
     */

    /**
     * delete
     */

    /**
     * read one
     */

    /**
     * read page
     */


}
