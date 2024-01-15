package com.daemoing.daemo.service;


import com.daemoing.daemo.domain.*;
import com.daemoing.daemo.domain.type.ClubAccessState;
import com.daemoing.daemo.dto.CategoryDto;
import com.daemoing.daemo.global.common.exception.CustomException;
import com.daemoing.daemo.repository.CategoryRepository;
import com.daemoing.daemo.repository.UserClubRepository;
import com.daemoing.daemo.repository.UserRepository;
import com.daemoing.daemo.repository.ClubRepository;
import com.daemoing.daemo.repository.custom.CategoryRepositoryCustom;
import com.daemoing.daemo.service.validator.ClubValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.daemoing.daemo.global.common.ErrorCode.*;
import static com.daemoing.daemo.dto.ClubDto.*;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClubService {

    private final ClubRepository clubRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final UserClubRepository userClubRepository;
    private final CategoryRepositoryCustom categoryRepositoryCustom;


    /**
     * create
     */
    @Transactional
    public Long save(SaveDto saveDto, UserDetails userDetails) {
        User user = userRepository.findByLoginId(userDetails.getUsername()).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        Category category = categoryRepository.findCategoryByParentCategoryAndChildCategory(saveDto.getParentCategory(), saveDto.getChildCategory())
                .orElseThrow(() -> new CustomException(RESOURCE_NOT_FOUND));
        Club club= new Club(saveDto.getName(),
                saveDto.getDescription(),
                saveDto.getOpenChatAddress(),
                saveDto.getActiveDate(),
                saveDto.isOnline(),
                saveDto.getParticipantMax(),
                saveDto.getUniv());

        club.setCategory(category);
        clubRepository.save(club);

        UserClub userClub = new UserClub(userDetails.getUsername(), ClubAccessState.LEADER);
        userClub.setClub(club);
        userClub.setUser(user);

        return club.getId();
    }

    /**
     * update
     */
    @Transactional
    public Long update(UpdateDto updateDto, Long id, UserDetails userDetails) {
        Club club = clubRepository.findClubAndUserClubByIdAndApplicant(id, userDetails.getUsername())
                .orElseThrow(() -> new CustomException(ACCESS_DENIED));
        ClubValidator.checkLeader(club);
        club.update(
                updateDto.getName(),
                updateDto.getDescription(),
                updateDto.getOpenChatAddress(),
                updateDto.isOnline(),
                updateDto.getActiveDate(),
                updateDto.getParticipantMax(),
                updateDto.getUniv());
        return club.getId();
        }

    /**
     * delete
     */
    @Transactional
    public void delete(Long id, UserDetails userDetails) {
        UserClub userClub = userClubRepository.findUserClubLeaderByApplicantAndClubId(userDetails.getUsername(),id)
                .orElseThrow(() -> new CustomException(ACCESS_DENIED));
        ClubValidator.checkLeader(userClub);
        clubRepository.deleteById(id);
    }

    /**
     * read one
     */
    @Transactional
    public DetailDto detail(Long id) {
        Club club = clubRepository.findById(id).orElseThrow(() -> new CustomException(RESOURCE_NOT_FOUND));
        club.increaseView(club.getViewCount());
        return new DetailDto(
                club.getName(),
                club.getDescription(),
                club.getOpenChatAddress(),
                club.getActiveDate(),
                club.isOnline(),
                club.getParticipantMax(),
                club.getUniv()
        );
    }

    /**
     * read order page list
     */
    public Page<CategoryDto.ClubListDto> readClubListWithCategory(CategoryDto.ReadReqDto readReqDto) {
        return categoryRepositoryCustom.searchPage(readReqDto,
                PageRequest.of(readReqDto.getPage() != null ? readReqDto.getPage() : 0, 10));
    }

    /**
     * 그룹 신청
     */
    @Transactional
    public Long apply(Long id, UserDetails userDetails) {
        User user = userRepository.findByLoginId(userDetails.getUsername()).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        Club club = clubRepository.findById(id).orElseThrow(() -> new CustomException(RESOURCE_NOT_FOUND));
        ClubValidator.checkDuplicateApplicant(user,club);
        UserClub userClub = new UserClub(userDetails.getUsername(),ClubAccessState.WAITING);
        userClub.setUser(user);
        club.increaseApplicant(club.getApplicantCount());
        userClub.setClub(club);
        return club.getId();
    }

    /**
     * 그룹 신청 상태
     */
    @Transactional
    public Long manage(Long id, String loginId, UserDetails userDetails, String management) throws CustomException {
        UserClub userClub = userClubRepository.findUserClubLeaderByApplicantAndClubId(userDetails.getUsername(), id)
                .orElseThrow(() -> new CustomException(ACCESS_DENIED));
        ClubValidator.checkLeader(userClub);
        UserClub applicantUserClub = userClubRepository.findUserClubByApplicantAndClubId(loginId, id)
                .orElseThrow(() -> new CustomException(RESOURCE_NOT_FOUND));
        if (management.equals("approval")) {
            applicantUserClub.update(ClubAccessState.ACCEPTED);
            userClub.getClub().overValidation(userClub.getClub().getParticipantCount());
            return userClub.getClub().getId();
        }
        if (management.equals("rejection")) {
            applicantUserClub.update(ClubAccessState.REJECTED);
            return userClub.getClub().getId();
        }
        throw new CustomException(INVALID_REQUEST);
    }

    /**
     * 그룹 신청 자 목록
     */
    public Page<ApplicantListDto> applicantList(int page, Long id, UserDetails userDetails) {
        UserClub userClub = userClubRepository.findUserClubLeaderByApplicantAndClubId(userDetails.getUsername(),id)
                .orElseThrow(() -> new CustomException(ACCESS_DENIED));
        ClubValidator.checkLeader(userClub);
        Page<UserClub> applicantListPage = userClubRepository.findApplicantPageByClubId(
                id, PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "createDate")));
        return applicantListPage.map(ApplicantListDto::new);
    }
}
