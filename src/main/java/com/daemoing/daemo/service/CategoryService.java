package com.daemoing.daemo.service;

import com.daemoing.daemo.domain.Category;
import com.daemoing.daemo.global.common.exception.CustomException;
import com.daemoing.daemo.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.daemoing.daemo.global.common.ErrorCode.*;
import static com.daemoing.daemo.dto.CategoryDto.*;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;


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
        category.update(updateDto.getChangeParentCategory(),updateDto.getChangeChildCategory());
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
        categoryRepository.deleteById(category.getId());
    }


    /**
     * read category all
     */
    public List<ReadResDto> readAll() {
        return categoryRepository.findAll().stream()
                .map(ReadResDto::new)
                .collect(Collectors.toList());
    }

}
