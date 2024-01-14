package com.daemoing.daemo.service;

import com.daemoing.daemo.domain.Category;
import com.daemoing.daemo.global.common.exception.CustomException;
import com.daemoing.daemo.repository.CategoryRepository;
import com.daemoing.daemo.repository.ClubRepository;
import com.daemoing.daemo.repository.custom.CategoryRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.daemoing.daemo.global.common.ErrorCode.*;
import static com.daemoing.daemo.dto.CategoryDto.*;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryRepositoryCustom categoryRepositoryCustom;
    /**
     * create
     */
    @Transactional
    public Long save(SaveDto saveDto) {
        return categoryRepository.save(new Category(
                saveDto.getParentCategory(),
                saveDto.getChildCategory())).getId();
    }

    /**
     * update
     */
    @Transactional
    public Long update(UpdateDto updateDto) {
        Category category = categoryRepository.findCategoryByParentCategoryAndChildCategory(
                updateDto.getParentCategory(),updateDto.getChildCategory())
                .orElseThrow(() -> new CustomException(RESOURCE_NOT_FOUND));
        category.update(updateDto.getParentCategory(), updateDto.getChildCategory());
        return category.getId();
    }

    /**
     * delete
     */
    @Transactional
    public void delete(DeleteDto deleteDto) {
        Category category = categoryRepository.findCategoryByParentCategoryAndChildCategory(
                deleteDto.getParentCategory(), deleteDto.getChildCategory())
                .orElseThrow(() -> new CustomException(RESOURCE_NOT_FOUND));
        category.update(deleteDto.getParentCategory(), deleteDto.getChildCategory());
    }

    /**
     * read 특정 카테고리 모임들 학교 정보도 ㄱㄱ
     */
    public Page<ClubListDto> readClubListWithCategory(ReadReqDto readReqDto) {
         return categoryRepositoryCustom.searchPage(readReqDto,
                 PageRequest.of(readReqDto.getPage() != null ? readReqDto.getPage() : 0, 10));
    }

    /**
     * read 카테고리 목록 페이지로 넘겨 받자
     */

    /**
     * 검색 카테고리의 모임 목록 검색어 넣자
     */

    /**
     * 그냥 모든 목록 출력 ?
     */
}
